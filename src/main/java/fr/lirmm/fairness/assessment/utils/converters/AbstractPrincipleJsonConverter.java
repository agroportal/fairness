package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;

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
		jsonObject.add("totalScore", gson.toJsonTree(this.item.getTotalScore()));
		return jsonObject;
	}
}
