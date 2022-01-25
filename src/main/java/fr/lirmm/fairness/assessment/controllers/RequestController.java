package fr.lirmm.fairness.assessment.controllers;

import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.models.PortalInstance;
import fr.lirmm.fairness.assessment.utils.requestparams.params.*;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class RequestController {

    private RequestParamController paramController;
    private PortalInstanceController portalInstanceController;
    public RequestController(HttpServletRequest req) {
        this.paramController = new RequestParamController(req);
        this.portalInstanceController = new PortalInstanceController(paramController);
    }


    public PortalInstance getPortalInstance() throws Exception {
        return portalInstanceController.getPortalInstance();
    }


    public PortalInstanceController getPortalInstanceController() {
        return portalInstanceController;
    }

    public RequestParamController getParamController() {
        return paramController;
    }

    public List<String> getOntologies() throws Exception {
        List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
        OntologiesParam ontologiesParam = paramController.getOntologiesParam();
        String pOntologies = ontologiesParam.get();
        List<String> allOntologyAcronyms = null;

        try{
            allOntologyAcronyms = getPortalInstance().getAllOntologiesAcronyms();
        } catch (Exception e){
            throw new Exception("Portal " +getPortalInstance().getUrl() + " is not accessible : " + e.getMessage() +", You must provide a valid API Key");
        }

        if (pOntologies != null && pOntologies.equals("all")) {
            ontologyAcronymsToEvaluate.addAll(allOntologyAcronyms);
        } else if (pOntologies != null) {
            ontologyAcronymsToEvaluate = testAcronyms(allOntologyAcronyms, pOntologies);
        }

        return  ontologyAcronymsToEvaluate;
    }


    private List<String> testAcronyms(List<String> allOntologyAcronyms , String pOntologies) throws Exception {
        Collection<String> ontologies_acronyms = Arrays.asList(pOntologies.split(","));
        if (allOntologyAcronyms.containsAll(ontologies_acronyms)) {
            return new ArrayList<>(ontologies_acronyms);
        } else {
            throw  new Exception(String.format("Ontologies '%s' not found", pOntologies));
        }
    }


    public boolean isCacheDisabled() throws Exception {
        SyncParam force = paramController.getSyncParam();
        boolean cacheIsEnabled = this.getPortalInstanceController().getPortalInstanceValue().isCacheEnabled();
        return !cacheIsEnabled || (force.getValue() != null);
    }


    public String getRequestURI(HttpServletRequest request) {

        return  request.getScheme() + "://" +
                request.getServerName() +
                ":" + request.getServerPort() +
                request.getRequestURI() +
                (request.getQueryString() != null ? "?" +
                        request.getQueryString() : "");
    }

    public boolean isCombinedParamUsed() {
        return paramController.isCombinedParamUsed();
    }







}
