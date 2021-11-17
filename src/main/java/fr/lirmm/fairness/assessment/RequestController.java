package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.utils.requestparams.params.OntologiesParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestController {

    private final HttpServletRequest req;
    private List<String> allOntologyAcronyms = null;

    public RequestController(HttpServletRequest req) {
        this.req = req;
    }


    public PortalInstance getPortalInstance() throws Exception {
        String portalName = null;
        PortalParam portalParam = new PortalParam();
        try {
            portalName = portalParam.get(req);
        } catch (Exception e) {
            String env = System.getenv(PortalInstance.SERVER_DEFAULT_PORTAL);

            if (portalParam.valueRequest(req)==null) {
                portalName = env;
            } else
                throw e;
        }

        try {
            return new PortalInstance(Configuration.getInstance() , portalName);
        } catch (Exception e){
            throw  new Exception("Portal configuration file not found (set a valid portal name parameter)");
        }
    }

    public List<String> getOntologies() throws Exception {
        List<String> ontologyAcronymsToEvaluate = new ArrayList<>();
        OntologiesParam ontologiesParam = new OntologiesParam();
        String pOntologies = ontologiesParam.get(req);


        try{
            allOntologyAcronyms = getPortalInstance().getAllOntologiesAcronyms();
        } catch (Exception e){
            throw new Exception("Portal " + getPortalInstance().getUrl() + " is not accessible");
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
        boolean cacheIsEnabled = getPortalInstance().isCacheEnabled();
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
}
