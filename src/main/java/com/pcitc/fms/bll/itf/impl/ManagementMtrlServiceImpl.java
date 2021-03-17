package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.ManagementMtrl;
import com.pcitc.fms.bll.itf.ManagementMtrlService;
import com.pcitc.fms.dal.dao.ManagementMtrlDao;
import com.pcitc.fms.dal.dao.MtrlTypeDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.core.http.HttpServerRequest;
import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class ManagementMtrlServiceImpl implements ManagementMtrlService {

	@Autowired
	private ManagementMtrlDao managementMtrlDao;

	@Autowired
	private MtrlTypeDao mtrlTypeDao;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<ManagementMtrl> getPageManagementMtrl(String uriStr,
			com.pcitc.fms.service.model.ManagementMtrl managementMtrl, Pageable pageable) throws Exception {

		if (StringUtils.isEmpty(managementMtrl.getMtrlTypeCode())) {
			throw new BusiException("", "物料类型编码不能为空！");
		}
		List<com.pcitc.fms.dal.pojo.MtrlType> pojoMtrlTypeList = new ArrayList<>();
		try {
			pojoMtrlTypeList = mtrlTypeDao.findByMtrlTypeCode(managementMtrl.getMtrlTypeCode());
		} catch (Exception e) {
			throw new BusiException("", e.getMessage());
		}

		if (null == pojoMtrlTypeList || pojoMtrlTypeList.isEmpty()) {
			throw new BusiException("", "根据物料类型编码获取物料类型失败！");
		}
		if (uriStr.contains("inUse")) {
			Integer dataStauts = managementMtrl.getInUse();
			if (null == dataStauts) {
				throw new BusiException("", "查询范围不能为空！");
			}
			if (1 != dataStauts && 0 != dataStauts && -1 != dataStauts) {
				throw new BusiException("", "查询范围参数值无效！");
			}
		}

		Pager<ManagementMtrl> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.ManagementMtrl> properties = managementMtrlDao.findManagementMtrls(managementMtrl,
				pageable);
		List<ManagementMtrl> entityList = ObjectConverter.listConverter(properties.getContent(), ManagementMtrl.class);

		List<ManagementMtrl> managementMtrlResult = new ArrayList<>();

		for (int i = 0; i < entityList.size(); i++) {
			for (int j = i + 1; j < entityList.size(); j++) {
				if (entityList.get(i).getMtrlCode().equals(entityList.get(j).getUpperMtrlCode())) {
					managementMtrlResult.add(entityList.get(i));
					managementMtrlResult.add(entityList.get(j));
				}
			}
		}
		if (managementMtrlResult.size() == 0) {
			pageData.setContent(entityList);
		} else {
			pageData.setContent(managementMtrlResult);
		}

		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(Long.valueOf(String.valueOf(pageData.getContent().size())));
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

	@Override
	public List<Object> getManagementMtrls() throws Exception {
		return null;
	}

}
