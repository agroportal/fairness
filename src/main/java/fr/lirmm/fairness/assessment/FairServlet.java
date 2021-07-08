package fr.lirmm.fairness.assessment;

import java.io.IOException;
import java.io.PrintWriter;
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
import fr.lirmm.fairness.assessment.utils.ResultCache;
import fr.lirmm.fairness.assessment.utils.converters.CombinedFairJsonConverter;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;
import fr.lirmm.fairness.assessment.utils.requestparams.params.CombinedParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.OntologiesParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalParam;
import org.json.JSONException;

/**
 *
 */
public class FairServlet extends HttpServlet {

    private static final long serialVersionUID = -2749023988723161904L;
    private String pPortalInstanceName = "";
    private PortalInstance portalInstance = null;
    private String pOntologies = "";
    private boolean computeCombinedScore = false;
    private ResultCache resultCache = new ResultCache();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        JsonObject response = new JsonObject();
        List<String> allOntologyAcronyms = null;
        String error = null;
        long startTime = System.currentTimeMillis();
        try {
            error = validateParams(req);
            if(error == null)
                allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();
        } catch (JSONException | IOException e) {
            error = "Portal " + portalInstance.getUrl() + " is not accessible";
        }

        if(error == null){
            try {
                List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
                if (pOntologies != null && pOntologies.equals("all")) {
                    ontologyAcronymsToEvaluate.addAll(allOntologyAcronyms);
                }else if (pOntologies !=null){
                    ontologyAcronymsToEvaluate = testAcronyms(allOntologyAcronyms);
                    if(ontologyAcronymsToEvaluate ==null) {
                        error = String.format("Ontologies '%s' not found", pOntologies);
                    }
                }

                JsonObject ontologies = null;
                if(error == null){
                    Logger.getAnonymousLogger().info("START EVALUATION OF : " + ontologyAcronymsToEvaluate.size() + " ONTOLOGIES FROM " + pPortalInstanceName.toUpperCase(Locale.ROOT));
                    if(this.isCacheDisabled(req)){
                        if (ontologyAcronymsToEvaluate.size() > 0) {
                            Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
                            ontologies = new JsonObject();
                            int i = 0;
                            while (it.hasNext()) {
                                Fair fair = new Fair();
                                String acronym = it.next();
                                fair.evaluate(new Ontology(acronym , portalInstance));
                                Logger.getAnonymousLogger().info("("+(i++)+"/"+ontologyAcronymsToEvaluate.size()+") > Ontology " + acronym + " evaluated in " + fair.getExecutionTime() + " s " );
                                ontologies.add(acronym ,new FairJsonConverter(fair).toJson().get(acronym));
                            }
                        }
                    }else {
                        JsonObject allResponse = resultCache.read(pPortalInstanceName);
                        if(ontologyAcronymsToEvaluate.size() == allOntologyAcronyms.size()){
                            ontologies = allResponse.getAsJsonObject("ontologies");
                        }else if (ontologyAcronymsToEvaluate.size() > 0) {
                            Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
                            ontologies = new JsonObject();
                            while (it.hasNext()) {
                                String acronym = it.next();
                                ontologies.add(acronym , allResponse.getAsJsonObject("ontologies").get(acronym));
                            }
                        }
                    }
                }

                if (ontologies != null) {
                    response.add("ontologies" , ontologies);
                    if (computeCombinedScore) {
                        response.add("combinedScores", this.getCombinedScore(ontologies));
                    }
                }
                response.add("status" , getStatus(error == null , getRequestURI(req) , System.currentTimeMillis() - startTime , error));

            } catch (Exception e) {
                e.printStackTrace();
                response.add("status" , getStatus(false , getRequestURI(req) , System.currentTimeMillis() - startTime , e.getMessage()));
            }
        }
        else {
            response.add("status" , getStatus(false , getRequestURI(req), System.currentTimeMillis() - startTime , error));
        }

        Logger.getAnonymousLogger().info("EVALUATION  ENDED WITH STATUS : " + response.get("status"));
        this.respond(response.toString(), resp);

    }


    private JsonObject getStatus(boolean success , String request , long time , String message){
        JsonObject jsonObject = new JsonObject();
        Gson gson = new GsonBuilder().create();
        jsonObject.add("request" ,gson.toJsonTree(request));
        jsonObject.add("success" ,gson.toJsonTree(success));
        if (message !=null && !message.isEmpty()) {
            jsonObject.add("message" ,gson.toJsonTree(message));
        }
        jsonObject.add("executionTime" ,gson.toJsonTree(time));
        return jsonObject;
    }

    private void respond(String json, HttpServletResponse resp)  {
        PrintWriter out;
        try {
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        } catch (IOException e) {
           Logger.getAnonymousLogger().severe("JSON RESPONSE ERROR : " + e.getMessage());
        }

    }

    private JsonElement getCombinedScore(JsonObject ontologies){
        CombinedFair combinedFair = new CombinedFair(ontologies.size());
        Gson gson = new GsonBuilder().create();
        for (String s : ontologies.keySet()) {
            combinedFair.addFairToCombine(ontologies.get(s).getAsJsonObject());
        }
        return gson.toJsonTree((new CombinedFairJsonConverter(combinedFair)).toJson());
    }
    private String validateParams(HttpServletRequest req) throws IOException {

        if (PortalParam.getInstance().validate(req)) {
            pPortalInstanceName = PortalParam.getInstance().getValue();
            portalInstance = PortalInstance.getInstanceByName(pPortalInstanceName);
        } else {
            return  PortalParam.getInstance().getErrorMessage();
        }

        if (OntologiesParam.getInstance().validate(req)) {
            pOntologies = OntologiesParam.getInstance().getValue();
        } else {
            return OntologiesParam.getInstance().getErrorMessage();
        }

        if (CombinedParam.getInstance().validate(req)) {
            computeCombinedScore = req.getParameter("combined") != null && req.getParameter("combined").trim().equals("true");
        }
        return null;
    }
    private List<String> testAcronyms(List<String> allOntologyAcronyms){
        Collection<String> ontologies_acronyms = Arrays.asList(pOntologies.split(","));
        List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
        if (allOntologyAcronyms.containsAll(ontologies_acronyms)) {
            ontologyAcronymsToEvaluate.addAll(ontologies_acronyms);
            return ontologyAcronymsToEvaluate;
        } else {
           return null;
        }

    }

    private boolean isCacheDisabled(HttpServletRequest req){
        String force = req.getParameter("sync");
        boolean cacheIsEnabled =  portalInstance.isCacheEnabled();
        return !cacheIsEnabled || (force != null);
    }

    private String getRequestURI(HttpServletRequest request){
        return portalInstance.getUrl()+'?'+request.getQueryString();
    }
}
