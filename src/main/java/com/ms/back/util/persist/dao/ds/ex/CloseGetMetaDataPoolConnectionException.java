package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;

public class CloseGetMetaDataPoolConnectionException extends AbstractSQLExceptionWrapperDataSource {

	private static final String OPERATION_TYPE_CLOSE_CONNECTION = "CLOSE_CONNECTION";
	private static final String TITLE_CLOSE_CONNECTION = "Cierre de Conexión";
	private static final String SUBJECT_CLOSE_CONNECTION = "Error al intentar cerrar una conexión. No se pudo cerrar la conexion para obtener información del origen de datos.";

	public CloseGetMetaDataPoolConnectionException(SQLException sqlException, DataSourceInfo dataSourceInfo) {
		super(sqlException, OPERATION_TYPE_CLOSE_CONNECTION, TITLE_CLOSE_CONNECTION, SUBJECT_CLOSE_CONNECTION,
				dataSourceInfo);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8032412644209026697L;

} // END CLASS -----------------------------------------------------------------
