package fr.lirmm.fairness.assessment.principles.impl;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import fr.lirmm.fairness.assessment.principles.AbstractScoredEntity;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.Evaluable;
import fr.lirmm.fairness.assessment.principles.ResultSet;

public abstract class AbstractPrincipleCriterion extends AbstractScoredEntity implements Evaluable, Serializable {
	
	private static final long serialVersionUID = -5519124612489307590L;

	protected List<Integer> questionsPoints = null;
	protected ResultSet resultSet = null; 


	
	public AbstractPrincipleCriterion() {
		super();
		this.fillQuestionsPoints();
	}

	@Override
	public final void evaluate(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		System.out.println("> Evaluating '" + this.getClass().getSimpleName() + "' of ontology '" + ontology.getAcronym() + "' on repository '" + ontology.getPortalInstance().getName() + "' (" + ontology.getPortalInstance().getUrl() + "?apikey=" + ontology.getPortalInstance().getApikey() + ").");
		this.resultSet = new ResultSet(this);
		this.doEvaluation(ontology);
		this.scores = this.resultSet.getScores();
		this.weights = this.questionsPoints;

	}
	
	protected abstract void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException;
	
	protected void addExplanation(int index, String explanation) {
		this.resultSet.getExplanations().add(index, explanation);
	}



	protected void addScore(int index, Integer score) {
		this.resultSet.getScores().add(index, score);
	}
	
	protected void addResult(int index, Integer score, String explanation) {
		this.addExplanation(index, explanation);
		this.addScore(index, score);
	}
	
	public ResultSet getResultSet() {
		return this.resultSet;
	}



	private void fillQuestionsPoints() {
		this.questionsPoints = new ArrayList<Integer>();
		try {
			Properties properties = Configuration.getInstance().getProperties();
			Set<Object> keys = properties.keySet();
			Map<Object, String> propertiesMap = new TreeMap<Object, String>();
			for(Object key : keys) {
				if(key.toString().startsWith(this.getClass().getSimpleName().concat("Q"))) {
					String propertyValue = properties.getProperty(key.toString());
					propertiesMap.put(key, propertyValue.replace(" ", ""));
				}
			}
			Object[] keysArray = propertiesMap.keySet().toArray();
			Arrays.sort(keysArray);
			for(Object key : keysArray) {
				this.questionsPoints.add(Integer.parseInt(propertiesMap.get(key)));
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
