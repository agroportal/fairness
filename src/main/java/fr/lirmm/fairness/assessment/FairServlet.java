package fr.lirmm.fairness.assessment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
import fr.lirmm.fairness.assessment.utils.converters.ErrorJsonConverter;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;
import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.utils.requestparams.params.CombinedParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.OntologiesParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalParam;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.Required;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            if(!validateParams(req ,resp))
                return;


            String force = req.getParameter("sync");
            boolean cacheIsEnabled =  portalInstance.isCacheEnabled();
            List<String> allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();

            if(!cacheIsEnabled && force != null && !force.trim().isEmpty()) {
            }else if(cacheIsEnabled){

            }

            List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
            if (pOntologies != null && pOntologies.equals("all")) {
                ontologyAcronymsToEvaluate.addAll(allOntologyAcronyms);
            }else if (pOntologies !=null){
                ontologyAcronymsToEvaluate = testAcronyms(allOntologyAcronyms);
                if(ontologyAcronymsToEvaluate !=null) {

                }else {
                    this.respond(
                            ErrorJsonConverter.toStringJson(String.format("Ontologies '%s' not found", pOntologies)), resp);
                }
            }

            JsonObject ontologies = null;
            if(this.notUseCache(cacheIsEnabled ,force)){
                if (ontologyAcronymsToEvaluate.size() > 0) {
                    Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
                    ontologies = new JsonObject();
                    while (it.hasNext()) {
                        Fair fair = new Fair();
                        String acronym = it.next();
                        fair.evaluate(new Ontology(acronym , portalInstance));
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


            JsonObject response = new JsonObject();
            Gson gson = new GsonBuilder().create();

            if (ontologies != null) {
                response.add("ontologies" , ontologies);
                if (computeCombinedScore) {
                    response.add("combinedScores", this.getCombinedScore(ontologies));
                }
            }

            response.add("request", gson.toJsonTree(req.getRequestURL().append('?').append(req.getQueryString())));
            this.respond(response.toString(), resp);
        } catch (Exception e) {
            e.printStackTrace();
            this.respond(ErrorJsonConverter.toStringJson(e.getMessage()), resp);
        }


    }

    private void respond(String json, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    private JsonElement getCombinedScore(JsonObject ontologies){
        CombinedFair combinedFair = new CombinedFair(ontologies.size());
        Gson gson = new GsonBuilder().create();
        for (String s : ontologies.keySet()) {
            combinedFair.addFairToCombine(ontologies.get(s).getAsJsonObject());
        }
        return gson.toJsonTree((new CombinedFairJsonConverter(combinedFair)).toJson());
    }
    private boolean validateParams(HttpServletRequest req ,HttpServletResponse resp) throws IOException {

        if (PortalParam.getInstance().validate(req)) {
            pPortalInstanceName = PortalParam.getInstance().getValue();
            portalInstance = PortalInstance.getInstanceByName(pPortalInstanceName);
        } else {
            this.respond(ErrorJsonConverter.toStringJson( PortalParam.getInstance().getErrorMessage()), resp);
            return false;
        }

        if (OntologiesParam.getInstance().validate(req)) {
            pOntologies = OntologiesParam.getInstance().getValue();
        } else {
            this.respond(ErrorJsonConverter.toStringJson(OntologiesParam.getInstance().getErrorMessage()), resp);
            return false;
        }

        if (CombinedParam.getInstance().validate(req)) {
            computeCombinedScore = req.getParameter("combined") != null && req.getParameter("combined").trim().equals("true");
        }
        return true;
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

    private boolean notUseCache(boolean cacheIsEnabled , String forceRefresh){
        return !cacheIsEnabled || (forceRefresh != null);
    }
}
