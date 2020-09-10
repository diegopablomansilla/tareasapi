package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class InsertException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_INSERT = "INSERT";
	private static final String TITLE_INSERT = "Insertando un Registro";
	private static final String SUBJECT_INSERT = "Error al intentar insertar un registro.";

	public InsertException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_INSERT, TITLE_INSERT, SUBJECT_INSERT, statement, connectionInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031413644209026597L;

} // END CLASS -----------------------------------------------------------------
