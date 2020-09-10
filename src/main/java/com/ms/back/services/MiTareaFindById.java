package com.ms.back.services;

import java.io.File;
import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.services.AbstractService;
import com.ms.back.dao.MiTareaByIdDAO;

public class MiTareaFindById extends AbstractService {

	private MiTareaByIdDAO dao = new MiTareaByIdDAO();

	public MiTareaFindById(EnvironmentVariables vars) {
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

		String id = request.getParameter("id");

		if (id == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		id = id.trim();

		if (id.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		JsonObject item = dao.exec(vars.getDataBases().get(0), id, vars.getHost(), vars.getContents().getPath(),
				request.getContextPath(), request.getServletPath());

		if (item == null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		response.setContentType(CONTENT_TYPE_JSON);

		String res = item.toString();

		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();

		response.setStatus(HttpServletResponse.SC_OK);

	}

}
