package fr.lirmm.fairness.assessment.utils.requestparams.tests;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;

public class RegexMatch implements ParamTest {


    private String regexMatch;
    private String errorMessage;
    public RegexMatch(String regexMatch , String errorMessage) {
        this.regexMatch = regexMatch;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isValid(String paramValue) {
        return paramValue.matches(regexMatch);
    }

    @Override
    public String getErrorMessage(String paramKey) {
        return paramKey + this.errorMessage;
    }
}
