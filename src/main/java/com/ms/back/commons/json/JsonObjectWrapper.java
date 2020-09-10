package com.ms.back.commons.json;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.ms.back.commons.model.ObjectModel;

public class JsonObjectWrapper {

	private JsonObjectBuilder b = Json.createObjectBuilder();

	public JsonObject build() {
		return b.build();
	}

	public void set(String key, Object[][] table) {
		JsonArrayWrapper ja = new JsonArrayWrapper();
		ja.addTable(table);
		set("thisPageItems", ja);
	}

	public void set(String key, ObjectModel value) {
		if (value != null) {
			b.add(key, value.toJson());
		} else {
			b.addNull(key);
		}
	}		

	public void set(String key, JsonArrayWrapper value) {
		if (value != null) {
			b.add(key, value.build());
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, JsonArray value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, JsonObject value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, String value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, Integer value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, Long value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, Boolean value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

	public void set(String key, Double value) {
		if (value != null) {
			b.add(key, value);
		} else {
			b.addNull(key);
		}
	}

}
