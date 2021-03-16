package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.IcVcf;

/**
 * 
 * @author xin.kou
 *
 */
public class IcVcfDaoImpl {
	@PersistenceContext
	private EntityManager entityManager;
	
	public MyPageImpl findVcfs(com.pcitc.fms.service.model.IcVcf icVcf, Pageable pageable){
		
		final String vcfs = "select new IcVcf(i.vcfId,i.den,i.temp,i.vcfVal,i.vcfTypeId,i.crtUserCode,i.crtUserName,i.crtDate,i.mntUserCode,i.mntUserName,i.mntDate,i.des,i.sortNum,i.version,v.vcfTypeCode,v.vcfTypeName) from IcVcf i,VcfType v where 1=1 and i.vcfTypeId=v.vcfTypeId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(vcfs);
		
		String countIcVcf="select count(1) from IcVcf i,VcfType v where 1=1 and i.vcfTypeId=v.vcfTypeId";
		StringBuilder countSql =new StringBuilder();
		countSql.append(countIcVcf);
		
		Map<String, Object> parameterMap = new HashMap<>();
		if (null != icVcf.getVcfTypeCode() && !StringUtils.isEmpty(String.valueOf(icVcf.getVcfTypeCode()))) {
			dataSql.append(" and v.vcfTypeCode = :vcfTypeCode");
			countSql.append(" and v.vcfTypeCode = :vcfTypeCode");
			parameterMap.put("vcfTypeCode", icVcf.getVcfTypeCode());
		}
		if (null != icVcf.getVcfTypeName() && !StringUtils.isEmpty(icVcf.getVcfTypeName())) {
			dataSql.append(" and v.vcfTypeName like :vcfTypeName");
			countSql.append(" and v.vcfTypeName like :vcfTypeName");
			parameterMap.put("vcfTypeName", "%" + icVcf.getVcfTypeName() + "%");
		}
		if (null != icVcf.getCodeList() && !icVcf.getCodeList().isEmpty()) {
			dataSql.append(" and v.vcfTypeCode in :codeList");
			countSql.append(" and v.vcfTypeCode in :codeList");
			parameterMap.put("codeList", icVcf.getCodeList());
		}
		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());
			
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = icVcf.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.IcVcf> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
		
	}
}
