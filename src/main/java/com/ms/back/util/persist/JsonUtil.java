package com.ms.back.util.persist;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class JsonUtil {

	protected String toJsonField(String name) {
		return "\"" + name + "\": null";
	}

	@SuppressWarnings("rawtypes")
	protected String toJsonField(String name, List values) {
		if (values == null || values.size() == 0) {
			return "\"" + name + "\": []";
		}

		String json = "\"" + name + "\": [";

		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) instanceof JsonUtil) {
				json += i != 0 ? ", " + ((JsonUtil) values.get(i)).toJson() : ((JsonUtil) values.get(i)).toJson();
			} else {
				json += i != 0 ? ", " + values.get(i) : values.get(i);
			}

		}

		return json + "]";
	}

	protected String toJsonField(String name, Object[] values) {
		if (values == null || values.length == 0) {
			return "\"" + name + "\": []";
		}

		String json = "\"" + name + "\": [";

		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof JsonUtil) {
				json += i != 0 ? ", " + ((JsonUtil) values[i]).toJson() : ((JsonUtil) values[i]).toJson();
			} else {
				json += i != 0 ? ", " + values[i] : values[i];
			}

		}

		return json + "]";
	}

	protected String toJsonField(String name, JsonUtil value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\": " + value.toJson();
	}

	protected String toJsonField(String name, String value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\": \"" + value + "\"";
	}

	protected String toJsonField(String name, Integer value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\":" + value;
	}
	
	protected String toJsonField(String name, Long value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\":" + value;
	}

	protected String toJsonField(String name, ZonedDateTime value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\": \"" + value + "\"";
	}
	
	protected String toJsonField(String name, Duration value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\": \"" + value + "\"";
	}

	protected String toJsonField(String name, Boolean value) {
		if (value == null) {
			return "\"" + name + "\": null";
		}

		return "\"" + name + "\":" + value;
	}

	public String toJson() {
		return null;
	}

}
