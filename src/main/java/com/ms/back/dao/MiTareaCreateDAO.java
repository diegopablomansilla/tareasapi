package com.ms.back.dao;

import java.sql.SQLException;

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

public class MiTareaCreateDAO {

	public JsonObject exec(String db, String tareaId, Integer personaId) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, tareaId, personaId);

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

	private JsonObject coreDao(DataBase dataBase, String tareaId, Integer personaId) throws InsertException,
			UpdateException, DeleteException, DDLException, LoadSQLTemplateDataSourceException, SQLException {

		Statement statement = buildStatement1(personaId);

		dataBase.update(statement);

		statement = buildStatement(tareaId, personaId);

		dataBase.insert(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement1(Integer personaId) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(personaId);

		statement.setSql("UPDATE TareaPersona SET sueltaTarea = GETDATE() WHERE sueltaTarea IS NULL AND persona = ?");

		// -----------------------------------------------------------------------------

		return statement;

	}

	private Statement buildStatement(String tareaId, Integer personaId) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(tareaId);
		statement.addArg(personaId);

		statement.setSql("INSERT INTO TareaPersona (tomaTarea, tarea, persona) VALUES (GETDATE(), ?, ?)");

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