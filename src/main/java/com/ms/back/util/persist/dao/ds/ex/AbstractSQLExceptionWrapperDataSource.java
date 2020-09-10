package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;

public abstract class AbstractSQLExceptionWrapperDataSource extends AbstractSQLExceptionWrapper {

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataSourceInfo dataSourceInfo;

	// ---------------------------------------------------------------------------------------------------------------------------

	public AbstractSQLExceptionWrapperDataSource(SQLException sqlException, String operationType, String title,
			String subject, DataSourceInfo dataSourceInfo) {
		super(sqlException, operationType, title, subject);
		this.dataSourceInfo = dataSourceInfo;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public DataSourceInfo getDataSourceInfo() {
		return dataSourceInfo;
	}

	public void setDataSourceInfo(DataSourceInfo dataSourceInfo) {
		this.dataSourceInfo = dataSourceInfo;
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
		if (this.getDataSourceInfo() != null) {
			s += this.getDataSourceInfo().toString();
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
	private static final long serialVersionUID = -5504398004204150743L;

} // END CLASS -----------------------------------------------------------------
