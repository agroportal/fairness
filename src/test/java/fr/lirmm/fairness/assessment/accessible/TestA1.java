package fr.lirmm.fairness.assessment.accessible;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.accessible.A1;

public class TestA1 extends AbstractTestPrincipleCriterion<A1> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 2, 2, 8, 0 };
		this.expectedScore = 12;
		this.expectedExplanations = new String[] {
			"Resolvable ontlogy URI and GUID",
			"",
			"Ontology and ontology metadata are accessible in xml format",
			"Ontology is not accessible through another standard protocol (SPARQL endpoint",
			"Ontology and ontology metadata are accessible in xml format",
			"Ontology and ontology metadata are accessible in json format",
			"Ontology and ontology metadata are accessible in xml format"
		};
	}
}
