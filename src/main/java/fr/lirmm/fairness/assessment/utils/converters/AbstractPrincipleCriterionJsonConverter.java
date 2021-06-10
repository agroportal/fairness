package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;

public class AbstractPrincipleCriterionJsonConverter extends AbstractJsonConverter<AbstractPrincipleCriterion> {

	public AbstractPrincipleCriterionJsonConverter(AbstractPrincipleCriterion c) {
		super(c);
	}

	@Override
	public JsonObject toJson() {
		var jsonObject = new JsonObject();
		ResultSetJsonConverter converter = new ResultSetJsonConverter(this.item.getResultSet());
		jsonObject.add("results", gson.toJsonTree(converter.toJson()));
		jsonObject.add("score", gson.toJsonTree(this.item.getTotalScore()));
		jsonObject.add("normalizedScore", gson.toJsonTree(this.item.getNormalizedTotalScore()));
		jsonObject.add("MaxScorePoints", gson.toJsonTree(this.item.getTotalScoreWeight()));
		return jsonObject;
	}
}
