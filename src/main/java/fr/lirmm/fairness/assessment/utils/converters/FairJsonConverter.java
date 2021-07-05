package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.Fair;
import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;

public class FairJsonConverter extends AbstractJsonConverter<Fair> {



	public FairJsonConverter(Fair fair) {
		super(fair);
	}

	@Override
	public JsonObject toJson() {
		JsonObject ontologyJsonObject = new JsonObject();
		for (AbstractPrinciple p : this.item.getPrinciples()) {
			AbstractPrincipleJsonConverter converter = new AbstractPrincipleJsonConverter(p);
			ontologyJsonObject.add(p.toString(), gson.toJsonTree(converter.toJson()));
		}
		ontologyJsonObject.add("score", gson.toJsonTree(this.item.getTotalScore()));
		ontologyJsonObject.add("normalizedScore", gson.toJsonTree(this.item.getNormalizedTotalScore()));
		ontologyJsonObject.add("maxCredits", gson.toJsonTree(this.item.getTotalScoreWeight()));
		ontologyJsonObject.add("executionTime", gson.toJsonTree(this.item.getExecutionTime()));

		JsonObject out = new JsonObject();
		out.add(this.item.getOntology().getAcronym() , ontologyJsonObject);
		return out;
	}
}
