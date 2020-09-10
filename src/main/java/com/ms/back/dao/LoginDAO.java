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
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.ex.UpdateException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class LoginDAO {

	public JsonObject exec(String db, String user, String pass) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase, user, pass);

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

	private JsonObject coreDao(DataBase dataBase, String user, String pass) throws InsertException, UpdateException,
			DeleteException, DDLException, LoadSQLTemplateDataSourceException, SQLException, SelectException {

		Statement statement = buildStatement(user, pass);

		Result r = dataBase.query(statement);

		Object[][] t = r.getTable();

		if (t.length > 0) {

			int i = 0;

			JsonObjectWrapper json = new JsonObjectWrapper();

			int j = 0;
			json.set("id", getValue(t[i][j]));
			j++;
			json.set("nombre", getValue(t[i][j]));
			j++;
			json.set("apellido", getValue(t[i][j]));
			j++;
			json.set("seccionId", getValue(t[i][j]));
			j++;
			json.set("puestoId", getValue(t[i][j]));
			j++;
			json.set("rol", getValue(t[i][j]));

			return json.build();
		}

		return null;

	}

	private Statement buildStatement(String user, String pass) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		statement.addArg(user);
		statement.addArg(pass);

		statement.setSql(
				"SELECT PERSONAL, NOMBRE, APELLIDO, SECCION, PUESTO, CASE WHEN TareaPersonaAdmin.persona IS NOT NULL THEN 'A' ELSE 'O' END AS ROL "
						+ "\nFROM PERSONAL LEFT JOIN TareaPersonaAdmin ON PERSONAL.PERSONAL = TareaPersonaAdmin.persona \nWHERE CAST(PERSONAL AS VARCHAR) = ? \nAND SUBSTRING(CAST(CAST(CUIL AS bigint) AS VARCHAR), LEN(CAST(CAST(CUIL AS bigint) AS VARCHAR)) - 3 , LEN(CAST(CAST(CUIL AS bigint) AS VARCHAR))) = ? ");

		// -----------------------------------------------------------------------------

		return statement;

	}

	private String getValue(Object value) {
		if (value == null) {
			return null;
		}

		return value.toString().trim();

	}
}