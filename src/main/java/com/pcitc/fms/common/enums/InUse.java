package com.pcitc.fms.common.enums;

/**
 * 是否启用
 * 
 * @author sleep
 *
 */
public enum InUse {
	/**
	 * 启用
	 */
	USE("启用", 1),
	/**
	 * 未启用
	 */
	UNUSE("未启用", 0);

	private InUse(String name, Integer index) {
		this.name = name;
		this.index = index;
	}

	/**
	 * 标号
	 */
	private Integer index;

	/**
	 * 名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
