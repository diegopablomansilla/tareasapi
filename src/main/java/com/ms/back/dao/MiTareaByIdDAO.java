package com.ms.back.dao;

import java.io.File;
import java.sql.SQLException;

import javax.json.JsonObject;

import com.ms.back.commons.json.JsonObjectWrapper;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class MiTareaByIdDAO {

	public JsonObject exec(String db, String id, String host, String contentsPath, String contextPath, String servletPath)
			throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase, id, host, contentsPath, contextPath, servletPath);

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

	private JsonObject coreDao(DataBase dataBase, String id, String host, String contentsPath, String contextPath,
			String servletPath) throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		// -----------------------------------------------------------------------------

		Result r = query(dataBase, id);

		Object[][] t = r.getTable();

		if (t.length > 0) {

			int i = 0;

			JsonObjectWrapper json = new JsonObjectWrapper();

			int j = 0;
			if (t[i][j] != null) {
				id = t[i][j].toString();
				json.set("id", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("nombre", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("detalle", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("ordenFabricacion", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("seccion", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("puesto", t[i][j].toString());
			}

			j++;
//			if (t[i][j] != null) {
//				json.set("adjunto", t[i][j].toString());

				// ---------------------------------------------------------------------------

				File folder = new File(contentsPath);
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.getName().equals(id.replace(".", "_") + ".pdf")) {
//						String url = host + contextPath + servletPath + "/Tarea/download?id=" + id;
						String url = host + "/Tarea/download?id=" + id;
						json.set("adjunto", url);
						break;
						// http://localhost:4567/RPC/v1/Tarea/download
					}
				}

				// ---------------------------------------------------------------------------
//			}

			j++;
			if (t[i][j] != null) {
				json.set("fecha", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("ordenFabricacionId", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("seccionId", t[i][j].toString());
			}

			j++;
			if (t[i][j] != null) {
				json.set("puestoId", t[i][j].toString());
			}

//			j++;
//			if (t[i][j] != null) {
//				json.set("comentario", t[i][j].toString());								
//			}

			return json.build();
		}

		return null;

	}

	private Result query(DataBase dataBase, String id)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(id);

		Result r = dataBase.query(statement);

		return r;

	}

	private Statement buildStatement(String id) throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------
		Statement statement = new Statement();
		statement.addArg(id);

		statement.setSql("SELECT *\nFROM\tVMiTarea\nWITH (NOLOCK)\nWHERE id = ?");

		// -----------------------------------------------------------------------------

		return statement;

	}

}