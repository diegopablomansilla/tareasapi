package com.ms.back.util.persist.dao.ds.ex;

import java.time.ZonedDateTime;

public class IllegalStateTransactionException extends Exception {

	// ---------------------------------------------------------------------------------------------------------------------------

	private ZonedDateTime time;
	private String title = "Estado de la transacción";
	private String subject = "unknown";
	private String connection = "unknown";
	private String operationType = "STATE_TRANSACTION";

	// ---------------------------------------------------------------------------------------------------------------------------

	public IllegalStateTransactionException(String connection, String subject) {
		this.time = ZonedDateTime.now();
		this.subject = subject;
		this.connection = connection;
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

	public String getConnection() {
		return connection;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "\n\n";

		s += "\n" + "Exception: " + this.getClass().getCanonicalName();
		s += "\n" + "Time: " + this.getTime();
		s += "\n" + "Title: " + this.getTitle();
		s += "\n" + "Subject: " + this.getSubject();
		s += "\n" + "Operation type: " + this.getOperationType();
		s += "\n" + "Connection: " + this.getConnection();
		s += "\n" + "Message: " + this.getMessage();
		s += "\n";

		return s;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -5504598004204150746L;

} // END CLASS -----------------------------------------------------------------
