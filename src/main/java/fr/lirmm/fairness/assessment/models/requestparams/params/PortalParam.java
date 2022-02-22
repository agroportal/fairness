package fr.lirmm.fairness.assessment.models.requestparams.params;

import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.models.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.models.requestparams.tests.ContainedIn;
import fr.lirmm.fairness.assessment.models.requestparams.tests.Present;

import javax.servlet.http.HttpServletRequest;

public class PortalParam extends RequestParam {

    public PortalParam(HttpServletRequest request) {
        super("portal" ,request);
    }


    private String[] getAvailablePortalInstances(){
        return Configuration.getInstance().getConfiguredPortalAvailable();
    }

    @Override
    protected boolean validate() {
        try {
            this.value = RequestParamValidator.getParam(request,getKey() ,  new ParamTest[]{new Present() , new ContainedIn(getAvailablePortalInstances())});
            return true;
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return  false;
        }
    }


}
