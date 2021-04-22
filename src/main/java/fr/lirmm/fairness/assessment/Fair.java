package fr.lirmm.fairness.assessment;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Iterator;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

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
import fr.lirmm.fairness.assessment.principles.ResultSet;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.Accessible;
import fr.lirmm.fairness.assessment.principles.impl.Findable;
import fr.lirmm.fairness.assessment.principles.impl.Interoperable;
import fr.lirmm.fairness.assessment.principles.impl.Reusable;

public class Fair {
	
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
		for(AbstractPrinciple principle : this.principles) {
			principle.evaluate(ontology);
		}
	}

	public AbstractPrinciple[] getPrinciples() {
		return principles;
	}
	
	public Ontology getOntology() {
		return ontology;
	}

	
	public static void writeExcelResults() {
		
	
 }

	
	public static void main(String[] args) {
		
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
					  
					  // Writing in Excel file
					  if (ontologyAcronyms != null) {
						  System.out.println(ontologyAcronyms);
							Fair.getInstance().evaluate(portalInstance.getAllOntologies(ontologyAcronyms));	
							FileInputStream file = new FileInputStream("Results/APFAIRnessResults.xlsx");
							XSSFWorkbook workbook = new XSSFWorkbook(file);
							XSSFSheet sheet = workbook.getSheetAt(0);
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
								cell.setCellValue(ab.getNormalizedToTPrincipleScore()); 
								colNum++;
								for (AbstractPrincipleCriterion c : ab.getPrincipleCriteria())
								{   
									cell = row.createCell(colNum);
									cell.setCellValue(c.getNormalizedTotalScore());
																	
									colNum++;
								}
								fairnessScore+=ab.getNormalizedToTPrincipleScore();
						}
							cell=row.createCell(colNum);
							cell.setCellValue(fairnessScore/4);
							rowNum++;
					   }
						FileOutputStream fileOut = new FileOutputStream("Results/APFAIRnessResults.xlsx");
						workbook.write(fileOut);  
				        fileOut.close();
				        workbook.close();
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
		}}
	

