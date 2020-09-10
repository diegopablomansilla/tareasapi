package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class DeleteException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_DELETE = "DELETE";
	private static final String TITLE_DELETE = "Borrando un Registro";
	private static final String SUBJECT_DELETE = "Error al intentar borrar un registro.";

	public DeleteException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_DELETE, TITLE_DELETE, SUBJECT_DELETE, statement, connectionInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031419644209426597L;

} // END CLASS -----------------------------------------------------------------
