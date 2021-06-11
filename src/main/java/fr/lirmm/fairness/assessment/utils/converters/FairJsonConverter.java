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
		JsonObject ontologyJsonObject = new JsonObject();
		for (AbstractPrinciple p : Fair.getInstance().getPrinciples()) {
			AbstractPrincipleJsonConverter converter = new AbstractPrincipleJsonConverter(p);
			ontologyJsonObject.add(p.toString(), gson.toJsonTree(converter.toJson()));
		}
		ontologyJsonObject.add("score", gson.toJsonTree(Fair.getInstance().getTotalScore()));
		ontologyJsonObject.add("normalizedScore", gson.toJsonTree(Fair.getInstance().getNormalizedTotalScore()));
		ontologyJsonObject.add("MaxScorePoints", gson.toJsonTree(Fair.getInstance().getTotalScoreWeight()));

		JsonObject out = new JsonObject();
		out.add(Fair.getInstance().getOntology().getAcronym() , ontologyJsonObject);
		return out;
	}
}
