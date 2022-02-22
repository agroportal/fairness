package fr.lirmm.fairness.assessment.models.requestparams.tests;

import fr.lirmm.fairness.assessment.models.requestparams.ParamTest;


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
