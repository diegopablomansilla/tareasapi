package com.ms.back.model;

import java.util.Arrays;

import javax.json.JsonObject;

import com.ms.back.commons.json.JsonObjectWrapper;
import com.ms.back.commons.model.ObjectModel;

public class NoPagin extends ObjectModel {

	private Integer cantRows;

	private Integer thisPageColumns;
	private Object[][] thisPageItems;

//	private DAONoPaginArgs args;

	public NoPagin(Integer cantRows) {
		super();
//		this.args = args;
		this.cantRows = cantRows;
	}

	public void setItems(Object[][] thisPageItems, Integer thisPageColumns) {
		this.thisPageItems = thisPageItems;
		this.thisPageColumns = thisPageColumns;

	}

	public Integer getCantRows() {
		return cantRows;
	}

	public Integer getThisPageColumns() {
		return thisPageColumns;
	}

	public Object[][] getThisPageItems() {
		return thisPageItems;
	}

	public String toString() {

		String s = "";

		s += "\n" + "ROWS=[" + cantRows + "] ";

		if (thisPageItems != null) {
			s += "\n\nTHIS_PAGE_COLUMNS=[" + thisPageColumns + "]";

			for (Object[] row : thisPageItems) {
				s += "\n" + Arrays.toString(row);
			}
		}

		return s;
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

//		j.set("args", args);

		j.set("cantRows", this.getCantRows());

		j.set("thisPageColumns", this.getThisPageColumns());
		j.set("thisPageColumns", this.getThisPageColumns());

		j.set("thisPageItems", thisPageItems);

		return j.build();
	}

}
