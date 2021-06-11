package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.utils.Result;

import java.util.List;

public class ResultSetJsonConverter extends AbstractJsonConverter<List<Result>> {

	public ResultSetJsonConverter(List<Result> rs) {
		super(rs);
	}

	@Override
	public JsonObject toJson() {

		JsonArray jsonObject = new JsonArray();
		for (Result result: this.item) {
			JsonObject object = new JsonObject();
			AbstractCriterionQuestion question = result.getQuestion();
			if(question != null){
				object.add("question" , gson.toJsonTree(result.getQuestion().getQuestion()));
			}
			object.add("score" , gson.toJsonTree(result.getScore()));
			if(!result.getExplication().trim().isBlank()){
				object.add("explication" , gson.toJsonTree(result.getExplication()));
			}
			jsonObject.add(object);
		}

		JsonObject out = new JsonObject();
		out.add("results" , jsonObject);
		return out;
	}
}
