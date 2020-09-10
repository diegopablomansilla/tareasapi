package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class CommitException extends AbstractSQLExceptionWrapperConnection {

	private static final String OPERATION_TYPE_COMMIT_TRANSACTION = "COMMIT_TRANSACTION";
	private static final String TITLE_COMMIT_TRANSACTION = "Fin de Transacción";
	private static final String SUBJECT_COMMIT_TRANSACTION = "Fin de Transacción";

	public CommitException(SQLException sqlException, Statement statement, ConnectionInfo connectionInfo) {
		super(sqlException, OPERATION_TYPE_COMMIT_TRANSACTION, TITLE_COMMIT_TRANSACTION, SUBJECT_COMMIT_TRANSACTION,
				statement, connectionInfo);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038419644209036597L;

} // END CLASS -----------------------------------------------------------------
