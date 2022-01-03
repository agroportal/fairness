package fr.lirmm.fairness.assessment.principles.criterion.question.tests;




import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResolvableURLTest implements Test<String> {

    private static ResolvableURLTest instance;

    @Override
    public boolean test(String... element) {
        URL url ;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(element[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(false);
            urlConnection.setRequestMethod("HEAD");

            if(element.length == 3 && element[2]!= null && !element[2].isEmpty()){

                urlConnection.setRequestProperty("Authorization", "apikey token=" + element[2]);
            }

            if(element.length >= 2){
                urlConnection.setRequestProperty("Accept", element[1]);
            }


            urlConnection.setConnectTimeout(1000); // 1 second
            int urlConnectionResponseCode = urlConnection.getResponseCode();
            boolean goodUrlContentType = true;
            if(element.length >= 2){
                goodUrlContentType = urlConnection.getContentType().split(";")[0].equals(element[1]);
            }
            return ((urlConnectionResponseCode == 200) || (urlConnectionResponseCode == 302)&& goodUrlContentType);
        } catch (IOException e) {
            return  false;
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }

    }


    private static ResolvableURLTest getInstance() {
        if(instance == null){
            instance = new ResolvableURLTest();
        }
        return instance;
    }
    public static boolean isValid(String... element){
        return getInstance().test(element);
    }
}
