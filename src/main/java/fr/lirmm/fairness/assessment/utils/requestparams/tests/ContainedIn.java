package fr.lirmm.fairness.assessment.utils.requestparams.tests;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;

import java.util.Arrays;

public class ContainedIn implements ParamTest {

    private String[] listToBeIn;
    public ContainedIn(String[] listToBeIn) {
        this.listToBeIn = listToBeIn;
    }

    @Override
    public boolean isValid(String paramValue) {
        return Arrays.asList(listToBeIn).contains(paramValue);
    }

    @Override
    public String getErrorMessage(String paramKey) {
        return paramKey + "most be contained in : " + Arrays.toString(listToBeIn);
    }
}
