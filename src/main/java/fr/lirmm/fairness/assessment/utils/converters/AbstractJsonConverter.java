package fr.lirmm.fairness.assessment.utils.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public abstract class AbstractJsonConverter<T> {

	protected Gson gson = null;
	protected T item = null;

	protected AbstractJsonConverter(T o) {
		this.gson = new GsonBuilder().create();
		this.item = o;
	}

	protected abstract JsonObject toJson();
}
