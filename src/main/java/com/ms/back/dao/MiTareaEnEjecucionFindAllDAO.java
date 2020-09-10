package com.ms.back.dao;

import java.sql.SQLException;

import com.ms.back.model.NoPagin;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class MiTareaEnEjecucionFindAllDAO {

	public NoPagin exec(String db, Integer personaId) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase, personaId);

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

	private NoPagin coreDao(DataBase dataBase, Integer personaId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		// -----------------------------------------------------------------------------

		int cantRows = count(dataBase, personaId);

		NoPagin pagin = new NoPagin(cantRows);

		if (cantRows <= 0) {
			return pagin;
		}

		Result r = query(dataBase, personaId);
		pagin.setItems(r.getTable(), r.getColumnCount());

		return pagin;

	}

	private int count(DataBase dataBase, Integer personaId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(true, personaId);

		Result r = dataBase.query(statement);

		return (int) r.getTable()[0][0];
	}

	private Result query(DataBase dataBase, Integer personaId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(false, personaId);

		Result r = dataBase.query(statement);

		for (Object[] row : r.getTable()) {
			row[row.length - 1] = row[row.length - 1] != null && row[row.length - 1].equals("true");
		}

		return r;

	}

	private Statement buildStatement(boolean count, Integer personaId) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(personaId);

		if (count) {
			statement.setSql(
					"SELECT\tCOUNT(*)\nFROM\tVTareaPersona\nWITH (NOLOCK)\nWHERE personaId = ? AND sueltaTarea IS NULL");

		} else {
			statement.setSql("SELECT *\nFROM\tVTareaPersona\nWITH (NOLOCK)\nWHERE personaId = ? AND sueltaTarea IS NULL"
					+ "\nORDER BY sueltaTarea DESC, tomaTarea DESC, apellido, nombre");

		}

		// -----------------------------------------------------------------------------

		return statement;

	}

}