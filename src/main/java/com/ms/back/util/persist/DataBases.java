package com.ms.back.util.persist;

import java.net.URL;

import com.ms.back.util.persist.dao.ds.DataSources;
import com.ms.back.util.persist.dao.ds.ex.CloseGetMetaDataPoolConnectionException;
import com.ms.back.util.persist.dao.ds.ex.GetConnectionException;
import com.ms.back.util.persist.dao.ds.ex.GetMetaDataPoolConnectionException;
import com.ms.back.util.persist.dao.ds.ex.IllegalStatePersistException;
import com.ms.back.util.persist.dao.ds.ex.LoadPropertiesDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.StartPoolConnectionException;

public class DataBases {

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataSources dataSources;
	private SQLTemplates sqlTemplates;
	private Integer defaultPaginLimit;

	private static DataBases instanceDataBases;

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataBases(Printer printer, boolean verbose, URL dataSourceURL, URL sqlTemplatesURL,
			Integer defaultPaginLimit) {

		this.dataSources = new DataSources(printer, verbose, dataSourceURL);
		this.sqlTemplates = new SQLTemplates(printer, verbose, sqlTemplatesURL);
		this.defaultPaginLimit = defaultPaginLimit;
	}

	public synchronized static void setup(Printer printer, boolean verbose, URL dataSourceURL, URL sqlTemplatesURL,
			Integer defaultPaginLimit) {

		if (printer == null) {
			throw new IllegalArgumentException("printer is required");
		}

		if (dataSourceURL == null) {
			throw new IllegalArgumentException("dataSourceURL is required");
		}

		if (sqlTemplatesURL == null) {
			throw new IllegalArgumentException("sqlTemplatesURL is required");
		}

		if (instanceDataBases == null) {
			instanceDataBases = new DataBases(printer, verbose, dataSourceURL, sqlTemplatesURL, defaultPaginLimit);
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private synchronized DataSources getDataSources() {
		return dataSources;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public synchronized static String getSQLTemplate(String key) throws LoadSQLTemplateDataSourceException {
		return instanceDataBases.sqlTemplates.getTemplate(key);
	}

	public synchronized static URL getSQLTemplateURL() {
		return instanceDataBases.sqlTemplates.getSqlTemplatesURL();
	}

	public synchronized static Integer getDefaultPaginLimit() {
		return instanceDataBases.defaultPaginLimit;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public static synchronized DataBase connectToDataBase(String key) throws GetConnectionException,
			IllegalStatePersistException, StartPoolConnectionException, GetMetaDataPoolConnectionException,
			CloseGetMetaDataPoolConnectionException, LoadPropertiesDataSourceException {

		if (instanceDataBases == null) {
			throw new IllegalStatePersistException("instanceDataBases is null.");
		}

		if (instanceDataBases.getDataSources() == null) {
			throw new IllegalStatePersistException("DataSources is null.");
		}

		instanceDataBases.getDataSources().getAndInit(key.trim());

		return new DataBase(instanceDataBases.getDataSources().getAndInit(key.trim()).getConnection());
	}

} // END CLASS -----------------------------------------------------------------