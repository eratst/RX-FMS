package com.pcitc.fms.config;

/**
 * Title: AreaNodeBasicSql
 * Description: TODO task mark zhenqiang.zhao
 *
 * @author 赵振强
 * @version 1.0
 * @date 2017年11月9日
 */
public interface AreaNodeBasicSql {

    //进出厂点
    static final String edgePoints = "select new EdgePoint(a.nodeId,a.nodeCode,a.transTypeId,a.inOutTypeId,a.formula,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.transTypeName,e.areaCode,a.sortNum,e.name,e.shortName,org.orgCode,d.transTypeCode) from EdgePoint a,NodeDictionary b,NodeType c,TransType d,AreaDictionary e,TPmOrg org  where "
            + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.transTypeId = d.transTypeId and b.areaId = e.areaDictionaryId and org.orgId=e.factoryId ";
    //采样点
    static final String samplePoint = "select new SamplePoint(a.nodeId,a.nodeCode,b.nodeName,b.nodeAlias,e.areaDictionaryId,e.areaCode,e.name,e.shortName,a.samplepointTypeId,d.samplepointTypeCode,d.samplepointTypeName,b.nodeLongitude,b.nodeLatitude,b.nodeAltitude,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.dataStatus,a.sortNum,b.des) from SamplePoint a,NodeDictionary b,NodeType c,SamplepointType d,AreaDictionary e,TPmOrg org where "
            + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.samplepointTypeId = d.samplepointTypeId and b.areaId = e.areaDictionaryId and e.factoryId=org.orgId ";

    //侧线
    static final String SideLine = "select new SideLine(a.nodeId, a.nodeCode, a.slineInOutTypeId, a.sidelineTypeId,a.slineMtrlTypeId, d.sidelineTypeCode, e.sideMtrlTypeCode, a.areaForm, a.formula, b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate, b.des, b.areaId, b.nodeLongitude, b.nodeLatitude, b.nodeTypeId, b.dataStatus,b.nodeAltitude, b.nodeName, b.nodeAlias, c.nodeTypeName, d.sidelineTypeName,e.sideMtrlTypeName,f.areaCode, org.orgCode, f.name, f.shortName,a.sortNum) " +
            "from SideLine a,NodeDictionary b,NodeType c,SidelineType d,SideMtrlType e,AreaDictionary f, TPmOrg org where"
            + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.sidelineTypeId = d.sidelineTypeId and a.slineMtrlTypeId = e.sideMtrlTypeId and b.areaId = f.areaDictionaryId and f.factoryId=org.orgId";
    //罐
    static final String Tank = "select new Tank(a.nodeId,a.nodeCode,b.nodeName,b.nodeAlias,a.tankTypeId,d.tankTypeCode,d.tankTypeName,e.areaCode,e.name,e.shortName,a.tankHgt,a.tankTotlCuba,a.fltPlatWgt,a.fltPlatPerhgt,a.fltTipLst,a.maxTankHgt,a.minTankHgt,a.maxTankStoarge,a.minTankStoarge,a.htPretTank,b.nodeLongitude,b.nodeLatitude,b.nodeAltitude"
            + ",b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.dataStatus,a.sortNum,b.des,b.nodeTypeId,org.orgCode,b.areaId) "
            + "from Tank a,NodeDictionary b,TankArea c,TankType d,AreaDictionary e, TPmOrg org where"
            + " a.nodeCode = b.nodeCode and a.tankTypeId = d.tankTypeId and b.areaId = e.areaDictionaryId and e.areaDictionaryId=c.tankAreaId and org.orgId=e.factoryId ";

    //节点
    static final String NodeDictionary = "select new NodeDictionary(a.nodeId, a.nodeCode, a.nodeName,a.nodeAlias, a.areaId, a.nodeLongitude,a.nodeLatitude, a.nodeTypeId,a.dataStatus,a.nodeAltitude, "
            + "a.des,b.nodeTypeCode,b.nodeTypeName,ad.areaCode,ad.name,ad.shortName,a.sortNum,a.crtUserCode, a.crtUserName, a.crtDate, a.mntUserCode, a.mntUserName, a.mntDate) "
            + "from NodeDictionary a,AreaDictionary ad,TPmOrg org,NodeType b "
            + "where a.areaId=ad.areaDictionaryId and a.nodeTypeId=b.nodeTypeId and ad.factoryId=org.orgId";

    //设备
    static final String Equipment = "select new Equipment(a.nodeId,a.nodeCode,a.equMgrCode,a.technicId,"
            + "b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, "
            + "b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId"
            + ",b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,"
            + "d.technicName,a.sortNum,e.name,e.shortName,d.technicCode,org.orgCode,e.areaCode) "
            + "from Equipment a,NodeDictionary b,NodeType c,EquTechnic d,AreaDictionary e,TPmOrg org where "
            + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.technicId = d.technicId and b.areaId = e.areaDictionaryId and org.orgId=e.factoryId ";
    //料仓
//	static final String Silo = "select new Silo(a.nodeId,a.nodeCode,a.siloTypeId,a.cubage,a.siloHgt,a.maxSiloHgt,a.minSiloHgt,a.maxSiloStoarge,a.minSiloStoarge,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.siloTypeName) from Silo a,NodeDictionary b,NodeType c,SiloType d,AreaDictionary e  where "
//						     + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.siloTypeId = d.siloTypeId and b.areaId = e.areaDictionaryId ";
    //库位
//	static final String Stock = "select new Stock(a.nodeId,a.nodeCode,a.locationDomain,a.rackCode,a.rackfloorCode,a.racklocationCode,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName) from Stock a,NodeDictionary b,NodeType c,AreaDictionary d  where "
//							  + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId ";
    //排放口
//	static final String Outlet = "select new Outlet(a.nodeId,a.nodeCode,a.signboardTypeId,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.signboardTypeName) from Outlet a,NodeDictionary b,NodeType c,SignboardType d,AreaDictionary e  where "
//							   + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.signboardTypeId = d.signboardTypeId and b.areaId = e.areaDictionaryId ";
//	//阀门
//	static final String Valve = "select new Valve(a.nodeId,a.nodeCode,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName) from Valve a,NodeDictionary b,NodeType c,AreaDictionary d  where "
//							  + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId ";
    //三通
    static final String Tee = "select new Tee(a.nodeId,a.nodeCode,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,d.areaCode,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.name,d.shortName,a.sortNum) from Tee a,NodeDictionary b,NodeType c,AreaDictionary d,TPmOrg org where "
            + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and d.factoryId=org.orgId ";
    //盲板
    static final String Plate = "select new Plate(a.nodeId,a.nodeCode,a.thickness,a.diameter,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName) from Plate a,NodeDictionary b,NodeType c,AreaDictionary d   where "
            + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId ";
    //管段
//	static final String Tubulation = "select new Tubulation(a.nodeId,a.nodeCode,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName) from Tubulation a,NodeDictionary b,NodeType c,AreaDictionary d   where "
//								   + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId ";
//	//度量指标
//	static final String measurements = "select new Measurement(a.idxId,a.idxCode,a.idxName,a.idxAlias,a.idxTypeId,a.nodeId,a.mtrlId,a.dimensionId,a.idxFormula,a.dataStatus,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,a.des,b.idxTypeName) from Measurement a,IdxType b  where "
//			+ "1 = 1  and a.idxTypeId = b.idxTypeId ";
	/*//度量指标关联关系
	static final String measurements = "select new Measurement(a.idxId,a.idxCode,a.idxName,a.idxAlias,a.idxTypeId,b.idxTypeCode,b.idxTypeName,a.nodeId,c.nodeCode,"
			+ "c.nodeName,c.nodeAlias,a.dimensionId,e.dimensionCode,e.dimensionName,e.dimensionAlias,a.idxFormula,a.inUse,a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,"
			+ "a.mntDate,a.sortNum,a.des,a.sourceDataType,a.exchangeRate,area.areaCode,area.name,area.shortName,d.nodeTypeCode,d.nodeTypeName) "
			+ "from Measurement a,IdxType b,NodeDictionary c,NodeType d ,Dimension e,AreaDictionary area,TPmOrg org where "
			+ " a.idxTypeId = b.idxTypeId and a.nodeId = c.nodeId and c.nodeTypeId = d.nodeTypeId and e.dimensionId = a.dimensionId and c.areaId=area.areaDictionaryId and area.factoryId=org.orgId ";
*/
    /**
     * 度量指标
     */
    public static final String measureMents = "select new Measurement (m.idxId,m.nodeId, "
            + "case when m.nodeId is NULL then '' else ( select n.nodeAlias from Node n where m.nodeId = n.nodeId ) end,"
            + "case when m.nodeId is NULL then '' else ( select n.nodeCode from Node n where m.nodeId = n.nodeId ) end,"
            + "case when m.nodeId is NULL then '' else ( select n.nodeName from Node n where m.nodeId = n.nodeId ) end,"
            + "case when m.nodeId is NULL then '' else ( select nt.nodeTypeCode from Node n,NodeType nt where m.nodeId = n.nodeId and n.nodeTypeId=nt.nodeTypeId ) end,"
            + "case when m.nodeId is NULL then '' else ( select nt.nodeTypeName from Node n,NodeType nt where m.nodeId = n.nodeId and n.nodeTypeId=nt.nodeTypeId ) end,"
            + "m.areaId,"
            + "case when m.areaId is NULL then '' else ( select area.areaAlias from Area area where m.areaId = area.areaId ) end,"
            + "case when m.areaId is NULL then '' else ( select area.areaCode from Area area where m.areaId = area.areaId ) end,"
            + "case when m.areaId is NULL then '' else ( select area.areaName from Area area where m.areaId = area.areaId ) end,"
            + "case when m.areaId is NULL then '' else ( select areat.areaTypeCode from Area area,AreaType areat where m.areaId = area.areaId and area.areaTypeId = areat.areaTypeId ) end,"
            + "case when m.areaId is NULL then '' else ( select areat.areaTypeName from Area area,AreaType areat where m.areaId = area.areaId and area.areaTypeId = areat.areaTypeId ) end,"
            + "m.idxCode,m.idxName,m.idxAlias,m.idxTypeId,i.idxTypeName,m.dimensionId,m.exchangeRate,d.dimensionAlias,"
            + "m.idxFormula,m.sourceDataType,m.inUse,m.crtUserId,m.crtUserName,m.crtDate,m.mntUserId,m.mntUserName,"
            + "m.mntDate,m.des,m.sortNum,m.version,m.ofMeasindexType)"
            + "from Measurement m,IdxType i,Dimension d"
            + " where m.idxTypeId = i.idxTypeId and m.dimensionId = d.dimensionId ";
//	//物料 停用
//	static final String materials = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,a.vcfType,a.mtrlTypeId,a.dec,a.dimensionId,a.tankIdt,a.dataStatus,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,a.des,a.nodeId,c.vcfTypeName,b.mtrlTypeName,e.nodeTypeName)" +
//			" from Material a,MtrlType b,VcfType c,NodeDictionary d,NodeType e  where "
//			+ " a.mtrlTypeId = b.mtrlTypeId and a.vcfType = c.vcfTypeId and a.nodeId = d.nodeId and d.nodeTypeId = e.nodeTypeId  ";

