package com.pcitc.fms.common;

import java.util.List;

import org.springframework.data.domain.Pageable;

public class Test extends MyPageImpl{

	public Test(List content, Pageable pageable, long total,String codes) {
		super(content, pageable, total);

	this.codes = codes;
	}
	
	private String codes;

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}
	
	

}
