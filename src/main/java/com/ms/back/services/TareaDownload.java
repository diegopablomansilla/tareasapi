package com.ms.back.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;
import com.ms.back.commons.services.AbstractService;

public class TareaDownload extends AbstractService {

	public TareaDownload(EnvironmentVariables vars) {
		super(vars);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		String fileName = vars.getContents().getFile() + File.separatorChar + id.replace(".", "_") + ".pdf";
		String fileType = "application/pdf";
		// Find this file id in database to get file name, and file type

		// You must tell the browser the file type you are going to send
		// for example application/pdf, text/plain, text/html, image/jpg
		response.setContentType(fileType);

		// Make sure to show the download dialog
		response.setHeader("Content-disposition", "attachment; filename=file.pdf;");

		// Assume file name is retrieved from database
		// For example D:\\file\\test.pdf

		File my_file = new File(fileName);

		if (my_file.exists()) {

			response.setStatus(HttpServletResponse.SC_OK);

			// This should send the file to browser
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(my_file);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();

		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

}