    //物料 启用
    static final String materials_new = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,a.vcfType,a.mtrlTypeId,a.dec,a.dimensionId,a.tankIdt,a.dataStatus,a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,a.des,c.vcfTypeName,b.mtrlTypeName)" +
            " from Material a,MtrlType b,VcfType c where "
            + " a.mtrlTypeId = b.mtrlTypeId and a.vcfType = c.vcfTypeId ";
    //物料 侧线
    static final String materials_sidelines = "select new Material(mtrl.mtrlId,mtrl.mtrlCode,mtrl.mtrlName,mtrl.mtrlAlias,mtrl.upperMtrlCode,mtrl.vcfType,mtrl.mtrlTypeId,mtrl.dec,mtrl.dimensionId,mtrl.tankIdt,mtrl.inUse,mtrl.crtUserCode,mtrl.crtUserName,mtrl.crtDate,mtrl.mntUserCode,mtrl.mntUserName,mtrl.mntDate,mtrl.des,sideline.nodeId,vcf.vcfTypeName,mtrlType.mtrlTypeName,nodeType.nodeTypeName,t.nodeCode)" +
            " from NodeDictionary t, TPmAssociative sideline,Material mtrl ,NodeType nodeType  ,VcfType vcf ,MtrlType mtrlType" +
            " where t.nodeId = sideline.nodeId and sideline.mtrlId = mtrl.mtrlId and nodeType.nodeTypeId =  t.nodeTypeId and mtrl.vcfType = vcf.vcfTypeId and mtrl.mtrlTypeId = mtrlType.mtrlTypeId";
