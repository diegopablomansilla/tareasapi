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

public class TareaSaveDAO {

	public JsonObject exec(String db, String nombre, String detalle, Integer seccion, Integer puesto,
			Integer ordenFabricacion, String id) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, nombre, detalle, seccion, puesto, ordenFabricacion, id);

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

	private JsonObject coreDao(DataBase dataBase, String nombre, String detalle, Integer seccion, Integer puesto,
			Integer ordenFabricacion, String id) throws InsertException, UpdateException, DeleteException, DDLException,
			LoadSQLTemplateDataSourceException, SQLException {

		Statement statement = buildStatement(nombre, detalle, seccion, puesto, ordenFabricacion, id);
		dataBase.update(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement(String nombre, String detalle, Integer seccion, Integer puesto,
			Integer ordenFabricacion, String id) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();
		statement.addArg(nombre);
		statement.addArg(detalle == null ? String.class : detalle);
		statement.addArg(seccion);
		statement.addArg(puesto == null ? Integer.class : puesto);
		statement.addArg(ordenFabricacion == null ? Integer.class : ordenFabricacion);
		statement.addArg(id);

		statement.setSql(
				"UPDATE Tarea SET nombre = ?\n\t, detalle = ?\n\t, seccion = ?\n\t, puesto = ?\n\t, ordenFabricacion = ?\nWHERE id = ?");

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