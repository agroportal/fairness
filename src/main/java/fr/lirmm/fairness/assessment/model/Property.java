package fr.lirmm.fairness.assessment.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Property {
    private String portal;
    private String mod;
    private String type;
    private String key;
    private String source;
    private List<String> value;


    public Property(Map<String,String> map) {
        this.portal = map.get("portal");
        this.mod = map.get("mod");
        this.type = map.get("type");
        this.key  = map.get("key");
        this.source = map.get("source");
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public List<?> getValue() {
        List<String> out = this.value;
        Gson g = new Gson();
        if(this.key != null){
            out = this.value.stream()
                    .map(x -> g.fromJson(x, JsonObject.class).get(this.key).getAsString())
                    .collect(Collectors.toList());
        }
        return out;
    }

    public String getModEquivalent() {
        return mod;
    }

    public String getPortal() {
        return portal;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getLabel(){
        return this.portal.split(":")[1];
    }

    public String getKey() {
        return key;
    }
}
