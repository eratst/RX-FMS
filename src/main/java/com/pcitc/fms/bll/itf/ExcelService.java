package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.bll.entity.Factory;


/**
 * The interface Excel service.
 */
public interface ExcelService {

    /**
     * Add excel integer.
     *
     * @param factoryEntityList the factory entity list
     * @return the integer
     */
    public Integer addExcel(List<Factory> factoryEntityList);

  
    
    
}
