package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.TIcNodetopDTL;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
* Title: TpmNodetopDTLService
* Description: TODO task mark zhenqiang.zhao
* @author zhenqiang.zhao
* @date 2017年11月21日
* @version 1.0
*/
public interface TIcNodetopDTLService {

   public  Pager<TIcNodetopDTL> getNodetopDTLs(com.pcitc.fms.service.model.TIcNodetopDTL TIcNodetopDTL,
       Pageable pageable)throws BusinessException;

   public List<TIcNodetopDTL> getNodetopDTLByCode(String tpmNodetopDTLCode, String opertype)throws BusinessException;

   public void updateNodetopDTL(List<TIcNodetopDTL> tpmNodetopDTLEntityList, String topCode, String tpmNodetopDTLCode)throws BusinessException;

   public void deleteNodetopDTLByCode( String topCode, String tpmNodetopDTLCode) throws BusinessException;







}
