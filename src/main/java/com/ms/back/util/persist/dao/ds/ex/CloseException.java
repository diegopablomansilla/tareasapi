package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class CloseException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_CLOSE_CONNECTION = "CLOSE_CONNECTION";
	private static final String TITLE_CLOSE_CONNECTION = "Cierre de Conexión";
	private static final String SUBJECT_CLOSE_CONNECTION = "Error al intentar cerrar una conexión.";

	public CloseException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_CLOSE_CONNECTION, TITLE_CLOSE_CONNECTION, SUBJECT_CLOSE_CONNECTION,
				statement, connectionInfo);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8032419644209026697L;

} // END CLASS -----------------------------------------------------------------
