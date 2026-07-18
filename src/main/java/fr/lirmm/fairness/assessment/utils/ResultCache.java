package fr.lirmm.fairness.assessment.utils;

import com.google.gson.*;
import fr.lirmm.fairness.assessment.Fair;
import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.models.PortalInstance;
import fr.lirmm.fairness.assessment.views.FairJsonConverter;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

public class ResultCache {

    private static final Logger LOGGER = Logger.getLogger(ResultCache.class.getName());

    public static String FILE_SAVE_NAME = "save.json";

    public void save(PortalInstance portalInstance) {
        try {
            ResultCache resultCache = new ResultCache();
            String portal = portalInstance.getName();

            List<String> allOntologyAcronyms = portalInstance.getAllOntologiesAcronyms();
            Gson gson = new GsonBuilder().create();
            JsonObject output = new JsonObject();
            JsonObject jsonObjects = new JsonObject();

            Iterator<String> it = allOntologyAcronyms.iterator();
            int total = allOntologyAcronyms.size();
            int i = 1;
            while (it.hasNext()) {
                String acronym = it.next();
                long start = System.currentTimeMillis();

                Fair fair = new Fair();
                fair.evaluate(new Ontology(acronym, portalInstance));

                JsonObject tmp = new FairJsonConverter(fair).toJson();
                tmp.entrySet().forEach(x -> jsonObjects.add(x.getKey(), x.getValue()));

                LOGGER.info("(" + (i++) + "/" + total + ") > Ontology " + acronym + " evaluated in " + ((System.currentTimeMillis() - start) / 1000.0) + " s");
            }

            output.add("ontologies", gson.toJsonTree(jsonObjects));

            getFileSaveName(portal);
            resultCache.store(output.toString(), Paths.get(FILE_SAVE_NAME), total);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save cache for " + portalInstance.getName(), e);
        }
    }

    public JsonObject read(PortalInstance portalInstance) throws IOException {
        String portal = portalInstance.getName();
        if (!this.isSaved(portal)) {
            throw new IOException("Cache not yet generated for portal '" + portal + "'. Run cache_reset.sh or wait for the next cron run.");
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(this.get(FILE_SAVE_NAME), JsonObject.class);
    }

    public boolean isSaved(String portal) {
        getFileSaveName(portal);
        File f = new File(FILE_SAVE_NAME);
        return (f.exists() && !f.isDirectory());
    }

    public boolean isValid(String portal) {
        try {
            return isValid(Paths.get(getFileSaveName(portal)));
        } catch (RuntimeException e) {
            return false;
        }
    }

    boolean isValid(Path path) {
        return ontologyCount(path) > 0;
    }

    private boolean isValid(Path path, int sourceCount) {
        return sourceCount > 0 && ontologyCount(path) == sourceCount;
    }

    private int ontologyCount(Path path) {
        if (!Files.isRegularFile(path)) {
            return -1;
        }
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            JsonElement root = new JsonParser().parse(reader);
            if (!root.isJsonObject()) {
                return -1;
            }
            JsonElement ontologies = root.getAsJsonObject().get("ontologies");
            return ontologies != null && ontologies.isJsonObject() ? ontologies.getAsJsonObject().size() : -1;
        } catch (IOException | JsonParseException | IllegalStateException e) {
            return -1;
        }
    }

    public void flush(String portal) {
        getFileSaveName(portal);
        File f = new File(FILE_SAVE_NAME);
        f.delete();
    }

    void store(String json, Path destination, int sourceCount) throws IOException {
        Path absoluteDestination = destination.toAbsolutePath();
        Path directory = absoluteDestination.getParent();
        Files.createDirectories(directory);
        Path temp = Files.createTempFile(directory, absoluteDestination.getFileName() + ".", ".tmp");
        try {
            ByteBuffer bytes = StandardCharsets.UTF_8.encode(json);
            try (FileChannel channel = FileChannel.open(temp, WRITE)) {
                while (bytes.hasRemaining()) {
                    channel.write(bytes);
                }
                channel.force(true);
            }
            if (!isValid(temp, sourceCount)) {
                throw new IOException("Refusing to publish an incomplete or invalid cache");
            }
            Files.move(temp, absoluteDestination, ATOMIC_MOVE, REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temp);
        }
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
            return sb.toString();
        } finally {
            br.close();
        }
    }

    private String getFileSaveName(String portal) {
        try {
            FILE_SAVE_NAME = Configuration.getInstance().getPortalProperties(portal.toLowerCase(Locale.ROOT)).getProperty("cacheFilePath");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to read cacheFilePath for portal " + portal, e);
        }
        return FILE_SAVE_NAME;
    }
}
