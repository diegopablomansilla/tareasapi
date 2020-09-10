package com.ms.back.commons.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ms.back.EnvironmentVariables;

public abstract class AbstractService {

	public static final String CONTENT_TYPE_JSON = "application/json";

	protected EnvironmentVariables vars;

	public AbstractService(EnvironmentVariables vars) {
		super();
		this.vars = vars;
	}

	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
