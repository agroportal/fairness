package fr.lirmm.fairness.assessment.principles.impl.accessible;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;

public class A11 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -4860728831077448445L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
		
		// Q1: Is an ontology relying on HTTP/URIs for its identification and access mechanisms?
		this.addResult(0, this.questionsPoints.get(0), "Ontology is relying on HTTP for its identification mechanisms");

		// Q2: Are the other protocols if any open, free, and universally implementable?
		this.addResult(1, this.questionsPoints.get(1), "Ontology is relying on other open, free and universal protocol for its identification mechanisms");
	}
	
}
