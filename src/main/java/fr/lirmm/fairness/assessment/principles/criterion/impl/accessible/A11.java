package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class A11 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -4860728831077448445L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
		
		// Q1: Is an ontology relying on HTTP/URIs for its identification and access mechanisms?
		this.addResult(0, this.questions.get(0).getMaxPoint().getScore(), "The repository supports HTTP for access and URI for identification.");

		// Q2: Are the other protocols if any open, free, and universally implementable?
		this.addResult(1, this.questions.get(1).getMaxPoint().getScore(), "The ontology is accessible under another open, free, and universally implementable protocol (such as sparql endpoint)");
	}
	
}
