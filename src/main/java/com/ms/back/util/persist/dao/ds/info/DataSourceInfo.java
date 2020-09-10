package com.ms.back.util.persist.dao.ds.info;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.back.util.persist.JsonUtil;

public class DataSourceInfo extends JsonUtil implements Cloneable {

	// ---------------------------------------------------------------------------------------------------------------------------

	private String dataBaseKey;
	private DataSourceMetaData dataSourceMetaData;
	private DataSourceProperties dataSourceProperties;

	// ---------------------------------------------------------------------------------------------------------------------------

	public DataSourceInfo(String dataBaseKey, DataSourceMetaData dataSourceMetaData,
			DataSourceProperties dataSourceProperties) {
		super();
		this.dataBaseKey = dataBaseKey;
		this.dataSourceMetaData = dataSourceMetaData;
		this.dataSourceProperties = dataSourceProperties;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public DataSourceMetaData getDataSourceMetaData() {
		return dataSourceMetaData;
	}

	public DataSourceProperties getDataSourceProperties() {
		return dataSourceProperties;
	}

	public void setDataSourceMetaData(DataSourceMetaData dataSourceMetaData) {
		this.dataSourceMetaData = dataSourceMetaData;
	}

	public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

	public String getDataBaseKey() {
		return dataBaseKey;
	}

	public void setDataBaseKey(String dataBaseKey) {
		this.dataBaseKey = dataBaseKey;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "";

		s += "\n" + "Data base key: " + this.getDataBaseKey();

		if (getDataSourceMetaData() != null) {
			s += getDataSourceMetaData().toString();
		}

		if (getDataSourceProperties() != null) {
			s += getDataSourceProperties().toString();
		}

		return s;
	}

	public DataSourceInfo clone() {

		DataSourceMetaData dataSourceMetaData = (this.getDataSourceMetaData() != null)
				? this.getDataSourceMetaData().clone()
				: null;
		DataSourceProperties dataSourceProperties = (this.getDataSourceProperties() != null)
				? this.getDataSourceProperties().clone()
				: null;

		// -------------------------------------------------------------------

		return new DataSourceInfo(getDataBaseKey(), dataSourceMetaData, dataSourceProperties);

		// -------------------------------------------------------------------
	}

	public String toJson() {

		String json = "{";

		json += this.toJsonField("dataBaseKey", this.getDataBaseKey()) + ",";
		json += this.toJsonField("dataSourceMetaData", this.getDataSourceMetaData()) + ",";
		json += this.toJsonField("dataSourceProperties", this.getDataSourceProperties());

		return json + "}";
	}

	public JsonObject toJsonObject() {

		JsonObject dataSourceMetaData = null;
		JsonObject dataSourceProperties = null;

		if (this.getDataSourceMetaData() != null) {
			dataSourceMetaData = this.getDataSourceMetaData().toJsonObject();
		}

		if (this.getDataSourceProperties() != null) {
			dataSourceProperties = this.getDataSourceProperties().toJsonObject();
		}

		JsonObject obj = Json.createObjectBuilder()

				.add("dataBaseKey", this.getDataBaseKey()).add("dataSourceMetaData", dataSourceMetaData)
				.add("dataSourceProperties", dataSourceProperties).build();

		return obj;

	}

} // END CLASS -----------------------------------------------------------------