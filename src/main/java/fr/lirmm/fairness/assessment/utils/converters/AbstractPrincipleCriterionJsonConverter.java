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
		jsonObject.add("resultSet", gson.toJsonTree(converter.toJson()));
		return jsonObject;
	}
}
