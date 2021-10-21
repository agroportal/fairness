package fr.lirmm.fairness.assessment.principles;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;

public interface Evaluable {
	
	void evaluate(Ontology ontology) throws JSONException, IOException;
}
