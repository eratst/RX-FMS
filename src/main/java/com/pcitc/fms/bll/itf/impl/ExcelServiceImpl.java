/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ExcelServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.ExcelService;
import com.pcitc.fms.dal.dao.DeupdownlimitDaoImpl;
import com.pcitc.fms.dal.dao.ExcelDao;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;


/**
 * The type Excel service.
 */
@Service
public class ExcelServiceImpl implements ExcelService{

    /**
     * The constant log.
     */
    private static Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);
    /**
     * The Excel dao.
     */
    @Autowired
    private ExcelDao excelDao;

    /**
     * Add excel integer.
     *
     * @param factoryEntityList the factory entity list
     * @return the integer
     */
    @Override
    public Integer addExcel(List<com.pcitc.fms.bll.entity.Factory> factoryEntityList) {
        Integer resultNum = null;
        try{
          
            List<com.pcitc.fms.dal.pojo.Factory> factoryPojoList = ObjectConverter.listConverter(factoryEntityList, com.pcitc.fms.dal.pojo.Factory.class);
            List<com.pcitc.fms.dal.pojo.Factory> factoryPojoLists = excelDao.save(factoryPojoList);
            resultNum  = factoryPojoLists.size();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return resultNum;
    }

 
    
}
