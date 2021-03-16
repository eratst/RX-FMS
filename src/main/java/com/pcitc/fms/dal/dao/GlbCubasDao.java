package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.GlbCubas;

public interface GlbCubasDao extends JpaSpecificationExecutor<GlbCubas>, JpaRepository<GlbCubas,Integer> {
	@Transactional
	public MyPageImpl findGlbCubas(com.pcitc.fms.service.model.GlbCubas glbCubasModel,Pageable pageable);
	
	@Query(value="select count(mt.interv) amount,mt.interv InterpolateV,length(mt.interv) - instr(mt.interv, '.', 1) Degree,mt.NODE_ID TankId, mt.node_code, mt.node_name from (select round(main1.GLBCUBA_HGT - main2.GLBCUBA_HGT, 3) as interv, main1.NODE_ID, main1.node_code, main1.node_name from "
			+ "(select t1.GLBCUBA_HGT,t1.NODE_ID,row_number() over(partition by t1.NODE_ID order by t1.GLBCUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.GLBCUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_GLBCUBA t,T_PM_NODE n,T_PM_AREA a,T_PM_ORG o where t.node_id=n.node_id and a.org_id=o.org_id and n.area_id=a.area_id and o.org_code in :orgCodes) t1) main1,"
			+ "(select t1.GLBCUBA_HGT,t1.NODE_ID,row_number() over(partition by t1.NODE_ID order by t1.GLBCUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.GLBCUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_GLBCUBA t,T_PM_NODE n,T_PM_AREA a,T_PM_ORG o where t.node_id=n.node_id and a.org_id=o.org_id and n.area_id=a.area_id and o.org_code in :orgCodes) t1) main2 "
			+ "where main1.sortid = main2.sortid - 1and main1.NODE_ID = main2.NODE_ID) mt group by mt.interv, mt.NODE_ID,mt.node_code,mt.node_name order by amount desc",nativeQuery=true)
	public List<Object> findGlbCubaDegree(@Param("orgCodes") List<String> orgCodes);
	
	@Query(value="select count(mt.interv) amount, mt.interv InterpolateV, length(mt.interv) - instr(mt.interv, '.', 1) Degree, mt.NODE_ID TankId, mt.node_code, mt.node_name from (select round(main1.GLBCUBA_HGT - main2.GLBCUBA_HGT, 3) as interv, main1.NODE_ID, main1.node_code, main1.node_name from "
			+ "(select t1.GLBCUBA_HGT, t1.NODE_ID, row_number() over(partition by t1.NODE_ID order by t1.GLBCUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.GLBCUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_GLBCUBA t, T_PM_NODE n where t.node_id = n.node_id) t1) main1, "
			+ "(select t1.GLBCUBA_HGT, t1.NODE_ID, row_number() over(partition by t1.NODE_ID order by t1.GLBCUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.GLBCUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_GLBCUBA t, T_PM_NODE n where t.node_id = n.node_id) t1) main2 "
			+ "where main1.sortid = main2.sortid - 1 and main1.NODE_ID = main2.NODE_ID) mt group by mt.interv, mt.NODE_ID,mt.node_code,mt.node_name order by amount desc",nativeQuery=true)
	public List<Object> findGlbCubaDegrees();
	
	
	/**
	 * 带罐编码和罐名称
	 */
	//"select count(mt.interv) amount, mt.interv InterpolateV, length(mt.interv) - instr(mt.interv, '.', 1) Degree, mt.NODE_ID TankId, mt.node_code, mt.node_name from (select round(main1.GLBCUBA_HGT - main2.GLBCUBA_HGT, 3) as interv, main1.NODE_ID, main1.node_code, main1.node_name from (select t1.GLBCUBA_HGT, t1.NODE_ID, row_number() over(partition by t1.NODE_ID order by t1.GLBCUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.GLBCUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_GLBCUBA t, T_PM_NODE n where t.node_id = n.node_id) t1) main1, (select t1.GLBCUBA_HGT, t1.NODE_ID, row_number() over(partition by t1.NODE_ID order by t1.GLBCUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.GLBCUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_GLBCUBA t, T_PM_NODE n where t.node_id = n.node_id) t1) main2 where main1.sortid = main2.sortid - 1 and main1.NODE_ID = main2.NODE_ID) mt group by mt.interv, mt.NODE_ID,mt.node_code,mt.node_name order by amount desc"


	

}
