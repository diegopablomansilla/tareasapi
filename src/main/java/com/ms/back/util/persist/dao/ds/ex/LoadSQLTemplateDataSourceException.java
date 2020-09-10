package com.ms.back.util.persist.dao.ds.ex;

import java.time.ZonedDateTime;

public class LoadSQLTemplateDataSourceException extends Exception {

	private static final String OPERATION_TYPE_LOAD_SQL_TEMPLATE = "LOAD_SQL_TEMPLATE";
	private static final String TITLE_LOAD_SQL_TEMPLATE = "Lectura Archivo Template SQL";
	private static final String SUBJECT_LOAD_SQL_TEMPLATE = "Error al intentar leer y cargar un archivo template sql.";

	// ---------------------------------------------------------------------------------------------------------------------------

	private Exception exception;

	// ---------------------------------------------------------------------------------------------------------------------------

	private ZonedDateTime time;
	private String title = "unknown";
	private String subject = "unknown";
	private String operationType = "unknown";
	private String key = "unknown";
	private String path = "unknown";

	// ---------------------------------------------------------------------------------------------------------------------------

	public LoadSQLTemplateDataSourceException(Exception exception, String path) {
		this.time = ZonedDateTime.now();
		this.exception = exception;

		this.title = TITLE_LOAD_SQL_TEMPLATE;
		this.subject = SUBJECT_LOAD_SQL_TEMPLATE;
		this.operationType = OPERATION_TYPE_LOAD_SQL_TEMPLATE;
		this.path = path;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Exception getException() {
		return exception;
	}

	public Throwable getCause() {
		return exception.getCause();
	}

	public String getMessage() {
		return exception.getMessage();
	}

	public StackTraceElement[] getStackTrace() {
		return exception.getStackTrace();
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

	public String getKey() {
		return key;
	}

	public String getPath() {
		return path;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "\n\n";

		s += "\n\t" + "Exception: " + this.getClass().getCanonicalName();
		s += "\n\t" + "Time: " + this.getTime();
		s += "\n\t" + "Title: " + this.getTitle();
		s += "\n\t" + "Subject: " + this.getSubject();
		s += "\n\t" + "Operation type: " + this.getOperationType();
		s += "\n\t" + "Message: " + this.getMessage();
		s += "\n\t" + "Key: " + this.getKey();
		s += "\n\t" + "Path: " + this.getPath();
		s += "\n";
		s += "\n";
		s += "\n\t" + "IOException:" + this.getException();
		s += "\n";

		return s;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419644203026597L;

} // END CLASS -----------------------------------------------------------------
