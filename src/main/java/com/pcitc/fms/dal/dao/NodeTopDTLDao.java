package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;

//@Service
//public interface NodeTopDTLDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.NodeTopDTL>, JpaRepository<com.pcitc.fms.dal.pojo.NodeTopDTL,Integer>{
//
//	public Page<com.pcitc.fms.dal.pojo.NodeTopDTL> findNodeTopDTLs(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTLModel,
//			Pageable pageable);
//	
//	@Query(value="select t.node_type_id from T_PM_NODE t where t.node_name= :nodeName",nativeQuery=true)
//	public Integer findNodeTypeId(@Param ("nodeName") String nodeName);
//	
//	@Query(AreaNodeBasicSql.nodeTopDTL)
//	public List<com.pcitc.fms.dal.pojo.NodeTopDTL> findAllNodeTopDTLs();
//	
//	//查询传入节点所对应的工厂id
//	@Query(value="select t.parent_org_id from T_PM_BIZORGDTL t where t.org_Code=(select o.org_code from T_PM_NODE n ,T_PM_AREA a,T_PM_ORG o WHERE n.area_id=a.area_id and a.org_id=o.org_id and n.node_name= :nodeName)",nativeQuery=true)
//	public Integer findOrgIdByNodeName(@Param ("nodeName") String nodeName);
//	
//	//查询工厂下的所有NodeTopDTL
//	@Query(AreaNodeBasicSql.nodeTopDTLs + " and tPmBizOrgDTL.parentOrgId= :orgId")
//	public List<com.pcitc.fms.dal.pojo.NodeTopDTL> findAllNodeTopDTL(@Param ("orgId")Integer orgId);
//	
//	
//}
