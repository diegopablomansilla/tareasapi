package com.ms.back.util.persist.dao.ds.info;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.back.util.persist.JsonUtil;

public class DataBaseInfo extends JsonUtil {

	private ConnectionInfo connectionInfo;
	private boolean transactionInProgress;

	public DataBaseInfo(ConnectionInfo connectionInfo, boolean transactionInProgress) {
		super();
		this.connectionInfo = connectionInfo;
		this.transactionInProgress = transactionInProgress;
	}

	public ConnectionInfo getConnectionInfo() {
		return connectionInfo;
	}

	public void setConnectionInfo(ConnectionInfo connectionInfo) {
		this.connectionInfo = connectionInfo;
	}

	public boolean isTransactionInProgress() {
		return transactionInProgress;
	}

	public void setTransactionInProgress(boolean transactionInProgress) {
		this.transactionInProgress = transactionInProgress;
	}

	public String toJson() {

		String json = "{";

		json += this.toJsonField("transactionInProgress", this.isTransactionInProgress()) + ",";
		json += this.toJsonField("connectionInfo", this.getConnectionInfo());

		return json + "}";
	}

	public JsonObject toJsonObject() {

		JsonObject connectionInfo = null;

		if (this.getConnectionInfo() != null) {
			connectionInfo = this.getConnectionInfo().toJsonObject();
		}

		JsonObject obj = Json.createObjectBuilder()

				.add("transactionInProgress", this.isTransactionInProgress()).add("connectionInfo", connectionInfo)

				.build();

		return obj;

	}

}
