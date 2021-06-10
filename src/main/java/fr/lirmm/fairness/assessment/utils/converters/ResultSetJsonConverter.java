package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.ResultSet;

public class ResultSetJsonConverter extends AbstractJsonConverter<ResultSet> {

	public ResultSetJsonConverter(ResultSet rs) {
		super(rs);
	}

	@Override
	public JsonObject toJson() {
		var jsonObject = new JsonObject();

		jsonObject.add("explanations", gson.toJsonTree(this.item.getExplanations()).getAsJsonArray());
		jsonObject.add("scores", gson.toJsonTree(this.item.getScores()).getAsJsonArray());
		return jsonObject;
	}
}
