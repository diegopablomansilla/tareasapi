package com.ms.back.services;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.services.AbstractService;
import com.ms.back.dao.CalendarioCreateDAO;

public class CalendarioCreate extends AbstractService {

	private CalendarioCreateDAO dao = new CalendarioCreateDAO();

	public CalendarioCreate(EnvironmentVariables vars) {
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

		String fechaString = null;
		String horaInicioString = null;
		String horaCierreString = null;

		String att = "fecha";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			fechaString = jo.getString(att);
		}

		att = "inicio";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			horaInicioString = jo.getString(att);
		}

		att = "cierre";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			horaCierreString = jo.getString(att);
		}

		// --------------------------

		LocalDate fecha = null;
		LocalTime horaInicio = null;
		LocalTime horaCierre = null;
		
		// ---

		if (fechaString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		fechaString = fechaString.trim();

		if (fechaString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			fecha = LocalDate.parse(fechaString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		// ---

		if (horaInicioString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		horaInicioString = horaInicioString.trim();

		if (horaInicioString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			horaInicio = LocalTime.parse(horaInicioString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		// ---

		if (horaCierreString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		horaCierreString = horaCierreString.trim();

		if (horaCierreString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			horaCierre = LocalTime.parse(horaCierreString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		// ---

		JsonObject item = dao.exec(vars.getDataBases().get(0), fecha, horaInicio, horaCierre);

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
