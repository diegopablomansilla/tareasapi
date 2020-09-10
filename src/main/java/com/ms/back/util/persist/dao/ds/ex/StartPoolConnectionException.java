package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;

public class StartPoolConnectionException extends AbstractSQLExceptionWrapperDataSource {

	private static final String OPERATION_TYPE_START_POOL_CONECTION = "START_POOL_CONECTION";
	private static final String TITLE_START_POOL_CONECTION = "Data Source - Pool Connection";
	private static final String SUBJECT_START_POOL_CONECTION = "No se pudo obtener las conexiones a la base de datos. Error al tratar de iniciar el pool de conexiones.";

	public StartPoolConnectionException(SQLException sqlException, DataSourceInfo dataSourceInfo) {
		super(sqlException, OPERATION_TYPE_START_POOL_CONECTION, TITLE_START_POOL_CONECTION,
				SUBJECT_START_POOL_CONECTION, dataSourceInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419644209026592L;

} // END CLASS -----------------------------------------------------------------
