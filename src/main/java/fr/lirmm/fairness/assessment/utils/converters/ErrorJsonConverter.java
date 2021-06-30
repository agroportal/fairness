package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.JsonObject;

public class ErrorJsonConverter extends AbstractJsonConverter<String>{


    public ErrorJsonConverter(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected JsonObject toJson() {
        var jsonObject = new JsonObject();
        jsonObject.add("status", gson.toJsonTree("error"));
        jsonObject.add("errorMessage", gson.toJsonTree(this.item));
        return jsonObject;
    }

    public static String toStringJson(String errorMessage){
        return new ErrorJsonConverter(errorMessage).toJson().toString();
    }
}
