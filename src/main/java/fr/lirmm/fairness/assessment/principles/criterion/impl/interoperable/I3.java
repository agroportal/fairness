package fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class I3 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -70784125403909250L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

			// Q1: Does an ontology provide qualified cross-references to external resources/databases?
			this.setNotResolvable(0);

			// Q2: Does an ontology provide cross-references to external resources?
			this.setNotResolvable(1);

           //Q3: Does the ontology use a valid URIs to encode some metadata values?
			// TODO change implementation to test a list of properties
           this.addResult(2, this.questions.get(2).getMaxPoint().getScore(),
					String.format("ontologies uses URIs to encode metadata values in TODO add list of accepted properties"));//TODO add list of accepted properties

	}
	
}
