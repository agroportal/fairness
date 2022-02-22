package fr.lirmm.fairness.assessment.models.requestparams;


public interface ParamTest {

    boolean isValid(String paramValue);
    String getErrorMessage(String paramKey);
}
