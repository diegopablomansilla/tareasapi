package com.ms.back.dao;

import java.sql.SQLException;

import com.ms.back.model.NoPagin;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class SeccionFindAllDAO {

	public NoPagin exec(String db) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase);

			// ------------------------------------------------------------------------

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (dataBase != null) {
					dataBase.disconnect();
				}
			} catch (Exception e) {
				throw e;
			}
		}

	}

	private NoPagin coreDao(DataBase dataBase)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		// -----------------------------------------------------------------------------

		int cantRows = count(dataBase);

		NoPagin pagin = new NoPagin(cantRows);

		if (cantRows <= 0) {
			return pagin;
		}

		Result r = query(dataBase);
		pagin.setItems(r.getTable(), r.getColumnCount());

		return pagin;

	}

	private int count(DataBase dataBase) throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(true);

		Result r = dataBase.query(statement);

		return (int) r.getTable()[0][0];
	}

	private Result query(DataBase dataBase) throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(false);

		Result r = dataBase.query(statement);

		return r;

	}

	private Statement buildStatement(boolean count) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		if (count) {

			statement.setSql("SELECT\tCOUNT(*)\nFROM\tSecciones\nWITH (NOLOCK)\n");

		} else {

			statement.setSql("SELECT SECCION, NOMBRE\nFROM\tSecciones\nWITH (NOLOCK) ORDER BY SECCION");

		}

		// -----------------------------------------------------------------------------

		return statement;

	}

}