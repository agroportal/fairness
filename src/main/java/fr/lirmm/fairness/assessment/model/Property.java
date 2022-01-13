package fr.lirmm.fairness.assessment.model;

import java.util.List;
import java.util.Map;

public class Property {
    private String portal;
    private String mod;
    private String type;
    private String source;
    private List<String> value;

    public Property(String portal, String mod, String type, String source) {
        this.portal = portal;
        this.mod = mod;
        this.type = type;
        this.source = source;
    }


    public Property(Map<String,String> map) {
        this.portal = map.get("portal");
        this.mod = map.get("mod");
        this.type = map.get("type");
        this.source = map.get("source");
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public List<?> getValue() {
        return value;
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
}
