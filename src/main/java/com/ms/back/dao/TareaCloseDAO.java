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

public class TareaCloseDAO {

	public JsonObject exec(String db, Double horas, String id) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, horas, id);

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

	private JsonObject coreDao(DataBase dataBase, Double horas, String id) throws InsertException, UpdateException,
			DeleteException, DDLException, LoadSQLTemplateDataSourceException, SQLException {

		Statement statement = buildStatement2(id);

		dataBase.update(statement);

		statement = buildStatement(horas, id);

		dataBase.update(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement(Double horas, String id) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(horas);
		statement.addArg(1);
		statement.addArg(id);

		statement.setSql("UPDATE Tarea SET horas = ?, cerrada = ?\nWHERE id = ?");

		// -----------------------------------------------------------------------------

		return statement;

	}

	private Statement buildStatement2(String id) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(id);

		statement.setSql("UPDATE TareaPersona SET sueltaTarea = GETDATE()  WHERE sueltaTarea IS NULL AND tarea = ?");

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