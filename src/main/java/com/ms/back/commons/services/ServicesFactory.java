package com.ms.back.commons.services;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

import com.ms.back.EnvironmentVariables;
import com.ms.back.PrinterImpl;
import com.ms.back.services.CalendarioCreate;
import com.ms.back.services.CalendarioDelete;
import com.ms.back.services.CalendarioFindAll;
import com.ms.back.services.CalendarioSave;
import com.ms.back.services.EnvironmentVariablesContents;
import com.ms.back.services.Login;
import com.ms.back.services.MiTareaCreate;
import com.ms.back.services.MiTareaEnEjecucionFindAll;
import com.ms.back.services.MiTareaFindAll;
import com.ms.back.services.MiTareaFindById;
import com.ms.back.services.MiTareaSave;
import com.ms.back.services.OrdenFabricacionFindAll;
import com.ms.back.services.PersonaFindAll;
import com.ms.back.services.PuestoFindAll;
import com.ms.back.services.SeccionFindAll;
import com.ms.back.services.TareaClose;
import com.ms.back.services.TareaCreate;
import com.ms.back.services.TareaDelete;
import com.ms.back.services.TareaDownload;
import com.ms.back.services.TareaFindAll;
import com.ms.back.services.TareaPersonaCreate;
import com.ms.back.services.TareaPersonaFindAll;
import com.ms.back.services.TareaSave;
import com.ms.back.util.persist.DataBases;

public class ServicesFactory {

	private EnvironmentVariables vars;

	public ServicesFactory() {
		super();

		// "file:D:/dev/ambientes/eti/workspace/ms_back_business/files/db";

		try {

			String TAREAS_API_FILES = System.getenv().get("TAREAS_API_FILES");

			if (TAREAS_API_FILES == null || TAREAS_API_FILES.trim().length() == 0) {
				TAREAS_API_FILES = "C:/tareasapi";

				System.out.println(
						"[WARNING] No se encontro la variable de entorno TAREAS_API_FILES. Se usa como valor por defecto "
								+ TAREAS_API_FILES);
			}

			FileReader reader = new FileReader(TAREAS_API_FILES + File.separatorChar + "tareasapi.properties");

			Properties p = new Properties();
			p.load(reader);

			vars = new EnvironmentVariables();

			vars.setHost(p.getProperty("api.host"));
			vars.setVerbose(Boolean.parseBoolean(p.getProperty("db.verbose")));
			vars.setDefaultPaginLimit(Integer.parseInt(p.getProperty("db.defaultPaginLimit")));
			vars.addDataBase(p.getProperty("db.default"));

			vars.setDataSourceURL(new URL("file:" + TAREAS_API_FILES + "/db/ds"));
			vars.setPpURL(new URL("file:" + TAREAS_API_FILES + "/db/pp"));
			vars.setSqlTemplatesURL(new URL("file:" + TAREAS_API_FILES + "/db/sql"));
			vars.setContents(new URL("file:" + TAREAS_API_FILES + "/db/contents"));

			DataBases.setup(new PrinterImpl(), vars.isVerbose(), vars.getDataSourceURL(), vars.getSqlTemplatesURL(),
					vars.getDefaultPaginLimit());

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public AbstractService buildService(String path, String v) {

		if (path == null) {
			return null;
		}

		path = path.trim();

		if (v.equalsIgnoreCase("GET")) {

			if ("/EnvironmentVariables/contents".equals(path)) {
				return new EnvironmentVariablesContents(vars);
			}

			if ("/Tarea/download".equals(path)) {
				return new TareaDownload(vars);
			}

			if ("/Seccion/findAll".equals(path)) {
				return new SeccionFindAll(vars);
			}

			if ("/Puesto/findAll".equals(path)) {
				return new PuestoFindAll(vars);
			}

			if ("/Tarea/findAll".equals(path)) {
				return new TareaFindAll(vars);
			}

			if ("/OrdenFabricacion/findAll".equals(path)) {
				return new OrdenFabricacionFindAll(vars);
			}

			if ("/TareaPersona/findAll".equals(path)) {
				return new TareaPersonaFindAll(vars);
			}

			if ("/MiTarea/findAll".equals(path)) {
				return new MiTareaFindAll(vars);
			}

			if ("/MiTareaEnEjecucion/findAll".equals(path)) {
				return new MiTareaEnEjecucionFindAll(vars);
			}

			if ("/Persona/findAll".equals(path)) {
				return new PersonaFindAll(vars);
			}

			if ("/Calendario/findAll".equals(path)) {
				return new CalendarioFindAll(vars);
			}

			if ("/MiTarea/findById".equals(path)) {
				return new MiTareaFindById(vars);
			}

		}

		if (v.equalsIgnoreCase("POST")) {

			if ("/login".equals(path)) {
				return new Login(vars);
			}

			if ("/Tarea/delete".equals(path)) {
				return new TareaDelete(vars);
			}

			if ("/Tarea/create".equals(path)) {
				return new TareaCreate(vars);
			}

			if ("/Tarea/save".equals(path)) {
				return new TareaSave(vars);
			}

			if ("/Tarea/close".equals(path)) {
				return new TareaClose(vars);
			}

			if ("/Calendario/create".equals(path)) {
				return new CalendarioCreate(vars);
			}

			if ("/Calendario/save".equals(path)) {
				return new CalendarioSave(vars);
			}

			if ("/Calendario/delete".equals(path)) {
				return new CalendarioDelete(vars);
			}

			if ("/TareaPersona/create".equals(path)) {
				return new TareaPersonaCreate(vars);
			}

			if ("/MiTarea/create".equals(path)) {
				return new MiTareaCreate(vars);
			}

			if ("/MiTarea/save".equals(path)) {
				return new MiTareaSave(vars);
			}
		}

		return null;
	}

}
