package com.ms.back.services;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.services.AbstractService;
import com.ms.back.dao.TareaPersonaCreateDAO;

public class TareaPersonaCreate extends AbstractService {

	private TareaPersonaCreateDAO dao = new TareaPersonaCreateDAO();

	public TareaPersonaCreate(EnvironmentVariables vars) {
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

		String tomaString = null;
		String sueltaString = null;
		String tareaIdString = null;
		String personaIdString = null;

		String att = "toma";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			tomaString = jo.getString(att);
		}

		att = "suelta";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			sueltaString = jo.getString(att);
		}

		att = "tarea";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			tareaIdString = jo.getString(att);
		}

		att = "persona";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			personaIdString = jo.getString(att);
		}

		// --------------------------

		LocalDateTime toma = null;
		LocalDateTime suelta = null;
		String tareaId = null;
		String personaId = null;

		// ---

		if (tomaString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		tomaString = tomaString.trim();

		if (tomaString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			toma = LocalDateTime.parse(tomaString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		// ---

		if (sueltaString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		sueltaString = sueltaString.trim();

		if (sueltaString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			suelta = LocalDateTime.parse(sueltaString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

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

		personaId = personaIdString;

		// ---

		JsonObject item = dao.exec(vars.getDataBases().get(0), toma, suelta, tareaId, personaId);

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
