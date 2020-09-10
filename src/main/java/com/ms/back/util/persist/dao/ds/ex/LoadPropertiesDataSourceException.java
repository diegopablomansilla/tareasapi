package com.ms.back.util.persist.dao.ds.ex;

import java.io.IOException;
import java.time.ZonedDateTime;

public class LoadPropertiesDataSourceException extends Exception {

	private static final String OPERATION_TYPE_LOAD_PROPERTIES = "LOAD_PROPERTIES";
	private static final String TITLE_LOAD_PROPERTIES = "Lectura Archivo Propiedades";
	private static final String SUBJECT_LOAD_PROPERTIES = "Error al intentar leer y cargar un archivo de propiedades.";

	// ---------------------------------------------------------------------------------------------------------------------------

	private IOException ioException;

	// ---------------------------------------------------------------------------------------------------------------------------

	private ZonedDateTime time;
	private String title = "unknown";
	private String subject = "unknown";
	private String operationType = "unknown";
	private String key = "unknown";
	private String path = "unknown";

	// ---------------------------------------------------------------------------------------------------------------------------

	public LoadPropertiesDataSourceException(IOException ioException, String key, String path) {
		this.time = ZonedDateTime.now();
		this.ioException = ioException;

		this.title = TITLE_LOAD_PROPERTIES;
		this.subject = SUBJECT_LOAD_PROPERTIES;
		this.operationType = OPERATION_TYPE_LOAD_PROPERTIES;
		this.key = key;
		this.path = path;

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public IOException getIOException() {
		return ioException;
	}

	public Throwable getCause() {
		return ioException.getCause();
	}

	public String getMessage() {
		return ioException.getMessage();
	}

	public StackTraceElement[] getStackTrace() {
		return ioException.getStackTrace();
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
		s += "\n\t" + "IOException:" + this.getIOException();
		s += "\n";

		return s;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419644203026597L;

} // END CLASS -----------------------------------------------------------------
