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
import com.ms.back.dao.TareaCreateDAO;

public class TareaCreate extends AbstractService {

	private TareaCreateDAO dao = new TareaCreateDAO();

	public TareaCreate(EnvironmentVariables vars) {
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

		String nombreString = null;
		String detalleString = null;
		String seccionString = null;
		String puestoString = null;
		String ordenFabricacionString = null;

		String att = "nombre";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			nombreString = jo.getString(att);
		}

		att = "detalle";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			detalleString = jo.getString(att);
		}

		att = "seccion";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			seccionString = jo.getString(att);
		}

		att = "puesto";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			puestoString = jo.getString(att);
		}

		att = "orden";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			ordenFabricacionString = jo.getString(att);
		}

		// --------------------------

		String nombre = nombreString;
		String detalle = detalleString;
		Integer seccion = null;
		Integer puesto = null;
		Integer ordenFabricacion = null;

		if (nombre == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		nombre = nombre.trim();

		if (nombre.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (seccionString == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		seccionString = seccionString.trim();

		if (seccionString.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			seccion = Integer.parseInt(seccionString);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		if (puestoString != null) {
			try {
				puesto = Integer.parseInt(puestoString);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		}

		if (ordenFabricacionString != null) {
			try {
				ordenFabricacion = Integer.parseInt(ordenFabricacionString);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		}

		JsonObject item = dao.exec(vars.getDataBases().get(0), nombre, detalle, seccion, puesto, ordenFabricacion);

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
