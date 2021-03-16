/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: CalcRTParamHelper
 * Date:18-3-5 下午4:17
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.stormItf;


import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.common.JsonTool;
import com.pcitc.fms.service.stormModel.TankTagVO;
import com.pcitc.fms.service.stormModel.TupleVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;

/**
 * 查询位号使用的数据库操作类
 *
 * @author haiwen.wang
 */
public class CalcRTParamHelper {
	private static Logger log = LoggerFactory.getLogger(CalcRTParamHelper.class);
	/**
	 * Instantiates a new Calc rt param helper.
	 */
	public CalcRTParamHelper() {
		
	}

	/**
	 * Gets connection.
	 *
	 * @return the connection
	 */
	private Connection getConnection()  {
		DataSource dataSource = (DataSource) ApplicationContextTool.getBean("dataSource");
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return con;
	}

	/**
	 * 根据罐id查询位号信息
	 *
	 * @param tupleList the tuple list
	 * @return string
	 * @throws BusinessException the business exception
	 */
	public String queryTagByTankID(String tupleList) throws BusinessException, SQLException {
		List<TupleVO> tt = JsonTool.stringToListBean(tupleList, TupleVO.class);
		Map<String, List<TankTagVO>> retMap = new HashMap<String, List<TankTagVO>>();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select a.node_id,a.tag_id,a.meas_type_id from PM_TANKMEAS_T a where a.use_flag = 1 and a.node_id in (");
		for (int i = 0; i < tt.size(); i++) {
			if (i != tt.size() - 1) {
				sql.append("'").append(tt.get(i).getTankid()).append("',");
			} else {
				sql.append("'").append(tt.get(i).getTankid()).append("')");
			}
		}

		// 执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if (con == null) {
				throw new BusinessException("","","queryTagByTankID(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql.toString());

			while (ret.next()) {
				String tankID = ret.getBigDecimal("node_id").toString();
				String tag = ret.getString("tag_id").toString();
				int tagType = ret.getInt("meas_type_id");
				TankTagVO tankTag = new TankTagVO();
				tankTag.setTag(tag);
				tankTag.setTagType(tagType);
				if (retMap.containsKey(tankID)) {
					List<TankTagVO> tankTagList = retMap.get(tankID);
					tankTagList.add(tankTag);
				} else {
					List<TankTagVO> tankTagList = new ArrayList<TankTagVO>();
					tankTagList.add(tankTag);
					retMap.put(tankID, tankTagList);
				}
			}
			return gson.toJson(retMap);
		} catch (SQLException e) {
			throw new BusinessException("","","queryTagByTankID is fail!"+e.toString());
		} finally {
			try {
				if (ret != null) {
					ret.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}finally {
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
	}
}
