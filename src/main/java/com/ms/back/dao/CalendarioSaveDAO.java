package com.ms.back.dao;

import java.sql.SQLException;
import java.time.LocalTime;

import javax.json.JsonObject;

import com.ms.back.commons.json.JsonObjectWrapper;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.DDLException;
import com.ms.back.util.persist.dao.ds.ex.DeleteException;
import com.ms.back.util.persist.dao.ds.ex.InsertException;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.UpdateException;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class CalendarioSaveDAO {

	public JsonObject exec(String db, LocalTime horaInicio, LocalTime horaCierre, String id) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, horaInicio, horaCierre, id);

			dataBase.commitTransaction();

			return r;

			// ------------------------------------------------------------------------

		} catch (Exception e) {
			e.printStackTrace();
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

	private JsonObject coreDao(DataBase dataBase, LocalTime horaInicio, LocalTime horaCierre, String id)
			throws InsertException, UpdateException, DeleteException, DDLException, LoadSQLTemplateDataSourceException,
			SQLException {

		Statement statement = buildStatement(horaInicio, horaCierre, id);
		dataBase.update(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement(LocalTime horaInicio, LocalTime horaCierre, String id)
			throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(horaInicio);
		statement.addArg(horaCierre);
		statement.addArg(id);

		statement.setSql("UPDATE TareaCalendario SET horaInicio = ?\n\t, horaCierre = ?\n\t\nWHERE id = ?");

		// -----------------------------------------------------------------------------

		return statement;

	}

//	private String getValue(Object value) {
//		if (value == null) {
//			return null;
//		}
//
//		return value.toString().trim();
//
//	}
}