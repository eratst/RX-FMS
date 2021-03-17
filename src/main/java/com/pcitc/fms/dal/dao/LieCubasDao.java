package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.LieCubas;

public interface LieCubasDao extends JpaRepository<LieCubas, Integer>, JpaSpecificationExecutor<LieCubas>{
	
	@Transactional
	public MyPageImpl getLieCubas(com.pcitc.fms.service.model.LieCubas lieCubas,Pageable pageable);

	@Query(value="select count(mt.interv) amount, mt.interv InterpolateV, length(mt.interv) - instr(mt.interv, '.', 1) Degree, mt.NODE_ID TankId, mt.node_code, mt.node_name from (select round(main1.LIECUBA_HGT - main2.LIECUBA_HGT, 3) as interv, main1.NODE_ID, main1.node_code, main1.node_name from "
			+ "(select t1.LIECUBA_HGT, t1.NODE_ID, row_number() over(partition by t1.NODE_ID order by t1.LIECUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.LIECUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_LIECUBA t, T_PM_NODE n where t.node_id = n.node_id) t1) main1, "
			+ "(select t1.LIECUBA_HGT, t1.NODE_ID, row_number() over(partition by t1.NODE_ID order by t1.LIECUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.LIECUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_LIECUBA t, T_PM_NODE n where t.node_id = n.node_id) t1) main2 "
			+ "where main1.sortid = main2.sortid - 1 and main1.NODE_ID = main2.NODE_ID) mt group by mt.interv, mt.NODE_ID,mt.node_code,mt.node_name order by amount desc",nativeQuery=true)
	public List<Object> findLieCubaDegrees();

	
	@Query(value="select count(mt.interv) amount,mt.interv InterpolateV,length(mt.interv) - instr(mt.interv, '.', 1) Degree,mt.NODE_ID TankId, mt.node_code, mt.node_name from (select round(main1.LIECUBA_HGT - main2.LIECUBA_HGT, 3) as interv, main1.NODE_ID, main1.node_code, main1.node_name from "
			+ "(select t1.LIECUBA_HGT,t1.NODE_ID,row_number() over(partition by t1.NODE_ID order by t1.LIECUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.LIECUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_LIECUBA t,T_PM_NODE n,T_PM_AREA a,T_PM_ORG o where t.node_id=n.node_id and a.org_id=o.org_id and n.area_id=a.area_id and o.org_code in :orgCodes) t1) main1,"
			+ "(select t1.LIECUBA_HGT,t1.NODE_ID,row_number() over(partition by t1.NODE_ID order by t1.LIECUBA_HGT desc) sortid, t1.node_code, t1.node_name from (select distinct t.LIECUBA_HGT, t.NODE_ID,n.node_code,n.node_name from T_IC_LIECUBA t,T_PM_NODE n,T_PM_AREA a,T_PM_ORG o where t.node_id=n.node_id and a.org_id=o.org_id and n.area_id=a.area_id and o.org_code in :orgCodes) t1) main2 "
			+ "where main1.sortid = main2.sortid - 1and main1.NODE_ID = main2.NODE_ID) mt group by mt.interv, mt.NODE_ID,mt.node_code,mt.node_name order by amount desc",nativeQuery=true)
	public List<Object> findLieCubaDegree(@Param("orgCodes") List<String> rentOrgCodes);
	
	
	
	
	
}
