package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class BeginException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_BEGIN_TRANSACTION = "BEGIN_TRANSACTION";
	private static final String TITLE_BEGIN_TRANSACTION = "Comienzo de Transacción";
	private static final String SUBJECT_BEGIN_TRANSACTION = "Error al intentar iniciar una transacción.";

	public BeginException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_BEGIN_TRANSACTION, TITLE_BEGIN_TRANSACTION, SUBJECT_BEGIN_TRANSACTION,
				statement, connectionInfo);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419644209026597L;

} // END CLASS -----------------------------------------------------------------
