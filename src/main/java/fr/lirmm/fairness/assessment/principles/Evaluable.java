package fr.lirmm.fairness.assessment.principles;

import java.io.IOException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.models.Ontology;

public interface Evaluable {
	
	void evaluate(Ontology ontology) throws JSONException, IOException;
}
