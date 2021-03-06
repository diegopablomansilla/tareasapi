package com.ms.back.dao;

import java.sql.SQLException;

import com.ms.back.model.NoPagin;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class PersonaFindAllDAO {

	public NoPagin exec(String db, Integer seccionId, Integer puestoId) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase, seccionId, puestoId);

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

	private NoPagin coreDao(DataBase dataBase, Integer seccionId, Integer puestoId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		// -----------------------------------------------------------------------------

		int cantRows = count(dataBase, seccionId, puestoId);

		NoPagin pagin = new NoPagin(cantRows);

		if (cantRows <= 0) {
			return pagin;
		}

		Result r = query(dataBase, seccionId, puestoId);
		pagin.setItems(r.getTable(), r.getColumnCount());

		return pagin;

	}

	private int count(DataBase dataBase, Integer seccionId, Integer puestoId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(true, seccionId, puestoId);

		Result r = dataBase.query(statement);

		return (int) r.getTable()[0][0];
	}

	private Result query(DataBase dataBase, Integer seccionId, Integer puestoId)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(false, seccionId, puestoId);

		Result r = dataBase.query(statement);

		return r;

	}

	private Statement buildStatement(boolean count, Integer seccionId, Integer puestoId)
			throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(seccionId);

		String where = "";

		if (puestoId != null) {
			where = "AND (puestoId IS NULL OR puestoId = ?)";
			statement.addArg(puestoId);
		}

		if (count) {
			statement.setSql("SELECT COUNT(*) FROM VPersona WHERE seccionId = ? " + where);
		} else {

			statement.setSql("SELECT * FROM	VPersona WHERE seccionId = ? " + where + " ORDER BY apellido, nombre");

		}

		// -----------------------------------------------------------------------------

		return statement;

	}

}