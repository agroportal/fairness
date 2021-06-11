package fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
//import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class I2 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -8239004504267037012L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
		
		List<String> useImports = ontology.getUseImports();
		List<String> ontologyRelatedTo = ontology.getOntologyRelatedTo();
		List<String> isAlignedTo = ontology.getIsAlignedTo();
		List<String> similarTo = ontology.getSimilarTo();
		List<String> metadataVoc = ontology.getMetadataVoc();
		String explanationEvolution= ontology.getExplanationEvolution();
		String translationOfWork= ontology.getTranslationOfWork();

		try {
			// Q1: Does an ontology import other vocabularies?
			if (!useImports.isEmpty()) {
				this.addResult(0, this.questionsPoints.get(0), "Ontology imports other vocabularies");
			} else {
				this.addResult(0, 0.0, "Ontology does not import other vocabularies");
			}

			// Q2: Does an ontology reuse other vocabularies URIs?
			if (!ontologyRelatedTo.isEmpty()) {
				this.addResult(1, this.questionsPoints.get(1), "Ontology reuses other vocabularies URIS");
			} else {
				this.addResult(1, 0.0, "Ontology does not reuse other vocabularies URIS");
			}

			// Q3: If yes, does it include the minimum information on that term (cf.
			// MIREOT)? Note: Default value 0, this indicator can not be evaluated in
			// %s.
			this.addResult(2, 0.0, String.format("Default value 0, this indicator can not be evaluated in %s",
					ontology.getPortalInstance().getName()));

			// Q4: Is an ontology aligned to other vocabularies?
			if (!isAlignedTo.isEmpty()) {
				this.addResult(3, this.questionsPoints.get(3), "Ontology is aligned to other vocabularies");
			} else {
				this.addResult(3, 0.0, "Ontology is not aligned to other vocabularies");
			}

			// Q5: If yes, are those alignements well represented and to unambigous
			// entities?
			this.addResult(4, 0.0, String.format("Default value 0, this indicator can not be evaluated in %s",
					ontology.getPortalInstance().getName()));

			// Q6: If yes, are those alignements curated?
			this.addResult(5, 0.0, String.format("Default value 0, this indicator can not be evaluated in %s",
					ontology.getPortalInstance().getName()));

			// Q7: Does an ontology provide information about influencal other vocabularies?
			int nbRelations = 0;
			if (!similarTo.isEmpty()) {
				nbRelations++; 
			} 
		   if (!explanationEvolution.isEmpty())
		   {
			   nbRelations++; 
		   }
		   if (!translationOfWork.isEmpty())
		   {
			   nbRelations++;   
		   }
	        
		   if (nbRelations!=0)
		   {
		    this.addResult(6, this.questionsPoints.get(6) ,
			     String.format("Ontology specifies" +  nbRelations + "influencal relations in %s", ontology.getPortalInstance().getName()));
		   }
		   
		   else 
		   {
			  this.addResult(6, 0.0,
					     String.format("Ontology specifies %d influencal relations in %s", ontology.getPortalInstance().getName()));
		   }

			// Q8: Does an ontology reuse standards metadata vocabularies to describe its
			// metadata?
			if (!metadataVoc.isEmpty()) {
				this.addResult(7, this.questionsPoints.get(7),
						"Ontology reuses standards metadata vocabularies to describe its metadata");
			} else {
				this.addResult(7, this.questionsPoints.get(7),
						"Ontology does not reuse standards metadata vocabularies to describe its metadata");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
