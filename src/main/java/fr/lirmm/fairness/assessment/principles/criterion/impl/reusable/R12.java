package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;

import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class R12 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = -6070443822483263038L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

		try {
			String[][] allMetadata = allMetadataToEvaluate(ontology);
			for (int i = 0; i < allMetadata.length; i++) {
				if (allMetadata[i].length > 0) {
				int nbinfo = 0;
				for (String s : allMetadata[i]) {
					if (MetaDataExistTest.isValid(s)) {
						nbinfo++;
					}
				}
					QuestionResult result = questions.get(i).getMaxPoint(nbinfo);
				this.addResult(i, result.getScore(), result.getExplanation());
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


				// Q1:Does the ontology provide information about the actors involved in its development?
				//$TODO if ontology.getTranslationOfWork() && ontology.getWorkTranslation() ; test ontology.getTranslator() ; else 0
				new String[]{ ontology.getCreator() , ontology.getCuratedBy() ,
						ontology.getContributor(), ontology.getTranslator()} ,

				// Q2:Does an ontology provide information on its general provenance?
				//TODO  if status existe & retired ; test was invalidated by ; else 0
				new String[] { ontology.getSource(), ontology.getWasGeneratedBy(), ontology.getWasInvalidatedBy() },

				// Q3:Are the accrual methods and policy documented?
				new String[] { ontology.getAccrualMethod(), ontology.getAccrualPeriodicity(),
						ontology.getAccrualPolicy() },

				// Q4:Is the ontology clearly versioned?
				// TODO test if a valid URI to hasPriorVersion
				new String[] { ontology.getVersion(), ontology.getHasPriorVersion() },

				// Q5:Are the ontology latest change documented?
				new String[] {},

				// Q6:Are the methodology and tools used to build the ontology documented?
				new String[] { ontology.getUsedOntologyEngineeringTool(),
						ontology.getUsedOntologyEngineeringMethodology(),
						ontology.getConformsToKnowledgeRepresentationParadigm() },

				// Q7:Is the ontology rationale documented?
				new String[] { ontology.getDesignedForOntologyTask(), ontology.getCompetencyQuestion() },

				// Q8:Does an ontology inform on its funding organization?
				new String[] { ontology.getFundedBy() }, };
	}
	
}
