package com.ms.back.util.persist;

import java.sql.SQLException;

import com.ms.back.util.persist.dao.ds.ConnectionWrapper;
import com.ms.back.util.persist.dao.ds.ex.BeginException;
import com.ms.back.util.persist.dao.ds.ex.CloseException;
import com.ms.back.util.persist.dao.ds.ex.CommitException;
import com.ms.back.util.persist.dao.ds.ex.DDLException;
import com.ms.back.util.persist.dao.ds.ex.DeleteException;
import com.ms.back.util.persist.dao.ds.ex.IllegalStateTransactionException;
import com.ms.back.util.persist.dao.ds.ex.InsertException;
import com.ms.back.util.persist.dao.ds.ex.RollBackException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.ex.UpdateException;
import com.ms.back.util.persist.dao.ds.info.DataBaseInfo;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class DataBase {

	// ---------------------------------------------------------------------------------------------------------------------------

	protected ConnectionWrapper connection;
	protected boolean transactionInProgress;

	// ---------------------------------------------------------------------------------------------------------------------------

	protected DataBase(ConnectionWrapper connection) {
		super();

		this.connection = connection;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public void disconnect() throws CloseException {
		this.connection.closeConnection();
	}

	public DataBaseInfo getDataBaseInfo() {
		return new DataBaseInfo(connection.getConnectionInfo(), isTransactionInProgress());
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public boolean isTransactionInProgress() {
		return transactionInProgress;
	}

	public void beginTransaction() throws BeginException, IllegalStateTransactionException {
		if (transactionInProgress == false) {
			this.connection.beginTransaction();
			transactionInProgress = true;
		} else {
			throw new IllegalStateTransactionException(connection.hashCode() + "", "There is an ongoing transaction.");
		}

	}

	public void commitTransaction() throws CommitException, IllegalStateTransactionException {
		if (transactionInProgress == true) {
			this.connection.commitTransaction();
			transactionInProgress = false;
		} else {
			throw new IllegalStateTransactionException(connection.hashCode() + "",
					"There is no transaction in progress.");
		}

	}

	public void rollBackTransaction() throws RollBackException, IllegalStateTransactionException {
		if (transactionInProgress == true) {
			this.connection.rollBackTransaction();
			transactionInProgress = false;
		} else {
			throw new IllegalStateTransactionException(connection.hashCode() + "",
					"There is no transaction in progress.");
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result insert(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.insert(sql);
	}

	public Result insert(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.insert(sql, args);
	}

	public Result insert(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.insert(statement);
	}

	public Result update(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.update(sql);
	}

	public Result update(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.update(sql, args);
	}

	public Result update(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.update(statement);
	}

	public Result delete(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.delete(sql);
	}

	public Result delete(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.delete(sql, args);
	}

	public Result delete(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return connection.delete(statement);
	}

	public Result query(String sql) throws SelectException, SQLException {
		return connection.query(sql);
	}

	public Result query(String sql, Object[] args) throws SelectException, SQLException {
		return connection.query(sql, args);
	}

	public Result query(Statement statement) throws SelectException, SQLException {
		return connection.query(statement);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	// fill rigthLevel
	// mejorar el isList
	// implementar DML por atributos
	// mejorar el batch en dsw

	// save

	// findById

	// todo
	// repository CRUD

	// sacar Identifiable, buscar un getId y setId con String solito
	// agregarle wehere a los fill
	// hacer un mapperorder

} // END CLASS -----------------------------------------------------------------