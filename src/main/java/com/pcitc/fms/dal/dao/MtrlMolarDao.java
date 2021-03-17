package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface MtrlMolarDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.MtrlMolar>, JpaRepository<com.pcitc.fms.dal.pojo.MtrlMolar,Integer>{

	@Transactional
	MyPageImpl findMtrlMolars(com.pcitc.fms.service.model.MtrlMolar mtrlMolar, Pageable pageable);


	
	@Query(value="select count(mt.interv) Amount, mt.interv InterpolateV, length(mt.interv) - instr(mt.interv, '.', 1) Degree, mt.mtrl_id MtrlId, mt.mtrl_code MtrlCode, mt.mtrl_name MtrlName from (select round(main1.density - main2.density, 3) as interv, main1.mtrl_id, main1.mtrl_code, main1.mtrl_name from (select t1.density, t1.mtrl_id, row_number() over(partition by t1.mtrl_id order by t1.density desc) sortid, t1.mtrl_code, t1.mtrl_name from (select distinct t.density, t.mtrl_id,mt.mtrl_code,mt.mtrl_name from T_IC_MTRLMOLAR t, T_PM_MTRL mt where mt.mtrl_id=mt.mtrl_id ) t1) main1, (select t1.density, t1.mtrl_id, row_number() over(partition by t1.mtrl_id order by t1.density desc) sortid, t1.mtrl_code, t1.mtrl_name from (select distinct t.density, t.mtrl_id,mt.mtrl_code,mt.mtrl_name from T_IC_MTRLMOLAR t, T_PM_MTRL mt where t.mtrl_id=mt.mtrl_id ) t1) main2 where main1.sortid = main2.sortid - 1 and main1.mtrl_id = main2.mtrl_id) mt group by mt.interv, mt.mtrl_id,mt.mtrl_code,mt.mtrl_name  order by amount desc",nativeQuery=true)
	List<Object> findMtrlMolarDegrees();
}
