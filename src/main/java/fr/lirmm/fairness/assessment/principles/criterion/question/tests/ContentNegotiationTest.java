package fr.lirmm.fairness.assessment.principles.criterion.question.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentNegotiationTest implements Test<String> {

    public final static  String[] FORMATS = {"application/json", "application/xml", "text/html", "text/plain","application/rdf+xml"};
    private static ContentNegotiationTest instance;

    @Override
    public boolean test(String... element) {
        return !acceptedFormats(element[0] , element[1] , FORMATS).isEmpty();
    }

    public static List<String> acceptedFormats(String url, String apikey, String[] formats) {

        List<String> foundFormats = new ArrayList<String>();
        List<String> args = new ArrayList<>();
        for (String format : formats) {
            args.clear();
            args.add(url);
            args.add(format);
            if (apikey != null) {
                args.add(apikey);
            }

            if (ResolvableURLTest.isValid(args.toArray(String[]::new))) {
                foundFormats.add(format);
            }
        }
        return foundFormats;
    }

    public static List<String> acceptedFormats(String url, String apikey) {
       return acceptedFormats(url,apikey , FORMATS);
    }

    public static ContentNegotiationTest getInstance() {
        if(instance == null){
            instance = new ContentNegotiationTest();
        }
        return instance;
    }


    public static boolean isValid(String... element){
        return getInstance().test(element);
    }
}
