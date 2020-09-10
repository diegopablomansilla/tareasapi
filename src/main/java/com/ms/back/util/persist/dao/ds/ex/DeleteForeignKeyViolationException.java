package com.ms.back.util.persist.dao.ds.ex;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class DeleteForeignKeyViolationException extends DeleteException {

	public DeleteForeignKeyViolationException(SQLException sqlException, Statement statement,
			ConnectionInfo connectionInfo) {
		super(sqlException, statement, connectionInfo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8131419644209426597L;

} // END CLASS -----------------------------------------------------------------
