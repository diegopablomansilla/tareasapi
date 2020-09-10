package com.ms.back.model;

import javax.json.JsonObject;

import com.ms.back.commons.json.JsonObjectWrapper;
import com.ms.back.commons.model.ObjectModel;

class Page extends ObjectModel implements Cloneable {

	private Integer pageNumber;
	private Integer indexFrom;
	private Integer indexTo;
	private Boolean firstPage;
	private Boolean thisPage = false;

	public Page(Integer pageNumber, Integer indexFrom, Integer indexTo) {
		super();
		this.pageNumber = pageNumber;
		this.indexFrom = indexFrom;
		this.indexTo = indexTo;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getIndexFrom() {
		return indexFrom;
	}

	public void setIndexFrom(Integer indexFrom) {
		this.indexFrom = indexFrom;
	}

	public Integer getIndexTo() {
		return indexTo;
	}

	public void setIndexTo(Integer indexTo) {
		this.indexTo = indexTo;
	}

	public Boolean getFirstPage() {
		return firstPage;
	}

//	public void setFirstPage(Boolean firstPage) {
//		this.firstPage = firstPage;
//	}

	public void setFirstPage() {
		this.firstPage = true;
	}

	public void setLastPage() {
		this.firstPage = true;
	}

	public Boolean getThisPage() {
		return thisPage;
	}

	public void setThisPage(Boolean thisPage) {
		this.thisPage = thisPage;
	}

	@Override
	public String toString() {
		String firstPageSufix = "";
		String thisPageSufix = "      ";
		if (firstPage != null && firstPage == true) {
			firstPageSufix = "(FIRST)";
		} else if (firstPage != null && firstPage == false) {
			firstPageSufix = "(LAST)";
		}
		if (thisPage != null) {
			thisPageSufix = "(THIS)";
		}
		return thisPageSufix + " PAGE (" + pageNumber + ") [" + indexFrom + "-" + indexTo + "] "
				+ firstPageSufix.trim();
	}

//	public Page clone() {
//		return new Page(this.pageNumber, this.fromIndex, this.toIndex);
//	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("pageNumber", this.getPageNumber());
		j.set("indexFrom", this.getIndexFrom());
		j.set("indexTo", this.getIndexTo());
		j.set("firstPage", this.getFirstPage());
		j.set("thisPage", this.getThisPage());

		return j.build();
	}

}
