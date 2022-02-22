package fr.lirmm.fairness.assessment.models.requestparams.params;

import fr.lirmm.fairness.assessment.models.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParamValidator;

import javax.servlet.http.HttpServletRequest;

public class SyncParam  extends RequestParam {

    public SyncParam(HttpServletRequest request) {
        super("sync" ,request);
    }

    @Override
    protected boolean validate() {
        try {
            this.value = RequestParamValidator.getParam(request,getKey() ,  new ParamTest[]{});
            return true;
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return  false;
        }
    }
}
