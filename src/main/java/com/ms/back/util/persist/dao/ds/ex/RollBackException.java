package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class RollBackException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_ROLLBACK_TRANSACTION = "ROLLBACK_TRANSACTION";
	private static final String TITLE_ROLLBACK_TRANSACTION = "Deshacer de Cambios de una Transacción";
	private static final String SUBJECT_ROLLBACK_TRANSACTION = "Error al intentar deshacer los cambios de una transacción.";

	public RollBackException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_ROLLBACK_TRANSACTION, TITLE_ROLLBACK_TRANSACTION,
				SUBJECT_ROLLBACK_TRANSACTION, statement, connectionInfo);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419644209036597L;

} // END CLASS -----------------------------------------------------------------
