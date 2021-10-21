package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class R12 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = -6070443822483263038L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

		// Q1:Does the ontology provide information about the actors involved in its development?
		this.addResult(Tester.doEvaluation(ontology, questions.get(0), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props = { ontology.getCreator() , ontology.getCuratedBy() ,
						ontology.getContributor(), ontology.getTranslator()};
				//$TODO if ontology.getTranslationOfWork() && ontology.getWorkTranslation() ; test ontology.getTranslator() ; else 0
				this.setScoreLevel(countExistentMetaData(props, question.getPoints().size()), question);
			}
		}));

		// Q2:Does an ontology provide information on its general provenance?
		this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props = { ontology.getSource(), ontology.getWasGeneratedBy(), ontology.getWasInvalidatedBy() };
				//TODO  if status existe & retired ; test was invalidated by ; else 0
				this.setScoreLevel(countExistentMetaData(props , question.getPoints().size()), question);
			}
		}));

		// Q3:Are the accrual methods and policy documented?
		this.addResult(Tester.doEvaluation(ontology, questions.get(2), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props = { ontology.getAccrualMethod(), ontology.getAccrualPeriodicity(),
						ontology.getAccrualPolicy() };

				this.setScoreLevel(countExistentMetaData(props ,  question.getPoints().size()), question);
			}
		}));

		// Q4:Is the ontology clearly versioned?
		this.addResult(Tester.doEvaluation(ontology, questions.get(3), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props = { ontology.getVersion(), ontology.getHasPriorVersion() };
				// TODO test if a valid URI to hasPriorVersion
				this.setScoreLevel(countExistentMetaData(props , question.getPoints().size()), question);
			}
		}));

		// Q5:Are the ontology latest change documented?
		this.setNotResolvable(4);

		// Q6:Are the methodology and tools used to build the ontology documented?
		this.addResult(Tester.doEvaluation(ontology, questions.get(5), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props = { ontology.getUsedOntologyEngineeringTool(),
						ontology.getUsedOntologyEngineeringMethodology(),
						ontology.getConformsToKnowledgeRepresentationParadigm() };

				this.setScoreLevel(countExistentMetaData(props , question.getPoints().size()), question);
			}
		}));

		// Q7:Is the ontology rationale documented?
		this.addResult(Tester.doEvaluation(ontology, questions.get(6), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props ={ ontology.getDesignedForOntologyTask(), ontology.getCompetencyQuestion() };

				this.setScoreLevel(countExistentMetaData(props, question.getPoints().size()), question);
			}
		}));

		// Q8:Does an ontology inform on its funding organization?
		this.addResult(Tester.doEvaluation(ontology, questions.get(7), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props ={ ontology.getFundedBy() };
				this.setScoreLevel(countExistentMetaData(props, question.getPoints().size()), question);
			}
		}));
	}


	
}
