package com.ms.back.dao;

import java.sql.SQLException;

import com.ms.back.model.NoPagin;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class TareaPersonaFindAllDAO {

	public NoPagin exec(String db, String tareaId) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase, tareaId);

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

	private NoPagin coreDao(DataBase dataBase, String tareaId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		// -----------------------------------------------------------------------------

		int cantRows = count(dataBase, tareaId);

		NoPagin pagin = new NoPagin(cantRows);

		if (cantRows <= 0) {
			return pagin;
		}

		Result r = query(dataBase, tareaId);
		pagin.setItems(r.getTable(), r.getColumnCount());

		return pagin;

	}

	private int count(DataBase dataBase, String tareaId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(true, tareaId);

		Result r = dataBase.query(statement);

		return (int) r.getTable()[0][0];
	}

	private Result query(DataBase dataBase, String tareaId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(false, tareaId);

		Result r = dataBase.query(statement);

		return r;

	}

	private Statement buildStatement(boolean count, String tareaId) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(tareaId);

		if (count) {
			statement.setSql("SELECT\tCOUNT(*)\nFROM\tVTareaPersona\nWITH (NOLOCK)\nWHERE tareaId = ?");
		} else {
			statement.setSql(
					"SELECT *\nFROM\tVTareaPersona\nWITH (NOLOCK)\nWHERE tareaId = ?\nORDER BY sueltaTarea DESC, tomaTarea DESC, apellido, nombre");
		}
		// -----------------------------------------------------------------------------

		return statement;

	}

}