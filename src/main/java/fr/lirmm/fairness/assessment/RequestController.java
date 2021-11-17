package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.utils.requestparams.params.ApiParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.OntologiesParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalUrlParam;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestController {

    private final HttpServletRequest req;
    private List<String> allOntologyAcronyms = null;
    private PortalInstance portalInstance;
    public RequestController(HttpServletRequest req) {
        this.req = req;
        this.portalInstance = null;
    }


    public PortalInstance initPortalInstance() throws Exception {

        try {
            return this.getPortalInstanceByUrlApi();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return this.getPortalInstanceByNameParam();
            }catch (Exception e1){
                e1.printStackTrace();
                try{
                    return this.getPortalInstanceByNameEnv();
                }catch (Exception e11){
                    e11.printStackTrace();
                    throw new Exception("Portal configuration file not found (set a valid portal name parameter)");
                }
            }

        }


    }

    public PortalInstance getPortalInstance() throws Exception {

        if (portalInstance==null){
            portalInstance = initPortalInstance();
        }
        return portalInstance;
    }


    public List<String> getOntologies() throws Exception {
        List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
        OntologiesParam ontologiesParam = new OntologiesParam();
        String pOntologies = ontologiesParam.get(req);


        try{
            allOntologyAcronyms = getPortalInstance().getAllOntologiesAcronyms();
        } catch (Exception e){
            throw new Exception("Portal " +getPortalInstance().getUrl() + " is not accessible");
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
        List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
        if (allOntologyAcronyms.containsAll(ontologies_acronyms)) {
            ontologyAcronymsToEvaluate.addAll(ontologies_acronyms);
            return ontologyAcronymsToEvaluate;
        } else {
            throw  new Exception(String.format("Ontologies '%s' not found", pOntologies));
        }
    }


    public boolean isCacheDisabled() throws Exception {
        String force = req.getParameter("sync");
        boolean cacheIsEnabled = this.getPortalInstance().isCacheEnabled();
        return !cacheIsEnabled || (force != null);
    }

    public List<String> getAllOntologyAcronyms() {
        return allOntologyAcronyms;
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
        return req.getParameter("combined") != null;
    }

    private PortalInstance getPortalInstanceByUrlApi() throws Exception {
        PortalUrlParam urlParam = new PortalUrlParam();
       ApiParam apiParam  = new ApiParam();
        if (!urlParam.validate(req)){
             throw  new Exception(urlParam.getErrorMessage());
        }

        if(!apiParam.validate(req)){
            throw  new Exception(apiParam.getErrorMessage());
        }

        return  new PortalInstance(urlParam.getValue(), apiParam.getValue(), false);
    }

    private PortalInstance getPortalInstanceByNameParam() throws Exception {
        PortalParam portalParam = new PortalParam();

        if(!portalParam.validate(req)){
            throw  new Exception(portalParam.getErrorMessage());
        }

        return new PortalInstance(Configuration.getInstance(), portalParam.getValue());
    }

    private PortalInstance getPortalInstanceByNameEnv() throws Exception {
        return new PortalInstance(Configuration.getInstance(), System.getenv(PortalInstance.SERVER_DEFAULT_PORTAL));
    }
}
