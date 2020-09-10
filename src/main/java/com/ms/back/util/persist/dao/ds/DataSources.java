package com.ms.back.util.persist.dao.ds;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.ms.back.util.persist.Printer;
import com.ms.back.util.persist.dao.ds.ex.CloseGetMetaDataPoolConnectionException;
import com.ms.back.util.persist.dao.ds.ex.GetMetaDataPoolConnectionException;
import com.ms.back.util.persist.dao.ds.ex.LoadPropertiesDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.StartPoolConnectionException;
import com.ms.back.util.persist.dao.ds.info.DataSourceProperties;

public class DataSources {

	// ---------------------------------------------------------------------------------------------------------------------------

	private final String MSG_1 = "\n[..] Leyendo archivo de propiedades ${path}";
	private final String MSG_2 = "${content}\n[OK] Lectura de archivo de propiedades ${path}";
	private final String MSG_3 = "\n[ERROR] Lectura de archivo de propiedades ${path}";

	// ---------------------------------------------------------------------------------------------------------------------------

	private boolean verbose;
	private URL dataSourceURL;
	private Printer printer;
	private Map<String, DataSourceWrapper> dataSourceMap = new HashMap<String, DataSourceWrapper>();

	// ---------------------------------------------------------------------------------------------------------------------------

//	public DataSources(Printer printer, boolean verbose, URL dataSourceURL) {
//		this.printer = printer;
//		this.verbose = verbose;
//		this.dataSourceURL = dataSourceURL;
//	}

