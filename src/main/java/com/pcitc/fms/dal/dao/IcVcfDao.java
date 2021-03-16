package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.IcVcf;

public interface IcVcfDao extends JpaRepository<IcVcf, Integer> {

	@Transactional
	public MyPageImpl findVcfs(com.pcitc.fms.service.model.IcVcf icVcf, Pageable pageable);

	@Query(value="select count(mt.interv) amount,mt.interv InterpolateV,mt.VCFTYPE_ID MtrlTypeId,mt.vcftype_code MtrlTypeCode,mt.vcftype_name MtrlTypeName from (select round(main1.temp - main2.temp, 3) as interv, main1.VCFTYPE_ID, main1.vcftype_code, main1.vcftype_name from (select t1.temp, t1.VCFTYPE_ID, t1.vcftype_code, t1.vcftype_name, row_number() over(partition by t1.VCFTYPE_ID order by t1.temp desc) sortid from (select distinct t.temp, vt.VCFTYPE_ID, vt.vcftype_code, vt.vcftype_name from T_IC_VCF t, T_PM_VCFTYPE vt where t.vcftype_id = vt.vcftype_id) t1) main1, (select t1.temp, t1.VCFTYPE_ID, row_number() over(partition by t1.VCFTYPE_ID order by t1.temp desc) sortid, t1.vcftype_code, t1.vcftype_name from (select distinct t.temp, vt.VCFTYPE_ID,vt.vcftype_code,vt.vcftype_name from T_IC_VCF t, T_PM_VCFTYPE vt where t.vcftype_id = vt.vcftype_id) t1) main2 where main1.sortid = main2.sortid - 1 and main1.VCFTYPE_ID = main2.VCFTYPE_ID) mt group by mt.interv, mt.VCFTYPE_ID, mt.vcftype_code, mt.vcftype_name order by amount desc",nativeQuery=true)
	public List<Object> findVcfDegrees();
}
