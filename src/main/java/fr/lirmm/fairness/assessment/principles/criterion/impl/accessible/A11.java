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
		this.setDefaultSuccess(0);


		// Q2: Are the other protocols if any open, free, and universally implementable?
		this.setDefaultSuccess(1);

	}
	
}
