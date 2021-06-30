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


            String force = req.getParameter("force");
            if(force != null && !force.trim().isEmpty()){
                System.out.println("flush data");;
                resultCache.flush(pPortalInstanceName);
            }

            List<String> allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();
            List<String> ontologyAcronymsToEvaluate = new ArrayList<String>();
            JsonObject ontologies = null;
            JsonObject allResponse = resultCache.read(pPortalInstanceName);
            Gson gson = new GsonBuilder().create();

            if (pOntologies != null && pOntologies.equals("all")) {
                if (portalInstance.isEnableAllOntologies()) {
                    ontologyAcronymsToEvaluate.addAll(allOntologyAcronyms);
                    ontologies = allResponse.get("ontologies").getAsJsonObject();
                } else {
                    this.respond(
                            ErrorJsonConverter.toStringJson(String.format("Processing all ontologies is not allowed for instance '%s'", pPortalInstanceName)), resp);
                }
            } else if (pOntologies != null) {
                Collection<String> ontologies_acronyms = Arrays.asList(pOntologies.split(","));
                if (allOntologyAcronyms.containsAll(ontologies_acronyms)) {
                    ontologyAcronymsToEvaluate.addAll(ontologies_acronyms);
                } else {
                    this.respond(
                            ErrorJsonConverter.toStringJson(String.format("Ontologies '%s' not found", pOntologies)), resp);
                }


                if (ontologyAcronymsToEvaluate.size() > 0) {
                    Iterator<String> it = ontologyAcronymsToEvaluate.iterator();
                    ontologies = new JsonObject();
                    while (it.hasNext()) {
                        String acronym = it.next();
                        ontologies.add(acronym , allResponse.get("ontologies").getAsJsonObject().get(acronym));
                    }
                }

            }
            JsonObject response = new JsonObject();
            if (ontologies != null) {
                response.add("ontologies" , ontologies);
                if (computeCombinedScore) {
                    CombinedFair combinedFair = null;

                    combinedFair = new CombinedFair(ontologies.size());
                    for (String s : ontologies.keySet()) {
                        combinedFair.addFairToCombine(ontologies.get(s).getAsJsonObject());
                    }
                    response.add("combinedScores", gson.toJsonTree((new CombinedFairJsonConverter(combinedFair)).toJson()));
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
}
