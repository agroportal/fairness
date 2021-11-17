package fr.lirmm.fairness.assessment.utils.requestparams.tests;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;


public class Present implements ParamTest {

    @Override
    public boolean isValid(String paramValue) {
        return paramValue!=null && !paramValue.trim().isEmpty();
    }

    @Override
    public String getErrorMessage(String paramKey) {
        return paramKey + " parameter is required";
    }
}
