package com.ms.back.util.persist.dao.ds.info;

import java.time.Duration;
import java.time.ZonedDateTime;

public interface Result {

	public String getSql();

	public Object[] getArgs();

	public boolean isExecuted();

	public ZonedDateTime getStartTime();

	public ZonedDateTime getEndTime();

	public Long getRowCount();

	public Integer getColumnCount();

	public Object[][] getTable();

	// ---------------------------------------------------------------------------------------------------------------------------

	public Duration getDuration();

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toStringTable();

} // END CLASS -----------------------------------------------------------------
