package com.ms.back.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

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

public class TareaPersonaCreateDAO {

	public JsonObject exec(String db, LocalDateTime toma, LocalDateTime suelta, String tareaId, String personaId)
			throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, toma, suelta, tareaId, personaId);

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

	private JsonObject coreDao(DataBase dataBase, LocalDateTime toma, LocalDateTime suelta, String tareaId,
			String personaId) throws InsertException, UpdateException, DeleteException, DDLException,
			LoadSQLTemplateDataSourceException, SQLException {

		Statement statement = buildStatement(toma, suelta, tareaId, personaId);
		dataBase.insert(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement(LocalDateTime toma, LocalDateTime suelta, String tareaId, String personaId)
			throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(toma);
		statement.addArg(suelta);
		statement.addArg(tareaId);
		statement.addArg(personaId);

		statement.setSql("INSERT TareaPersona VALUES (?, ?, 'Tarea asignada manualmente', ?, ? )");

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