//	//物料 罐
//	static final String materials_tanks = "select new Material(mtrl.mtrlId,mtrl.mtrlCode,mtrl.mtrlName,mtrl.mtrlAlias,mtrl.upperMtrlCode,mtrl.vcfType,mtrl.mtrlTypeId,mtrl.dec,mtrl.dimensionId,mtrl.tankIdt,mtrl.dataStatus,mtrl.crtUserId,mtrl.crtUserName,mtrl.crtDate,mtrl.mntUserId,mtrl.mntUserName,mtrl.mntDate,mtrl.des,mtrl.nodeId,vcf.vcfTypeName,mtrlType.mtrlTypeName,nodeType.nodeTypeName,t.nodeCode)" +
//			"from NodeDictionary t, TPmAssociative tank,Material mtrl ,NodeType nodeType,VcfType vcf ,MtrlType mtrlType" +
//			" where t.nodeId = tank.nodeId and tank.mtrlId = mtrl.mtrlId and nodeType.nodeTypeId =  t.nodeTypeId  and mtrl.vcfType = vcf.vcfTypeId and mtrl.mtrlTypeId = mtrlType.mtrlTypeId";
//	//物料 管段
//	static final String materials_tubulations = "select new Material(mtrl.mtrlId,mtrl.mtrlCode,mtrl.mtrlName,mtrl.mtrlAlias,mtrl.upperMtrlCode,mtrl.vcfType,mtrl.mtrlTypeId,mtrl.dec,mtrl.dimensionId,mtrl.tankIdt,mtrl.dataStatus,mtrl.crtUserId,mtrl.crtUserName,mtrl.crtDate,mtrl.mntUserId,mtrl.mntUserName,mtrl.mntDate,mtrl.des,mtrl.nodeId,vcf.vcfTypeName,mtrlType.mtrlTypeName,nodeType.nodeTypeName,t.nodeCode) " +
//			"from NodeDictionary t, TPmAssociative tubu,Material mtrl ,NodeType nodeType ,VcfType vcf ,MtrlType mtrlType" +
//			" where t.nodeId = tubu.nodeId and tubu.mtrlId = mtrl.mtrlId and  nodeType.nodeTypeId =  t.nodeTypeId and mtrl.vcfType = vcf.vcfTypeId and mtrl.mtrlTypeId = mtrlType.mtrlTypeId";

    //总部
    static final String headquarters = "select distinct new Headquarter(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,t.version,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Headquarter t,TPmOrg org,TPmOrgType orgType where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId ";
    //事业部
    static final String divisions = "select distinct new Division(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Division t,TPmOrg org,TPmOrgType orgType,TPmBizOrgDTL dtl,TPmBizOrgMain biz where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
    //企业
    static final String enterprises = "select distinct new Enterprise(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode,orgType.orgTypeName, entrType.entrTypeCode, entrType.entrTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Enterprise t,TPmOrg org,TPmOrgType orgType, EntrType entrType,TPmBizOrgDTL dtl,TPmBizOrgMain biz where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and t.entrTypeId = entrType.entrTypeId and t.orgId = org.orgId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
    //公司处室
    static final String offices = "select distinct new Office(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Office t,TPmOrg org,TPmOrgType orgType ,TPmBizOrgDTL dtl,TPmBizOrgMain biz where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
    //生产工厂
    static final String produceFactories = "select distinct new ProduceFactory(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, fctrBlockType.fctrBlockTypeCode, fctrBlockType.fctrBlockTypeName, t.businessTypeId, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des,t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from ProduceFactory t,TPmOrg org,TPmOrgType orgType,FctrBlockType fctrBlockType,TPmBizOrgDTL dtl,TPmBizOrgMain biz  where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and fctrBlockType.fctrBlockTypeId = t.fctrBlockId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
    //科室
    static final String departments = "select distinct new Department(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Department t,TPmOrg org,TPmOrgType orgType ,TPmBizOrgDTL dtl,TPmBizOrgMain biz where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
    //车间
    static final String workshops = "select distinct new Workshop(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Workshop t,TPmOrg org,TPmOrgType orgType ,TPmBizOrgDTL dtl,TPmBizOrgMain biz where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
    //班组
    static final String teams = "select distinct new Team(t.orgId, t.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode, orgType.orgTypeName, org.inUse, org.crtUserCode, org.crtUserName, org.crtDate, org.mntUserCode, org.mntUserName, org.mntDate, t.des, t.sortNum,org.orgLatitude,org.orgAltitude,org.orgLongitude) from Team t,TPmOrg org,TPmOrgType orgType where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId ";

    static final String areaDictionary = "select new AreaDictionary(t.areaCode, t.name, t.shortName, o.orgCode, o.orgName, o.orgAlias, at.areaTypeCode, at.areaTypeName, t.enabled,t.sortNum,t.des,t.areaLatitude,t.areaAltitude,t.areaLongitude,t.crtUserCode,t.crtUserName,t.crtDate,t.mntUserCode,t.mntUserName,t.mntDate) from AreaDictionary t,TPmOrg o,AreaType at where t.factoryId = o.orgId and t.areaTypeId = at.areaTypeId ";

    //业务域 多业务组织层次明细//orgDTL.parentOrgId
    static final String bizOrgDTLs = "select  new TPmBizOrgDTL(orgDTL.dtlId, orgDTL.orgId, orgDTL.orgCode, org.orgName, org.orgAlias, org.orgTypeId, orgType.orgTypeCode,orgType.orgTypeName, orgDTL.expendFlag, orgDTL.inUse, orgDTL.bizId," +
            " orgDTL.parentOrgId, " +
            " case  when  (select tpmorg.orgCode  from TPmOrg  tpmorg where tpmorg.orgId = orgDTL.parentOrgId )  is NULL then '0' else (select tpmorg.orgCode  from TPmOrg  tpmorg where tpmorg.orgId = orgDTL.parentOrgId )  end," +
            " case  when  (select tpmorg.orgName  from TPmOrg  tpmorg where tpmorg.orgId = orgDTL.parentOrgId )  is NULL then '0' else (select tpmorg.orgName  from TPmOrg  tpmorg where tpmorg.orgId = orgDTL.parentOrgId )  end," +
            " case  when  (select tpmorg.orgAlias  from TPmOrg  tpmorg where tpmorg.orgId = orgDTL.parentOrgId )  is NULL then '0' else (select tpmorg.orgAlias  from TPmOrg  tpmorg where tpmorg.orgId = orgDTL.parentOrgId )  end," +
            " orgMain.bizCode, orgMain.bizName, orgMain.bizAlias, orgDTL.crtUserCode, orgDTL.crtUserName,orgDTL.crtDate, orgDTL.mntUserCode, orgDTL.mntUserName, orgDTL.mntDate, orgDTL.version, orgDTL.sortNum, orgDTL.des)" +
            "  from TPmBizOrgDTL orgDTL ,TPmBizOrgMain  orgMain ,TPmOrg org,TPmOrgType orgType where" +
            " orgDTL.bizId = orgMain.bizId and orgDTL.orgId = org.orgId " +
            " and org.orgTypeId = orgType.orgTypeId ";

    //拓扑关系明细//
    static final String snodetopDTLs = "select new TIcNodetopDTL(dtl.dataId,dtl.dataCode, dtl.snodeId, dtl.tnodeId, dtl.topId,\n"
            + "      dtl.crtUserId, dtl.crtUserName, dtl.crtDate, dtl.mntUserId,\n"
            + "      dtl.mntUserName, dtl.mntDate, dtl.version, dtl.sortNum, dtl.des,\n"
            + "      (select nodeDic.nodeCode  from NodeDictionary  nodeDic where nodeDic.nodeId = dtl.snodeId ), "
            + "      (select nodeDic.nodeAlias  from NodeDictionary  nodeDic where nodeDic.nodeId = dtl.snodeId ),"
            + "     (select tnodeDic.nodeCode  from NodeDictionary  tnodeDic where tnodeDic.nodeId = dtl.tnodeId ),"
            + "    (select tnodeDic.nodeAlias  from NodeDictionary  tnodeDic where tnodeDic.nodeId = dtl.tnodeId ),"
            + " main.topCode, main.topName, main.topAlias) "
            + " from TIcNodetopDTL dtl, TIcNodetopMain main, NodeDictionary node "
            + "  where dtl.topId = main.topId and dtl.snodeId = node.nodeId";

    static final String tnodetopDTLs = "select new TIcNodetopDTL(dtl.dataId, dtl.snodeId, dtl.tnodeId, dtl.topId,\n"
            + "      dtl.crtUserId, dtl.crtUserName, dtl.crtDate, dtl.mntUserId,\n"
            + "      dtl.mntUserName, dtl.mntDate, dtl.version, dtl.sortNum, dtl.des,\n"
            + "      (select nodeDic.nodeCode  from NodeDictionary  nodeDic where nodeDic.nodeId = dtl.snodeId ), "
            + "      (select nodeDic.nodeAlias  from NodeDictionary  nodeDic where nodeDic.nodeId = dtl.snodeId ), "
            + "        (select tnodeDic.nodeCode  from NodeDictionary  tnodeDic where tnodeDic.nodeId = dtl.tnodeId ),"
            + "        (select tnodeDic.nodeAlias  from NodeDictionary  tnodeDic where tnodeDic.nodeId = dtl.tnodeId ),"
            + " main.topCode, main.topName, main.topAlias) "
            + " from TIcNodetopDTL dtl, TIcNodetopMain main, NodeDictionary node "
            + "  where dtl.topId = main.topId and dtl.tnodeId = node.nodeId";


    static final String areaNodeTypes = "select new TPmAreaNodeType(areaNodetype.id, areaNodetype.areaCode, areaNodetype.nodeTypeCode,nodetype.nodeTypeName)"
            + " from AreaDictionary area , TPmAreaNodeType areaNodetype,NodeType  nodetype  "
            + " where area.areaCode = areaNodetype.areaCode "
            + " and  areaNodetype.nodeTypeCode = nodetype.nodeTypeCode";

    //区域层
    //办公区
    static final String administration = "select new AdministrationArea(a.administrationId, a.administrationCode, ad.areaTypeId, ad.name," +
            "ad.shortName, ad.factoryId, ad.crtUserCode, ad.crtUserName, ad.crtDate, ad. mntUserCode, ad.mntUserName, ad.mntDate, a.sortNum, ad.des, ad.version," +
            "ad.enabled,ar.areaTypeName,org.orgCode,org.orgName,org.orgAlias,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude) from AdministrationArea a,AreaDictionary ad, "
            + "AreaType ar,TPmOrg org where a.administrationId=ad.areaDictionaryId and ad.areaTypeId=ar.areaTypeId"
            + " and ad.factoryId=org.orgId";
    //生活区
    static final String community = "select new CommunityArea(a.communityId, a.communityCode, ad.areaTypeId, ad.name," +
            "ad.shortName, ad.factoryId, ad.crtUserCode, ad.crtUserName, ad.crtDate, ad.mntUserCode, ad.mntUserName, ad.mntDate, a.sortNum, ad.des, ad.version," +
            "ad.enabled,ar.areaTypeName,org.orgCode,org.orgName,org.orgAlias,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude) from CommunityArea a,AreaDictionary ad, " +
            "AreaType ar,TPmOrg org where a.communityId=ad.areaDictionaryId and ad.areaTypeId=ar.areaTypeId and " +
            "ad.factoryId=org.orgId";
    //工厂
