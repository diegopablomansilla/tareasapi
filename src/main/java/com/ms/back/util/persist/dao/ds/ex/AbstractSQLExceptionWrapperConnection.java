package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public abstract class AbstractSQLExceptionWrapperConnection extends AbstractSQLExceptionWrapper {

	// ---------------------------------------------------------------------------------------------------------------------------

	private Statement statement;
	private ConnectionInfo connectionInfo;

	// ---------------------------------------------------------------------------------------------------------------------------

	public AbstractSQLExceptionWrapperConnection(SQLException sqlException, String operationType, String title, String subject,
			Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, operationType, title, subject);
		this.statement = statement;
		this.connectionInfo = connectionInfo;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Statement getStatement() {
		return statement;
	}

	public ConnectionInfo getConnectionInfo() {
		return connectionInfo;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "\n\n";

		s += "\n" + "Exception: " + this.getClass().getCanonicalName();
		s += "\n" + "Time: " + this.getTime();
		s += "\n" + "Title: " + this.getTitle();
		s += "\n" + "Subject: " + this.getSubject();
		s += "\n" + "Operation type: " + this.getOperationType();
		s += "\n" + "Error code: " + this.getErrorCode();
		s += "\n" + "SQL state: " + this.getSQLState();
		s += "\n" + "Message: " + this.getMessage();
		s += "\n" + "Statement error:\n";
		if (this.getStatement() != null) {
			s += this.getStatement().toString();
		}
		s += "\n\n" + "Connection info:\n";
		if (this.getConnectionInfo() != null) {
			s += this.getConnectionInfo().toString();
		}
		s += "\n";
		s += "\n";
		s += "\n" + "SQLException:" + this.getSQLException();
		s += "\n";

		return s;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -5504598004204150743L;

} // END CLASS -----------------------------------------------------------------
