
package fr.lirmm.fairness.assessment.models.requestparams.params;

import fr.lirmm.fairness.assessment.models.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.models.requestparams.tests.Present;

import javax.servlet.http.HttpServletRequest;

public class ApiParam extends RequestParam {
    public ApiParam(HttpServletRequest request) {
        super("apikey" ,request);
    }

    @Override
    protected boolean validate() {
        try {
            this.value = RequestParamValidator.getParam(request,getKey() ,  new ParamTest[]{new Present()});
            return true;
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return  false;
        }
    }

}
