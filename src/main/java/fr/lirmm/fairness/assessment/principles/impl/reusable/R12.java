package fr.lirmm.fairness.assessment.principles.impl.reusable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class R12 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = -6070443822483263038L;

	private final static String[] metadataExplanations = new String[] {
		"Ontology provides %d information about its provenance",
		"Ontology provides %d accrual information about methods/periodicity/policy",
		"Ontology provides %d information about versioning",
		"Ontology provides %d information about methodology and tools used to build it",
		"Ontology provides %d information about rationale documentation",
		"Ontology provides %d information about its funding organization",
	};

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		try {
			String[][] allMetadata = allMetadataToEvaluate(ontology);
			for (int i = 0; i < allMetadata.length; i++) {
				if (allMetadata[i].length > 0) {
				int nbinfo = 0;
				int points= 0, score=0;
				for (String s : allMetadata[i]) {
					points= this.questionsPoints.get(i)/allMetadata[i].length;
					if (OntologyRestApi.isValidMetadatumValue(s)) {
						nbinfo++;
						score+=points; 
					}
				}
				this.addResult(i, score, String.format(metadataExplanations[i], nbinfo));
			}
				else 
				{
					this.addResult(i, 0, "Ontology does not provide information about all R1.2 required metadata");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String[][] allMetadataToEvaluate(Ontology ontology) {
		return new String[][] {
				// Q1: Does an ontology metadata inform on its general provenance?
				new String[] { ontology.getSource(), ontology.getWasGeneratedBy(), ontology.getWasInvalidatedBy() },

				// Q2: Are the accrual methods and policy documented?
				new String[] { ontology.getAccrualMethod(), ontology.getAccrualPeriodicity(),
						ontology.getAccrualPolicy() },

				// Q3: Is the ontology clearly versionned?
				new String[] { ontology.getVersion(), ontology.getDiffFilePath(), ontology.getSubmissions() },

				// Q4: Are the methodology and tools used to build the ontology documented?
				new String[] { ontology.getUsedOntologyEngineeringTool(),
						ontology.getUsedOntologyEngineeringMethodology(),
						ontology.getConformsToKnowledgeRepresentationParadigm() },

				// Q5: Is the ontology rationale documented?
				new String[] { ontology.getDesignedForOntologyTask(), ontology.getCompetencyQuestion() },

				// Q6: Does an ontology inform on its funding organization?
				new String[] { ontology.getFundedBy() }, };
	}
	
}