//		static final String factorySite  = "select new FactorySiteArea(a.factorySiteId, a.factorySiteCode, ad.areaTypeId, ad.name," + 
//					                              "ad.shortName, ad.factoryId, a.creatorId, a.creator, a.createTime," + 
//					                              "a.editorId, a.editor, a.maintainTime, a.sortNum, ad.des, ad.version," + 
//					                               "ad.enabled) from FactorySite a,AreaDictionary ad ";
    //装置
    static final String plant = "select new PlantArea(a.plantId, a.plantCode, ad.areaTypeId, ad.name, ad.shortName," +
            "ad.factoryId, ad.crtUserCode, ad.crtUserName, ad.crtDate, ad.mntUserCode," +
            "ad.mntUserName, ad.mntDate, ad.sortNum, ad.des, ad.version, ad.enabled," +
            "u.unitTyprName, t.technicName, a.capacity,a.initialAssetValue, a.netAssetValue, ar.areaTypeName,org.orgCode"
            + ",org.orgName,org.orgAlias,u.unitTypeId,u.unitTypeCode,t.technicId,t.technicCode,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude, a.capacityUnitId,"
            + "case when a.capacityUnitId is NULL then '' else (select capacityUnit.capacityUnitName from CapacityUnit capacityUnit where a.capacityUnitId = capacityUnit.capacityUnitId) end ) from "
            + "PlantArea a,AreaDictionary ad,Technic t,UnitType u, AreaType ar,TPmOrg org "
            + "where ar.areaTypeId = ad.areaTypeId and a.plantId = ad.areaDictionaryId "
            + "and a.technicId = t.technicId and a.plantTypeId = u.unitTypeId and ad.factoryId=org.orgId";
    //罐区
    static final String tankArea = "select new TankArea(a.tankAreaId, a.tankAreaCode, ad.name, ad.shortName, org.orgId, org.orgCode, org.orgName, org.orgAlias,"
            + " ad.crtUserCode, ad.crtUserName, ad.crtDate, ad.mntUserCode, ad.mntUserName, ad.mntDate, a.sortNum, ad.des, ad.enabled, a.version, "
            + "a.tankAreaTypeId, tt.tankAreaTypeCode, tt.tankAreaTyprName, a.technicId, ta.technicCode, ta.technicName,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude)"
            + " from TankArea a,AreaDictionary ad,TankAreaTechnic ta,TankAreaType tt, TPmOrg org "
            + "where ad.factoryId=org.orgId and a.tankAreaId = ad.areaDictionaryId and a.technicId = ta.technicId and a.tankAreaTypeId = tt.tankAreaTypeId ";

    //仓库
    static final String warehouse = "select new WarehouseArea(a.warehouseId, a.warehouseCode, ad.areaTypeId, ad.name," +
            "ad.shortName, ad.factoryId, ad.crtUserCode, ad.crtUserName, ad.crtDate, ad.mntUserCode, ad.mntUserName, ad.mntDate, a.sortNum, ad.des, ad.version," +
            "ad.enabled, tt.warehouseTypeName, ta.technicName,org.orgCode,org.orgName,org.orgAlias,"
            + "ta.technicCode,tt.warehouseTypeCode,tt.warehouseTypeId,ta.technicId,tt.warehouseTypeName,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude) from"
            + " WarehouseArea a,AreaDictionary ad,WarehousTechnic ta,WarehouseType tt,TPmOrg org"
            + " where a.warehouseId = ad.areaDictionaryId and a.technicId = ta.technicId "
            + "and a.warehouseTypeId = tt.warehouseTypeId and ad.factoryId=org.orgId";
    //装卸台
    static final String loadingDock = "select new LoadingDockArea(a.loadingDockId, a.loadingDockCode, ad.areaTypeId, ad.name," +
            "ad.shortName, ad.factoryId, ad.crtUserCode, ad.crtUserName, ad.crtDate, ad.mntUserCode, ad.mntUserName, ad.mntDate,a.sortNum, ad.des, ad.version," +
            "ad.enabled, ta.technicName,ar.areaTypeName,a.technicId,org.orgCode,org.orgName,org.orgAlias,ta.technicCode,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude) "
            + "from LoadingDockArea a,AreaDictionary ad,LoadrackTechnic ta,AreaType ar,TPmOrg org "
            + "where ar.areaTypeId = ad.areaTypeId and a.loadingDockId = ad.areaDictionaryId "
            + "and a.technicId = ta.technicId and ad.factoryId=org.orgId";
    //管网
    static final String pipeNetwork = "select new PipeNetworkArea(a.pipeNetworkId, a.pipeNetworkCode, ad.areaTypeId, ad.name," +
            "ad.shortName, ad.factoryId, ad.crtUserCode, ad.crtUserName, ad.crtDate, ad.mntUserCode, ad.mntUserName, ad.mntDate,a.sortNum, ad.des, ad.version," +
            "ad.enabled, ta.technicName,org.orgCode,org.orgName,org.orgAlias,ta.technicCode,a.technicId,ad.areaLatitude,ad.areaAltitude,ad.areaLongitude) "
            + "from PipeNetworkArea a,AreaDictionary ad,PipenetTechnic ta,TPmOrg org "
            + "where a.pipeNetworkId = ad.areaDictionaryId and a.technicId = ta.technicId "
            + "and ad.factoryId=org.orgId";

    //操作平稳率计算配置项
    static final String staalgrConfitem = "select new StaalgrConfitem(a.staalgrConfitemId, a.staalgrConfId, a.code, a.name,\r\n" +
            "			a.weightings, a.inUse, a.creatorId, a.creator, a.createTime, a.editorId,\r\n" +
            "			a.editor, a.maintainTime, a.sortNum, a.des, a.version,\r\n" +
            "			cr.name, op.name,a.agentCode,a.craftSchemeId,a.opeindexId) from StaalgrConfitem a,Openindex op,CraftScheme cr where a.opeindexId = op.openindexId and a.craftSchemeId = cr.craftSchemeId ";
    //操作指标
    static final String openindex = "select new Openindex(a.openindexId, a.name, a.code, a.unitId, a.calcFormula,\r\n" +
            "			a.isPublic, a.equipId, a.isInnerOp, a.inUse, a.crtUserCode, a.crtUserName,\r\n" +
            "			a.crtDate, a.mntUserCode, a.mntUserName, a.mntDate, a.des, a.sortnum,\r\n" +
            "			a.tagName, op.name, co.name, di.dimensionName) "
            + "from Openindex a,OpenindexClass op,Controldep co,Dimension di,MonLevel m, "
            + "Deupdownlimit d, Plant pl,Equipment eq "
            + "where   "
            + "m.monLevelId=d.monLevelId and d.opeindexId=a.openindexId and a.opeindexClassId = op.openindexClassId "
            + "and a.controlDepId = co.controlDepId and a.measUnitId=di.dimensionId  "
            + "and pl.plantId=a.unitId "
            + "and  eq.nodeId=a.equipId\r\n" +
            "";
    //上下限
    static final String deupdownlimit = "select new Deupdownlimit(a.deupDownLimitId, a.uniqueId, a.opeindexId, a.upLimitValue,\r\n" +
            "			a.downLimitValue, a.attentionValue, a.thresholdValue, a.minDvtTime,\r\n" +
            "			a.maxChangeTate, a.techcardCtlFlag, a.monFlag, a.ptrlFlag, a.startTime,\r\n" +
            "			a.status, a.mntFlag, a.inUse, a.creatorId, a.creator, a.creatTime,\r\n" +
            "			a.cmtUserId, a.cmtUser, a.cmtDate, a.rlsUserId, a.rlsUser, a.rlsDate,\r\n" +
            "			a.editorId, a.editor, a.maintainTime, a.des, a.applyReason, a.riskAndSolution,\r\n" +
            "			a.upLimitFormula, a.downLimitFormula, a.attentionFormula, mo.name,\r\n" +
            "			cr.name) from Deupdownlimit a left join  CraftScheme cr left join MonLevel m join Openindex op with a.craftSchemeId=cr.craftSchemeId and a.monLevelId=m.monLevelId and";
    //装置告警
    static final String unitAlarm = "select new UnitAlarm(a.unitAlarmId, a.unitAlarmCode, a.unitAlarmName, a.unitAlarmAlias,\r\n" +
            "			a.unitId, a.idxId, a.idxType, a.itemNo, a.upLimit, a.downLimit,\r\n" +
            "			a.upperLimit, a.lowerLimit, a.crtUserCode, a.crtUserName, a.crtDate,\r\n" +
            "			a.mntUserCode, a.mntUserName, a.mntDate, a.sortNum, a.des, a.version,\r\n" +
            "			a.inUse) from UnitAlarm a,Plant pl where a.unitId=pl.plantId ";

    //操作平稳率计算配置
    static final String staalgrConf = "select new StaalgrConf(a.staalgrConfId, a.code, a.name, a.equipId, a.inUse,\r\n" +
            "			a.creatorId, a.creator, a.createTime, a.editorId, a.editor, a.maintainTime,\r\n" +
            "			a.sortNum, a.des, a.version, st.name, mo.name) from StaalgrConf a,Staalgr st,MonLevel mo ,Plant p where a.monLevelId = mo.monLevelId and a.staalgrId = st.staalgrId ";

    //装置实时计算
    static final String openindexVO = "select new OpenindexVO(a.openindexId, a.name, a.code, p.plantId, a.opeindexClassId,\r\n" +
            "			a.controlDepId, a.calcFormula, a.isPublic, a.measUnitId, a.equipId,\r\n" +
            "			a.isInnerOp, s.weightings) from OpenindexVO a, StaalgrConfitem s, Plant p where a.openindexId=s.opeindexId and a.unitId=p.plantId";
    //组织结构-所有区域
    static final String orgFindAllArea = "select new AreaDictionary(a.areaDictionaryId,a.areaCode,a.name,a.shortName,a.areaTypeId,a.factoryId,a.enabled,a.sortNum,a.des,a.version,c.areaTypeName as type,b.orgCode)"
            + " from AreaDictionary a,TPmOrg b,AreaType c where a.factoryId = b.orgId and a.areaTypeId = c.areaTypeId ";
    //区域下节点类型以及数量
    static final String orgFindAllAreaNodeTypeNum =
            "select new com.pcitc.fms.dal.pojo.Area_NodeType_Num(nodetype.nodeTypeName,count(t.nodeTypeId)) "
