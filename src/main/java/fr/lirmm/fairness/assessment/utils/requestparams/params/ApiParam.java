
package fr.lirmm.fairness.assessment.utils.requestparams.params;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.Present;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.ValidUrl;

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
