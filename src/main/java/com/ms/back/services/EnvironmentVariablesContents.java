package com.ms.back.services;

import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.json.JsonObjectWrapper;
import com.ms.back.commons.services.AbstractService;

public class EnvironmentVariablesContents extends AbstractService {

	public EnvironmentVariablesContents(EnvironmentVariables vars) {
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

		if (vars.getContents() == null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		response.setContentType(CONTENT_TYPE_JSON);

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("id", vars.getContents().toString());

		JsonObject jsonObject = j.build();

		String res = jsonObject.toString();

		response.setStatus(HttpServletResponse.SC_OK);

		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();

	}

}