//			"select new AreaDictionary(a.areaDictionaryId,a.areaCode,a.name,a.shortName,a.areaTypeId,a.factoryId,a.enabled,a.sortNum,a.des,a.version,c.areaTypeName as type)"
                    + "  from AreaDictionary a,TPmOrg b,AreaType c ,NodeDictionary t, NodeType  nodetype "
                    + "  where a.factoryId = b.orgId and a.areaTypeId = c.areaTypeId "
                    + "  and t.areaId = a.areaDictionaryId "
                    + "  and t.nodeTypeId = nodetype.nodeTypeId  ";

    //调度物料
    static final String managementMtrls = "select new ManagementMtrl(mm.mtrlId, mm.mtrlCode, mm.mtrlName, mm.mtrlSname, mm.upperMtrlCode, mm.mtrlTypeId, mt.mtrlTypeCode, mt.mtrlTypeName, mm.vcfTypeId"
            + ", vt.vcfTypeCode, vt.vcfTypeName, mm.dimensionId, ds.dimensionCode, ds.dimensionName, ds.dimensionAlias, ds.dataStatus, mm.dec, mm.sortNum) from ManagementMtrl mm, MtrlType mt, VcfType vt, Dimension ds where mm.mtrlTypeId = mt.mtrlTypeId and mm.vcfTypeId = vt.vcfTypeId and mm.dimensionId = ds.dimensionId ";

    static final String managementTankAreas = "select distinct new ManagementTankArea(mt.areaId, mt.areaCode, a.name, a.shortName, tt.tankAreaTypeId, tat.technicId, mt.sortNum, a.enabled, org.orgCode, org.orgName, org.orgAlias, tt.tankAreaTypeCode, tt.tankAreaTyprName,tat.technicCode, tat.technicName) from ManagementTankArea mt, AreaDictionary a, TankAreaType tt, TankAreaTechnic tat, TPmOrg org, TPmBizOrgDTL bizDtl,TPmBizOrgMain bizMain where bizMain.bizId = bizDtl.bizId and org.orgId=a.factoryId and org.orgId=bizDtl.orgId and mt.areaId = a.areaDictionaryId and mt.tankAreaTypeId = tt.tankAreaTypeId and mt.technicId = tat.technicId ";

    static final String managementTank = "select new ManagementTank(tank.nodeId, tank.nodeCode, node.nodeName, node.nodeAlias, tankType.tankTypeCode, tankType.tankTypeName, tank.tankHgt, area.areaCode, area.name, area.shortName, tank.maxTankHgt, tank.minTankHgt, tank.maxTankStorage, tank.minTankStorage, tank.tankTotlCuba, tank.sortNum, tank.fltPlatWgt, tank.fltPlatPerhgt, tank.fltTipLst, tank.htPretTank, node.nodeLongitude, node.nodeLatitude, node.nodeAltitude, node.dataStatus) from ManagementTank tank, AreaDictionary area, TankArea tankArea, TankType tankType, NodeDictionary node, TPmOrg org, TPmBizOrgDTL bizDtl ,TPmBizOrgMain bizMain where bizMain.bizId = bizDtl.bizId and bizDtl.orgId = org.orgId and org.orgId = area.factoryId and area.areaDictionaryId = tankArea.tankAreaId and area.areaDictionaryId = node.areaId and tankType.tankTypeId = tank.tankTypeId and tank.nodeId = node.nodeId ";

    //立罐浮前罐容
    static final String fltperCuab = "select new FltperCuab(f.dataId,f.nodeId,f.fltPerHgt,f.fltPerCuba,f.dispSeqNbr,t.nodeCode,f.crtUserCode,f.crtUserName,f.crtDate,f.mntUserCode,f.mntUserName,f.mntDate,f.des,f.inUse,f.sortNum) from FltperCuab f,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where f.nodeId= t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId  ";

    //立罐静压修正
    static final String stdpresCoef = "select new StdpresCoef(s.dataId,s.nodeId,t.nodeCode,s.intPl,s.fstDecFra,s.presRevCofe,s.crtUserCode,s.crtUserName,s.crtDate,s.mntUserCode,s.mntUserName,s.mntDate,s.des,s.inUse,s.sortNum) from StdpresCoef s,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where s.nodeId= t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";

    //简易罐容
    static final String spclCuab = "select new SpclCuab(s.dataId,s.nodeId,t.nodeCode,s.oilChk,s.netWgt,s.crtUserCode,s.crtUserName,s.crtDate,s.mntUserCode,s.mntUserName,s.mntDate,s.des,s.inUse,s.sortNum) from SpclCuab s,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where s.nodeId= t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";

    //温度标准密度
    static final String tempden = "select new Tempden(t.tempdenId,t.mtrlId,m.mtrlCode,t.cubaTempCofe,t.den,t.crtUserCode,t.crtUserName,t.crtDate,t.mntUserCode,t.mntUserName,t.mntDate,t.des,t.inUse,t.sortNum) from Tempden t,Material m where t.mtrlId= m.mtrlId ";

    //温度浓度标准密度
    static final String tempconden = "select new Tempconden(t.tempcondenId,t.mtrlId,m.mtrlCode,t.cubaTempCofe,t.den,t.con,t.crtUserCode,t.crtUserName,t.crtDate,t.mntUserCode,t.mntUserName,t.mntDate,t.des,t.inUse,t.sortNum) from Tempconden t,Material m where t.mtrlId= m.mtrlId ";
    //罐量指标—卧罐罐容
    static final String LieCubas = "select new LieCubas(lie.dataId,lie.nodeId,lie.hgt,lie.cuba,t.nodeCode,lie.crtUserCode, lie.crtUserName, lie.crtDate, lie.mntUserCode, lie.mntUserName, lie.mntDate,lie.des,lie.inUse,lie.sortNum) "
            + "from LieCubas lie,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where lie.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";
    //罐量指标—球罐罐容
    static final String GlbCubas = "select new GlbCubas(glb.dataId,glb.nodeId,glb.hgt,glb.cuba,glb.presFlrLmt,glb.presUpLmt,t.nodeCode,glb.crtUserCode, glb.crtUserName, glb.crtDate, glb.mntUserCode, glb.mntUserName, glb.mntDate,glb.des,glb.inUse,glb.sortNum) "
            + "from GlbCubas glb,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where glb.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";
    //罐量指标—立罐分米（厘米）
    static final String StddmCuabs = "select new StddmCuabs(st.dataId,st.nodeId,st.hgt,st.dmCuba,t.nodeCode,st.crtUserCode,st.crtUserName,st.crtDate,st.mntUserCode,st.mntUserName,st.mntDate,st.des,st.inUse,st.sortNum) from StddmCuabs st,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org  where st.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";
    //罐量指标—立罐厘米（毫米）
    static final String StdcmmmCubas = "select new StdcmmmCubas(st.dataId,st.nodeId,st.secId,st.secIntPart,st.dmTnkareaVal,st.mmTnkareaVal,t.nodeCode,st.crtUserCode,st.crtUserName,st.crtDate,st.mntUserCode,st.mntUserName,st.mntDate,st.des,st.inUse,st.sortNum) from StdcmmmCubas st,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where st.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";
    //罐量指标—立罐区间
    static final String StdSec = "select new StdSec(st.dataId,st.nodeId,st.secId,st.secFlrLmt,st.secUpLmt,t.nodeCode,st.crtUserCode,st.crtUserName,st.crtDate,st.mntUserCode,st.mntUserName,st.mntDate,st.des,st.inUse,st.sortNum) from StdSec st,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where st.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";
    //采样点与节点关联关系
    static final String MapSampleNode = "select new MapSampleNode(MSN.SnodeMapId,MSN.SnodeId, MSN.MnodeId, a.areaCode, a.name, a.shortName, n.nodeCode, n.nodeName, "
            + "n.nodeAlias,st.samplepointTypeCode,st.samplepointTypeName,n.dataStatus,n.sortNum,n.des,n2.nodeCode,n2.nodeName,n2.nodeAlias) "
            + "from MapSampleNode MSN,NodeDictionary n,NodeDictionary n2,AreaDictionary a,SamplePoint s,SamplepointType st,TPmOrg org "
            + "where MSN.MnodeId=n.nodeId and n.areaId=a.areaDictionaryId and MSN.SnodeId=s.nodeId and s.nodeId=n2.nodeId and s.samplepointTypeId=st.samplepointTypeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";

    /**
     * 生产单元度量指标
     */
    public static final String prdtcellMeasindexs = "select new PrdtcellMeasindex(prdtcellMeasindex.idxCode,prdtcellMeasindex.idxName,prdtcellMeasindex.idxAbbrName,"
            + "idxType.idxTypeCode,idxType.idxTypeName,prdtcell.cellCode,prdtcell.cellName,prdtcell.cellAbbrName,dimension.dimensionCode,dimension.dimensionName,dimension.dimensionAlias,"
            + "prdtcellMeasindex.exchangeRate,prdtcellMeasindex.idxFormula,prdtcellMeasindex.sourceDataType,prdtcellMeasindex.inUse,prdtcellMeasindex.sortNum,prdtcellMeasindex.des) "
            + "from PrdtcellMeasindex prdtcellMeasindex, IdxType idxType, Prdtcell prdtcell, Dimension dimension, AreaDictionary area, Plant plant, TPmOrg org "
            + "where prdtcellMeasindex.idxTypeId = idxType.idxTypeId and prdtcellMeasindex.cellId = prdtcell.cellId and prdtcellMeasindex.dimensionId = dimension.dimensionId and org.orgId = area.factoryId and area.areaDictionaryId = plant.plantId and plant.plantId = prdtcell.areaId ";

    public static final String prdtcells = "select new Prdtcell(prdtcell.cellCode, prdtcell.cellName,prdtcell.cellAbbrName, plant.plantCode, area.name, area.shortName, prdtcell.inUse,prdtcell.sortNum,prdtcell.des) from Prdtcell prdtcell,AreaDictionary area, Plant plant, TPmOrg org where org.orgId = area.factoryId and area.areaDictionaryId = plant.plantId and plant.plantId = prdtcell.areaId ";

    public static final String associatives = "select new Associatives(associatives.associativeId,associatives.nodeId,associatives.nodeCode,"
            + "associatives.nodeName,node.nodeAlias,associatives.mtrlId,associatives.mtrlCode,associatives.mtrlName,mtrl.mtrlAlias,mtrl.upperMtrlCode"
            + ",case  when  (select material.mtrlName from Material  material where material.mtrlCode = mmm.upperMtrlCode )  is NULL then '0' else (select material.mtrlName from Material  material where material.mtrlCode = mmm.upperMtrlCode)  end"
            + ",case  when  (select material.mtrlAlias from Material  material where material.mtrlCode = mmm.upperMtrlCode )  is NULL then '0' else (select material.mtrlAlias from Material  material where material.mtrlCode = mmm.upperMtrlCode)  end"
            + ",v.vcfTypeId,v.vcfTypeCode,v.vcfTypeName,mt.mtrlTypeId,mt.mtrlTypeCode,mt.mtrlTypeName"
            + ",mtrl.dec,d.dimensionId,d.dimensionCode,d.dimensionName,nodeType.nodeTypeId,nodeType.nodeTypeCode,nodeType.nodeTypeName,associatives.inUse"
            + ",associatives.sortNum,associatives.des) "
            + "from Associatives associatives, NodeDictionary node, AreaDictionary a,TPmOrg org, NodeType nodeType,Material mtrl,VcfType v,MtrlType mt,Dimension d,Material mmm "
            + "where associatives.nodeId = node.nodeId and node.nodeTypeId = nodeType.nodeTypeId and associatives.mtrlId = mtrl.mtrlId "
            + "and mtrl.vcfTypeId=v.vcfTypeId and mtrl.mtrlTypeId=mt.mtrlTypeId and mtrl.dimensionId=d.dimensionId and mtrl.mtrlCode=mmm.mtrlCode and a.areaDictionaryId=node.areaId and a.factoryId=org.orgId ";

    public static final String rents = "select new Rent(r.rentId,r.rentName,r.rentCode,r.enable,r.des"
            + ",r.crtUserCode,r.crtUserName,r.crtDate,r.mntUserCode,r.mntUserName,r.mntDate) from Rent r ";


