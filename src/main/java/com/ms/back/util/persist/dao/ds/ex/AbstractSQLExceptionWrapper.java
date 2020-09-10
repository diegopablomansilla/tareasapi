package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;
import java.time.ZonedDateTime;

public abstract class AbstractSQLExceptionWrapper extends Exception {

	// ---------------------------------------------------------------------------------------------------------------------------

	private SQLException sqlException;

	// ---------------------------------------------------------------------------------------------------------------------------

	private ZonedDateTime time;
	private String title = "unknown";
	private String subject = "unknown";
	private String operationType = "unknown";

	// ---------------------------------------------------------------------------------------------------------------------------

	public AbstractSQLExceptionWrapper(SQLException sqlException, String operationType, String title, String subject) {

		this.time = ZonedDateTime.now();
		this.sqlException = sqlException;

		this.title = title;
		this.subject = subject;
		this.operationType = operationType;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public SQLException getSQLException() {
		return sqlException;
	}

	public Throwable getCause() {
		return sqlException.getCause();
	}

	public String getMessage() {
		return sqlException.getMessage();
	}

	public StackTraceElement[] getStackTrace() {
		return sqlException.getStackTrace();
	}

	public int getErrorCode() {
		return sqlException.getErrorCode();
	}

	public String getSQLState() {
		return sqlException.getSQLState();
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public ZonedDateTime getTime() {
		return time;
	}

	public String getTitle() {
		return title;
	}

	public String getSubject() {
		return subject;
	}

	public String getOperationType() {
		return operationType;
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
	private static final long serialVersionUID = -5504598004204150746L;

} // END CLASS -----------------------------------------------------------------
