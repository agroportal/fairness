package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.utils.CombinedResult;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
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
			object.add("score" , gson.toJsonTree(Math.round(result.getScore()*100.0)/100.0));
			if(result instanceof QuestionResult){
				if(!((QuestionResult)result).getExplication().trim().isBlank()){
					object.add("explication" , gson.toJsonTree(((QuestionResult)result).getExplication()));
				}
			}
			if(question != null) {
				object.add("maxCredits" , gson.toJsonTree(question.getMaxPoint()));
				jsonObject.add(question.getLabel() , object);
			}

			if(result instanceof CombinedResult){
				JsonObject state = new JsonObject();
				state.add("success" , gson.toJsonTree(((CombinedResult)result).getSuccessCount()));
				state.add("average" , gson.toJsonTree(((CombinedResult)result).getAverageCount()));
				state.add("fail" , gson.toJsonTree(((CombinedResult)result).getFailCount()));
				object.add("state",state);
			}

		}

		JsonObject out = new JsonObject();
		out.add("results" , jsonObject);
		return out;
	}
}
