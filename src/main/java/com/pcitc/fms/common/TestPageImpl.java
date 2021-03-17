package com.pcitc.fms.common;

import java.util.List;

import org.springframework.data.domain.Pageable;

public class TestPageImpl extends MyPageImpl{

	public TestPageImpl(List content, Pageable pageable, long total,String logs) {
		super(content, pageable, total);
		this.logs =logs;
	}
	
	private String logs;

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}
	
	

}
