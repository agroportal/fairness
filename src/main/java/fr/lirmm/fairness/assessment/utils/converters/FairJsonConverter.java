package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.Fair;
import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;

public class FairJsonConverter extends AbstractJsonConverter<Fair> {

	public FairJsonConverter() {
		this(Fair.getInstance());
	}

	public FairJsonConverter(Fair fair) {
		super(fair);
	}

	@Override
	public JsonObject toJson() {
		var jsonObject = new JsonObject();
		for (AbstractPrinciple p : Fair.getInstance().getPrinciples()) {
			AbstractPrincipleJsonConverter converter = new AbstractPrincipleJsonConverter(p);
			jsonObject.add(p.toString(), gson.toJsonTree(converter.toJson()));
		}
		var ontologyJsonObject = new JsonObject();
		ontologyJsonObject.add(Fair.getInstance().getOntology().getAcronym(), jsonObject);
		ontologyJsonObject.add("score", gson.toJsonTree(Fair.getInstance().getTotalScore()));
		ontologyJsonObject.add("normalizedScore", gson.toJsonTree(Fair.getInstance().getNormalizedTotalScore()));

		return ontologyJsonObject;
	}
}
