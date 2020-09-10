package com.ms.back.util.persist;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;

class SQLTemplates {

	private final String MSG_1 = "\n[..] Leyendo archivo template sql ${path}";
	private final String MSG_2 = "${content}\n[OK] Lectura de archivo template sql ${path}";
	private final String MSG_3 = "\n[ERROR] Lectura de archivo template sql ${path}";

	// ---------------------------------------------------------------------------------------------------------------------------

	private boolean verbose;
	private Printer printer;

	private URL sqlTemplatesURL;
	private Map<String, String> sources = new HashMap<String, String>();

	// ---------------------------------------------------------------------------------------------------------------------------

	public SQLTemplates(Printer printer, boolean verbose, URL sqlTemplatesURL) {
		this.printer = printer;
		this.sqlTemplatesURL = sqlTemplatesURL;
		this.verbose = verbose;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private Map<String, String> getSources() {
		return sources;
	}

	public URL getSqlTemplatesURL() {
		return sqlTemplatesURL;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getTemplate(String key) throws LoadSQLTemplateDataSourceException {

		URL templateURL = null;

		try {
			templateURL = new URL(
					getSqlTemplatesURL().getProtocol() + ":" + getSqlTemplatesURL().getPath() + "/" + key);
		} catch (IOException e) {
			new IllegalArgumentException("Se intento pedir un template sql con una url mal formada. " + key);
		}

		key = templateURL.toString();

		if (getSources().containsKey(key) == false) {

//			try {

			loadTemplate(templateURL);
//
//			} catch (Exception e) {
//				new IllegalArgumentException(
//						"Se intento levantar un template file sql que no existe. Por favor verifique " + templateURL);
//			}
		}

		if (getSources().containsKey(key) == false) {
			new IllegalArgumentException("Se intento pedir un template sql que no existe.");
		}

		return getSources().get(key);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	private void loadTemplate(URL templateURL) throws LoadSQLTemplateDataSourceException {
		loadTemplateFromFileSystem(templateURL);
	}

	private void loadTemplateFromFileSystem(URL templateURL) throws LoadSQLTemplateDataSourceException {

		try {

			URL url = new URL(templateURL.getProtocol() + ":" + templateURL.getPath() + ".sql");

			print(MSG_1.replace("${path}", url.toString()));

			File file = new File(url.getPath());

			Path path = Paths.get(file.getAbsolutePath());

			StringBuilder content = new StringBuilder();

			try (Stream<String> lines = Files.lines(path)) {
				lines.forEach(line -> content.append(line).append("\n"));
			} catch (IOException e) {
				throw e;
			}

			getSources().put(templateURL.toString(), content.toString());

			print(MSG_2.replace("${path}", url.toString()).replace("${content}", "\n" + content.toString()));

		} catch (Exception e) {

			print(MSG_3.replace("${path}", templateURL.toString()));

			throw new LoadSQLTemplateDataSourceException(e, templateURL.toString());

		}

	}

	private void print(String s) {
		if (this.verbose) {
			this.printer.print(s);
		}
	}

//	private void loadQueryFromFileSystem(String key) throws IOException, URISyntaxException {
//		
//
//		URL templateURL = new URL(getSqlTemplatesURL().getProtocol() + ":" + getSqlTemplatesURL().getPath() + "/" + key);
//		
//		System.out.println("XXXXXXXX : " + templateURL);
//		
//		File folder = new File(getSqlTemplatesURL().getPath());
//		File[] files = folder.listFiles();
//
//		for (File file : files) {
//
//			if (file.getName().equals(key + ".sql")) {
//
////				Path path = Paths.get(getClass().getClassLoader().getResource(this.path).toURI());
//				Path path = Paths.get(file.getAbsolutePath());
//				StringBuilder content = new StringBuilder();
//
//				try (Stream<String> lines = Files.lines(path)) {
//					lines.forEach(line -> content.append(line).append("\n"));
//				} catch (IOException e) {
//					throw e;
//				}
//
//				getSources().put(file.getName().replace(".sql", ""), content.toString());
//			}
//
//		}
//	}

} // END CLASS -----------------------------------------------------------------