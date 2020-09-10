package com.ms.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.commons.services.AbstractService;
import com.ms.back.commons.services.ServicesFactory;

public class TareasAPI extends HttpServlet {

	private ServicesFactory servicesFactory;

	public TareasAPI() {
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++ CONSTRUCTOR");
		servicesFactory = new ServicesFactory();
		
//		System.out.println("this.getServletInfo() " + this.getServletInfo() );
//		System.out.println("this.getServletName() " + this.getServletName() );
//		System.out.println("this.getServletConfig() " + this.getServletConfig() );
//		System.out.println("this.getServletContext() " + this.getServletContext() );
//		System.out.println("this.getServletContext().getServletContextName() " + this.getServletContext().getServletContextName() );
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String r = "GET";
//		r += "\n" + "Protocol = " + request.getProtocol();
//		r += "\n" + "PathInfo = " + request.getPathInfo();
//		r += "\n" + "ContextPath = " + request.getContextPath();
//		r += "\n" + "ServletPath = " + request.getServletPath();
//		r += "\n" + "HeaderNames = " + Collections.list(request.getHeaderNames());
//		r += "\n" + "AttributeNames = " + Collections.list(request.getAttributeNames());
//		r += "\n" + "ParameterNames = " + Collections.list(request.getParameterNames());
//		System.out.println(r);

		try {
			AbstractService service = servicesFactory.buildService(request.getPathInfo(), "GET");
			if (service == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			service.execute(request, response);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();

			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String r = "POST";
//		r += "\n" + "Protocol = " + request.getProtocol();
//		r += "\n" + "PathInfo = " + request.getPathInfo();
//		r += "\n" + "ContextPath = " + request.getContextPath();
//		r += "\n" + "ServletPath = " + request.getServletPath();
//		r += "\n" + "HeaderNames = " + Collections.list(request.getHeaderNames());
//		r += "\n" + "AttributeNames = " + Collections.list(request.getAttributeNames());
//		r += "\n" + "ParameterNames = " + Collections.list(request.getParameterNames());
//		System.out.println(r);
		
		try {
			AbstractService service = servicesFactory.buildService(request.getPathInfo(), "POST");
			if (service == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			service.execute(request, response);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();

			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

	}

	private static final long serialVersionUID = -4751096228274971485L;

}