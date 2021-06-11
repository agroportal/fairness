package fr.lirmm.fairness.assessment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.lirmm.fairness.assessment.principles.AbstractScoredEntity;
import fr.lirmm.fairness.assessment.principles.criterion.Question.AbstractCriterionQuestion;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.Accessible;
import fr.lirmm.fairness.assessment.principles.impl.Findable;
import fr.lirmm.fairness.assessment.principles.impl.Interoperable;
import fr.lirmm.fairness.assessment.principles.impl.Reusable;

public class Fair extends AbstractScoredEntity {
	
	private static Fair instance = null;
	private AbstractPrinciple[] principles = null;
	private Ontology ontology = null;
	public static Fair getInstance() {
		if(instance == null) {
			instance = new Fair();
		}
		return instance;
	}
	
	private Fair() {
		super();
		this.principles = new AbstractPrinciple[] {
			Findable.getInstance(),
			Accessible.getInstance(),
			Interoperable.getInstance(),
			Reusable.getInstance()
		};
	}
	
	public void evaluate(Ontology[] ontologies) {
		this.evaluate(Arrays.asList(ontologies));
	}
	
	public void evaluate(List<Ontology> ontologies) {
		for(Ontology ontology : ontologies) {
			this.evaluate(ontology);
		}
	}
	
	public void evaluate(Ontology ontology) {
		this.ontology = ontology;
		this.scores = new ArrayList<>(this.principles.length);
		this.weights = new ArrayList<>(this.principles.length);
		for(AbstractPrinciple principle : this.principles) {
			principle.evaluate(ontology);
			this.scores.add(principle.getTotalScore());
			this.weights.add(principle.getTotalScoreWeight());
		}
	}

	public AbstractPrinciple[] getPrinciples() {
		return principles;
	}
	
