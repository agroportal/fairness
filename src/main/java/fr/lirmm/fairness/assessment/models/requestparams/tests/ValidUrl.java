package fr.lirmm.fairness.assessment.models.requestparams.tests;

import fr.lirmm.fairness.assessment.models.requestparams.ParamTest;
import org.apache.commons.validator.routines.UrlValidator;

public class ValidUrl implements ParamTest {

    @Override
    public boolean isValid(String paramValue) {
        String[] CustomURISchemes = { "http", "https" };
        UrlValidator customURIValidator = new UrlValidator(CustomURISchemes);
        return customURIValidator.isValid(paramValue);
    }

    @Override
    public String getErrorMessage(String paramKey) {
        return paramKey + " parameter is not a valid URL";
    }

}
