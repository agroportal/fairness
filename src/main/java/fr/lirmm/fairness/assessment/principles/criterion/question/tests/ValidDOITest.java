package fr.lirmm.fairness.assessment.principles.criterion.question.tests;

import fr.lirmm.fairness.assessment.utils.OntologyRestApi;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ValidDOITest implements Test<String>{


    private static ValidDOITest instance;

    @Override
    public boolean test(String... element) {
        String doiApiUrl = null;
        JSONObject obj;
        try {
            doiApiUrl = OntologyRestApi.get(element[0], element[1], "application/json");
            obj = new JSONObject(doiApiUrl);
            int responseCode = Integer.parseInt(obj.getString("responseCode"));
            return  responseCode == 1;
        } catch (Exception e) {
            return false;
        }


    }



    public static ValidDOITest getInstance() {
        if( instance == null){
            instance = new ValidDOITest();
        }
        return instance ;
    }

    public static boolean isValid(String... element){
        return getInstance().test(element);
    }
}