	public Ontology getOntology() {
		return ontology;
	}


	
	public static void main(String[] args) {
		/*Gson gson = new Gson();

		// create a reader
		Reader reader = null;
		try {
			/reader = Files.newBufferedReader(Paths.get("src/AgroPortalFAIRnessConfig/questions.config.json"));
			Map<?, ?> map = gson.fromJson(reader, Map.class);
			Map<? , ?> criteria = (Map<?, ?>) map.values().stream().filter(principal -> ((Map<?,?>)principal).containsKey("F3"))
					.findFirst().get();
			List<Map<?,?>> questionList = (List<Map<?,?>>) criteria.get("F3");

			List<AbstractCriterionQuestion> questions = new ArrayList<AbstractCriterionQuestion>();
			for (Map<?,?> q: questionList) {
				questions.add(new AbstractCriterionQuestion(q.get("question").toString() , (int) Double.parseDouble(q.get("points").toString())));
			}
			System.out.println(questions.stream().map(AbstractCriterionQuestion::getPoints).collect(Collectors.toList()));
			//System.out.println(gson.fromJson(map.get("F").toString(), Map.class));
			// close reader
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		// convert JSON file to map
		/*
		final Options options = new Options();
			
		Option portalInstanceNameOpt = new Option("r", "repository-name", true,
				"Name of the ontology repository (agroportal, bioportal, stageportal).");
		portalInstanceNameOpt.setRequired(true);

		Option ontologyAcronymOpt = new Option("o", "ontology-acronyms", true,
				"Acronyms of the onologies to evaluate (comma separated).");
		ontologyAcronymOpt.setRequired(false);

		Option allOntologiesOpt = new Option("a", "all-ontologies", false, "Evaluates all ontologies.");
		allOntologiesOpt.setRequired(false);

		options.addOption(portalInstanceNameOpt);
		options.addOption(ontologyAcronymOpt);
		options.addOption(allOntologiesOpt);

		try {
			CommandLineParser clp = new DefaultParser();
			CommandLine call = clp.parse(options, args);

			String optValue = null;
			String portalInstanceName = null;
			List<String> ontologyAcronyms = null;
			PortalInstance portalInstance = null;
			Integer fairnessScore=0; 
			optValue = call.getOptionValue("r");

			if (optValue != null) {

				portalInstanceName = optValue.replaceAll(" ", "");
				Properties repositoryProperties = Configuration.getInstance().getProperties(portalInstanceName);
				portalInstance = new PortalInstance(repositoryProperties);

				if (call.hasOption("-a")) {
					ontologyAcronyms = portalInstance.getAllOntologiesAcronyms();
				} else {
					optValue = call.getOptionValue("o");
					if (optValue != null) {
						ontologyAcronyms = Arrays.asList(optValue.replaceAll(" ", "").split(","));
					}
				} 		
				  try { 
					  
					  // Writing Normalized results in Excel file
					  if (ontologyAcronyms != null) {
							Fair.getInstance().evaluate(portalInstance.getAllOntologies(ontologyAcronyms));	
							FileInputStream APFile = new FileInputStream("Results/APFAIRnessResults.xlsx");
							XSSFWorkbook APWorkbook = new XSSFWorkbook(APFile);
							XSSFSheet sheet = APWorkbook.getSheetAt(0);
							XSSFCell cell;
							XSSFRow row; 						
							int rowNum=1, colNum;
							
						for (Ontology onto : portalInstance.getAllOntologies(ontologyAcronyms))	
						{  
							colNum=2;
							row = sheet.createRow(rowNum);
							cell = row.createCell(0);
							cell.setCellValue(onto.getAcronym());
							cell = row.createCell(1);
							cell.setCellValue(onto.getUri());
							
							for(AbstractPrinciple ab : Fair.getInstance().getPrinciples()) {							
								cell = row.createCell(colNum);
								cell.setCellValue(ab.getNormalizedTotalScore());
								colNum++;
								for (AbstractPrincipleCriterion c : ab.getPrincipleCriteria())
								{   
									cell = row.createCell(colNum);
									cell.setCellValue(c.getNormalizedTotalScore());
																	
									colNum++;
								}
								fairnessScore+=ab.getNormalizedTotalScore();
						}
							cell=row.createCell(colNum);
							cell.setCellValue(fairnessScore/4);
							rowNum++;
					   }
						FileOutputStream APFileOut = new FileOutputStream("Results/APFAIRnessResults.xlsx");
						APWorkbook.write(APFileOut);  
				        APFileOut.close();
				        APWorkbook.close();
					  } 
					  
					  if (ontologyAcronyms != null)
					  { 
						Fair.getInstance().evaluate(portalInstance.getAllOntologies(ontologyAcronyms));	
						FileInputStream QFile = new FileInputStream("Results/QResults.xlsx");
						XSSFWorkbook QWorkbook = new XSSFWorkbook(QFile);
						XSSFSheet sheet = QWorkbook.getSheetAt(0);
						XSSFCell cell;
						XSSFRow row; 						
						int rowNum=1, colNum;
						for (Ontology onto : portalInstance.getAllOntologies(ontologyAcronyms))	
						{  
							colNum=2;
							row = sheet.createRow(rowNum);
							cell = row.createCell(0);
							cell.setCellValue(onto.getAcronym());
							cell = row.createCell(1);
							cell.setCellValue(onto.getUri());
							
							for(AbstractPrinciple ab : Fair.getInstance().getPrinciples()) {							
                                
								for (AbstractPrincipleCriterion c : ab.getPrincipleCriteria())
								{   
									cell = row.createCell(colNum);
									cell.setCellValue(c.getResultSet().getScores().toString());															
									colNum++;
								}				
						}
							rowNum++;
					   }
						FileOutputStream QFileOut = new FileOutputStream("Results/QResults.xlsx");
						QWorkbook.write(QFileOut);  
				        QFileOut.close();
				        QWorkbook.close();
					  } 					  
					 }
				  catch (FileNotFoundException e) {
					        e.printStackTrace();
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
			}
		}
             catch (Exception ex) {
			ex.printStackTrace();
		     }
		 */
		}


}
	

