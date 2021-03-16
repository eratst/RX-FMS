package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.TPmBizOrgDTL;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* Title: TpmBizOrgDTLService
* Description: TODO task mark zhenqiang.zhao
* @author zhenqiang.zhao
* @date 2017年11月21日
* @version 1.0
*/
public interface BizOrgDTLService {

   public Pager<TPmBizOrgDTL> getBizOrgDTLs(com.pcitc.fms.service.model.TPmBizOrgDTL TPmBizOrgDTL, Pageable pageable)throws BusinessException;

   public List<TPmBizOrgDTL> getBizOrgDTLByCode(String tpmBizOrgDTLCode, String opertype, List<String> orgCodes, String bizCode, com.pcitc.fms.service.model.TPmBizOrgDTL bizOrgDTLsTableModel, Pageable pageable)throws BusinessException;

}
