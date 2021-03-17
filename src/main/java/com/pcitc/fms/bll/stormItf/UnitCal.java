/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: UnitCal
 * Date:18-3-5 下午5:10
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.stormItf;

import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.service.stormModel.UnitCalVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;

public class UnitCal {
  private static Logger log = LoggerFactory.getLogger(UnitCal.class);
  public UnitCal() {

  }

  private Connection getConnection() {
    DataSource dataSource = (DataSource) ApplicationContextTool.getBean("dataSource");
    Connection con = null;
    try {
      con = dataSource.getConnection();
    } catch (Exception e) {
     log.error(e.getMessage());
    }
    return con;
  }

  //平稳率计算
  public String stableRate(String plantId, String equpimentId)
      throws BusinessException, SQLException {
    String sql = "de.de_up_down_limit_id as UPPERLOWER_ID,/* 上下限ID*/\r\n" +
        "        op.ope_index_id as OPERATIONINDEX_ID," +
        "        de.up_limit_value as UPPER_LIMIT,/*上限值*/\r\n" +
        "        de.down_limit_value as LOWER_LIMIT，/*下限值*/\r\n" +
        "        de.attention_value as CONCERN_VALUE,/* 关注值*/\r\n" +
        "       st.weightings as WEIGHT_FACTOR/* 权重*/\r\n" +
        "from\r\n" +
        "((((t_fms_plant pl inner join T_OPM_CRAFTSCHEME cr on cr.equip_id = pl.plant_id )\r\n" +
        "inner join  T_OPM_STAALGRCONFITEM st on st.craft_scheme_id = cr.craft_scheme_id)\r\n" +
        "inner join t_opm_opeindex op on st.ope_index_id = op.ope_index_id)\r\n" +
        "inner join T_OPM_DEUPDOWNLIMIT de on de.ope_index_id = op.ope_index_id)\r\n" +
        "where op.equip_id=";
    StringBuffer sb = new StringBuffer(sql);
    sb.append(plantId);
    sb.append(" and op.unit_id=");
    sb.append(equpimentId);

    List<UnitCalVO> result = new ArrayList<UnitCalVO>();

    // 执行
    Connection con = null;
    Statement pst = null;
    ResultSet ret = null;
    Gson gson = new Gson();
    try {
      con = getConnection();
      pst = con.createStatement();
      ret = pst.executeQuery(sb.toString());

      while (ret.next()) {

        String operationIndexID = ret.getString("OPERATIONINDEX_ID");// 指标ID
        String upperLowerID = ret.getString("UPPERLOWER_ID");// 上下限ID
        String upperLimit = ret.getString("UPPER_LIMIT");//上限值
        String lowerLimit = ret.getString("LOWER_LIMIT");
        double concernValue = ret.getDouble("CONCERN_VALUE");//关注值
        int weightFactor = ret.getInt("WEIGHT_FACTOR");//权重

        UnitCalVO usvo = new UnitCalVO();
        usvo.setPlantId(plantId);
        usvo.setDeviceID(equpimentId);
        usvo.setOperationIndexID(operationIndexID);
        usvo.setUpperLimit(upperLimit);
        usvo.setLowerLimit(lowerLimit);
        usvo.setUpperLowerID(upperLowerID);
        usvo.setConcernValue(concernValue);
        usvo.setWeightFactor(weightFactor);
        result.add(usvo);
      }
    } catch (SQLException e) {
      throw new BusinessException("", "", "queryConfig is fail!" + e.toString());
    } finally {
      try {
        if (ret != null) {
          ret.close();
        }
      } catch (SQLException e) {
        log.error(e.getMessage());
      } finally {
        try {
          if (pst != null) {
            pst.close();
          }
        } catch (SQLException e) {
          log.error(e.getMessage());
        } finally {
          if (con != null) {
            con.close();
          }
        }
      }
    }
    return gson.toJson(result);


  }