//		public static final String nodeTopDTL="select new NodeTopDTL(nodeTop.dataId,nodeTop.dataCode,nodeTop.snodeId,nodeTop.tnodeId,n1.nodeCode,n1.nodeName,n1.nodeAlias,n2.nodeCode,n2.nodeName,n2.nodeAlias,nodeTop.topId,nodeTop.crtUserId,nodeTop.crtUserName,nodeTop.crtDate,nodeTop.mntUserId,nodeTop.mntUserName,nodeTop.mntDate,nodeTop.version,nodeTop.sortNum,nodeTop.des) "
//				+ "from NodeTopDTL nodeTop,NodeDictionary n1,NodeDictionary n2 "
//				+ "where nodeTop.snodeId=n1.nodeId and nodeTop.tnodeId=n2.nodeId ";

//		//查询工厂下的所有NodeTopDTL
//		public static final String nodeTopDTLs="select new NodeTopDTL(nodeTop.dataId,nodeTop.dataCode,nodeTop.snodeId,nodeTop.tnodeId,n1.nodeCode,n1.nodeName,n1.nodeAlias,n2.nodeCode,n2.nodeName,n2.nodeAlias,nodeTop.topId,nodeTop.crtUserId,nodeTop.crtUserName,nodeTop.crtDate,nodeTop.mntUserId,nodeTop.mntUserName,nodeTop.mntDate,nodeTop.version,nodeTop.sortNum,nodeTop.des) "
//				+ "from NodeTopDTL nodeTop,NodeDictionary n1,NodeDictionary n2,AreaDictionary a,TPmBizOrgDTL tPmBizOrgDTL "
//				+ "where nodeTop.snodeId=n1.nodeId and nodeTop.tnodeId=n2.nodeId and n1.areaId=a.areaDictionaryId and a.factoryId=tPmBizOrgDTL.orgId ";

    /**
     * 拓扑关系明细
     */
    public static final String nodeTopDTLnew = " select new NodeTopDTL(	n.dataId, n.dataCode, n.snodeId, n.tnodeId, snd.nodeName, "
            + "tnd.nodeName,snd.nodeCode, tnd.nodeCode, n.topId, n.crtUserId, n.crtUserName, n.crtDate,n.mntUserId, n.mntUserName,"
            + " n.mntDate, n.des, n.sortNum, n.version,n.inUse, nm.topCode, nm.topName) from NodeTopDTL n,NodeTopMain nm,NodeDictionary snd,"
            + "NodeDictionary tnd,AreaDictionary area,TPmOrg org where n.topId=nm.topId and n.tnodeId=tnd.nodeId and n.snodeId=snd.nodeId and snd.areaId=area.areaDictionaryId and area.factoryId=org.orgId ";

    /**
     * 用户表
     */
    public static final String users = "select new User(user.userId, user.orgId, org.orgCode, org.orgName, org.orgAlias, "
            + "user.userCode,user.userName, user.jobDesc, user.email, user.tel, user.mobile, user.sex, user.birthday, "
            + "user.employeeId, user.expiredTime, user.appUserCode, user.crtUserCode, user.crtUserName,user.crtDate, "
            + "user.mntUserCode, user.mntUserName, user.mntDate, user.des, user.version,user.inUse, user.sortNum) "
            + "from User user,TPmOrg org where user.orgId=org.orgId ";

    /**
     * 班组用户关联表
     */
    public static final String teamAndUsers = "select new TeamAndUser(teamAndUser.teamUserId, teamAndUser.userId, user.userCode, "
            + "user.userName, teamAndUser.orgId, team.orgCode,org.orgName, org1.orgAlias, teamAndUser.crtUserCode, teamAndUser.crtUserName, "
            + "teamAndUser.crtDate, teamAndUser.mntUserCode,teamAndUser.mntUserName, teamAndUser.mntDate, teamAndUser.des, teamAndUser.version, "
            + "teamAndUser.inUse, teamAndUser.sortNum) from TeamAndUser teamAndUser,User user,Team team,TPmOrg org,TPmOrg org1 "
            + "where teamAndUser.userId =user.userId and teamAndUser.orgId = team.orgId and team.orgId =org.orgId and org1.orgId = user.orgId ";

    /**
     * [计量模型-罐量指标]11物料摩尔系数表
     */
    public static final String mtrlMolars = "select new MtrlMolar (mtrlMolar.mtrlMolarId, mtrlMolar.mtrlId, material.mtrlCode, "
            + "material.mtrlName, mtrlMolar.density, mtrlMolar.weight, mtrlMolar.crtUserCode, mtrlMolar.crtUserName, mtrlMolar.crtDate, "
            + "mtrlMolar.mntUserCode, mtrlMolar.mntUserName, mtrlMolar.mntDate, mtrlMolar.des, mtrlMolar.sortNum, mtrlMolar.version, "
            + "mtrlMolar.inUse) from MtrlMolar mtrlMolar, Material material where mtrlMolar.mtrlId = material.mtrlId ";


    /**
     * [计量模型-罐量指标]12球罐压力系数
     */
    public static final String glbPreCoefs = "select new GlbPreCoef (glbPreCoef.glbPreCoefId, glbPreCoef.nodeId, tank.nodeCode, "
            + "node.nodeName, glbPreCoef.presRevCofe,glbPreCoef.presFlrLmt, glbPreCoef.presUpLmt, glbPreCoef.crtUserCode, "
            + "glbPreCoef.crtUserName, glbPreCoef.crtDate,glbPreCoef.mntUserCode, glbPreCoef.mntUserName, glbPreCoef.mntDate, "
            + "glbPreCoef.des, glbPreCoef.sortNum, glbPreCoef.version,glbPreCoef.inUse) from GlbPreCoef glbPreCoef, Tank tank, "
            + "NodeDictionary node, AreaDictionary area, TPmOrg org where glbPreCoef.nodeId = tank.nodeId "
            + "and tank.nodeId = node.nodeId and node.areaId = area.areaDictionaryId and area.factoryId = org.orgId ";


    /**
     * [计量模型-罐量指标]14标准密度表
     */
    public static final String stanDens = "select new StanDen (stanDen.stanDenId, stanDen.temp, stanDen.den, stanDen.mtrlTypeId, "
            + "mtrlType.mtrlTypeCode, mtrlType.mtrlTypeName,stanDen.stanDenVal, stanDen.crtUserCode, stanDen.crtUserName, stanDen.crtDate, "
            + "stanDen.mntUserCode,stanDen.mntUserName, stanDen.mntDate, stanDen.des, stanDen.sortNum, stanDen.version, "
            + "stanDen.inUse) from StanDen stanDen, MtrlType mtrlType where stanDen.mtrlTypeId = mtrlType.mtrlTypeId ";


    public static final String IcStangasden = "select new IcStangasden(icStangasden.stangasdenId,icStangasden.liquidDen,icStangasden.gasDen,"
            + "icStangasden.crtUserCode,icStangasden.crtUserName,icStangasden.crtDate,icStangasden.mntUserCode,icStangasden.mntUserName,"
            + "icStangasden.mntDate,icStangasden.des,icStangasden.sortNum,icStangasden.version,icStangasden.inUse) from IcStangasden icStangasden where 1=1 ";


    public static final String IcMtrlFormCnfg = "select new IcMtrlFormCnfg(icMtrlFormCnfg.mtrlFormCnfgId,icMtrlFormCnfg.mtrlId,mtrl.mtrlCode,"
            + "mtrl.mtrlName,icMtrlFormCnfg.nodeId,node.nodeCode,node.nodeName,icMtrlFormCnfg.tankForm,icMtrlFormCnfg.crtUserCode,icMtrlFormCnfg.crtUserName,icMtrlFormCnfg.crtDate,icMtrlFormCnfg.mntUserCode,icMtrlFormCnfg.mntUserName,"
            + "icMtrlFormCnfg.mntDate,icMtrlFormCnfg.des,icMtrlFormCnfg.sortNum,icMtrlFormCnfg.version,icMtrlFormCnfg.inUse) "
            + "from IcMtrlFormCnfg icMtrlFormCnfg ,Tank tank,Material mtrl,NodeDictionary node "
            + "where icMtrlFormCnfg.mtrlId = mtrl.mtrlId and tank.nodeId = icMtrlFormCnfg.nodeId and node.nodeId = tank.nodeId ";


    /**
     * [计量模型-罐量指标]1 单罐配置
     */
    public static final String tankCnfgs = "select new TankCnfg(tc.tankCnfgId, tc.nodeId, t.nodeCode, tc.stdDen, tc.vtf, tc.modChkForm,tc.totlChkVpf, tc.wtrChkVpf, tc.cubaTempCoef, tc.vcf, tc.vcfDecFraDgt, tc.tnkForm,tc.tnkAirDen, tc.tnkGasForm, tc.tnkFormMode, tc.stdCubaForm, tc.crtUserCode,tc.crtUserName, tc.crtDate, tc.mntUserCode, tc.mntUserName, tc.mntDate, tc.inUse,tc.sortNum, tc.des, tc.version) "
            + "from TankCnfg tc, Tank t, NodeDictionary n,AreaDictionary a,TPmOrg org where tc.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";

    public static final String deltcnfgs = "select new Deltcnfg(d.deltcnfgId, d.deltcnfgVal, d.deltcnfgName, d.deltcnfgType, d.crtUserCode,d.crtUserName, d.crtDate, d.mntUserCode, d.mntUserName, d.mntDate, d.inUse,d.sortNum, d.des, d.version) from Deltcnfg d where 1=1 ";

    public static final String IcPipenettankCoef = "select new IcPipenettankCoef(t.pipenettankCoefId, t.month, t.coefficient, t.crtUserCode,t.crtUserName, t.crtDate, t.mntUserCode, t.mntUserName, t.mntDate, t.des, t.sortNum,t.version,t.inUse) from IcPipenettankCoef t where 1=1 ";


    /**
     * 空气密度修正系数表
     */
    public static final String airDenModCoef = "select new AirDenModCoef(a.denModCoefId, a.denFlrLmt, a.denUpLmt, a.flogModCoef, a.crtUserCode,a.crtUserName, a.crtDate, a.mntUserCode, a.mntUserName, a.mntDate, a.des, a.sortNum, a.version, a.inUse) "
            + "from AirDenModCoef a where 1=1 ";
    /**
     * 物料温度对应VCF表
     */
    public static final String mtrlVcf = "select new MtrlVcf(mv.mtrlVcfId, m.mtrlId, m.mtrlName, m.mtrlAlias, m.mtrlCode, mv.temp, mv.vcfVal, mv.crtUserCode, mv.crtUserName, mv.crtDate, mv.mntUserCode, mv.mntUserName, mv.mntDate, mv.des, mv.sortNum, mv.version, mv.inUse) "
            + " from MtrlVcf mv,Material m where mv.mtrlId=m.mtrlId ";

    public static final String Position = "select new Position(position.positionId,position.positionCode,position.positionName,position.positionAlias,position.crtUserCode,position.crtUserName,position.crtDate,position.mntUserCode,position.mntUserName,position.mntDate,"
            + "position.des,position.version,position.inUse,position.sortNum) from Position position where 1 = 1 ";

    public static final String PositionOrg = "select new PositionOrg(po.positionOrgId,po.positionId,position.positionCode,position.positionName,position.positionAlias,po.orgId,org.orgCode,org.orgName,org.orgAlias,po.crtUserCode,po.crtUserName,po.crtDate,po.mntUserCode,po.mntUserName,po.mntDate,"
            + "po.des,po.version,po.inUse,po.sortNum) from PositionOrg po,Position position,TPmOrg org where po.positionId = position.positionId and po.orgId = org.orgId ";

    public static final String UserPosition = "select new UserPosition(up.userPositionId,up.userId,user.userCode,user.userName,up.positionOrgId,position.positionCode,org.orgCode,position.positionName,org.orgName,up.crtUserCode,up.crtUserName,up.crtDate,up.mntUserCode,up.mntUserName,up.mntDate,"
            + "up.des,up.version,up.inUse,up.sortNum) from UserPosition up,PositionOrg po,Position position,TPmOrg org,User user where up.positionOrgId = po.positionOrgId and po.positionId = position.positionId and po.orgId = org.orgId and up.userId = user.userId ";

    public static final String loadPointType = "select new LoadPointType(t.loadPointTypeId,t.loadPointTypeCode,t.loadPointTypeName,t.des,t.sortNum,t.inUse,t.version,t.crtUserCode,t.crtUserName,t.crtDate,t.mntUserCode,t.mntUserName,t.mntDate) from LoadPointType t where 1=1 ";

    /**
     * 4.13节点-装卸点
     */

    public static final String loadPoints = "select new LoadPoint(loadPoint.nodeId, node.nodeCode, node.nodeName, node.nodeAlias, "
            + "loadPoint.loadPointTypeId,loadPointType.loadPointTypeCode, loadPointType.loadPointTypeName, loadPoint.inOutTypeId, "
            + "loadPoint.formular, node.crtUserCode,node.crtUserName, node.crtDate, node.mntUserCode, node.mntUserName, node.mntDate, "
            + "loadPoint.sortNum,loadPoint.version, area.areaCode, area.shortName, org.orgCode, org.orgAlias, node.nodeLongitude, "
            + "node.nodeLatitude, node.nodeAltitude, nodeType.nodeTypeCode, nodeType.nodeTypeName, node.dataStatus, node.des) "
            + "from LoadPoint loadPoint,LoadPointType loadPointType,NodeDictionary node,NodeType nodeType,LoadingDock loadRack,AreaDictionary area,TPmOrg org "
            + "where loadPoint.nodeId = node.nodeId and loadPoint.loadPointTypeId = loadPointType.loadPointTypeId "
            + "and node.nodeTypeId = nodeType.nodeTypeId and node.areaId = loadRack.loadingDockId and loadRack.loadingDockId = area.areaDictionaryId and area.factoryId = org.orgId ";

    // 装置加工能力单位
    public static final String capacityUnit = "select new CapacityUnit(capacityUnit.capacityUnitId, capacityUnit.capacityUnitCode, "
            + "capacityUnit.capacityUnitName, capacityUnit.des, capacityUnit.sortNum, capacityUnit.inUse, capacityUnit.version, "
            + "capacityUnit.crtUserCode, capacityUnit.crtUserName, capacityUnit.crtDate, capacityUnit.mntUserCode, capacityUnit.mntUserName, "
            + "capacityUnit.mntDate) from CapacityUnit capacityUnit where 1=1 ";

    //装置界区
    static final String unitarea = "select new UnitArea(unitArea.unitAreaId,unitArea.unitAreaCode,unitArea.unitAreaName,"
            + "unitArea.unitAreaAlias,unitArea.orgId,org.orgCode,org.orgName,org.orgAlias,unitArea.crtUserId,unitArea.crtUserName,"
            + "unitArea.crtDate,unitArea.mntUserId,unitArea.mntUserName,unitArea.mntDate,unitArea.dataStatus,unitArea.sortNum,unitArea.des,unitArea.version,"
            + "unitArea.bizId,biz.bizCode,unitArea.rentId,rent.rentCode) "
            + " from UnitArea unitArea,Org org, BizorgMain biz,Rent rent "
            + " where unitArea.orgId = org.orgId and unitArea.bizId=biz.bizId and unitArea.rentId=rent.rentId ";

    //能源管网
    static final String enPipeNet = "select new EnPipeNet(enpipenet.netId,enpipenet.netCode,enpipenet.netName,"
            + "enpipenet.netAlias,enpipenet.upperNetCode,enpipenet.mtrlId,material.mtrlCode,material.mtrlName,"
            + "enpipenet.orgId,org.orgCode,org.orgName,org.orgAlias,enpipenet.crtUserId,enpipenet.crtUserName,enpipenet.crtDate,"
            + "enpipenet.mntUserId,enpipenet.mntUserName,enpipenet.mntDate,enpipenet.dataStatus,enpipenet.sortNum,enpipenet.des,"
            + "enpipenet.version,enpipenet.bizId,biz.bizCode,enpipenet.rentId,rent.rentCode) "
            + " from EnPipeNet enpipenet,Material material,Org org, BizorgMain biz ,Rent rent "
            + " where enpipenet.mtrlId = material.mtrlId and enpipenet.orgId = org.orgId and enpipenet.bizId=biz.bizId and enpipenet.rentId=rent.rentId ";

    //虚拟装置
    static final String ywUnit = "select new YwUnit(ywUnit.areaId,ywUnit.areaCode,ywUnit.areaName,"
            + "ywUnit.areaAlias,ywUnit.unitTypeId,unitType.unitTypeName,ywUnit.technicId,technic.technicName,"
            + "ywUnit.orgId,org.orgCode,org.orgName,org.orgAlias,ywUnit.crtUserId,ywUnit.crtUserName,ywUnit.crtDate,"
            + "ywUnit.mntUserId,ywUnit.mntUserName,ywUnit.mntDate,ywUnit.bizId,biz.bizCode,"
            + "ywUnit.capacity,ywUnit.initialAssetValue,ywUnit.netAssetValue,ywUnit.dataStatus,ywUnit.sortNum,ywUnit.des,"
            + "ywUnit.version,ywUnit.capacityUnitId,case when ywUnit.capacityUnitId is NULL then '' else "
            + "(select capacityUnit.capacityUnitName from CapacityUnit capacityUnit where ywUnit.capacityUnitId = capacityUnit.capacityUnitId) end,"
            + " ywUnit.rentId,rent.rentCode) "
            + " from YwUnit ywUnit,Org org, BizorgMain biz ,UnitType unitType,Technic technic,Rent rent  "
            + " where ywUnit.orgId = org.orgId and ywUnit.bizId = biz.bizId and ywUnit.unitTypeId=unitType.unitTypeId "
            + "and ywUnit.technicId=technic.technicId and ywUnit.rentId=rent.rentId ";

    // 装置与装置界区关系
    public static final String unitAreaRel = "select new UnitAreaRel(unitAreaRel.unitAreaRelId, unitAreaRel.areaId, "
            + "case when unitAreaRel.ofFms=0 then ( select ywUnit.areaCode from YwUnit ywUnit where unitAreaRel.areaId = ywUnit.areaId ) "
            + "else ( select unit.plantCode from Plant unit where unitAreaRel.areaId = unit.plantId ) end, "
            + "case when unitAreaRel.ofFms=0 then ( select ywUnit.areaName from YwUnit ywUnit where unitAreaRel.areaId = ywUnit.areaId ) "
            + "else ( select area.areaName from Plant unit,Area area where unitAreaRel.areaId = unit.plantId and area.areaId = unit.plantId) end, "
            + "case when unitAreaRel.ofFms=0 then ( select ywUnit.areaAlias from YwUnit ywUnit where unitAreaRel.areaId = ywUnit.areaId ) "
            + "else ( select area.areaAlias from Plant unit,Area area where unitAreaRel.areaId = unit.plantId and area.areaId = unit.plantId ) end, "
            + "unitAreaRel.unitAreaId, unitArea.unitAreaCode, unitArea.unitAreaName,unitArea.unitAreaAlias,unitAreaRel.dataStatus, "
            + "unitAreaRel.crtUserId,unitAreaRel.crtUserName,unitAreaRel.crtDate,unitAreaRel.mntUserId, unitAreaRel.mntUserName,"
            + "unitAreaRel.mntDate,unitAreaRel.sortNum, unitAreaRel.version,unitAreaRel.des, unitAreaRel.bizId, biz.bizCode,unitAreaRel.ofFms ,"
            + "unitArea.rentId,rent.rentCode)"
            + " from UnitAreaRel unitAreaRel,UnitArea unitArea, BizorgMain biz,Rent rent "
            + " where unitAreaRel.unitAreaId = unitArea.unitAreaId and unitAreaRel.bizId = biz.bizId and unitArea.rentId=rent.rentId";

    //能源节点
    public static final String enNode = "select new EnNode (enNode.enNodeId, enNode.enNodeCode, enNode.enNodeName, enNode.enNodeAlias, "
            + "enNode.enNodeTypeId, enNodeType.enNodeTypeCode, enNodeType.enNodeTypeName, enNode.formula, enNode.areaId, "
            + "case when enNode.markOfVirtual=1 then ( select ywUnit.areaCode from YwUnit ywUnit where enNode.areaId = ywUnit.areaId ) "
            + "else ( select unit.plantCode from Plant unit where enNode.areaId = unit.plantId ) end, "
            + "case when enNode.markOfVirtual=1 then ( select ywUnit.areaName from YwUnit ywUnit where enNode.areaId = ywUnit.areaId ) "
            + "else ( select area.areaName from Plant unit,Area area where enNode.areaId = unit.plantId and area.areaId = unit.plantId) end, "
            + "case when enNode.markOfVirtual=1 then ( select ywUnit.areaAlias from YwUnit ywUnit where enNode.areaId = ywUnit.areaId ) "
            + "else ( select area.areaAlias from Plant unit,Area area where enNode.areaId = unit.plantId and area.areaId = unit.plantId ) end, "
            + "enNode.bizId, biz.bizCode, enNode.crtUserId, enNode.crtUserName, enNode.crtDate, enNode.mntUserId, "
            + "enNode.mntUserName, enNode.mntDate, enNode.dataStatus, enNode.sortNum, enNode.des, enNode.version,enNode.markOfVirtual, enNode.netId, "
            + "case when enNode.netId is NULL then '' else ( select enpipenet.netCode from EnPipeNet enpipenet where enNode.netId = enpipenet.netId ) end, "
            + "case when enNode.netId is NULL then '' else ( select enpipenet.netName from EnPipeNet enpipenet where enNode.netId = enpipenet.netId ) end, "
            + "case when enNode.netId is NULL then '' else ( select enpipenet.netAlias from EnPipeNet enpipenet where enNode.netId = enpipenet.netId ) end,"
            + " enNodeType.rentId,rent.rentCode) "
            + "from EnNode enNode, EnNodeType enNodeType,  BizorgMain biz ,Rent rent "
            + "where enNode.enNodeTypeId = enNodeType.enNodeTypeId and enNode.bizId = biz.bizId and enNodeType.rentId=rent.rentId ";

    //能源节点类型
    public static final String enNodeType = "select new EnNodeType (enNodeType.enNodeTypeId, enNodeType.enNodeTypeCode, enNodeType.enNodeTypeName, "
            + "enNodeType.bizId, biz.bizCode, enNodeType.des, enNodeType.sortNum, enNodeType.inUse, enNodeType.version, enNodeType.crtUserId, "
            + "enNodeType.crtUserName, enNodeType.crtDate, enNodeType.mntUserId, enNodeType.mntUserName, enNodeType.mntDate,enNodeType.rentId,rent.rentCode )"
            + " from EnNodeType enNodeType, BizorgMain biz ,Rent rent"
            + " where enNodeType.bizId = biz.bizId and enNodeType.rentId=rent.rentId ";

    // 3.8 站/厂
    static final String stations = "select new Station(org.orgCode,station.areaId, org.orgAlias,  station.areaCode,"
            + "area.areaName,area.areaAlias, area.areaTypeId, areaType.areaTypeCode, areaType.areaTypeName, area.inUse,"
            + "org.crtUserId, org.crtUserName, org.crtDate, org.mntUserId, org.mntUserName, org.mntDate,"
            + "station.sortNum, station.version, area.des, org.orgAltitude, org.orgLongitude, org.orgLatitude)"
            + " from Station station,Area area,AreaType areaType,Org org "
            + "where org.orgId = area.orgId and area.areaId = station.areaId and area.areaTypeId = areaType.areaTypeId ";


}
