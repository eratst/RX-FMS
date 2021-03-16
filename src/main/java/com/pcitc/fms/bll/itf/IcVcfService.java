package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.IcVcf;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.VcfDegree;

/**
 * 
 * @author xin.kou
 *
 */
@Service
public interface IcVcfService {
	/**
	 * @Title: findAll @Description: 查询Vcf所有数据 @return @throws Exception @return
	 *         List<IcVcf> 返回类型 @throws
	 */
	public Pager<IcVcf> findIcVcf(com.pcitc.fms.service.model.IcVcf icVcf, Pageable pageable) throws Exception;


	public List getVcfDegree(VcfDegree model);
}
