package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F3 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -2182415468902087930L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
        // Q1: Are the ontology metadata included and maintained in the ontology file?
		this.setNotResolvable(0);

		// Q2: If not, are the ontology metadata described in an external file?
		this.setDefaultSuccess(1);
		//Q3: Does that external file explicitly link to the ontology and vice-versa?

		this.addResult(Tester.doEvaluation(ontology, questions.get(2), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				this.setScoreLevel(1,question);
			}
		}));
	}
	
}
