package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Enterprise;
import com.pcitc.fms.service.model.Pager;

/**
 * Title: EnterpriseService Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
public interface EnterpriseService {

  public Pager<Enterprise> getEnterprises(com.pcitc.fms.service.model.Enterprise Enterprise,
      Pageable pageable) throws BusinessException;

  public Enterprise getEnterpriseByCode(String enterpriseCode) throws BusinessException;

}
