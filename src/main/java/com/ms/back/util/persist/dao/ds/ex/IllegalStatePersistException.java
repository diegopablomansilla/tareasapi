package com.ms.back.util.persist.dao.ds.ex;

import java.time.ZonedDateTime;

public class IllegalStatePersistException extends Exception {

	// ---------------------------------------------------------------------------------------------------------------------------

	private ZonedDateTime time;
	private String title = "Estado de la capa de persistencia";
	private String subject = "unknown";
	private String operationType = "STATE_PERSIST";

	// ---------------------------------------------------------------------------------------------------------------------------

	public IllegalStatePersistException(String subject) {
		this.time = ZonedDateTime.now();
		this.subject = subject;
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
