package com.pcitc.fms.common;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class PageReturn extends BaseResRep{

	private Long count;
	
	private String logs;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}
	
}