	public DataSources(Printer printer, boolean verbose, URL dataSourceURL) {
		this.printer = printer;
		this.verbose = verbose;
		this.dataSourceURL = dataSourceURL;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public synchronized DataSourceWrapper get(String key) {

		return this.getDataSource(key);
	}

	public synchronized DataSourceWrapper getAndInit(String key)
			throws StartPoolConnectionException, GetMetaDataPoolConnectionException,
			CloseGetMetaDataPoolConnectionException, LoadPropertiesDataSourceException {

		return this.getAndInitDataSource(key);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private synchronized DataSourceWrapper getAndInitDataSource(String key)
			throws StartPoolConnectionException, GetMetaDataPoolConnectionException,
			CloseGetMetaDataPoolConnectionException, LoadPropertiesDataSourceException {
		
		

		if (dataSourceMap.containsKey(key) == false) {			
			dataSourceMap.put(key, buildDataSource(key));
		}

		return dataSourceMap.get(key);
	}

	private synchronized DataSourceWrapper getDataSource(String key) {
		return dataSourceMap.get(key);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private DataSourceWrapper buildDataSource(String key) throws LoadPropertiesDataSourceException,
			StartPoolConnectionException, GetMetaDataPoolConnectionException, CloseGetMetaDataPoolConnectionException {

		// -------------------------------------------------------------------

		Properties properties = loadProperties(key);

		// -------------------------------------------------------------------

		String path = "jdbc.";

		DataSourceProperties dataSourceProperties = new DataSourceProperties();

		dataSourceProperties.setDriverClassName(properties.getProperty(path + "driverClassName"));
		dataSourceProperties.setUrl(properties.getProperty(path + "url"));
		dataSourceProperties.setUserName(properties.getProperty(path + "userName"));
		dataSourceProperties.setUserPassword(properties.getProperty(path + "userPassword"));
		dataSourceProperties.setInitialSize(Integer.parseInt(properties.getProperty(path + "initialSize")));
		dataSourceProperties.setMaxActive(Integer.parseInt(properties.getProperty(path + "maxActive")));
		dataSourceProperties.setMaxIdle((Integer.parseInt(properties.getProperty(path + "maxIdle"))));
		dataSourceProperties.setValidationQuery(properties.getProperty(path + "validationQuery"));
		dataSourceProperties.setVerbose((Boolean.parseBoolean(properties.getProperty(path + "verbose"))));
		dataSourceProperties.setSchema(properties.getProperty(path + "schema"));
		dataSourceProperties.setConvertToJavaTimeValues(
				(Boolean.parseBoolean(properties.getProperty(path + "convertToJavaTimeValues"))));

		dataSourceProperties.setServerName(properties.getProperty(path + "serverName"));
		dataSourceProperties.setPortNumber(Integer.parseInt(properties.getProperty(path + "portNumber")));
		dataSourceProperties.setDatabaseName(properties.getProperty(path + "databaseName"));

		// -------------------------------------------------------------------

		DataSource ds = null;

		if (dataSourceProperties.getDriverClassName().equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {

			SQLServerDataSource dsMS = new SQLServerDataSource();
			// ds.setIntegratedSecurity(true);
			dsMS.setServerName(properties.getProperty(path + "serverName"));
			dsMS.setPortNumber(Integer.parseInt(properties.getProperty(path + "portNumber")));
			dsMS.setDatabaseName(properties.getProperty(path + "databaseName"));
			dsMS.setUser(dataSourceProperties.getUserName());
			dsMS.setPassword(dataSourceProperties.getUserPassword());

			ds = dsMS;

		} else if (dataSourceProperties.getDriverClassName().equals("org.postgresql.Driver")) {

			BasicDataSource dsPG = new BasicDataSource();
			dsPG.setDriverClassName(dataSourceProperties.getDriverClassName());
			dsPG.setUrl(dataSourceProperties.getUrl());
			dsPG.setUsername(dataSourceProperties.getUserName());
			dsPG.setPassword(dataSourceProperties.getUserPassword());
			dsPG.setInitialSize(dataSourceProperties.getInitialSize());
			dsPG.setMaxActive(dataSourceProperties.getMaxActive());
			dsPG.setMaxIdle(dataSourceProperties.getMaxIdle());
			dsPG.setValidationQuery(dataSourceProperties.getValidationQuery());

			ds = dsPG;
		}

		// -------------------------------------------------------------------		
		

		return new DataSourceWrapper(key, printer.clone(), ds, dataSourceProperties);

		// -------------------------------------------------------------------

	}

	private Properties loadProperties(String key) throws LoadPropertiesDataSourceException {
		return loadPropertiesFromFileSystem(key);
	}

	private Properties loadPropertiesFromFileSystem(String key) throws LoadPropertiesDataSourceException {

		URL jdbcPropertiesURL = null;

		try {
			jdbcPropertiesURL = new URL(
					dataSourceURL.getProtocol() + ":" + dataSourceURL.getPath() + "/" + key + "_jdbc.properties");
		} catch (IOException ex) {
			print(MSG_3.replace("${path}", dataSourceURL.getPath() + "/" + key + "_jdbc.properties"));

			throw new LoadPropertiesDataSourceException(ex, key,
					dataSourceURL.getPath() + "/" + key + "_jdbc.properties");
		}

		Properties properties = new Properties();
		InputStream input = null;

		try {

			print(MSG_1.replace("${path}", jdbcPropertiesURL.toString()));

//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			input = classLoader.getResourceAsStream(path);

			input = new FileInputStream(jdbcPropertiesURL.getPath());
			properties.load(input);

			print(MSG_2.replace("${path}", jdbcPropertiesURL.toString()).replace("${content}",
					printProperties(properties)));

		} catch (IOException ex) {

			print(MSG_3.replace("${path}", jdbcPropertiesURL.getPath()));

			throw new LoadPropertiesDataSourceException(ex, key, jdbcPropertiesURL.getPath());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {

					print(MSG_3.replace("${path}", jdbcPropertiesURL.getPath()));

					throw new LoadPropertiesDataSourceException(e, key, jdbcPropertiesURL.getPath());
				}
			}
		}

		return properties;

	}

	private String printProperties(Properties properties) {
		String s = "";

		for (Enumeration<Object> e = properties.keys(); e.hasMoreElements();) {
			Object obj = e.nextElement();
			s += "\n\t" + obj + ":" + properties.getProperty(obj.toString());
		}

		s = s.substring(0, s.length() - 1);

		s += "\n";

		return s;
	}

	private void print(String s) {
		if (this.verbose) {
			this.printer.print(s);
		}
	}

} // END CLASS -----------------------------------------------------------------