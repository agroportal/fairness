package fr.lirmm.fairness.assessment.principles.criterion.question.tests;

import org.apache.commons.validator.routines.UrlValidator;

public class URLValidTest implements Test<String> {

    private static URLValidTest instance;

    public URLValidTest() {
    }

    @Override
    public boolean test(String... element) {
        String[] CustomURISchemes = { "http", "https" };
        UrlValidator customURIValidator = new UrlValidator(CustomURISchemes);

        return customURIValidator.isValid(element[0]);
    }


    public static URLValidTest getInstance() {
        if(instance == null){
            instance = new  URLValidTest();
        }
        return instance;
    }

    public static boolean isValid(String... element){
        return getInstance().test(element);
    }

}
