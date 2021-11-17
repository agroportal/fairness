package fr.lirmm.fairness.assessment.utils.requestparams.params;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.ContainedIn;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.Required;

import javax.servlet.http.HttpServletRequest;

public class PortalParam extends RequestParam {

    private final String[] AVAILABLE_PORTAL_INSTANCES = {"stageportal" ,"agroportal" , "bioportal"};

    public PortalParam() {
        super("portal");
    }


    @Override
    public boolean validate(HttpServletRequest request) {
        try {
            this.value = RequestParamValidator.getParam(request,getKey() ,  new ParamTest[]{new Required() , new ContainedIn(AVAILABLE_PORTAL_INSTANCES)});
            return true;
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return  false;
        }
    }


}
