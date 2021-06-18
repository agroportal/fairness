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

		JsonObject jsonObject = new JsonObject();
		for (Result result: this.item) {
			JsonObject object = new JsonObject();
			AbstractCriterionQuestion question = result.getQuestion();
			if(question != null){
				object.add("question" , gson.toJsonTree(question.getQuestion()));
			}
			object.add("score" , gson.toJsonTree(result.getScore()));
			if(!result.getExplication().trim().isBlank()){
				object.add("explication" , gson.toJsonTree(result.getExplication()));
			}
			if(question != null) {
				object.add("maxCredits" , gson.toJsonTree(question.getPoints()));
				jsonObject.add(question.getLabel() , object);
			}
		}

		JsonObject out = new JsonObject();
		out.add("results" , jsonObject);
		return out;
	}
}
