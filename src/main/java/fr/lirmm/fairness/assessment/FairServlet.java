package fr.lirmm.fairness.assessment;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.models.PortalInstance;
import fr.lirmm.fairness.assessment.utils.ResultCache;
import fr.lirmm.fairness.assessment.utils.converters.CombinedFairJsonConverter;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;
import org.json.JSONException;

/**
 *
 */
public class FairServlet extends HttpServlet {

    private static final long serialVersionUID = -2749023988723161904L;
    private final ResultCache resultCache = new ResultCache();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {


        long startTime = System.currentTimeMillis();

        ResponseController responseController = new ResponseController(resp); ;
        RequestController requestController = new RequestController(req);

        try {
            JsonObject ontologies = null;
            PortalInstance portalInstance = requestController.getPortalInstance();
            Logger.getAnonymousLogger().info("USE THE PORTAL : " + portalInstance.getName() + "; url= " + portalInstance.getUrl() + "; apikey= " + portalInstance.getApikey());
            List<String> ontologyAcronymsToEvaluate = requestController.getOntologies();

            Logger.getAnonymousLogger().info("START EVALUATION OF : " + ontologyAcronymsToEvaluate.size() + " ONTOLOGIES FROM " + portalInstance.getName().toUpperCase(Locale.ROOT));
            if (requestController.isCacheDisabled()) {
                if (ontologyAcronymsToEvaluate.size() > 0) {
                    Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
                    ontologies = new JsonObject();
                    int i = 0;
                    while (it.hasNext()) {
                        String acronym = it.next();
                        JsonElement fair = evaluateOntology(acronym, portalInstance);
                        ontologies.add(acronym, fair);
                        Logger.getAnonymousLogger().info("(" + (i++) + "/" + ontologyAcronymsToEvaluate.size() + ") > Ontology " + acronym + " evaluated in " + fair.getAsJsonObject().get("executionTime").getAsString() + " s ");
                    }
                }
            } else {
                JsonObject allResponses = resultCache.read(portalInstance);
                if (ontologyAcronymsToEvaluate.size() == requestController.getAllOntologyAcronyms().size()) {
                    ontologies = allResponses.getAsJsonObject("ontologies");
                } else if (ontologyAcronymsToEvaluate.size() > 0) {
                    Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
                    ontologies = new JsonObject();
                    while (it.hasNext()) {
                        String acronym = it.next();
                        ontologies.add(acronym, allResponses.getAsJsonObject("ontologies").get(acronym));
                    }
                }
            }
            if (ontologies != null) {
                responseController.getResponse().add("ontologies", ontologies);
                if (requestController.isCombinedParamUsed()) {
                    responseController.getResponse().add("combinedScores", this.getCombinedScore(ontologies));
                }
            }



            responseController.respond(true, requestController.getRequestURI(req) , startTime ,"");
            Logger.getAnonymousLogger().info("EVALUATION  ENDED WITH STATUS : " + responseController.getResponse().get("status"));

        } catch (Exception e) {
            e.printStackTrace();

            responseController.respond(false, requestController.getRequestURI(req) , startTime ,e.getMessage());
        }


    }




    private JsonElement getCombinedScore(JsonObject ontologies) throws JSONException {
        CombinedFair combinedFair = new CombinedFair(ontologies.size());
        Gson gson = new GsonBuilder().create();
        for (String s : ontologies.keySet()) {
            combinedFair.addFairToCombine(ontologies.get(s).getAsJsonObject());
        }
        return gson.toJsonTree((new CombinedFairJsonConverter(combinedFair)).toJson());
    }


    private JsonElement evaluateOntology(String acronym, PortalInstance portalInstance) throws JSONException, IOException {
        Fair fair = new Fair();
        fair.evaluate(new Ontology(acronym, portalInstance));
        return new FairJsonConverter(fair).toJson().get(acronym);
    }



}