  //偏差计算
  public String deviationRate(String plantId, String equpimentId)
      throws BusinessException, SQLException {
    String sql1 = "SELECT op.ope_index_id as OPERATIONINDEX_ID,/* 指标ID*/\r\n" +
        "       de.de_up_down_limit_id as UPPERLOWER_ID,/* 上下限ID*/\r\n" +
        "       de.up_limit_value as UPPER_LIMIT,/*上限值*/\r\n" +
        "       de.down_limit_value as LOWER_LIMIT，/*下限值*/\r\n" +
        "       de.min_dvt_time as MIN_DEVIATION_TIME，/*最小偏差时间*/\r\n" +
        "       de.threshold_value as THRESHOLD，/*阈值*/\r\n" +
        "       0 AS CALC_MODEL ,/*平稳率算法*/\r\n" +
        "       mo.name as MONLEVEL/*监控级别*/ FROM\r\n" +
        "(((((T_OPM_OPEINDEX op inner join t_Fms_Equipment eq on op.unit_id = eq.equipment_id )\r\n"
        +
        "INNER JOIN T_OPM_DEUPDOWNLIMIT DE ON OP.OPE_INDEX_ID = DE.OPE_INDEX_ID)\r\n" +
        "INNER JOIN T_OPM_STAALGRCONF ST ON ST.EQUIP_ID = OP.EQUIP_ID)\r\n" +
        "INNER JOIN T_OPM_STAALGR STA ON STA.STA_ALGR_ID = ST.STA_ALGR_ID)\r\n" +
        "INNER JOIN T_OPM_MONLEVEL MO ON MO.MON_LEVEL_ID = DE.MON_LEVEL_ID)  \r\n" +
        "where op.equip_id=";
    StringBuffer sb = new StringBuffer(sql1);
    List<UnitCalVO> result = new ArrayList<UnitCalVO>();
    sb.append(plantId);
    sb.append(" and op.unit_id=");
    sb.append(equpimentId);
    // 执行
    Connection con = null;
    Statement pst = null;
    ResultSet ret = null;
    Gson gson = new Gson();
    try {
      con = getConnection();
      pst = con.createStatement();
      ret = pst.executeQuery(sb.toString());

      while (ret.next()) {

        String deviceID = ret.getString("DEVICE_ID");// 设备ID
        String operationIndexID = ret.getString("OPERATIONINDEX_ID");// 指标ID
        String upperLowerID = ret.getString("UPPERLOWER_ID");// 上下限ID
        int calcModel = ret.getInt("CALC_MODEL");// 平稳率算法
        String upperLimit = ret.getString("UPPER_LIMIT");// 上限值
        String lowerLimit = ret.getString("LOWER_LIMIT");// 下限值
        Double threshold = ret.getDouble("THRESHOLD");// 阈值
        int minDeviationTime = ret.getInt("MIN_DEVIATION_TIME");// 最小偏差时间
        String monLevel = ret.getString("MONLEVEL");//监控级别

        UnitCalVO usvo = new UnitCalVO();
        usvo.setPlantId(plantId);
        usvo.setDeviceID(equpimentId);
        usvo.setOperationIndexID(operationIndexID);
        usvo.setUpperLowerID(upperLowerID);
        usvo.setCalcModel(calcModel);
        usvo.setUpperLimit(upperLimit);
        usvo.setLowerLimit(lowerLimit);
        usvo.setThreshold(threshold);
        usvo.setMinDeviationTime(minDeviationTime);
        usvo.setMonLevel(monLevel);
        result.add(usvo);
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    } finally {
      try {
        if (ret != null) {
          ret.close();
        }
      } catch (SQLException e) {
       log.error(e.getMessage());
      } finally {
        try {
          if (pst != null) {
            pst.close();
          }
        } catch (SQLException e) {
          log.error(e.getMessage());
        } finally {
          if (con != null) {
            con.close();
          }
        }

      }
    }
    return gson.toJson(result);
  }
}
