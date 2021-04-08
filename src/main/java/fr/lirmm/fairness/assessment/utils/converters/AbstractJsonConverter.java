package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public abstract class AbstractJsonConverter<T> {

	protected Gson gson = null;
	protected T item = null;

	protected AbstractJsonConverter(T o) {
		this.gson = new GsonBuilder().create();
		this.item = o;
	}

	protected abstract JsonObject toJson();
/*
	public JsonObject convertFairToJson() {
		var jsonObject = new JsonObject();
		for (AbstractPrinciple p : Fair.getInstance().getPrinciples()) {
			jsonObject.add(p.toString(), gson.toJsonTree(this.convertToJson(p)));
		}
		return jsonObject;
	}

	private JsonObject convertToJson(AbstractPrinciple p) {
		var jsonObject = new JsonObject();
		for (AbstractPrincipleCriterion c : p.getPrincipleCriteria()) {
			jsonObject.add(c.toString(), gson.toJsonTree(this.convertToJson(c)));
		}
		jsonObject.add("totalScore", gson.toJsonTree(p.getTotalScore()));
		return jsonObject;
	}

	private JsonObject convertToJson(AbstractPrincipleCriterion c) {
		var jsonObject = new JsonObject();
		jsonObject.add("resultSet", gson.toJsonTree(this.convertToJson(c.getResultSet())));
		return jsonObject;
	}

	private JsonObject convertToJson(ResultSet rs) {
		var jsonObject = new JsonObject();
		jsonObject.add("explanations", gson.toJsonTree(rs.getExplanations()).getAsJsonArray());
		jsonObject.add("scores", gson.toJsonTree(rs.getScores()).getAsJsonArray());
		jsonObject.add("totalScore", gson.toJsonTree(rs.getTotalScore()));
		return jsonObject;
	}*/
}
