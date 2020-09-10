package com.ms.back.util.persist.dao.ds;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ms.back.util.persist.Printer;
import com.ms.back.util.persist.dao.ds.ex.BeginException;
import com.ms.back.util.persist.dao.ds.ex.CloseException;
import com.ms.back.util.persist.dao.ds.ex.CommitException;
import com.ms.back.util.persist.dao.ds.ex.DDLException;
import com.ms.back.util.persist.dao.ds.ex.DeleteException;
import com.ms.back.util.persist.dao.ds.ex.DeleteForeignKeyViolationException;
import com.ms.back.util.persist.dao.ds.ex.InsertException;
import com.ms.back.util.persist.dao.ds.ex.RollBackException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.ex.UpdateException;
import com.ms.back.util.persist.dao.ds.info.ConnectionInfo;
import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

class ConnectionWrapperImpl implements ConnectionWrapper {

	// ---------------------------------------------------------------------------------------------------------------------------

	private final String MSG_1 = "Se pretende agregar un parámetro a una sentencia sql que posee un tipo de dato desconocido. Se recibió [${value}] de tipo ${class}, y se espera String | Boolean | Short | Integer | Long | Float | Double | BigDecimal | Date | Timestamp | Time";

	// ---------------------------------------------------------------------------------------------------------------------------

	private boolean verbose;
	private Printer printer;
	private Connection connection;
	private ConnectionInfo connectionInfo;

	// ---------------------------------------------------------------------------------------------------------------------------

	public ConnectionWrapperImpl(Printer printer, Connection connection, DataSourceInfo dataSourceInfo,
			Statement statement) {

		this.verbose = dataSourceInfo.getDataSourceProperties().isVerbose();

		this.printer = printer;

		connectionInfo = new ConnectionInfo(dataSourceInfo);

		connectionInfo.addStatement(statement);

		this.connection = connection;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public ConnectionInfo getConnectionInfo() {
		return connectionInfo;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

//	public boolean isBeginTransaction() throws SQLException {
//		return connection.getAutoCommit() == false;
//	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public void beginTransaction() throws BeginException {

		Statement statement = new Statement(connectionInfo.getDataSourceInfo().getDataBaseKey() + ".beginTransaction("
				+ connection.hashCode() + ")");
		statement.setDataBase(connectionInfo.getDataSourceInfo().getDataBaseKey());
		statement.setConnection(connection.hashCode() + "");
		connectionInfo.addStatement(statement);

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());
		print(statement.toStringStart());

		try {

			connection.setAutoCommit(false);

		} catch (SQLException e) {

			statement.setEndTime(ZonedDateTime.now());
			statement.setExecuted(false);

			print(statement.toStringEnd());

			throw new BeginException(e, statement, connectionInfo);
		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);

		print(statement.toStringEnd());

		// --------------------------------------------------
	}

	public void commitTransaction() throws CommitException/* , CloseException */ {

		Statement statement = new Statement(connectionInfo.getDataSourceInfo().getDataBaseKey() + ".commitTransaction("
				+ connection.hashCode() + ")");
		statement.setDataBase(connectionInfo.getDataSourceInfo().getDataBaseKey());
		statement.setConnection(connection.hashCode() + "");
		connectionInfo.addStatement(statement);

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());
		print(statement.toStringStart());

		try {

			connection.commit();

		} catch (SQLException e) {

			statement.setEndTime(ZonedDateTime.now());
			statement.setExecuted(false);

			print(statement.toStringEnd());

			throw new CommitException(e, statement, connectionInfo);
		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);

		print(statement.toStringEnd());

		// --------------------------------------------------

//		closeConnection();

	}

	public void rollBackTransaction() throws RollBackException/* , CloseException */ {

		Statement statement = new Statement(connectionInfo.getDataSourceInfo().getDataBaseKey()
				+ ".rollBackTransaction(" + connection.hashCode() + ")");
		statement.setDataBase(connectionInfo.getDataSourceInfo().getDataBaseKey());
		statement.setConnection(connection.hashCode() + "");
		connectionInfo.addStatement(statement);

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());
		print(statement.toStringStart());

		try {

			connection.rollback();

		} catch (SQLException e) {

			statement.setEndTime(ZonedDateTime.now());
			statement.setExecuted(false);

			print(statement.toStringEnd());

			throw new RollBackException(e, statement, connectionInfo);
		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);

		print(statement.toStringEnd());

		// --------------------------------------------------

