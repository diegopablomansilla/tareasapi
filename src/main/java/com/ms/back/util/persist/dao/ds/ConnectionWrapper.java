package com.ms.back.util.persist.dao.ds;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.ex.BeginException;
import com.ms.back.util.persist.dao.ds.ex.CloseException;
import com.ms.back.util.persist.dao.ds.ex.CommitException;
import com.ms.back.util.persist.dao.ds.ex.DDLException;
import com.ms.back.util.persist.dao.ds.ex.DeleteException;
import com.ms.back.util.persist.dao.ds.ex.InsertException;
import com.ms.back.util.persist.dao.ds.ex.RollBackException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.ex.UpdateException;
import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public interface ConnectionWrapper {

	// ---------------------------------------------------------------------------------------------------------------------------
	public ConnectionInfo getConnectionInfo();

	// ---------------------------------------------------------------------------------------------------------------------------

//	public boolean isBeginTransaction() throws SQLException;
	
	public void beginTransaction() throws BeginException;

	public void commitTransaction() throws CommitException/* , CloseException */;

	public void rollBackTransaction() throws RollBackException/* , CloseException */;

	public void closeConnection() throws CloseException;

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result insert(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;

	public Result insert(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;
	
	public Result insert(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result update(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;

	public Result update(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;
	
	public Result update(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result delete(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;

	public Result delete(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;
	
	public Result delete(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException;

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result query(String sql) throws SelectException, SQLException;

	public Result query(String sql, Object[] args) throws SelectException, SQLException;

	public Result query(Statement statement) throws SelectException, SQLException;

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result execute(int op, Statement statement)
			throws SQLException, InsertException, UpdateException, DeleteException, DDLException;

} // END CLASS -----------------------------------------------------------------