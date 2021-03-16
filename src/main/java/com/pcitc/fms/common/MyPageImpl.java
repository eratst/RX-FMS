package com.pcitc.fms.common;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class MyPageImpl extends PageImpl {
	
	public MyPageImpl(List content, Pageable pageable, long total) {
		super(content, pageable, total);
		this.count=total;
	}

	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
