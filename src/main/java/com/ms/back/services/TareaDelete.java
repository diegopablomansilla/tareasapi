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
import com.ms.back.dao.TareaDeleteDAO;

public class TareaDelete extends AbstractService {

	private TareaDeleteDAO dao = new TareaDeleteDAO();

	public TareaDelete(EnvironmentVariables vars) {
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

//		String user = request.getParameter("user");
//		String pass = request.getParameter("pass");
		String id = null;

		String att = "id";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			id = jo.getString(att);
		}

		if (id == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		id = id.trim();

		if (id.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		JsonObject item = dao.exec(vars.getDataBases().get(0), id, vars.getContents().getPath());

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
