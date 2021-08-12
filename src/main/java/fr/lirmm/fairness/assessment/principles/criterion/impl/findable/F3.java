package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import fr.lirmm.fairness.assessment.utils.QuestionResult;
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
		// TODO change explanation to precise that agroportal provide external metadata file
		this.setDefaultSucesses(1, "(TODO:change explanation) Ontology metadata are not included in an external file");
		//Q3: Does that external file explicitly link to the ontology and vice-versa?
		// TODO change explanation
		this.addResult(2, this.questions.get(2).getMaxPoint().getScore() / 2 , "(TODO:change explanation)  Agroportal make  explicit link from metadata to the ontology", null);
	}
	
}
