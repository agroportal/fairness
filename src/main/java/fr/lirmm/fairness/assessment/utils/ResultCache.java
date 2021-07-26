package fr.lirmm.fairness.assessment.utils;

import com.google.gson.*;
import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.Fair;
import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.utils.converters.FairJsonConverter;
import org.json.JSONException;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class ResultCache {

    public   static String FILE_SAVE_NAME = "save.json";

    private void store(String json, String fileName) throws IOException {
        FileWriter file = null;
        try {

            this.createDirIfNotExist(fileName);
            file = new FileWriter(fileName);
            file.write(json);

        } finally {
            try {
                if(file != null){
                    file.flush();
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean createDirIfNotExist(String filePath){
        String dirPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        File file = new File(dirPath);

        return file.mkdir();
    }

    private String get(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return  sb.toString();
        } finally {
            br.close();
        }
    }

    public  void save(String portal){
        try {
            ResultCache resultCache = new ResultCache();
            PortalInstance portalInstance = PortalInstance.getInstanceByName(portal);
            // List<String> allOntologyAcronyms = Arrays.asList(new String[]{"E-PHY"});
            List<String> allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();
            Gson gson = new GsonBuilder().create();
            JsonObject output = new JsonObject();
            JsonObject jsonObjects = new JsonObject();


            Iterator<String> it = allOntologyAcronyms.iterator();

            while (it.hasNext()) {

                Fair fair = new Fair();
                fair.evaluate(new Ontology(it.next(), portalInstance));

                JsonObject tmp = new FairJsonConverter(fair).toJson();
                tmp.entrySet().forEach(x -> jsonObjects.add(x.getKey(), x.getValue()));

            }

            output.add("ontologies", gson.toJsonTree(jsonObjects));

            resultCache.store(output.toString() , FILE_SAVE_NAME);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public JsonObject read(String portal) throws IOException {
        if (!this.isSaved(portal)){
            System.out.println(portal+" save files not exist ");
            this.save(portal);
        }
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(this.get(FILE_SAVE_NAME) , JsonObject.class);

    }

    public boolean isSaved(String portal){
        getFileSaveName(portal);
        File f = new File(FILE_SAVE_NAME);
        return (f.exists() && !f.isDirectory());
    }
    public void flush(String portal) {
        getFileSaveName(portal);
        File f = new File(FILE_SAVE_NAME);
        f.delete();
    }

    private String getFileSaveName(String portal)  {
        try {
            FILE_SAVE_NAME = Configuration.getInstance().getProperties(portal).getProperty("cacheFilePath");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FILE_SAVE_NAME;
    }
}
