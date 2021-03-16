package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: BizOrgDTLDao Description: TODO task mark zhenqiang.zhao
 * 
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface BizOrgDTLDao extends JpaSpecificationExecutor<TPmBizOrgDTL>, JpaRepository<TPmBizOrgDTL, Integer> {

	public List<String> getLeavesCodes(String orgCode,String bizCode);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and orgDTL.orgCode = :orgCode")
	TPmBizOrgDTL getBizOrgDTLByOrgCode(@Param("orgCode") String orgCode);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and orgDTL.orgId = :orgId")
	List<TPmBizOrgDTL> findBizOrgDTLByOrgId(@Param("orgId") Long orgId);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and orgDTL.orgCode = :orgCode and orgMain.bizId =:bizId")
	TPmBizOrgDTL getBizOrgDTLByOrgCodeAndBizId(@Param("orgCode") String orgCode, @Param("bizId") Long bizId);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and orgDTL.orgCode in :orgCodes")
	List<TPmBizOrgDTL> getBizOrgDTLByCode(@Param("orgCodes") List<String> orgCodes);


	@Query(AreaNodeBasicSql.bizOrgDTLs + " and orgDTL.orgCode in :orgCodes and orgMain.bizCode =:bizCode")
	List<TPmBizOrgDTL> getBizOrgDTLByCodeAndBizCode(@Param("orgCodes") List<String> orgCodes,
			@Param("bizCode") String bizCode);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " order by org.orgCode")
	List<TPmBizOrgDTL> getBizOrgDTLs();

	List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndChildren(Long orgId, String bizCode, Integer inUse,
			com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel, Pageable pageable);

	@Query(AreaNodeBasicSql.bizOrgDTLs
			+ " and org.orgId in :orgIds and orgDTL.inUse = 1 and orgMain.bizCode = :bizCode")
	List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndAncestors(@Param("orgIds") List<Long> orgIds,
			@Param("bizCode") String bizCode);

	public List<String> getAllChildrenOrgCodes(String orgCode,Integer levelValue);

	@Query(value = "select t.org_code from (select t1.* from T_PM_BIZORGDTL t1 where t1.inUse=1) t where FIND_IN_SET(t.ORG_CODE, getDtlChildByCode(:orgCode))", nativeQuery = true)
	public List<String> getAllChildrenOrgCodesNotLevel(@Param("orgCode") String orgCode);

	@Transactional
	List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndAllChildren(List<String> orgCodes, String bizCode,
			com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel, Pageable pageable);

	@Query(value = "select t.org_code from (select t1.* from T_PM_BIZORGDTL t1 where t1.inUse=1) t where FIND_IN_SET(t.ORG_CODE, getDtlParentByCode(:orgCode))", nativeQuery = true)
	public List<String> getAllToRootOrgCodes(@Param("orgCode") String orgCode);

	@Transactional
	List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndAllToRoot(List<String> orgCodes, String bizCode,
			com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel, Pageable pageable);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and org.orgId in :orgIds")
	List<TPmBizOrgDTL> getBizOrgDTLByOrgIds(@Param("orgIds") List<Integer> orgIds);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and org.orgId = :orgId")
	TPmBizOrgDTL getBizOrgDTLByOrgId(@Param("orgId") Integer orgId);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and org.orgId = :orgId and orgMain.bizCode =:bizCode")
	TPmBizOrgDTL getBizOrgDTLByOrgIdAndBizCode(@Param("orgId") Long orgId, @Param("bizCode") String bizCode);

	@Transactional
	@SQLDelete(sql = "delete from TPmBizOrgDTL where org.orgCode = :orgCode")
	void deleteByOrgCode(@Param("orgCode") String orgCode);

	@Transactional
	public MyPageImpl findBizOrgDTLs(com.pcitc.fms.service.model.TPmBizOrgDTL BizOrgDTLModel, Pageable pageable);

	@Query(AreaNodeBasicSql.bizOrgDTLs + " and org.orgCode = :orgCode ")
	TPmBizOrgDTL findBizOrgDTLByorgCode(@Param("orgCode") String orgCode);

	List<TPmBizOrgDTL> findBizOrgDTLByorgCodeIn(List<String> orgCodes);

	void deleteByorgCode(String orgcode);

	//从dtl中关联查到节点
	@Query(value = "select t2.node_code,t2.node_name,t2.node_alias, "
			+" (select nodetype.nodetype_name from T_PM_NODETYPE nodetype  where nodetype.nodetype_id = t2.nodetype_id) as nodetypename, "
			+" t1.area_code,  t1.area_name,  t1.area_alias,  "
			+" (select areatype.areatype_name from T_PM_AREATYPE areatype  where areatype.areatype_id = t1.areatype_id) as areatypename, "
			+" t.org_code,  t.org_name,   t.org_alias,  "
			+" (select orgtype.orgtype_name from T_PM_ORGTYPE orgtype    where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+" (select dtl1.org_code  from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1 where dtl1.org_id = t.parent_org_id   and biz1.bizorgmain_id = dtl1.bizorgmain_id and biz1.bizorgmain_code = ':bizCode') as upperorgcode,   "
			+" (select dtl1.org_name from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1   where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id and biz1.bizorgmain_code = ':bizCode') as upperorgname, " 
			+" (select dtl1.org_alias from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1  where dtl1.org_id = t.parent_org_id and biz1.bizorgmain_id = dtl1.bizorgmain_id and biz1.bizorgmain_code = ':bizCode') as upperorgalias  "
			+" from  "
			+" (select * from (select dtl.*  from T_PM_BIZORGDTL dtl, T_PM_BIZORGMAIN biz  where dtl.bizorgmain_id = biz.bizorgmain_id and biz.bizorgmain_code = ':bizCode') trees  "
			+" where FIND_IN_SET(trees.ORG_CODE, getDtlChildByCode(':orgCode'))) t  "
			+" left join T_PM_AREA t1 on t.org_id = t1.org_id "
			+" left join T_PM_NODE t2 on t2.area_id = t1.area_id", nativeQuery = true)
	public List<Object> getFMSObjectTreeToNode(@Param("bizCode") String bizCode, @Param("orgCode") String orgCode);

	//从dtl中关联查到度量指标
	@Query(value = "select mes.measindex_code,mes.measindex_name, mes.measindex_alias,"
			+" (select idx.idxtype_name from T_PM_IDXTYPE idx where idx.idxtype_id = mes.idxtype_id) as idxTypeName, "
			+" t2.node_code,t2.node_name, t2.node_alias, "
			+" (select nodetype.nodetype_name from T_PM_NODETYPE nodetype  where nodetype.nodetype_id = t2.nodetype_id) as nodetypename, "
			+" t1.area_code, t1.area_name, t1.area_alias, "
			+" (select areatype.areatype_name from T_PM_AREATYPE areatype  where areatype.areatype_id = t1.areatype_id) as areatypename, "
			+" t.org_code,   t.org_name, t.org_alias,"
			+" (select orgtype.orgtype_name from T_PM_ORGTYPE orgtype  where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+" (select dtl1.org_code   from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1 where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id and biz1.bizorgmain_code = ':bizCode') as upperorgcode,  "
			+" (select dtl1.org_name from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1   where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgname, "
			+" (select dtl1.org_alias  from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1 where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgalias  "
			+" from (select * from (select dtl.*  from T_PM_BIZORGDTL dtl, T_PM_BIZORGMAIN biz where dtl.bizorgmain_id = biz.bizorgmain_id  and biz.bizorgmain_code = ':bizCode') trees  where FIND_IN_SET(trees.ORG_CODE, getDtlChildByCode(':orgCode'))) t " 
			+" left join T_PM_AREA t1 on t.org_id = t1.org_id "
			+" left join T_PM_NODE t2 on t2.area_id = t1.area_id  "
			+" left join T_PM_MEASINDEX mes on t2.node_id = mes.node_id", nativeQuery = true)
	public List<Object> getFMSObjectTreeToMeasurement(@Param("bizCode") String bizCode,
			@Param("orgCode") String orgCode);

	//从dtl中关联查到区域
	@Query(value = "select t1.area_code,t1.area_name,  t1.area_alias, "
			+" (select areatype.areatype_name from T_PM_AREATYPE areatype where areatype.areatype_id = t1.areatype_id) as areatypename,  "
			+" t.org_code, t.org_name, t.org_alias,  "
			+" (select orgtype.orgtype_name from T_PM_ORGTYPE orgtype   where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+" (select dtl1.org_code  from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1 where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgcode, " 
			+" (select dtl1.org_name  from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1 where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgname, " 
			+" (select dtl1.org_alias from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1 where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgalias " 
			+" from (select * from (select dtl.*  from T_PM_BIZORGDTL dtl, T_PM_BIZORGMAIN biz where dtl.bizorgmain_id = biz.bizorgmain_id and biz.bizorgmain_code = ':bizCode') trees where FIND_IN_SET(trees.ORG_CODE, getDtlChildByCode(':orgCode'))) t  " 
			+" left join T_PM_AREA t1 on t.org_id = t1.org_id", nativeQuery = true)
	public List<Object> getFMSObjectTreeToArea(@Param("bizCode") String bizCode, @Param("orgCode") String orgCode);

	//从dtl中查组织机构树
	@Query(value = "select t.org_code,t.org_name, t.org_alias, "
			+" (select orgtype.orgtype_name from T_PM_ORGTYPE orgtype where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+" (select dtl1.org_code  from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1  where dtl1.org_id = t.parent_org_id and biz1.bizorgmain_id = dtl1.bizorgmain_id and biz1.bizorgmain_code = ':bizCode') as upperorgcode,  "
			+" (select dtl1.org_name from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1  where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgname, "
			+" (select dtl1.org_alias  from T_PM_BIZORGDTL dtl1, T_PM_BIZORGMAIN biz1  where dtl1.org_id = t.parent_org_id  and biz1.bizorgmain_id = dtl1.bizorgmain_id  and biz1.bizorgmain_code = ':bizCode') as upperorgalias  "
			+" from (select *  from (select dtl.*  from T_PM_BIZORGDTL dtl, T_PM_BIZORGMAIN biz  where dtl.bizorgmain_id = biz.bizorgmain_id and biz.bizorgmain_code = ':bizCode') trees where FIND_IN_SET(trees.ORG_CODE, getDtlChildByCode(':orgCode'))) t ", nativeQuery = true)
	public List<Object> getFMSObjectTreeToOrg(@Param("bizCode") String bizCode, @Param("orgCode") String orgCode);

	//在org表中从本级开始查询下级的组织机构树结构（包含本级）
	@Query(value = "select t.org_code, t.org_name, t.org_alias, "
			+"	(select orgtype.orgtype_name from T_PM_ORGTYPE orgtype   where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+"	(select innert.org_code   from T_PM_ORG innert where innert.org_id = t.upper_org_id) as upper_org_code,  "
			+"	(select innert.org_name from T_PM_ORG innert  where innert.org_id = t.upper_org_id) as upper_org_name, "
			+"	(select innert.org_alias from T_PM_ORG innert where innert.org_id = t.upper_org_id) as upper_org_alias "
			+"	 from T_PM_ORG t "
			+"	where FIND_IN_SET(t.org_id, getOrgChildById(:orgId))", nativeQuery = true)
	public List<Object> getFMSOrgFromStandard(@Param("orgId") Long orgId);
 
	//在org表中从本级查到最上层的根（包含本级）
	@Query(value = "select t.org_code, t.org_name, t.org_alias, "
			+"	(select orgtype.orgtype_name from T_PM_ORGTYPE orgtype   where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+"	(select innert.org_code   from T_PM_ORG innert where innert.org_id = t.upper_org_id) as upper_org_code,  "
			+"	(select innert.org_name from T_PM_ORG innert  where innert.org_id = t.upper_org_id) as upper_org_name, "
			+"	(select innert.org_alias from T_PM_ORG innert where innert.org_id = t.upper_org_id) as upper_org_alias "
			+"	 from T_PM_ORG t "
			+"	where FIND_IN_SET(t.org_id, getOrgParentById(:orgId))", nativeQuery = true)
	public List<Object> getFMSOrgFromStandardToUp(@Param("orgId") Long orgId);

	//从org表关联到节点
	@Query(value = "SELECT t2.node_code,t2.node_name,t2.node_alias,"
			+" (SELECT nodetype.nodetype_name FROM T_PM_NODETYPE nodetype WHERE nodetype.nodetype_id = t2.nodetype_id ) AS nodetypename,"
			+" t1.area_code,t1.area_name,t1.area_alias,"
			+" (SELECT areatype.areatype_name FROM T_PM_AREATYPE areatype WHERE areatype.areatype_id = t1.areatype_id) AS areatypename,"
			+" t.org_code,t.org_name,t.org_alias,"
			+" (SELECT orgtype.orgtype_name FROM T_PM_ORGTYPE orgtype WHERE orgtype.orgtype_id = t.orgtype_id ) AS orgtypename,"
			+" (SELECT dtl1.org_code FROM T_PM_ORG dtl1 WHERE dtl1.org_id = t.upper_org_id) AS upperorgcode,"
			+" (SELECT dtl1.org_name FROM T_PM_ORG dtl1 WHERE dtl1.org_id = t.upper_org_id) AS upperorgname,"
			+" (SELECT dtl1.org_alias FROM T_PM_ORG dtl1 WHERE dtl1.org_id = t.upper_org_id) AS upperorgalias"
			+" FROM"
			+" (SELECT * FROM T_PM_ORG org WHERE FIND_IN_SET(org.org_id, getOrgChildById(:orgId))) t"
			+" LEFT JOIN T_PM_AREA t1 ON t.org_id = t1.org_id"
			+" LEFT JOIN T_PM_NODE t2 ON t2.area_id = t1.area_id", nativeQuery = true)
	public List<Object> getFMSNodeFromStandard(@Param("orgId") Long orgId);

	//从org表关联到度量指标
	@Query(value = "select mes.measindex_code,mes.measindex_name, mes.measindex_alias,"
			+" (select idx.idxtype_name from T_PM_IDXTYPE idx where idx.idxtype_id = mes.idxtype_id) as idxTypeName,"
			+" t2.node_code, t2.node_name,t2.node_alias,  "
			+" (select nodetype.nodetype_name from T_PM_NODETYPE nodetype where nodetype.nodetype_id = t2.nodetype_id) as nodetypename, "  
			+" t1.area_code, t1.area_name, t1.area_alias,  " 
			+" (select areatype.areatype_name from T_PM_AREATYPE areatype where areatype.areatype_id = t1.areatype_id) as areatypename,"   
			+" t.org_code, t.org_name, t.org_alias,"    
			+" (select orgtype.orgtype_name  from T_PM_ORGTYPE orgtype  where orgtype.orgtype_id = t.orgtype_id) as orgtypename, "
			+" (select dtl1.org_code from T_PM_ORG dtl1  where dtl1.org_id = t.upper_org_id ) as upperorgcode,  "  
			+" (select dtl1.org_name  from T_PM_ORG dtl1 where dtl1.org_id = t.upper_org_id) as upperorgname,"    
			+" (select dtl1.org_alias from T_PM_ORG dtl1  where dtl1.org_id = t.upper_org_id) as upperorgalias "
			+" from (select * from T_PM_ORG org where FIND_IN_SET(org.org_id, getOrgChildById(:orgId))) t "
			+" left join T_PM_AREA t1 on t.org_id = t1.org_id "
			+" left join T_PM_NODE t2 on t2.area_id = t1.area_id "
			+" left join T_PM_MEASINDEX mes on t2.node_id = mes.node_id", nativeQuery = true)
	public List<Object> getFMSMeasurementFromStandard(@Param("orgId") Long orgId);

	//从org表关联到区域
	@Query(value = "select t1.area_code, t1.area_name,t1.area_alias,  "
			+" (select areatype.areatype_name  from T_PM_AREATYPE areatype  "
			+" where areatype.areatype_id = t1.areatype_id) as areatypename,  t.org_code,  "
			+" t.org_name, t.org_alias, (select orgtype.orgtype_name  "
			+" from T_PM_ORGTYPE orgtype  where orgtype.orgtype_id = t.orgtype_id) as orgtypename,  "
			+" (select dtl1.org_code from T_PM_ORG dtl1  where dtl1.org_id = t.upper_org_id  "
			+" ) as upperorgcode,(select dtl1.org_name  from T_PM_ORG dtl1  "
			+" where dtl1.org_id = t.upper_org_id) as upperorgname, (select dtl1.org_alias  "
			+" from T_PM_ORG dtl1  where dtl1.org_id = t.upper_org_id) as upperorgalias  "
			+" from (select *  from T_PM_ORG org where FIND_IN_SET(org.org_id, getOrgChildById(:orgId))) t left join T_PM_AREA t1 on t.org_id = t1.org_id ", nativeQuery = true)
	public List<Object> getFMSAreaFromStandard(@Param("orgId") Long orgId);

}
