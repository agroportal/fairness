package fr.lirmm.fairness.assessment.models.requestparams;

import javax.servlet.http.HttpServletRequest;

public class RequestParamValidator {


    public static String getParam(HttpServletRequest req , String fieldName , ParamTest[] tests) throws Exception {
        String value = req.getParameter(fieldName);
        for (ParamTest test : tests) {
            if(!test.isValid(value))
                throw new Exception(test.getErrorMessage(fieldName));
        }
        return value;
    }
}
