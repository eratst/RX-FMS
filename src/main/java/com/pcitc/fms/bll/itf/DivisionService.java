package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Division;
import com.pcitc.fms.service.model.Pager;

/**
 * Title: DivisionService Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
public interface DivisionService {

  public Pager<Division> getDivisions(com.pcitc.fms.service.model.Division Division,
      Pageable pageable) throws BusinessException;

  public Division getDivisionByCode(String divisionCode) throws BusinessException;

}
