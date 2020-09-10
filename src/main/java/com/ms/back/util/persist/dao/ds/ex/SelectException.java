package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class SelectException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_SELECT = "SELECT";
	private static final String TITLE_SELECT = "Consultando Listado de Registros";
	private static final String SUBJECT_SELECT = "Error al intentar consultar un listado del total de registros.";

	public SelectException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_SELECT, TITLE_SELECT, SUBJECT_SELECT, statement, connectionInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031419644209026597L;

} // END CLASS -----------------------------------------------------------------
