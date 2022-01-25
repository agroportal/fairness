package fr.lirmm.fairness.assessment.utils.requestparams.params;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.ContainedIn;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.Present;

import javax.servlet.http.HttpServletRequest;

public class PortalParam extends RequestParam {

    private final String[] AVAILABLE_PORTAL_INSTANCES = {"stageportal" ,"agroportal" , "bioportal"};

    public PortalParam(HttpServletRequest request) {
        super("portal" ,request);
    }


    @Override
    protected boolean validate() {
        try {
            this.value = RequestParamValidator.getParam(request,getKey() ,  new ParamTest[]{new Present() , new ContainedIn(AVAILABLE_PORTAL_INSTANCES)});
            return true;
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return  false;
        }
    }


}
