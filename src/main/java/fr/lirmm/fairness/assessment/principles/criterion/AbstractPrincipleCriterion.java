package fr.lirmm.fairness.assessment.principles.criterion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.Gson;
import fr.lirmm.fairness.assessment.principles.AbstractScoredEntity;
import fr.lirmm.fairness.assessment.principles.criterion.Question.AbstractCriterionQuestion;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.Evaluable;
import fr.lirmm.fairness.assessment.principles.ResultSet;

public abstract class AbstractPrincipleCriterion extends AbstractScoredEntity implements Evaluable, Serializable {
	
	private static final long serialVersionUID = -5519124612489307590L;

	protected List<Integer> questionsPoints = null;
	protected List<AbstractCriterionQuestion> questions = null;


	
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
		try {

			// create Gson instance
			Gson gson = new Gson();

			// create a reader

			String propFileName = "config/common/questions.config.json";
			Reader reader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(propFileName)));
			// convert JSON file to map
			Map<?, ?> map = gson.fromJson(reader, Map.class);
			Map<? , ?> criteria = (Map<?, ?>) map.values().stream().filter(principal -> ((Map<?,?>)principal).containsKey(this.getClass().getSimpleName()))
					.findFirst().get();
			List<Map<?,?>> questionList = (List<Map<?,?>>) criteria.get(this.getClass().getSimpleName());

			this.questions = new ArrayList<AbstractCriterionQuestion>();
			this.questionsPoints = new ArrayList<>();
			for (Map<?,?> q: questionList) {
				this.questions.add(new AbstractCriterionQuestion(q.get("question").toString() , (int) Double.parseDouble(q.get("points").toString())));
				this.questionsPoints.add((int) Double.parseDouble(q.get("points").toString()));
			}

			reader.close();
			this.weights = this.questionsPoints;
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}
	}



	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
