package fr.lirmm.fairness.assessment.views;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class AbstractPrincipleJsonConverter extends AbstractJsonConverter<AbstractPrinciple> {

	public AbstractPrincipleJsonConverter(AbstractPrinciple p) {
		super(p);
	}

	@Override
	public JsonObject toJson() {
		var jsonObject = new JsonObject();
		for (AbstractPrincipleCriterion c : this.item.getPrincipleCriteria()) {
			AbstractPrincipleCriterionJsonConverter converter = new AbstractPrincipleCriterionJsonConverter(c);
			jsonObject.add(c.toString(), gson.toJsonTree(converter.toJson()));
		}
		jsonObject.add("score", gson.toJsonTree(this.item.getTotalScore()));
		jsonObject.add("normalizedScore", gson.toJsonTree(this.item.getNormalizedTotalScore()));
		jsonObject.add("maxCredits", gson.toJsonTree(this.item.getTotalScoreWeight()));
		jsonObject.add("portalMaxCredits", gson.toJsonTree(this.item.getTotalPortalMaxCredits()));
		return jsonObject;
	}
}
