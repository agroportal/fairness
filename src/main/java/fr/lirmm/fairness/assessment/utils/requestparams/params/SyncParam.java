package fr.lirmm.fairness.assessment.utils.requestparams.params;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.Present;

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
