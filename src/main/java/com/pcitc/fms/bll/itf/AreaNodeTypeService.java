package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.TPmAreaNodeType;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
* Title: TpmBizOrgDTLService
* Description: TODO task mark zhenqiang.zhao
* @author zhenqiang.zhao
* @date 2017年11月21日
* @version 1.0
*/
public interface AreaNodeTypeService {

   public  Pager<TPmAreaNodeType> getAreaNodeTypes(com.pcitc.fms.service.model.TPmAreaNodeType tPmAreaNodeType,
       Pageable pageable)throws BusinessException;

   public List<TPmAreaNodeType> getAreaNodeType(String tPmAreaNodeType, String opertype)throws BusinessException;

}
