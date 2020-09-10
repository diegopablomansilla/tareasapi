package com.ms.back.dao;

import java.io.File;
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

public class TareaDeleteDAO {

	public JsonObject exec(String db, String id, String pathContent) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, id);

			File file = new File(pathContent + File.separatorChar + id.replace(".", "_") + ".pdf");
			if (file.exists()) {
				if (file.delete() == false) {
					throw new RuntimeException("No se pudo borrar el archivo " + file.getPath());
				}
			}

			dataBase.commitTransaction();

			return r;

			// ------------------------------------------------------------------------

		} catch (

		Exception e) {
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

	private JsonObject coreDao(DataBase dataBase, String id) throws InsertException, UpdateException, DeleteException,
			DDLException, LoadSQLTemplateDataSourceException, SQLException {

		Statement statement = buildStatement2(id);
		dataBase.delete(statement);

		statement = buildStatement(id);
		dataBase.delete(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("id", id);

		return j.build();

	}

	private Statement buildStatement(String id) throws LoadSQLTemplateDataSourceException {

// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(id);

		statement.setSql("DELETE FROM Tarea\nWHERE id = ?");

// -----------------------------------------------------------------------------

		return statement;

	}

	private Statement buildStatement2(String id) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(id);

		statement.setSql("DELETE FROM TareaPersona\nWHERE tarea = ?");

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