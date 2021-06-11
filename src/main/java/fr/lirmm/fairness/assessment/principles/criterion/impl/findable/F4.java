package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F4 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -3047153112624011099L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		try {
			int i; 
			int score = 0, points=0; 
			// Q1: Is the ontology registered in multiple ontology libraries?
			// By default, AgroPortal ontologies are automatically registered in FAIRsharing and Agrisemantics Map of Data Standards.
			score= 2; 
			List<String> includedInDataCatalog = ontology.getIncludedInDataCatalog();		
			if (includedInDataCatalog.isEmpty()) {
				this.addResult(0, score, "Ontology is not registred in any additional ontology library (except FAIRsharing and Agrisemantics)");
			} else {
				points= 1; // Give 1pt per ontology library
				i = 0;
				while ((i <= (includedInDataCatalog.size()- 1)) && (score < this.questionsPoints.get(0)))
				{
					if (includedInDataCatalog.get(i).contains("obofoundry.org")) {
						score+=points;
					} else if (includedInDataCatalog.get(i).contains("ontobee.org")) {
						score+=points;
					} else if (includedInDataCatalog.get(i).contains("aber-owl.net")) {
						score+=points;
					}
					else if (includedInDataCatalog.get(i).contains("vest.agrisemantics.org")) {
						score+=points;
					}
					else if (includedInDataCatalog.get(i).contains("ontohub.org")) {
						score+=points;
					}
					else if (includedInDataCatalog.get(i).contains("fairsharing.org")) {
						score+=points;
					}
					else if (includedInDataCatalog.get(i).contains("biosharing.org")) {
						score+=points;
					}
					i++;
				}
				this.addResult(0, score, String.format("Ontology is registred in %d ontology libraries", i));
			}

			// Q2: Is the ontology registered in multiple open ontology repositories?  Here we
			// consider OBO foundry, NCBO Bioportal, Ontobee, Aber and OLS.
			
			score =1; // Ontology is in AgroPortal repository 
			points= 1; // 1pt per ontology repository 
			
			if (includedInDataCatalog.isEmpty()) {
				this.addResult(1, score, "Ontology is not registred in any additional open ontology repository (except AgroPortal)");
			} else {
				i = 0;
				while ((i < (includedInDataCatalog.size())) && (score < this.questionsPoints.get(1))) {

					if (includedInDataCatalog.get(i).contains("bioportal.bioontology.org")) {
						score += points;
					}
					else if (includedInDataCatalog.get(i).contains("ebi.ac.uk/ols")) {
						score += points;
					}
					else if (includedInDataCatalog.get(i).contains("obofoundry.org")) {
						score += points;
					}					
					else if (includedInDataCatalog.get(i).contains("ontobee.org"))  {
						
						score+=points; 
					}				     
					else if (includedInDataCatalog.get(i).contains("aber-owl.net"))
					{
						score+=points;
					}
					
					i++;
				}
				this.addResult(1, score, String.format("Ontology is registred in %d open ontology libraries", i));
			}

			// Q3: Is an ontology registered in the "de facto" reference libraries or repositories?
			
			score=1; // ontology is registered in AgroPortal ("de facto repository")
			points= this.questionsPoints.get(2)/3;
			
			if (includedInDataCatalog.isEmpty()) {
				this.addResult(2, 0, "Ontology is not registred in any additionnal de-fact ontology repository (except AgroPortal)");
			} else {
				score=0; 
				i = 0;
				while ((i < (includedInDataCatalog.size())) && (score < this.questionsPoints.get(2))) {

					if (includedInDataCatalog.get(i).contains("bioportal.bioontology.org")) {
						score += points; 
					}

					else if (includedInDataCatalog.get(i).contains("www.ebi.ac.uk/ols")) {
						score += points;
					}

					else if (includedInDataCatalog.get(i).contains("www.ontobee.org")) {
						score += points;
					}
					i++;
				}
				this.addResult(2, score, String.format("Ontology is registred in %d de-facto ontology libraries", i));
			}
			
		 // To do -> Q4: Are the ontology libraries or repositories properly indexed by Web search engines?
			
			
			
		}
			catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
