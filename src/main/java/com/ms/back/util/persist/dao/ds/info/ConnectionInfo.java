package com.ms.back.util.persist.dao.ds.info;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.ms.back.util.persist.JsonUtil;

public class ConnectionInfo extends JsonUtil implements Cloneable {

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataSourceInfo dataSourceInfo;
	private List<Statement> statements = new ArrayList<Statement>();

	// ---------------------------------------------------------------------------------------------------------------------------

	public ConnectionInfo(DataSourceInfo dataSourceInfo) {
		super();
		this.dataSourceInfo = dataSourceInfo;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public DataSourceInfo getDataSourceInfo() {
		return dataSourceInfo;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public boolean addStatement(Statement e) {

		if (dataSourceInfo.getDataSourceProperties().getStackStatementSize() > -1
				&& statements.size() >= dataSourceInfo.getDataSourceProperties().getStackStatementSize()) {
			statements = new ArrayList<Statement>();
		}

		e.setNumber(statements.size() + 1 + "");
		return statements.add(e);

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "";

		if (dataSourceInfo != null) {
			s += dataSourceInfo.toString();
		}

//		s += "\n\n\t" + "Connection info statements history:\n";
//		if (statements != null) {
//			for(Statement stm : statements) {
//				s += stm.toString();
//			}
//		}

		return s;
	}

	public ConnectionInfo clone() {

		ConnectionInfo other = new ConnectionInfo(dataSourceInfo.clone());

		return other;

	}

	public String toJson() {

		String json = "{";

		json += this.toJsonField("dataSourceInfo", this.getDataSourceInfo()) + ", ";
		json += this.toJsonField("statements", this.getStatements());

		return json + "}";
	}

	public JsonObject toJsonObject() {

		JsonObject dataSourceInfo = null;
		JsonArrayBuilder statements = Json.createArrayBuilder();

		if (this.getDataSourceInfo() != null) {
			dataSourceInfo = this.getDataSourceInfo().toJsonObject();
		}

		if (this.getStatements() != null) {
			for (Statement statement : this.getStatements()) {
				if (statement != null) {
					statements.add(statement.toJsonObject());
				} else {
					statements.addNull();
				}
			}
		}

		JsonObject obj = Json.createObjectBuilder()

				.add("dataSourceInfo", dataSourceInfo).add("statements", statements)

				.build();

		return obj;

	}

} // END CLASS -----------------------------------------------------------------