package com.pcitc.fms.service.stormModel;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * 罐对应的位号
 * 
 * @author haiwen.wang
 *
 */
public class TankTagVO  extends BaseResRep{

	private String tag;// 位号

	private int tagType;// 位号类型

	private String tagTypeName;// 位号类型名称

	private double value;// 值

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getTagType() {
		return tagType;
	}

	public void setTagType(int tagType) {
		this.tagType = tagType;
	}

	public String getTagTypeName() {
		return tagTypeName;
	}

	public void setTagTypeName(String tagTypeName) {
		this.tagTypeName = tagTypeName;
	}

}
