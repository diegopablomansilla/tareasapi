package com.ms.back.services;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.services.AbstractService;
import com.ms.back.dao.MiTareaCreateDAO;

public class MiTareaCreate extends AbstractService {

	private MiTareaCreateDAO dao = new MiTareaCreateDAO();

	public MiTareaCreate(EnvironmentVariables vars) {
		super(vars);

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		String db = request.getHeader("db");
//
//		if (db == null) {
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		String body = buffer.toString();

		JsonObject jo = Json.createReader(new StringReader(body)).readObject();

		if (jo == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		if (jo.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		String tareaIdString = null;
		String personaIdString = null;

		String att = "tarea";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			tareaIdString = jo.getString(att);
		}

		att = "persona";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			personaIdString = jo.getString(att);
		}

		// --------------------------

		String tareaId = null;
		Integer personaId = null;

		// ---

		if (tareaIdString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		tareaIdString = tareaIdString.trim();

		if (tareaIdString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		tareaId = tareaIdString;

		// ---

		if (personaIdString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		personaIdString = personaIdString.trim();

		if (personaIdString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			personaId = Integer.parseInt(personaIdString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		// ---

		JsonObject item = dao.exec(vars.getDataBases().get(0), tareaId, personaId);

		if (item == null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		response.setContentType(CONTENT_TYPE_JSON);

		response.setStatus(HttpServletResponse.SC_CREATED);

		String res = item.toString();

		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();

	}

}
