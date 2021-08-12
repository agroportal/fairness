package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.GsonBuilder;
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


				if(!((QuestionResult)result).getExplanation().trim().isBlank())
					object.add("explanation" , gson.toJsonTree(((QuestionResult)result).getExplanation()));

				if(((QuestionResult)result).getValues() != null && !((QuestionResult)result).getValues().isEmpty())
					object.add("properties" , gson.toJsonTree(((QuestionResult)result).getValues()));
				else if(question!=null && question.getProperties() != null){
					object.add("properties" , gson.toJsonTree(question.getProperties()));
				}
			}

			if(question != null) {
				object.add("maxCredits" , gson.toJsonTree(question.getMaxPoint().getScore()));

				object.add("points" , new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(question.getPoints()));



				jsonObject.add(question.getLabel() , object);
			}


			if(result instanceof CombinedResult){
				JsonObject state = new JsonObject();

				if(question!=null && question.getProperties() != null){
					object.add("properties" , gson.toJsonTree(question.getProperties()));
				}
				
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
