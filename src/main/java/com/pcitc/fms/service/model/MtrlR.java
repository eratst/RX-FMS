/*
 * Copyright 2002-2018 Petro-CyberWorks Information Technology Co. Ltd. All rights reserved.
 * PCITC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.pcitc.fms.service.model;

import java.util.Date;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * 物料 resource
 * 
 * @author <a href="mailto:sun.bian@pcitc.com">sun bian</a>
 * @version 1.0, 2018年2月6日
 */
public class MtrlR extends BaseResRep {

    /**
     * 物料ID
     */
    @ResourceMember(InTemplate = true, Name = "id")
    private Integer MtrlId;

    /**
     * 物料编码
     */
    @ResourceMember(InTemplate = true, Name = "code")
    private String MtrlCode;

    /**
     * 物料名称
     */
    @ResourceMember(InTemplate = true, Name = "name")
    private String MtrlName;

    /**
     * 物料别名
     */
    @ResourceMember(InTemplate = true, Name = "alias")
    private String MtrlAlias;

    /**
     * 上级物料编码
     */
    @ResourceMember(InTemplate = true, Name = "upperCode")
    private String MtrlUpperCode;

    /**
     * VCF表类型编码
     */
    @ResourceMember(InTemplate = true, Name = "vcfType")
    private Integer vcfTypeId;

    /**
     * VCF表类型名称
     */
    @ResourceMember(InTemplate = true, Name = "vcfTypeName")
    private String vcfTypeName;

    /**
     * 物料类型ID
     */
    @ResourceMember(InTemplate = true, Name = "mtrlType")
    private Integer mtrlTypeId;

    /**
     * 物料类型名称
     */
    @ResourceMember(InTemplate = true, Name = "mtrlTypeName")
    private String mtrlTypeName;

    /**
     * 数据精度
     */
    @ResourceMember(InTemplate = true, Name = "dec")
    private Integer dec;

    /**
     * 显示顺序
     */
    @ResourceMember(InTemplate = true, Name = "displayOrder")
    private Integer displayOrder;

    /**
     * 创建时间
     */
    @ResourceMember(InTemplate = true, Name = "crtTime")
    private Date crtTime;

    /**
     * 修改时间
     */
    @ResourceMember(InTemplate = true, Name = "mntTime")
    private Date mntTime;

    /**
     * 启用标识
     */
    @ResourceMember(InTemplate = true, Name = "useFlag")
    private Integer useFlag;
    /**
     * 量纲
     */
    @ResourceMember(InTemplate = true, Name = "dimensionId")
    private Integer dimensionId;

	@ResourceMember(InTemplate = true, Name = "des")
	private String des;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

    public Integer getMtrlId() {
        return MtrlId;
    }

    public void setMtrlId(Integer mtrlId) {
        MtrlId = mtrlId;
    }

    public String getMtrlCode() {
        return MtrlCode;
    }

    public void setMtrlCode(String mtrlCode) {
        MtrlCode = mtrlCode;
    }

    public String getMtrlName() {
        return MtrlName;
    }

    public void setMtrlName(String mtrlName) {
        MtrlName = mtrlName;
    }

    public String getMtrlAlias() {
        return MtrlAlias;
    }

    public void setMtrlAlias(String mtrlAlias) {
        MtrlAlias = mtrlAlias;
    }

    public String getMtrlUpperCode() {
        return MtrlUpperCode;
    }

    public void setMtrlUpperCode(String mtrlUpperCode) {
        MtrlUpperCode = mtrlUpperCode;
    }

    public Integer getVcfTypeId() {
        return vcfTypeId;
    }

    public void setVcfTypeId(Integer vcfTypeId) {
        this.vcfTypeId = vcfTypeId;
    }

    public String getVcfTypeName() {
        return vcfTypeName;
    }

    public void setVcfTypeName(String vcfTypeName) {
        this.vcfTypeName = vcfTypeName;
    }

    public Integer getMtrlTypeId() {
        return mtrlTypeId;
    }

    public void setMtrlTypeId(Integer mtrlTypeId) {
        this.mtrlTypeId = mtrlTypeId;
    }

    public String getMtrlTypeName() {
        return mtrlTypeName;
    }

    public void setMtrlTypeName(String mtrlTypeName) {
        this.mtrlTypeName = mtrlTypeName;
    }

    public Integer getDec() {
        return dec;
    }

    public void setDec(Integer dec) {
        this.dec = dec;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getMntTime() {
        return mntTime;
    }

    public void setMntTime(Date mntTime) {
        this.mntTime = mntTime;
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public Integer getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }

}
