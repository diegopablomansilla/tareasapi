package com.ms.back.services;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.services.AbstractService;
import com.ms.back.dao.TareaPersonaFindAllDAO;
import com.ms.back.model.NoPagin;

public class TareaPersonaFindAll extends AbstractService {

	private TareaPersonaFindAllDAO dao = new TareaPersonaFindAllDAO();

	public TareaPersonaFindAll(EnvironmentVariables vars) {
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

		String tarea = request.getParameter("tarea");

		if (tarea == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		tarea = tarea.trim();

		if (tarea.length() == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		NoPagin items = dao.exec(vars.getDataBases().get(0), tarea);

		if (items.getCantRows() == 0) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		// -------------------------------------------------------------------------------------

//		Object[][] table = items.getThisPageItems();
//		for (Object[] row : table) {
//
////							row[8] = vars.getApiHome() + "/RPC/" + vars.getApiVersion() + "/Tarea/download";
//
//			File folder = new File(vars.getContents().getPath());
//			File[] files = folder.listFiles();
//			for (File file : files) {
//				if (file.getName().equals(row[0].toString().replace(".", "_") + ".pdf")) {
//					row[8] = vars.getHost() + "/Tarea/download?id=" + row[0];
//					// http://localhost:4567/RPC/v1/Tarea/download
//				}
//			}
//		}

		// -------------------------------------------------------------------------------------

		response.setContentType(CONTENT_TYPE_JSON);

		String res = items.toJson().toString();

		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();

		response.setStatus(HttpServletResponse.SC_OK);

	}

}
