package fr.lirmm.fairness.assessment.utils.requestparams;


public interface ParamTest {

    boolean isValid(String paramValue);
    String getErrorMessage(String paramKey);
}
