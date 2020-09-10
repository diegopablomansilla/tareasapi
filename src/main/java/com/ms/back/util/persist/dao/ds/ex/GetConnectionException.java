package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;

public class GetConnectionException extends AbstractSQLExceptionWrapperDataSource {

	private static final String OPERATION_TYPE_START_CONNECTION = "START_CONNECTION";
	private static final String TITLE_START_CONNECTION = "Apertura de Conexión";
	private static final String SUBJECT_START_CONNECTION = "Error al intentar abrir una conexión.";;

	public GetConnectionException(SQLException sqlException, DataSourceInfo dataSourceInfo) {
		super(sqlException, OPERATION_TYPE_START_CONNECTION, TITLE_START_CONNECTION, SUBJECT_START_CONNECTION,
				dataSourceInfo);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7038419644209026597L;

} // END CLASS -----------------------------------------------------------------
