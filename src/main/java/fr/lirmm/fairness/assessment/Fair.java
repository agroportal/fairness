package fr.lirmm.fairness.assessment;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.principles.AbstractScoredEntity;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.Evaluable;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.impl.Accessible;
import fr.lirmm.fairness.assessment.principles.impl.Findable;
import fr.lirmm.fairness.assessment.principles.impl.Interoperable;
import fr.lirmm.fairness.assessment.principles.impl.Reusable;
import fr.lirmm.fairness.assessment.utils.ResultCache;
import fr.lirmm.fairness.assessment.utils.converters.CombinedFairJsonConverter;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;
import org.json.JSONException;

public class Fair extends AbstractScoredEntity implements Evaluable {

	private AbstractPrinciple[] principles = null;
	private Ontology ontology = null;
	private long executionTime = 0L;

	
	public Fair() {
		super();
		this.principles = new AbstractPrinciple[] {
			new Findable(),
			new Accessible(),
			new Interoperable(),
			new Reusable()
		};
	}

	public static void main(String[] args) {

			List<String> out = new ArrayList<>();
			out.add("syphax");
			out.add("bouazzouni");
			searchGoogle(out);
	}

	public static void searchGoogle(List<String> keywords){
		try {
			ObjectMapper mapper = new ObjectMapper();
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			Gson parser = new GsonBuilder().create();
			GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
			url.put("query", "syphaxbouazzouni");
			url.put("limit", "1");
			url.put("indent", "true");
			url.put("key", "AIzaSyAGgXIIa0rcBRLDv2Tsdk8OX0rIHYBscoE");
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			String responseString = httpResponse.parseAsString();

			JsonNode node = mapper.readTree(responseString).get("itemListElement");
			System.out.println(node);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	@Override
	public void evaluate(Ontology ontology) {
		long startTime = System.currentTimeMillis();
		this.ontology = ontology;
		this.scores = new ArrayList<>(this.principles.length);
		this.weights = new ArrayList<>(this.principles.length);
		for(AbstractPrinciple principle : this.principles) {
			principle.evaluate(ontology);
			this.scores.add(principle.getTotalScore());
			this.weights.add(principle.getTotalScoreWeight());
		}
		long endTime = System.currentTimeMillis();
		this.executionTime = endTime - startTime;
	}

	public AbstractPrinciple[] getPrinciples() {
		return principles;
	}
	
	public Ontology getOntology() {
		return ontology;
	}

	public long getExecutionTime() {
		return executionTime;
	}


}
	

