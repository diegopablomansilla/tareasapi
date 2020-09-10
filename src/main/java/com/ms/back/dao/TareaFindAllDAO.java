package com.ms.back.dao;

import java.sql.SQLException;

import com.ms.back.model.NoPagin;
import com.ms.back.util.persist.DataBase;
import com.ms.back.util.persist.DataBases;
import com.ms.back.util.persist.dao.ds.ex.LoadSQLTemplateDataSourceException;
import com.ms.back.util.persist.dao.ds.ex.SelectException;
import com.ms.back.util.persist.dao.ds.info.Result;
import com.ms.back.util.persist.dao.ds.info.Statement;

public class TareaFindAllDAO {

	public NoPagin exec(String db, Integer meses, Boolean cerrada, Integer ordenFabricacionNumero) throws Exception {

		// -----------------------------------------------------------------------------

		DataBase dataBase = null;

		try {

			dataBase = DataBases.connectToDataBase(db);

			return coreDao(dataBase, meses, cerrada, ordenFabricacionNumero);

			// ------------------------------------------------------------------------

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (dataBase != null) {
					dataBase.disconnect();
				}
			} catch (Exception e) {
				throw e;
			}
		}

	}

	private NoPagin coreDao(DataBase dataBase, Integer meses, Boolean cerrada, Integer ordenFabricacionNumero)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		// -----------------------------------------------------------------------------

		int cantRows = count(dataBase, meses, cerrada, ordenFabricacionNumero);

		NoPagin pagin = new NoPagin(cantRows);

		if (cantRows <= 0) {
			return pagin;
		}

		Result r = query(dataBase, meses, cerrada, ordenFabricacionNumero);
		pagin.setItems(r.getTable(), r.getColumnCount());

		return pagin;

	}

	private int count(DataBase dataBase, Integer meses, Boolean cerrada, Integer ordenFabricacionNumero)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(true, meses, cerrada, ordenFabricacionNumero);

		Result r = dataBase.query(statement);

		return (int) r.getTable()[0][0];
	}

	private Result query(DataBase dataBase, Integer meses, Boolean cerrada, Integer ordenFabricacionNumero)
			throws LoadSQLTemplateDataSourceException, SelectException, SQLException {

		Statement statement = buildStatement(false, meses, cerrada, ordenFabricacionNumero);

		Result r = dataBase.query(statement);
		
		for(Object[] row : r.getTable()) {
			row[row.length - 1] = row[row.length - 1] != null && row[row.length - 1].equals("true"); 
		}

		return r;

	}

	private Statement buildStatement(boolean count, Integer meses, Boolean cerrada, Integer ordenFabricacionNumero)
			throws LoadSQLTemplateDataSourceException {

		// -----------------------------------------------------------------------------

		Statement statement = new Statement();

		if (meses == null) {
			meses = 1;
		}

		if (meses < 1) {
			meses = 1;
		} else if (meses > 24) {
			meses = 24;
		}

		statement.addArg(meses);

		String extraWhere = "";

		if (cerrada != null) {
			extraWhere += " AND cerrada = ?";
			statement.addArg(cerrada ? 1 : 0);
		}

		if (ordenFabricacionNumero != null) {
			extraWhere += " AND ordenFabricacionId = ?";
			statement.addArg(ordenFabricacionNumero);
		}

		if (count) {

			statement.setSql("SELECT\tCOUNT(*)\nFROM\tVTarea\nWITH (NOLOCK)\nWHERE fecha > DATEADD(MM, -?, fecha) "
					+ extraWhere);

		} else {

			statement.setSql("SELECT *\nFROM\tVTarea\nWITH (NOLOCK)\nWHERE fecha > DATEADD(MM, -?, fecha) " + extraWhere
					+ "\nORDER BY cerrada, fecha DESC, id");

		}

		// -----------------------------------------------------------------------------

		return statement;

	}

}