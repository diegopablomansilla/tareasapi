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

public class TareaCreateDAO {

	public JsonObject exec(String db, String nombre, String detalle, Integer seccion, Integer puesto,
			Integer ordenFabricacion) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, nombre, detalle, seccion, puesto, ordenFabricacion);

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
			Integer ordenFabricacion) throws InsertException, UpdateException, DeleteException, DDLException,
			LoadSQLTemplateDataSourceException, SQLException {

		Statement statement = buildStatement(nombre, detalle, seccion, puesto, ordenFabricacion);
		dataBase.insert(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement(String nombre, String detalle, Integer seccion, Integer puesto,
			Integer ordenFabricacion) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(nombre);
		statement.addArg(detalle == null ? String.class : detalle);
		statement.addArg(ordenFabricacion == null ? Integer.class : ordenFabricacion);
		statement.addArg(seccion);
		statement.addArg(puesto == null ? Integer.class : puesto);
		statement.addArg(0);

		String id = "(SELECT CONCAT(COUNT(*) + 1, '.', MONTH(GETDATE()), '.', YEAR(GETDATE()))  FROM Tarea WITH (NOLOCK) WHERE MONTH (fecha) = MONTH(GETDATE()) AND YEAR(fecha) =  YEAR(GETDATE()))";

		statement.setSql("INSERT INTO Tarea (id, nombre, detalle, ordenFabricacion, seccion, puesto, cerrada, fecha) "
				+ "VALUES (" + id + ", ?, ?, ?, ?, ?, ?, GETDATE())");

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