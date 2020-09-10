package com.ms.back.util.persist.dao.ds.info;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.back.util.persist.JsonUtil;

public class DataSourceProperties extends JsonUtil implements Cloneable {

	// ---------------------------------------------------------------------------------------------------------------------------

	private String driverClassName = "unknown";
	private int initialSize = -1;
	private int maxActive = -1;
	private int maxIdle = -1;
	private String validationQuery = "unknown";
	private String url = "unknown";
	private String userName = "unknown";
	private String userPassword = "unknown";
	private boolean verbose = false;
	private String schema = "unknown";
	private boolean convertToJavaTimeValues = false;
	private int stackStatementSize = 50;

	private String serverName = "unknown";
	private int portNumber = -1;
	private String databaseName = "unknown";

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public boolean isConvertToJavaTimeValues() {
		return convertToJavaTimeValues;
	}

	public void setConvertToJavaTimeValues(boolean convertToJavaTimeValues) {
		this.convertToJavaTimeValues = convertToJavaTimeValues;
	}

	public int getStackStatementSize() {
		return stackStatementSize;
	}

	public void setStackStatementSize(int stackStatementSize) {
		this.stackStatementSize = stackStatementSize;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "";

		s += "\n\tDriver class name:" + this.getDriverClassName() + "";
		s += "\n\tInitial size:" + this.getInitialSize() + "";
		s += "\n\tMax active:" + this.getMaxActive() + "";
		s += "\n\tMax idle:" + this.getMaxIdle() + "";
		s += "\n\tValidation query:" + this.getValidationQuery() + "";
		s += "\n\tURL:" + this.getUrl() + "";
		s += "\n\tUser name:" + this.getUserName() + "";
		s += "\n\tUser password:" + "SECRET" + "";
		s += "\n\tverbose:" + this.isVerbose() + "";
		s += "\n\tSchema:" + this.getSchema() + "";
		s += "\n\tConvert to Java time values:" + this.isConvertToJavaTimeValues() + "";
		s += "\n\tStack statement size:" + this.getStackStatementSize() + "";

		s += "\n\tServer name:" + this.getServerName();
		s += "\n\tPort number:" + this.getPortNumber() + "";
		s += "\n\tDatabase name:" + this.getDatabaseName();

		s += "\n";

		return s;
	}

	public DataSourceProperties clone() {

		DataSourceProperties other = new DataSourceProperties();

		other.setDriverClassName(this.getDriverClassName());
		other.setInitialSize(this.getInitialSize());
		other.setMaxActive(this.getMaxActive());
		other.setMaxIdle(this.getMaxIdle());
		other.setValidationQuery(this.getValidationQuery());
		other.setUrl(this.getUrl());
		other.setUserName(this.getUserName());
		other.setUserPassword(this.getUserPassword());
		other.setVerbose(this.isVerbose());
		other.setSchema(this.getSchema());
		other.setConvertToJavaTimeValues(this.isConvertToJavaTimeValues());
		other.setStackStatementSize(this.getStackStatementSize());

		other.setServerName(getServerName());
		other.setPortNumber(getPortNumber());
		other.setDatabaseName(getDatabaseName());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

	public String toJson() {

		String json = "{";

		json += this.toJsonField("driverClassName", this.getDriverClassName()) + ",";
		json += this.toJsonField("initialSize", this.getInitialSize()) + ",";
		json += this.toJsonField("maxActive", this.getMaxActive()) + ",";
		json += this.toJsonField("maxIdle", this.getMaxIdle()) + ",";
		json += this.toJsonField("validationQuery", this.getValidationQuery()) + ",";
		json += this.toJsonField("url", this.getUrl()) + ",";
		json += this.toJsonField("userName", this.getUserName()) + ",";
		json += this.toJsonField("userPassword", "SECRET") + ",";
		json += this.toJsonField("verbose", this.isVerbose()) + ",";
		json += this.toJsonField("schema", this.getSchema()) + ",";
		json += this.toJsonField("convertToJavaTimeValues", this.isConvertToJavaTimeValues()) + ",";
		json += this.toJsonField("stackStatementSize", this.getStackStatementSize()) + "";

		json += this.toJsonField("serverName", this.getServerName()) + ",";
		json += this.toJsonField("portNumber", this.getPortNumber()) + ",";
		json += this.toJsonField("databaseName", this.getDatabaseName()) + "";

		return json + "}";

	}

	public JsonObject toJsonObject() {

		JsonObject obj = Json.createObjectBuilder()

				.add("driverClassName", this.getDriverClassName()).add("initialSize", this.getInitialSize())
				.add("maxActive", this.getMaxActive()).add("maxIdle", this.getMaxIdle())
				.add("validationQuery", this.getValidationQuery()).add("url", this.getUrl())
				.add("userName", this.getUserName()).add("userPassword", "SECRET").add("verbose", this.isVerbose())
				.add("schema", this.getSchema()).add("convertToJavaTimeValues", this.isConvertToJavaTimeValues())
				.add("serverName", this.getServerName()).add("portNumber", this.getPortNumber())
				.add("databaseName", this.getDatabaseName()).build();

		return obj;

	}

} // END CLASS -----------------------------------------------------------------