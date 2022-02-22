package fr.lirmm.fairness.assessment.views;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class AbstractPrincipleCriterionJsonConverter extends AbstractJsonConverter<AbstractPrincipleCriterion> {

	public AbstractPrincipleCriterionJsonConverter(AbstractPrincipleCriterion c) {
		super(c);
	}

	@Override
	public JsonObject toJson() {
		var jsonObject = new JsonObject();

		ResultSetJsonConverter converter = new ResultSetJsonConverter(this.item.getResults());
		jsonObject.add("label", gson.toJsonTree(this.item.getLabel()));
		jsonObject.add("results", converter.toJson().get("results"));
		jsonObject.add("score", gson.toJsonTree(this.item.getTotalScore()));
		jsonObject.add("normalizedScore", gson.toJsonTree(this.item.getNormalizedTotalScore()));
		jsonObject.add("maxCredits", gson.toJsonTree(this.item.getTotalScoreWeight()));
		jsonObject.add("portalMaxCredits", gson.toJsonTree(this.item.getPortalMaxCredits()));
		return jsonObject;
	}
}
