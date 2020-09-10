package com.ms.back.util.persist.dao.ds;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZonedDateTime;

import javax.sql.DataSource;

import com.ms.back.util.persist.Printer;
import com.ms.back.util.persist.dao.ds.ex.CloseGetMetaDataPoolConnectionException;
import com.ms.back.util.persist.dao.ds.ex.GetConnectionException;
import com.ms.back.util.persist.dao.ds.ex.GetMetaDataPoolConnectionException;
import com.ms.back.util.persist.dao.ds.ex.StartPoolConnectionException;
import com.ms.back.util.persist.dao.ds.info.DataSourceInfo;
import com.ms.back.util.persist.dao.ds.info.DataSourceMetaData;
import com.ms.back.util.persist.dao.ds.info.DataSourceProperties;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class DataSourceWrapper {

	// ---------------------------------------------------------------------------------------------------------------------------

	private final String MSG_1 = "\n\n[..] Conectandose a ${url}\n${content}";
	private final String MSG_2 = "${content}\n\n[OK] Conectando a ${url}\n";
	private final String MSG_3 = "\n[ERROR] No conectando a ${url}\n\n";

	// ---------------------------------------------------------------------------------------------------------------------------

	private boolean verbose;
	private Printer printer;
	private DataSource dataSource;
	private DataSourceInfo dataSourceInfo;

	// ---------------------------------------------------------------------------------------------------------------------------

	private synchronized DataSource getDataSource() {
		return dataSource;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public DataSourceWrapper(String dataBaseKey, Printer printer, DataSource dataSource,
			DataSourceProperties properties) throws StartPoolConnectionException, GetMetaDataPoolConnectionException,
			CloseGetMetaDataPoolConnectionException {

		this.verbose = properties.isVerbose();

		this.printer = printer;

		print(MSG_1.replace("${content}", properties.toString()).replace("${url}", properties.getUrl()));

		this.dataSource = dataSource;
		dataSourceInfo = new DataSourceInfo(dataBaseKey, null, properties);

		Connection connection = null;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {

			print(MSG_3.replace("${content}", properties.toString()).replace("${url}", properties.getUrl()));

			throw new StartPoolConnectionException(e, dataSourceInfo.clone());
		} catch (Exception e) {
			throw e;
		} catch (Throwable e) {
			throw e;
		}

		DataSourceMetaData dataSourceMetaData = null;

		try {

			dataSourceMetaData = new DataSourceMetaData();

			dataSourceMetaData.setUrl(connection.getMetaData().getURL());
			dataSourceMetaData.setUserName(connection.getMetaData().getUserName());
			dataSourceMetaData.setDatabaseProductName(connection.getMetaData().getDatabaseProductName());
			dataSourceMetaData.setDatabaseProductVersion(connection.getMetaData().getDatabaseProductVersion());
			dataSourceMetaData.setDriverName(connection.getMetaData().getDriverName());
			dataSourceMetaData.setDriverVersion(connection.getMetaData().getDriverVersion());
			dataSourceMetaData.setjDBCMajorVersion(connection.getMetaData().getJDBCMajorVersion() + "");
			dataSourceMetaData.setjDBCMinorVersion(connection.getMetaData().getJDBCMinorVersion() + "");

		} catch (SQLException e) {

			print(MSG_3.replace("${content}", properties.toString()).replace("${url}", properties.getUrl()));

			throw new GetMetaDataPoolConnectionException(e, dataSourceInfo.clone());

		} catch (Exception e) {

//			e.printStackTrace();

			throw e;

		} finally {

			try {
				if (connection != null && connection.isClosed() == false) {
					connection.close();
				}
			} catch (SQLException e) {

				print(MSG_3.replace("${content}", properties.toString()).replace("${url}", properties.getUrl()));

				throw new CloseGetMetaDataPoolConnectionException(e, dataSourceInfo.clone());

			}
		}

		this.dataSourceInfo.setDataSourceMetaData(dataSourceMetaData);

		print(MSG_2.replace("${content}", dataSourceMetaData.toString()).replace("${url}",
				dataSourceMetaData.getUrl()));

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public synchronized ConnectionWrapper getConnection() throws GetConnectionException {

		Connection connection = null;

		// --------------------------------------------------

		Statement statement = new Statement(dataSourceInfo.getDataBaseKey() + ".openConnection()");
		statement.setDataBase(dataSourceInfo.getDataBaseKey());
		statement.setNumber(1 + "");

		// --------------------------------------------------

		statement.setStartTime(ZonedDateTime.now());
		print(statement.toStringStart());

		try {

			connection = getDataSource().getConnection();

//			printSQLWarning(connection.getWarnings());

		} catch (SQLException e) {

			print(statement.toStringEnd());

			throw new GetConnectionException(e, dataSourceInfo.clone());
		}

		statement.setEndTime(ZonedDateTime.now());
		statement.setExecuted(true);
		statement.setConnection(connection.hashCode() + "");
		statement.setSql(statement.getConnection() + " = " + statement.getSql());

		print(statement.toStringEnd());

		// --------------------------------------------------

		return new ConnectionWrapperImpl(printer.clone(), connection, dataSourceInfo.clone(), statement);

		// --------------------------------------------------

	}

//	public void printSQLWarning(SQLWarning sqlWarning, boolean verbose) {
//
//		String msg = "\n\nSQL WARNING " + ZonedDateTime.now() + "\n\n";
//
//		String msg2 = "";
//
//		while (sqlWarning != null) {
//
//			msg2 += "Warning : " + sqlWarning.getErrorCode() + " Message : " + sqlWarning.getMessage() + " SQL state "
//					+ sqlWarning.getSQLState() + "\n";
//
//			sqlWarning = sqlWarning.getNextWarning();
//		}
//
//		if (msg2 != null && msg2.isEmpty() == false) {
//			msg += msg2;
//		}
//
//		msg += "\n\nEND SQL WARNING " + ZonedDateTime.now() + "\n\n";
//
//		if (verbose && msg2 != null && msg2.isEmpty() == false) {
//			System.out.println(msg);
//		}
//	}

	private void print(String s) {
		if (this.verbose) {
			this.printer.print(s);
		}
	}

} // END CLASS -----------------------------------------------------------------