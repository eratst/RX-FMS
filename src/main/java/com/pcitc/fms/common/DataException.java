package com.pcitc.fms.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: DataException Description: 数据库异常处理
 * 
 * @author lei.y
 * @date 2018年1月10日
 * @version 1.0
 */
public class DataException {
	/**
	 * @Title: getMaps
	 * @Description: TODO 检查数据库异常
	 * @date 2018年1月15日
	 * @return: void
	 * @author lei.y
	 */
	public static Map<String, String> getMaps() {
		Map<String, String> maps = new HashMap<>();
		maps.put("AK_PM_NODE_NAME", "nodeName违反唯一约束，该字段已经存在！");
		maps.put("FK_BIZORG_DTL_REF_ORG", "orgCode违反外键约束，父项关键字不存在！");
		maps.put("CKC_INOUT_TYPE_ID_T_PM_SHI", "inOutType违反检查约束条件！");
		maps.put("AK_PM_NODE_CODE", "nodeCode违反唯一约束，该字段已经存在！");
		maps.put("PK_T_PM_EQUTECHNIC", "technicId违反主键约束！");
		maps.put("AK_PM_EQUTECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_EQUTECHNIC_NAME", "technicName违反唯一约束，该字段已经存在！");
		maps.put("FK_PM_EQU_REF_TECHNIC", "technicId违反外键约束！");
		maps.put("PK_T_PM_EQUIPMENT", "nodeId违反主键约束，执行失败！");
		
		maps.put("UK_NAME", "unitAlarmName违反唯一约束，该字段已经存在，执行失败！");
		maps.put("UK_SHORTNAME", "unitAlarmShortName违反唯一约束，该字段已经存在，执行失败！");
		maps.put("UK_CODE", "unitAlarmCode违反唯一约束，该字段已经存在，执行失败！");
		
		maps.put("FK_PM_NODE_REF_PM_AREA", "areaId违反外键约束，执行失败！");
		maps.put("PK_T_PM_NODE", "nodeId违反主键约束，执行失败！");
		maps.put("AK_PM_NODE_CODE", "nodeCode违反唯一约束，该字段已经存在，执行失败！");
		maps.put("AK_PM_NODE_NAME", "nodeName违反唯一约束，该字段已经存在，执行失败！");
		maps.put("AK_PM_NODE_ALIAS", "nodeAlias违反唯一约束，该字段已经存在，执行失败！");
		maps.put("FK_PM_EQU_REF_NODE", "nodeId违反外键约束，执行失败！");
		maps.put("FK_SHIPMENT_REF_NODE", "nodeId违反外键约束，执行失败！");
		maps.put("FK_PM_SILO_REF_PM_NODE", "nodeId违反外键约束，执行失败！");
		maps.put("PK_T_PM_SILO", "nodeId违反主键约束！");
		maps.put("FK_PM_SILO_REF_PM_NODE", "nodeId违反外键约束！");
		maps.put("FK_PM_SILO_REF_SILOTYPE", "siloTypeId违反外键约束！");
		maps.put("PK_T_PM_SILOTYPE", "siloTypeId违反主键约束！");
		maps.put("AK_PM_SILOTYPE_CODE", "siloTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_SILOTYPE_NAME", "siloTypeName违反唯一约束，该字段已经存在！");
		maps.put("CKC_INOUT_TYPE_ID_T_PM_SHI", "inOutTypeId只能是0或1！");
		maps.put("PK_T_PM_SHIPMENT", "nodeId违反主键约束！");
		maps.put("FK_SHIPMENT_REF_TRANSTYPE", "transTypeId违反外键约束！");
		maps.put("FK_SHIPMENT_REF_NODE", "nodeId违反外键约束！");
		maps.put("PK_T_PM_TRANSTYPE", "transTypeId违反主键约束！");
		maps.put("AK_PM_TRANSTYPE_CODE", "transTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_TRANSTYPE_NAME", "transTypeName违反唯一约束，该字段已经存在！");
		
		
		maps.put("PK_T_PM_ORGTYPE", "orgTypeId违反主键约束！");
		maps.put("AK_PM_ORGTYPECODE", "orgTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_ORGTYPENAME", "orgTypeName违反唯一约束，该字段已经存在！");
		maps.put("PK_T_PM_CUSTOMTYPE", "customTypeId违反主键约束！");
		maps.put("AK_PM_CUSTOMTYPECODE", "customTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_CUSTOMTYPENAME", "customTypeName违反唯一约束，该字段已经存在！");
		maps.put("PK_T_PM_AREATYPE", "areaTypeId违反主键约束！");
		maps.put("AK_PM_AREATYPE_CODE", "areaTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_AREATYPE_NAME", "areaTypeName违反唯一约束，该字段已经存在！");
		maps.put("PK_T_PM_UNITTYPE", "unitTypeId违反主键约束！");
		maps.put("AK_PM_UNITTYPE_CODE", "unitTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_UNITTYPE_NAME", "unitTypeName违反唯一约束，该字段已经存在！");
		maps.put("PK_T_PM_TECHNIC", "technicId违反主键约束！");
		maps.put("AK_PM_TECHNIC_ALIAS", "technicAlias违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_TECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_TECHNIC_NAME", "technicName违反唯一约束，该字段已经存在！");
		maps.put("PK_T_OPM_OPEINDEXCLASS", "opeIndexClassId违反主键约束！");
		maps.put("UK_OPEINDEXCLASS_CODE", "code违反唯一约束，该字段已经存在！");
		maps.put("UK_OPEINDEXCLASS_NAME", "name违反唯一约束，该字段已经存在！");
		maps.put("PK_T_OPM_CONTROLDEP", "controlDepId违反主键约束！");
		maps.put("UK_CONTROLDEP_CODE", "code违反唯一约束，该字段已经存在！");
		maps.put("UK_CONTROLDEP_NAME", "name违反唯一约束，该字段已经存在！");
		maps.put("PK_T_OPM_STAALGR", "staAlgrId违反主键约束！");
		maps.put("UK_STAALGR_CODE", "code违反唯一约束，该字段已经存在！");
		maps.put("UK_STAALGR_NAME", "name违反唯一约束，该字段已经存在！");
		maps.put("PK_T_PM_TANKAREATYPE", "tankareaTypeId违反主键约束！");
		maps.put("AK_PM_TANKAREATYPE_CODE", "tankareaTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_TANKAREATYPE_NAME", "tankareaTypeName违反唯一约束，该字段已经存在！");
		
		maps.put("PK_T_PM_TUBULATION", "nodeId违反主键约束！");
		maps.put("FK_TUBULATION_REF_PM_NODE", "nodeId违反外键约束！");
		maps.put("PK_PM_TEMCONPDEN_T", "tempcondenId违反主键约束！");
		maps.put("AK_PM_TEMPCONDEN_T", "mtrlId, cubaTempCofe, con违反唯一约束，该字段已经存在！");
		maps.put("FK_TEMPCONDEN_REF_MTRL", "mtrlId违反外键约束！");
		
		maps.put("PK_T_PM_TANKAREATECHNIC", "technicId违反主键约束！");
		maps.put("AK_PM_TANKAREATECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_TANKAREATECHNIC_NAME", "technicName违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_TANKAREATECHNIC_ALIAS", "technicAlias违反唯一约束，该字段已经存在！");
		
		maps.put("PK_T_PM_WAREHOUSETYPE", "wareHouseTypeId违反主键约束！");
		maps.put("AK_PM_WAREHOUSETYPE_CODE", "wareHouseTypeCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_WAREHOUSETYPE_NAME", "wareHouseTypeName违反唯一约束，该字段已经存在！");
		
		maps.put("PK_T_PM_WAREHOUSETECHNIC", "technicId违反主键约束！");
		maps.put("AK_PM_WAREHOUSETECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_WAREHOUSETECHNIC_NAME", "technicName违反唯一约束，该字段已经存在！");
		maps.put("AK_PM_WAREHOUSETECHNIC_ALIAS", "technicAlias违反唯一约束，该字段已经存在！");
		
		maps.put("PK_T_PM_LOADRACKTECHNIC", "technicId违反主键约束!");
		maps.put("AK_PM_LOADRACKTECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_LOADRACKTECHNIC_NAME", "technicName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_PIPENETTECHNIC", "technicId违反主键约束!");
		maps.put("AK_PM_PIPENETTECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_PIPENETTECHNIC_NAME", "technicName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_NODETYPE", "nodeTypeId违反主键约束!");
		maps.put("AK_PM_NODETYPE_CODE", "nodeTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_NODETYPE_NAME", "nodeTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_SIDELINETYPE", "sideLineTypeId违反主键约束!");
		maps.put("AK_PM_SIDELINETYPE_CODE", "sideLineTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_SIDELINETYPE_NAME", "sideLineTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_SIDEMTRLTYPE", "sideMtrlTypeId违反主键约束!");
		maps.put("AK_PM_SIDEMTRLTYPE_CODE", "sideMtrlTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_SIDEMTRLTYPE_NAME", "sideMtrlTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_TANKTYPE", "tankTypeId违反主键约束!");
		maps.put("AK_PM_TANKTYPE_CODE", "tankTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_TANKTYPE_NAME", "tankTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_PM_VCF_TYPE_T", "vcfTypeId违反主键约束!");
		maps.put("AK_VCF_TYPE_NAME", "vcfTypeName违反唯一约束，该字段已经存在!");
		maps.put("AK_VCF_TYPE_CODE", "vcfTypeCode违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_EQUTECHNIC", "technicId违反主键约束!");
		maps.put("AK_PM_EQUTECHNIC_CODE", "technicCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_EQUTECHNIC_NAME", "technicName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_EQUTECHNIC", "transTypeId违反主键约束!");
		maps.put("AK_PM_TRANSTYPE_CODE", "transTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_TRANSTYPE_NAME", "transTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_SILOTYPE", "siloTypeId违反主键约束!");
		maps.put("AK_PM_SILOTYPE_CODE", "siloTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_SILOTYPE_NAME", "siloTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_SAMPLEPOINT_TYPE", "samplePointTypeId违反主键约束!");
		maps.put("AK_PM_SAMPLEPOINTTYPE_CODE", "samplePointTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_SAMPLEPOINTTYPE_NAME", "samplePointTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_SIGNBOARD_TYPE", "signBoardTypeId违反主键约束!");
		maps.put("AK_PM_SIGNBOARDTYPE_CODE", "signBoardTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_SIGNBOARDTYPE_NAME", "signBoardTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_IDX_TYPE", "idxTypeId违反主键约束!");
		maps.put("AK_PM_IDXTYPE_CODE", "idxTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_IDXTYPE_NAME", "idxTypeName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_MTRL_TYPE", "mtrlTypeId违反主键约束!");
		maps.put("AK_PM_MTRLTYPE_CODE", "mtrlTypeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_MTRLTYPE_NAME", "mtrlTypeName违反唯一约束，该字段已经存在!");
		maps.put("FK_SIDE_REF_SIDEMTRLTYPE", "sideMtrlTypeId违反外键约束!");
		
		maps.put("PK_T_PM_ORG", "orgId违反主键约束!");
		maps.put("AK_PM_ORGCODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_ORGNAME", "orgName违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_ORGALIAS", "orgAlias违反唯一约束，该字段已经存在!");
		maps.put("FK_ORG_REF_ORGTYPE", "orgTypeId违反外键约束!");
		
		maps.put("PK_T_PM_HEADQUARTERS", "orgId违反主键约束!");
		maps.put("AK_PM_HEADQUARTERS_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_T_PM_HEA_REFERENCE_T_PM_ORG", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_DIVISION", "orgId违反主键约束!");
		maps.put("AK_PM_DIVISION_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_DIVISION_REF_PM_ORG", "orgId违反外键约束!");
		
		maps.put("CKC_ENTR_TYPE_T_PM_ENT", "entrType只能是1，2，3!");
		maps.put("PK_T_PM_ENTERPRISE", "orgId违反主键约束!");
		maps.put("AK_PM_ENTERPRISECODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_ENT_REF_PM_ORG", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_OFFICES", "orgId违反主键约束!");
		maps.put("AK_PM_OFFICE_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_OFFICE_REF_PM_ORG", "orgId违反外键约束!");
		
		maps.put("CKC_FCTR_BLOCK_T_PM_FAC", "fctrBlock只能是1，2，3!");
		maps.put("CKC_BUSINESS_TYPE_T_PM_FAC", "businessType只能是1，2，3，4，9,0!");
		maps.put("PK_T_PM_FACTORY", "orgId违反主键约束!");
		maps.put("AK_PM_FACTORY_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_FACTORY_REF_PM_ORG", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_DEPARTMENT", "orgId违反主键约束!");
		maps.put("AK_PM_DEPT_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_DEPT_REF_PM_ORG", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_WORKSHOP", "orgId违反主键约束!");
		maps.put("AK_PM_WORKSHOP_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_WORKSHOP_REF_FCTR", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_TEAM", "orgId违反主键约束!");
		maps.put("AK_PM_TEAM_CODE", "orgCode违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_TEAM_REF_PM_ORG", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_CUSTOM", "customId违反主键约束!");
		maps.put("AK_PM_CUSTOMCODE", "customCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_CUSTOMNAME", "customName违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_CUSTOMALIAS", "customAlias违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_CUSTOM_REF_TYPE", "customTypeId违反外键约束!");
		
		maps.put("PK_T_PM_AREA", "areaId违反主键约束!");
		maps.put("AK_PM_ARERCODE", "areaCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_ARERNAME", "areaName违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_ARERALIAS", "areaAlias违反唯一约束，该字段已经存在!");
		maps.put("FK_T_PM_ARE_REFERENCE_T_PM_FAC", "parentAreaId违反外键约束!");
		
		maps.put("FK_UNIT_REF_UNITTYPE", "unitTypeCode违反外键约束!");
		maps.put("FK_UNIT_REF_TECHNIC", "technicCode违反外键约束!");
		maps.put("FK_UNIT_REF_AREA", "areaId违反外键约束!");
		
		maps.put("PK_T_PM_TANKAREA", "areaId违反主键约束!");
		maps.put("FK_TANKAREA_REF_TANKTECHNIC", "technicCode违反外键约束!");
		maps.put("FK_TANKAREA_REF_TANKAREATYPE", "tankAreaTypeId违反外键约束!");
		maps.put("FK_TANKAREA_REF_AREA", "areaId违反外键约束!");
		
		maps.put("FK_WARE_REF_WARETYPE", "wareHouseTypeId违反外键约束!");
		maps.put("FK_WARE_REF_WARETECHNIC", "techinicCode违反外键约束!");
		maps.put("FK_WARE_REF_AREA", "areaId违反外键约束!");
		
		maps.put("PK_T_PM_LOADRACK", "areaId违反主键约束!");
		maps.put("FK_LOADRACK_REF_TECHNIC", "technicCode违反外键约束!");
		maps.put("FK_LOADRACK_REF_AREA", "areaId违反外键约束!");
		
		maps.put("PK_T_PM_PIPENET", "areaId违反主键约束!");
		maps.put("FK_PIPENET_REF_TECHNIC", "technicCode违反外键约束!");
		maps.put("FK_T_PM_PIP_REFERENCE_T_PM_ARE", "areaId违反外键约束!");
		
		maps.put("PK_T_PM_COMMUNITY", "areaId违反主键约束!");
		maps.put("FK_COMMUNITY_REF_AREA", "areaCode违反外键约束!");
		
		maps.put("PK_T_PM_ADMINISTRATION", "areaId违反主键约束!");
		maps.put("FK_ADMINISTRATION_REF_AREA", "areaId违反外键约束!");
		
		maps.put("PK_T_PM_NODE", "nodeId违反主键约束!");
		maps.put("AK_PM_NODE_CODE", "nodeCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_NODE_NAME", "nodeName违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_NODE_ALIAS", "nodeAlias违反唯一约束，该字段已经存在!");
		maps.put("FK_PM_NODE_REF_NODETYPE", "nodeTypeId违反外键约束!");
		maps.put("FK_PM_NODE_REF_PM_AREA", "areaId违反外键约束!");
		
		maps.put("CKC_SLINE_INOUT_TYPE__T_PM_SID", "slineInOutTypeId只能是0,1!");
		maps.put("PK_T_PM_SIDELINE", "nodeId违反主键约束!");
		
		maps.put("PK_T_PM_TANK", "nodeId违反主键约束!");
		maps.put("FK_TANK_REF_TANKTYPE", "tankTypeId违反外键约束!");
		maps.put("FK_PM_TANK_REF_NODE", "nodeId违反主键约束!");
		
		maps.put("PK_T_PM_EQUIPMENT", "nodeId违反主键约束!");
		maps.put("FK_PM_EQU_REF_TECHNIC", "technicId违反外键约束!");
		maps.put("FK_PM_EQU_REF_NODE", "nodeId违反外键约束!");
		
		maps.put("CKC_INOUT_TYPE_ID_T_PM_SHI", "inOutTypeId只能是0,1!");
		maps.put("PK_T_PM_SHIPMENT", "nodeId违反主键约束!");
		maps.put("FK_SHIPMENT_REF_TRANSTYPE", "transTypeId违反外键约束!");
		maps.put("FK_SHIPMENT_REF_NODE", "nodeId违反外键约束!");
		
		maps.put("PK_T_PM_SILO", "nodeId违反主键约束!");
		maps.put("FK_PM_SILO_REF_PM_NODE", "nodeId违反外键约束!");
		maps.put("FK_PM_SILO_REF_SILOTYPE", "siloTypeId违反外键约束!");
		
		maps.put("PK_T_PM_LOCATION", "nodeId违反主键约束!");
		maps.put("FK_STOCK_REF_PM_NODE", "nodeId违反外键约束!");
		
		maps.put("PK_T_PM_SAMPLEPOINT", "nodeId违反主键约束!");
		maps.put("FK_SAMPOINT_REF_PM_NODE", "nodeId违反外键约束!");
		maps.put("FK_SAMPOINT_REF_SAMPTYPE", "samplePointTypeId违反外键约束!");
		
		maps.put("PK_T_PM_DRAINAGEPORT", "nodeId违反主键约束!");
		maps.put("FK_DRAINAGE_REF_NODE", "nodeId违反外键约束!");
		maps.put("FK_DRAINAGE_REF_SIGNTYPE", "signBoardTypeId违反外键约束!");
		
		maps.put("PK_T_PM_TUBULATION", "nodeId违反主键约束!");
		maps.put("FK_TUBULATION_REF_PM_NODE", "nodeId违反外键约束!");
		
		maps.put("PK_T_PM_VALVE", "nodeId违反主键约束!");
		maps.put("FK_VALVE_REF_PM_NODE", "nodeId违反外键约束!");
		
		maps.put("PK_T_PM_CLOSURE", "nodeId违反主键约束!");
		maps.put("FK_PM_CLOSURE_REF_NODE", "nodeId违反外键约束!");
		
		maps.put("PK_T_PM_TEE", "nodeId违反主键约束!");
		maps.put("FK_TEE_REF_NODE", "nodeId违反外键约束!");
		
		maps.put("PK_T_PM_MEASINDEX", "idxId违反主键约束!");
		maps.put("FK_MEAIDX_REF_MTRL", "mtrlId违反外键约束!");
		maps.put("FK_MEAIDX_REF_NODE", "nodeId违反外键约束!");
		maps.put("FK_MEAIDX_REF_DIMENSION", "dimensionId违反外键约束!");
		maps.put("FK_MEAIDX_REF_IDXTYPE", "idxTypeId违反外键约束!");
		
		maps.put("PK_T_PM_MTRL", "mtrlId违反主键约束!");
		maps.put("AK_PM_MTRL_CODE", "mtrlCode违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_MTRL_NAME", "mtrlName违反唯一约束，该字段已经存在!");
		maps.put("AK_PM_MTRL_ALIAS", "mtrlAlias违反唯一约束，该字段已经存在!");
		maps.put("FK_T_PM_MTR_REFERENCE_T_PM_NOD", "nodeId违反外键约束!");
		maps.put("FK_MTRL_REF_DIMENSION", "dimensionId违反外键约束!");
		maps.put("FK_MTRL_REF_VCFTYPE", "vcfType违反外键约束!");
		maps.put("FK_MTRL_REF_MTRLTYPE", "mtrlTypeId违反外键约束!");
		maps.put("UK_STAALGRCONF_EQUIP_ID", "equipId,monLevelId违反联合唯一约束!");
		maps.put("FK_T_OPM_STAALGR_R_T_OPM_EQUIP", "equipId违反外键约束!");
		
		maps.put("PK_T_PM_DIMENSION", "dimensionId违反主键约束!");
		maps.put("AK_DIMENSION_CODE", "dimensionCode违反唯一约束，该字段已经存在!");
		maps.put("AK_DIMENSION_NAME", "dimensionName违反唯一约束，该字段已经存在!");
		maps.put("AK_DIMENSION_ALIAS", "dimensionAlias违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_PM_MTRL_FACTORY_RELATION", "mtrlFactoryId违反主键约束!");
		maps.put("FK_T_PM_MTR_REFERENCE_T_PM_MTR", "mtrlId违反外键约束!");
		maps.put("FK_T_PM_MTR_REFERENCE_T_PM_ORG", "orgId违反外键约束!");
		
		maps.put("PK_T_PM_BIZORG_MAIN", "bizId违反主键约束!");
		
		maps.put("CKC_EXPEND_FLAG_T_PM_BIZ", "expendFlag智能是0或1!");
		maps.put("PK_T_PM_BIZORG_DTL", "dtlId违反主键约束!");
		maps.put("AK_BIZORG_DTL_ID", "违反联合唯一约束，请修改bizCode或orgCode!");
		maps.put("AK_BIZORG_DTL_CODE", "bizId,orgCode违反联合唯一约束!");
		maps.put("AK_BIZORG_DTL_NAME", "bizId,orgCode违反联合唯一约束!");
		maps.put("AK_BIZORG_DTL_ALIAS", "bizId,orgAlias违反联合唯一约束!");
		maps.put("FK_T_PM_BIZ_REFERENCE_T_PM_ORG", "orgTypeId违反外键约束!");
		maps.put("FK_T_PM_BIZ_REFERENCE_T_PM_ORG", "orgId违反外键约束!");
		maps.put("FK_BIZORG_DTL_REF_BIZORG", "bizId违反外键约束!");
		
		maps.put("PK_T_IC_SIMPLE_NODE_MAP", "sNodeId违反主键约束!");
		maps.put("FK_SNMAP_REF_SNODE", "sNodeId违反外键约束!");
		maps.put("FK_SNMAP_REF_MNODE", "mNodeId违反外键约束!");
		
		maps.put("PK_T_IC_NODETOP_MAIN", "topId违反主键约束!");
		maps.put("AK_IC_NODETOP_CODE", "topCode违反唯一约束，该字段已经存在!");
		maps.put("AK_IC_NODETOP_NAME", "topName违反唯一约束，该字段已经存在!");
		maps.put("AK_IC_NODETOP_ALIAS", "topAlias违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_IC_NODETOP_DTL", "dataId违反主键约束!");
		maps.put("AK_KEY_2_T_IC_NOD", "sNodeId,tNodeId违反联合唯一约束!");
		maps.put("FK_NODETOP_S_REF_NODE", "sNodeId违反外键约束!");
		maps.put("FK_NODETOP_T_REF_NODE", "tNodeId违反外键约束!");
		maps.put("FK_NODETOPDTL_REF_MAIN", "topId违反外键约束!");
		
		maps.put("PK_T_IC_TNK_CNFG", "tnkCnfgId违反主键约束!");
		maps.put("AK_IC_TNKCNFG", "nodeId违反唯一约束，该字段已经存在!");
		maps.put("FK_IC_TNKCNFG_REF_PM_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_LIE_CUBA", "dataId违反主键约束!");
		maps.put("AK_PM_LIECUBA_TANK", "nodeId,hgt违反联合唯一约束!");
		maps.put("FK_LIECUBA_REF_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_GLB_CUBA", "dataId违反主键约束!");
		maps.put("AK_PM_GLBCUBA_TANK", "nodeId,hgt违反联合唯一约束!");
		maps.put("FK_IC_GLBCUBA_REF_PM_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_STDDM_CUAB", "dataId违反主键约束!");
		maps.put("AK_IC_STDDMCUAB_TANK", "nodeId,hgt违反联合唯一约束!");
		maps.put("FK_IC_STDMM_REF_PM_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_STDCMMM_CUAB", "dataId违反主键约束!");
		maps.put("AK_IC_STDCMMM_TANK", "nodeId,secId,secIntPart违反联合唯一约束!");
		maps.put("FK_IC_STDCMMM_REF_PM_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_STD_SEC", "dataId违反主键约束!");
		maps.put("AK_IC_STDSEC_TANKSEC", "nodeId,secId违反联合唯一约束!");
		maps.put("FK_IC_STDSEC_RE_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_FLTPER_CUAB", "dataId违反主键约束!");
		maps.put("AK_FLTPER_TANK", "nodeId,fltPerHgt违反联合唯一约束!");
		maps.put("FK_FLTPERCUAB_REF_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_STDPRES_COEF", "dataId违反主键约束!");
		maps.put("AK_STDPRES_TANK", "nodeId,intPl,fstDecFra违反联合唯一约束!");
		maps.put("FK_IC_STDPRES_REF_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_SPCL_CUBA", "dataId违反主键约束!");
		maps.put("AK_SPCL_CUBA_TANK", "nodeId,oilChk违反联合唯一约束!");
		maps.put("FK_IC_SPCLCUBA_REF_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_IC_TEMPDEN", "tempdenId违反主键约束!");
		maps.put("AK_PM_TEMPDEN_T", "mtrlId,cubaTempCofe违反联合唯一约束!");
		maps.put("FK_IC_TEMPDEN_REF_MTRL", "mtrlId违反外键约束!");
		
		maps.put("PK_PM_TEMCONPDEN_T", "tempcondenId违反主键约束!");
		maps.put("AK_PM_TEMPCONDEN_T", "mtrlId,cubaTempCofe,con违反联合唯一约束!");
		maps.put("FK_TEMPCONDEN_REF_MTRL", "mtrlId违反外键约束!");
		
		maps.put("PK_T_IC_DENMOD_VAL", "airFlogModId违反主键约束!");
		maps.put("AK_PM_DEN_MOD_VAL_T", "denFlrLmt,denUpLmt违反联合唯一约束!");
		
		maps.put("PK_T_IC_DENMOD_COEF", "denModCoefId违反主键约束!");
		maps.put("AK_PM_DEN_MOD_COEF_T", "denFlrLmt,denUpLmt违反联合唯一约束!");
		maps.put("FK_TEMPCONDEN_REF_MTRL", "mtrlId违反外键约束!");
		
		maps.put("PK_T_IC_LIQPROD_COEF", "liqprodId违反主键约束!");
		maps.put("AK_PM_LIQPROD_COEF_T", "mtrlId违反唯一约束，该字段已经存在!");
		maps.put("FK_LIQPROD_REF_MTRL", "mtrlId违反外键约束!");
		
		maps.put("PK_T_IC_CUBATEMP_COEF", "cubaTempCofeId违反主键约束!");
		maps.put("AK_PM_CUBA_TEMP_COEF_T", "denFlrLmt,denUpLmt违反联合唯一约束!");
		
		maps.put("PK_T_IC_PIPENETTNK_COEF", "pipenetTankId违反主键约束!");
		maps.put("AK_PM_PIPENETTNK_COEF_T", "month违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_IC_VCF", "vcfId违反主键约束!");
		maps.put("AK_PM_VCF_T", "den,temp,artcSt违反联合唯一约束!");
		maps.put("FK_VCF_REF_VCFTYPE", "vcfTypeId违反外键约束!");
		
		maps.put("PK_T_IC_TANKMEAS", "dataId违反主键约束!");
		maps.put("FK_TANKMEAS_RE_TANK", "nodeId违反外键约束!");
		
		maps.put("PK_T_OPM_OPEINDEX", "opeIndexId违反主键约束!");
		maps.put("UK_OPEINDEX_NAME", "equipId,name违反联合唯一约束!");
		maps.put("UK_OPEINDEX_STD_CODE", "stdCode违反唯一约束，该字段已经存在!");
		maps.put("FK_T_OPM_OPEINDE_R_T_OPM_CONTR", "controlDepId违反外键约束!");
		maps.put("FK_T_OPM_OPEINDE_R_T_OPM_EQUIP", "equipId违反外键约束!");
		maps.put("FK_T_OPM_OPEINDE_R_T_OPM_OPEIN", "openIndexClassId违反外键约束!");
		maps.put("FK_T_OPM_OPEINDE_R_T_OPM_UNIT", "unitId违反外键约束!");
		maps.put("FK_T_OPM_OPEINDE_T_PM_DIMENS", "measUnitId违反外键约束!");
		maps.put("FK_T_OPM_OPEINDE_T_PM_MEASINDX", "openIndexId违反外键约束!");
		
		maps.put("PK_T_OPM_CRAFTSCHEME", "craftSchemeId违反主键约束!");
		maps.put("UK_CRAFTSCHEME_NAME", "equipId,name违反联合唯一约束!");
		maps.put("UK_CRAFTSCHEME_SNAME", "equipId,sname违反联合唯一约束!");
		maps.put("UK_CRAFTSCHEME_STD_CODE", "stdCode违反唯一约束，该字段已经存在!");
		maps.put("FK_T_OPM_CRAFTSC_R_T_OPM_EQUIP", "equipId违反外键约束!");
		
		maps.put("PK_T_OPM_MONLEVEL", "monLevelId违反主键约束!");
		maps.put("UK_MONLEVEL_NAME", "mName违反唯一约束，该字段已经存在!");
		
		maps.put("PK_T_OPM_DEUPDOWNLIMIT", "deUpDownLimitId违反主键约束!");
		maps.put("FK_T_OPM_DEUPDOW_R_T_OPM_CRAFT", "craftSchemeId违反外键约束!");
		maps.put("FK_T_OPM_DEUPDOW_R_T_OPM_MONLE", "monLevelId违反外键约束!");
		maps.put("FK_T_OPM_DEUPDOW_R_T_OPM_OPEIN", "opeIndexId违反外键约束!");
		
		maps.put("PK_T_OPM_STAALGRCONF", "staAlgrConfId违反主键约束!");
		maps.put("UK_CODE_STAALGRCONF", "code违反唯一约束，该字段已经存在!");
		maps.put("UK_NAME_STAALGRCONF", "name违反唯一约束，该字段已经存在!");
		maps.put("UK_STAALGRCONF_NAME", "name,des违反联合唯一约束!");
		maps.put("FK_T_OPM_STAALGR_R_T_OPM_EQUIP", "equipId违反外键约束!");
		maps.put("FK_T_OPM_STAALGR_R_T_OPM_MONLE", "monLevelId违反外键约束!");
		maps.put("FK_T_OPM_STAALGR_R_T_OPM_STAAL", "staAlgrId违反外键约束!");
		
		maps.put("PK_T_OPM_STAALGRCONFITEM", "staAlgrConfItemId违反主键约束!");
		maps.put("UK_CODE1", "code违反唯一约束，该字段已经存在!");
		maps.put("UK_NAME1", "name违反唯一约束，该字段已经存在!");
		maps.put("UK_STAALGRCONFITEM_1", "name,staAlgrConfId违反联合唯一约束!");
		maps.put("UK_STAALGRCONFITEM_OPEINDEX_ID", "staAlgrConfId,opeIndexId违反联合唯一约束!");
		maps.put("FK_T_OPM_STAALGR_R_STAALGRCONF", "staAlgrConfId违反外键约束!");
		maps.put("FK_T_OPM_STAALGR_R_T_OPM_CRAFT", "craftSchemeId违反外键约束!");
		maps.put("FK_T_OPM_STAALGR_R_T_OPM_OPEIN", "opeIndexId违反外键约束!");
		
		maps.put("PK_TUBULATION", "tubulationId违反主键约束!");
		maps.put("AK_ORG_CODE_TUBULATI", "code违反唯一约束，该字段已经存在!");
		
		maps.put("FK_PM_NODE_REF_NODETYPE", "nodeTypeId违反外键约束，执行失败！");
		
		return maps;
	}

}
