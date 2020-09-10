package com.ms.back.commons.model;

import javax.json.JsonObject;

public abstract class ObjectModel {

	protected String emptyIsNull(String value) {

		return (value == null || value.trim().length() == 0) ? null : value.trim();

	}
	
	public JsonObject toJson() {
		return null;
	}

	// =============================================================

//	protected void set(JsonObjectBuilder b, String key, List<String> values) {
//
//		if (values != null) {
//
//			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//
//			for (String o : values) {
//				if (o != null) {
//					arrayBuilder.add(o);
//				}
//
//			}
//
//			b.add(key, arrayBuilder.build());
//
//		} else {
//			b.addNull(key);
//		}
//	}

	// =============================================================

//	protected String getString(JsonObject json, String key) {
//		if (json.containsKey(key) == false || json.isNull(key)) {
//			return null;
//		}
//
//		return json.getString(key);
//
//	}

//	protected Boolean getBoolean(JsonObject json, String key) {
//		if (json.containsKey(key) == false || json.isNull(key)) {
//			return null;
//		}
//
//		if (json.get(key).toString().equalsIgnoreCase("true")) {
//			return true;
//		}
//
//		return false;
//
//	}

//	protected Long getLong(JsonObject json, String key) {
//		if (json.containsKey(key) == false || json.isNull(key)) {
//			return null;
//		}
//
//		return json.getJsonNumber(key).longValue();
//	}

//	protected Double getDouble(JsonObject json, String key) {
//		if (json.containsKey(key) == false || json.isNull(key)) {
//			return null;
//		}
//
//		return json.getJsonNumber(key).doubleValue();
//	}

//	protected JsonObject getObj(JsonObject json, String key) {
//
//		if (json.containsKey(key) == false || json.isNull(key)) {
//			return null;
//		}
//
//		return json.getJsonObject(key);
//	}

//	protected JsonArray getArray(JsonObject json, String key) {
//
//		if (json.containsKey(key) == false || json.isNull(key)) {
//			return null;
//		}
//
//		return json.getJsonArray(key);
//	}

	// =============================================================

}
