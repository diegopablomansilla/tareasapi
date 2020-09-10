package com.ms.back.dao;

import java.sql.SQLException;
import java.time.LocalDate;
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

public class CalendarioCreateDAO {

	public JsonObject exec(String db, LocalDate fecha, LocalTime horaInicio, LocalTime horaCierre) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);
			
			dataBase.beginTransaction();

			JsonObject r = coreDao(dataBase, fecha, horaInicio, horaCierre);
			
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

	private JsonObject coreDao(DataBase dataBase, LocalDate fecha, LocalTime horaInicio, LocalTime horaCierre)
			throws InsertException, UpdateException, DeleteException, DDLException, LoadSQLTemplateDataSourceException,
			SQLException {

		Statement statement = buildStatement(fecha, horaInicio, horaCierre);
		dataBase.insert(statement);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("ok", true);

		return j.build();

	}

	private Statement buildStatement(LocalDate fecha, LocalTime horaInicio, LocalTime horaCierre)
			throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(fecha.toString());
		statement.addArg(fecha);
		statement.addArg(horaInicio);
		statement.addArg(horaCierre);

		statement.setSql("INSERT INTO TareaCalendario (id, fecha, horaInicio, horaCierre) VALUES (?, ?, ?, ?)");

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