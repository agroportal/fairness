package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.utils.requestparams.params.OntologiesParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RequestController {

    private final HttpServletRequest req;
    private List<String> allOntologyAcronyms = null;

    public RequestController(HttpServletRequest req) {
        this.req = req;
    }


    public PortalInstance getPortalInstance() throws Exception {
       return PortalInstance.getInstanceByName(PortalParam.getInstance().get(req));
    }

    public List<String> getOntologies() throws Exception {
        List<String> ontologyAcronymsToEvaluate = new ArrayList<>();

        String pOntologies = OntologiesParam.getInstance().get(req);


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
        try {
            if (getPortalInstance() != null)
                return getPortalInstance().getUrl() + '?' + request.getQueryString();
            else
                return request.getRequestURL().toString() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        } catch (Exception e) {
            e.printStackTrace();
            return  "";
        }
    }

    public boolean isCombinedParamUsed() {
        return req.getParameter("combined") != null;
    }
}
