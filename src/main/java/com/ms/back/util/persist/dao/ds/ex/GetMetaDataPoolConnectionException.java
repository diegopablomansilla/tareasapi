package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;

public class GetMetaDataPoolConnectionException extends AbstractSQLExceptionWrapperDataSource {

	private static final String OPERATION_TYPE_METADATA_CONECTION = "METADATA_CONECTION";
	private static final String TITLE_METADATA_CONECTION = "Data Source - Meta Data";
	private static final String SUBJECT_METADATA_CONECTION = "No se pudo obtener información del origen de datos.";

	public GetMetaDataPoolConnectionException(SQLException sqlException, DataSourceInfo dataSourceInfo) {
		super(sqlException, OPERATION_TYPE_METADATA_CONECTION, TITLE_METADATA_CONECTION, SUBJECT_METADATA_CONECTION,
				dataSourceInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419645209026592L;

} // END CLASS -----------------------------------------------------------------