//		closeConnection();

	}

	public void closeConnection() throws CloseException {

		Statement statement = new Statement(connectionInfo.getDataSourceInfo().getDataBaseKey() + ".closeConnection("
				+ connection.hashCode() + ")");
		statement.setDataBase(connectionInfo.getDataSourceInfo().getDataBaseKey());
		statement.setConnection(connection.hashCode() + "");
		connectionInfo.addStatement(statement);

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());
		print(statement.toStringStart());

		try {
			if (connection != null & connection.isClosed() == false) {
				connection.close();
			}
		} catch (SQLException e) {

			statement.setEndTime(ZonedDateTime.now());
			statement.setExecuted(false);

			print(statement.toStringEnd());

			throw new CloseException(e, statement, connectionInfo);
		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);

		print(statement.toStringEnd());

		// --------------------------------------------------
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result insert(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {

		return execute(1, new Statement(sql));
	}

	public Result insert(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(1, new Statement(sql, args));
	}

	public Result insert(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(1, statement);
	}

	public Result update(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(2, new Statement(sql));
	}

	public Result update(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(2, new Statement(sql, args));
	}

	public Result update(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(2, statement);
	}

	public Result delete(String sql)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(3, new Statement(sql));
	}

	public Result delete(String sql, Object[] args)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(3, new Statement(sql, args));
	}

	public Result delete(Statement statement)
			throws InsertException, UpdateException, DeleteException, SQLException, DDLException {
		return execute(3, statement);
	}

	public Result execute(int op, Statement statement)
			throws SQLException, InsertException, UpdateException, DeleteException, DDLException {

		statement.setDataBase(connectionInfo.getDataSourceInfo().getDataBaseKey());

		connectionInfo.addStatement(statement);

		PreparedStatement preparedStatement = buildPreparedStatement(statement);

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());

		print(statement.toStringStart());

		try {

			statement.setRowCount((long) preparedStatement.executeUpdate());

		} catch (SQLException e) {

			statement.setEndTime(ZonedDateTime.now());
			statement.setExecuted(false);

			print(statement.toStringEnd());

			if (op == 1) {
				throw new InsertException(e, statement, connectionInfo);
			} else if (op == 2) {
				throw new UpdateException(e, statement, connectionInfo);
			} else if (op == 3) {
				if (this.getConnectionInfo().getDataSourceInfo().getDataSourceMetaData().isPostgreSql()
						&& e.getSQLState().equalsIgnoreCase("23503")) {
					throw new DeleteForeignKeyViolationException(e, statement, connectionInfo);
				}
				throw new DeleteException(e, statement, connectionInfo);
			} else if (op == 0) {
				throw new DDLException(e, statement, connectionInfo);
			}

		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);

		// --------------------------------------------------

		// if (preparedStatement.isClosed() == false) {
		// preparedStatement.close();
		// }

		// --------------------------------------------------

		print(statement.toStringEnd());

		// --------------------------------------------------

		return statement;

		// --------------------------------------------------

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public Result query(String sql) throws SelectException, SQLException {
		return query(new Statement(sql));
	}

	public Result query(String sql, Object[] args) throws SelectException, SQLException {
		return query(new Statement(sql, args));
	}

	public Result query(Statement statement) throws SQLException, SelectException {

		statement.setDataBase(connectionInfo.getDataSourceInfo().getDataBaseKey());

		List<Object[]> listT = new ArrayList<Object[]>();

		connectionInfo.addStatement(statement);

		PreparedStatement preparedStatement = buildPreparedStatement(statement);

		ResultSet resultSet = null;

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());

		print(statement.toStringStart());

		try {

			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {

			statement.setEndTime(ZonedDateTime.now());
			statement.setExecuted(false);

			print(statement.toStringEnd());

			throw new SelectException(e, statement, connectionInfo);
		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);

		// --------------------------------------------------

		int columnCount = resultSet.getMetaData().getColumnCount();

		statement.setColumnCount(columnCount);

		while (resultSet.next()) {

			Object[] row = new Object[columnCount];

			for (int j = 0; j < columnCount; j++) {

				row[j] = resultSet.getObject((j + 1));

				if (this.getConnectionInfo().getDataSourceInfo().getDataSourceProperties()
						.isConvertToJavaTimeValues()) {

					if (row[j] != null && row[j] instanceof java.sql.Date) {

						row[j] = ((java.sql.Date) row[j]).toLocalDate();

					} else if (row[j] != null && row[j] instanceof java.sql.Timestamp) {

						row[j] = ((java.sql.Timestamp) row[j]).toLocalDateTime();

					}

				}

			}

			listT.add(row);
		}

		// --------------------------------------------------

		// if (resultSet != null && resultSet.isClosed() == false) {
		// resultSet.close();
		// }

		// if (preparedStatement != null && preparedStatement.isClosed() ==
		// false) {
		// preparedStatement.close();
		// }

		// --------------------------------------------------

		Object[][] table = new Object[listT.size()][columnCount];

		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < columnCount; j++) {
				table[i][j] = listT.get(i)[j];
			}

		}

		statement.setTable(table);

		// --------------------------------------------------

		print(statement.toStringEnd());

		// --------------------------------------------------

		return statement;

		// --------------------------------------------------
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private PreparedStatement buildPreparedStatement(Statement statement) throws SQLException {

		if (statement.getSql() == null || statement.getSql().trim().length() == 0) {
			throw new IllegalArgumentException("Se intento ejecutar una sentencia sql nula.");
		}

		statement.setConnection(this.connection.hashCode() + "");

		PreparedStatement preparedStatement = this.connection.prepareStatement(statement.getSql());

////		printSQL.printSQLWarning(preparedStatement.getWarnings(), dataSourceProperties.isVerbose());

		if (statement.getArgs() != null) {

			for (int i = 0; i < statement.getArgs().length; i++) {
				setValueOnStatement(preparedStatement, statement.getArgs()[i], (i + 1));
			}
		}

		if (this.connectionInfo.getDataSourceInfo().getDataSourceMetaData().isPostgreSql()) {
			statement.setSql(preparedStatement.toString());
		}

		return preparedStatement;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	private void setValueOnStatement(PreparedStatement preparedStatement, Object value, Integer i) throws SQLException {

		if (value != null) {

			if (value.getClass() == String.class) {

				preparedStatement.setString(i, (String) value);

			} else if (value.getClass() == Boolean.class) {

				preparedStatement.setBoolean(i, (Boolean) value);

			} else if (value.getClass() == Short.class) {

				preparedStatement.setShort(i, (Short) value);

			} else if (value.getClass() == Integer.class) {

				preparedStatement.setInt(i, (Integer) value);

			} else if (value.getClass() == Long.class) {

				preparedStatement.setLong(i, (Long) value);

			} else if (value.getClass() == Float.class) {

				preparedStatement.setFloat(i, (Float) value);

			} else if (value.getClass() == Double.class) {

				preparedStatement.setDouble(i, (Double) value);

			} else if (value.getClass() == BigDecimal.class) {

				preparedStatement.setBigDecimal(i, (BigDecimal) value);

			} else if (value.getClass() == Date.class) {

				preparedStatement.setDate(i, (Date) value);

			} else if (value.getClass() == java.util.Date.class) {

				Date sqlDate = new Date(((java.util.Date) value).getTime());

				preparedStatement.setDate(i, sqlDate);

			} else if (value.getClass() == java.time.LocalDate.class) {

				preparedStatement.setObject(i, (java.time.LocalDate) value);

			} else if (value.getClass() == Timestamp.class) {

				preparedStatement.setTimestamp(i, (Timestamp) value);

			} else if (value.getClass() == java.time.LocalDateTime.class) {

				preparedStatement.setObject(i, (java.time.LocalDateTime) value);

			} else if (value.getClass() == Time.class) {

				preparedStatement.setTime(i, (Time) value);

			} else if (value.getClass() == LocalTime.class) {

				preparedStatement.setObject(i, (LocalTime) value);

			}else if (value instanceof Class) {

				Class c = (Class) value;

				if (c.equals(String.class)) {
					preparedStatement.setNull(i, Types.VARCHAR);
				} else if (c.equals(Boolean.class)) {
					preparedStatement.setNull(i, Types.BOOLEAN);
				} else if (c.equals(Short.class)) {
					preparedStatement.setNull(i, Types.SMALLINT);
				} else if (c.equals(Integer.class)) {
					preparedStatement.setNull(i, Types.INTEGER);
				} else if (c.equals(Long.class)) {
					preparedStatement.setNull(i, Types.BIGINT);
				} else if (c.equals(Float.class)) {
					preparedStatement.setNull(i, Types.REAL);
				} else if (c.equals(Double.class)) {
					preparedStatement.setNull(i, Types.FLOAT);
				} else if (c.equals(BigDecimal.class)) {
					preparedStatement.setNull(i, Types.NUMERIC);
				} else if (c.equals(Date.class)) {
					preparedStatement.setNull(i, Types.DATE);
				} else if (c.equals(java.util.Date.class)) {
					preparedStatement.setNull(i, Types.DATE);
				} else if (c.equals(java.time.LocalDate.class)) {
					preparedStatement.setNull(i, Types.DATE);
				} else if (c.equals(Timestamp.class)) {
					preparedStatement.setNull(i, Types.TIMESTAMP);
				} else if (c.equals(java.time.LocalDateTime.class)) {
					preparedStatement.setNull(i, Types.TIMESTAMP);
				} else if (c.equals(Time.class)) {
					preparedStatement.setNull(i, Types.TIME);
				} else if (c.equals(LocalTime.class)) {
					preparedStatement.setNull(i, Types.TIME);	
				} else {
					throw new IllegalArgumentException(MSG_1.replace("${value}", value.toString()).replace("${class}",
							value.getClass().getSimpleName()));
				}

			} else {
				throw new IllegalArgumentException(MSG_1.replace("${value}", value.toString()).replace("${class}",
						value.getClass().getCanonicalName()));
			}
		}
	}

	private void print(String s) {
		if (this.verbose) {
			this.printer.print(s);
		}
	}

} // END CLASS -----------------------------------------------------------------