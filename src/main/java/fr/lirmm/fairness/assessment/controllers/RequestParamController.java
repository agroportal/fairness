package fr.lirmm.fairness.assessment.controllers;

import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.utils.requestparams.params.*;

import javax.servlet.http.HttpServletRequest;

public class RequestParamController {


    public final PortalParam portalName;
    public final PortalUrlParam portalUrl;
    public final SyncParam syncParam;
    public final ApiParam apiParam;
    public final OntologiesParam ontologiesParam;
    public final String combinedParam;


    public RequestParamController(HttpServletRequest request) {

        this.portalName = new PortalParam(request);
        this.portalUrl = new PortalUrlParam(request);
        this.syncParam= new SyncParam(request);
        this.apiParam = new ApiParam(request);
        this.ontologiesParam = new OntologiesParam(request);
        this.combinedParam = request.getParameter("combined");

    }

    public ApiParam getApiKeyParam() throws Exception {
        return (ApiParam) getAndValidates(this.apiParam);
    }

    public PortalUrlParam getPortalUrlParam() throws Exception {
        return (PortalUrlParam) getAndValidates(this.portalUrl);
    }
    public PortalParam getPortalNameParam() throws Exception {
        return (PortalParam) getAndValidates( this.portalName);
    }
    public OntologiesParam getOntologiesParam() throws Exception {
        return (OntologiesParam) getAndValidates(this.ontologiesParam);
    }

    public SyncParam getSyncParam() throws Exception {
        return (SyncParam) getAndValidates(this.syncParam);
    }

    private RequestParam getAndValidates(RequestParam requestParam) throws Exception {
        if(!requestParam.isValid()){
            throw  new Exception(requestParam.getErrorMessage());
        }
        return requestParam;
    }

    public boolean isCombinedParamUsed() {
        return  this.combinedParam != null;
    }


}
