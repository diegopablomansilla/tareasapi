package com.ms.back;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentVariables {

	public static final int LIMIT_PAGIN_DEFAULT = 20;

	private String host;

	private boolean verbose = true;
	private URL sqlTemplatesURL;
	private URL dataSourceURL;
	private URL ppURL;
	private List<String> dataBases = new ArrayList<String>();
	private Integer defaultPaginLimit = LIMIT_PAGIN_DEFAULT;
	private URL contents;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host.trim();
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public URL getSqlTemplatesURL() {
		return sqlTemplatesURL;
	}

	public void setSqlTemplatesURL(URL sqlTemplatesURL) {
		this.sqlTemplatesURL = sqlTemplatesURL;
	}

	public URL getDataSourceURL() {
		return dataSourceURL;
	}

	public void setDataSourceURL(URL dataSourceURL) {
		this.dataSourceURL = dataSourceURL;
	}

	public URL getPpURL() {
		return ppURL;
	}

	public void setPpURL(URL ppURL) {
		this.ppURL = ppURL;
	}

	public List<String> getDataBases() {
		return dataBases;
	}

	public boolean containsDataBase(String dataBaseKey) {

		if (dataBaseKey == null) {
			return false;
		}

		dataBaseKey = dataBaseKey.trim();

		for (String dataBase : dataBases) {
			if (dataBase.equals(dataBaseKey)) {
				return true;
			}
		}

		return false;
	}

	public boolean addDataBase(String dataBaseKey) {

		if (dataBaseKey == null) {
			return false;
		}

		dataBaseKey = dataBaseKey.trim();

		return dataBases.add(dataBaseKey);
	}

	public Integer getDefaultPaginLimit() {
		this.defaultPaginLimit = defaultPaginLimit == null ? LIMIT_PAGIN_DEFAULT : defaultPaginLimit;
		return defaultPaginLimit;
	}

	public void setDefaultPaginLimit(Integer defaultPaginLimit) {
		this.defaultPaginLimit = defaultPaginLimit == null ? LIMIT_PAGIN_DEFAULT : defaultPaginLimit;
	}

	public URL getContents() {
		return contents;
	}

	public void setContents(URL contents) {
		this.contents = contents;
	}

}
