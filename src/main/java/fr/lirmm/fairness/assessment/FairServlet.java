package fr.lirmm.fairness.assessment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;
import org.json.JSONObject;

public class FairServlet extends HttpServlet {

	private static final long serialVersionUID = -2749023988723161904L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String pPortalInstanceName = req.getParameter("portal");
			PortalInstance portalInstance = PortalInstance.getInstanceByName(pPortalInstanceName);
			String pOntologies = req.getParameter("ontologies");
			boolean computeCombinedScore = req.getParameter("combined")!=null && req.getParameter("combined").trim().equals("true");

			if (portalInstance == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("Portal instance '%s' unknown", pPortalInstanceName));
			} else {
				List<String> allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();
				List<String> ontologyAcronymsToEvaluate = new ArrayList<String>();
				if (pOntologies != null && pOntologies.equals("all")) {
					if(portalInstance.isEnableAllOntologies()) {
						ontologyAcronymsToEvaluate.addAll(allOntologyAcronyms);
					}
					else {
						resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, String.format("Processing all ontologies is not allowed for instance '%s'", pPortalInstanceName));
					}
				} else if(pOntologies != null) {
					Collection<String> ontologies = Arrays.asList(pOntologies.split(","));
					if(!allOntologyAcronyms.containsAll(ontologies)) {
						resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("Ontology '%s' not found", pOntologies));
					}
					else {
						ontologyAcronymsToEvaluate.addAll(ontologies);
					}
				}

				Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
				Gson gson = new GsonBuilder().create();
				JsonObject output = new JsonObject();
				List<JsonObject> jsonObjects = new ArrayList<JsonObject>(ontologyAcronymsToEvaluate.size());
				Double combinedScore = 0.0;
				while(it.hasNext()) {
					Fair.getInstance().evaluate(new Ontology(it.next(), portalInstance));
					jsonObjects.add(new FairJsonConverter(Fair.getInstance()).toJson());
					combinedScore += Fair.getInstance().getTotalScore();
				}

				output.add("ontologies" , gson.toJsonTree(jsonObjects));
				if(computeCombinedScore){
					output.add("score" , gson.toJsonTree(combinedScore/ontologyAcronymsToEvaluate.size()));
				}
				output.add("request" , gson.toJsonTree(req.getRequestURL().append('?').append(req.getQueryString())));
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				out.print(output);
				out.flush();
			}
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
