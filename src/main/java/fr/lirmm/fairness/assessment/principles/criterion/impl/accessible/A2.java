package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class A2 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -4777318956999586500L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {


		// Q1: Is the ontology accessible in a repository that supports versioning?
		this.setDefaultSuccess(0);

		// Q2: Are the metadata of each version available?
		this.setDefaultSuccess(1);

		// Q3: Are ontology metadata accessible even if no more versions of the ontology are available?
		this.setDefaultSuccess(2);



		// Q4: Is the status of the ontology clearly informed?
		this.addResult(Tester.doEvaluation(ontology, questions.get(3), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				String[] props = new String[]{ontology.getStatus(), ontology.getDeprecated()};
				this.setScoreLevel(countExistentMetaData(props ,question.getPoints().size()), question);
			}
		}));

		/* OLD Q1-Q3
		String acronym = ontology.getAcronym();

		try {

			// Q1: Are "most" ontology versions accessible? (ad-hoc solution see Annex6)
			if ((acronym.contains("OBOE"))
					|| (acronym.contains("PR") || (acronym.contains("BCO")) || (acronym.contains("STY")))) {
				this.addResult(0, 0.0, "Most ontology versions are accessible");
			} else {
				this.addResult(0, this.questionsPoints.get(0), "Most ontology versions are accessible");
			}

			// Q2: Are the metadata of each version available?
			this.addResult(1, this.questionsPoints.get(1), String.format("Default value 2, %s makes metadata of ontology version available", ontology.getPortalInstance().getName()));

			// Q3: Are the metadata of an ontology accessible even if no more version of the
			// ontology are available?
			this.addResult(2, this.questionsPoints.get(2), String.format("Default value 2, %s AgroPortal enables access to ontology metadata even if no more version of the ontology are available", ontology.getPortalInstance().getName()));
			
			// Q4: Is the status of the ontology clearly informed?
			if (!status.isEmpty()) {
				this.addResult(3, questionsPoints.get(3), "The status of the ontology is clearly informed");
			}
			else {
				this.addResult(3, 0.0, "The status of the ontology is not clearly informed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
}
