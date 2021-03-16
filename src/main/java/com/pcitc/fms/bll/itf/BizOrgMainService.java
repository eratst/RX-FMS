package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.TPmBizOrgMain;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* Title: BizOrgMainService
* Description: TODO task mark zhenqiang.zhao
* @author zhenqiang.zhao
* @date 2017年11月21日
* @version 1.0
*/
public interface BizOrgMainService {

   public Pager<TPmBizOrgMain> getBizOrgMains(com.pcitc.fms.service.model.TPmBizOrgMain TPmBizOrgMain, Pageable pageable)throws BusinessException;

   public TPmBizOrgMain getBizOrgMainByCode(String headquarterCode)throws BusinessException;

}
