package com.ms.back.commons.json;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.ms.back.commons.model.ObjectModel;

public class JsonArrayWrapper {

	private JsonArrayBuilder builder = Json.createArrayBuilder();

	public JsonArray build() {
		return builder.build();
	}

	public void addTable(Object[][] table) {
		if (table != null) {
			for (Object[] row : table) {
				JsonArrayWrapper jaRow = new JsonArrayWrapper();
				jaRow.addArray(row);
				add(jaRow.build());
			}
		}
	}

	public void addArray(Object[] values) {
		for (int i = 0; i < values.length; i++) {
			add(values[i]);
		}
	}

	public void add(Object value) {

		if (value != null) {

			if (value instanceof Boolean) {
				addValue((Boolean) value);
			} else if (value instanceof String) {
				addValue((String) value);
			} else if (value instanceof Byte) {
				addValue((Byte) value);
			} else if (value instanceof Short) {
				addValue((Short) value);
			} else if (value instanceof Integer) {
				addValue((Integer) value);
			} else if (value instanceof Long) {
				addValue((Long) value);
			} else if (value instanceof Float) {
				addValue((Float) value);
			} else if (value instanceof Double) {
				addValue((Double) value);
			} else if (value instanceof BigDecimal) {
				addValue((BigDecimal) value);
			} else if (value instanceof java.sql.Date) {
				addValue((java.sql.Date) value);
			} else if (value instanceof java.util.Date) {
				addValue((java.util.Date) value);
			} else if (value instanceof LocalDate) {
				addValue((LocalDate) value);
			} else if (value instanceof LocalDateTime) {
				addValue((LocalDateTime) value);
			} else if (value instanceof ZonedDateTime) {
				addValue((ZonedDateTime) value);
			} else if (value instanceof Timestamp) {
				addValue((Timestamp) value);
			} else if (value instanceof Time) {
				addValue((Time) value);
			} else if (value instanceof JsonObject) {
				addValue((JsonObject) value);
			} else if (value instanceof JsonArray) {
				addValue((JsonArray) value);
			} else if (value instanceof ObjectModel) {
				addValue((ObjectModel) value);
			} else {
				throw new IllegalArgumentException("Data type invalid: " + value.getClass());
			}

		} else {
			builder.addNull();
		}
	}

	private void addValue(ObjectModel value) {
		if (value != null) {
			builder.add(value.toJson());
		} else {
			builder.addNull();
		}
	}

	private void addValue(JsonArray value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(JsonObject value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(Boolean value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(String value) {
		addValue(value, true);
	}

	private void addValue(String value, boolean trim) {
		if (value != null) {
			if (trim) {
				builder.add(value.trim());
			} else {
				builder.add(value);
			}
		} else {
			builder.addNull();
		}
	}

	private void addValue(Byte value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(Short value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(Integer value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(Long value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(Float value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(Double value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(BigDecimal value) {
		if (value != null) {
			builder.add(value);
		} else {
			builder.addNull();
		}
	}

	private void addValue(java.sql.Date value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

	private void addValue(java.util.Date value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

	private void addValue(LocalDate value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

	private void addValue(LocalDateTime value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

	private void addValue(Timestamp value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

	private void addValue(ZonedDateTime value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

	private void addValue(Time value) {
		if (value != null) {
			builder.add(value.toString());
		} else {
			builder.addNull();
		}
	}

}
