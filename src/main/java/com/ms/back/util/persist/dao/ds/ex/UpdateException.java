package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class UpdateException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_UPDATE = "UPDATE";
	private static final String TITLE_UPDATE = "Actualizando un Registro";
	private static final String SUBJECT_UPDATE = "Error al intentar actualizar un registro.";

	public UpdateException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_UPDATE, TITLE_UPDATE, SUBJECT_UPDATE, statement, connectionInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031413644203026597L;

} // END CLASS -----------------------------------------------------------------
