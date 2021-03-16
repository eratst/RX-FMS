package com.pcitc.fms.bll.stormItf;

import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.ClassUtil;
import com.pcitc.fms.common.JsonTool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;


/**
 * 预处理公式的数据库操作类
 *
 * @author haiwen.wang
 */
public class DealFormulaHelper {
	private static Logger log = LoggerFactory.getLogger(DealFormulaHelper.class);
	/**
	 * Instantiates a new Deal formula helper.
	 */
	public DealFormulaHelper() {
		
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
//		return ConnectionFactory.getHikariConnection(dbParam);
	}

	/**
	 * 根据参数名字查找对应的值
	 *
	 * @param str the str
	 * @param tankID the tank id
	 * @param materialID the material id
	 * @return string
	 * @throws BusinessException the business exception
	 */
	public String queryValueByPara(String str, String tankID, String materialID)
			throws BusinessException, SQLException {
		List<String> paraList = JsonTool.StringToStringtList(str);
		Gson gson = new Gson();
		Map<String, Double> retMap = new HashMap<String, Double>();
		if(null == paraList||paraList.size() == 0 ){
			return gson.toJson(retMap);
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select VAL, PARA FROM PM_TANK_PARA_T T WHERE (");
		if(CheckUtil.checkStringIsNotNull(tankID)){
			sql.append(" NODE_ID = '"+tankID+"'");
		}else{
			sql.append(" 1 = 1 ");
		}
		if(CheckUtil.checkStringIsNotNull(materialID)){
			sql.append(" OR NODE_ID = '"+materialID+"')");
		}else{
			sql.append(" OR 1 = 1 )");
		}
		sql.append("and  T.PARA in (");
		for (int i = 0; i < paraList.size(); i++) {
			if (i != paraList.size() - 1) {
				sql.append("'").append(paraList.get(i)).append("',");
			} else {
				sql.append("'").append(paraList.get(i)).append("')");
			}
		}
		// 执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		
		try {
			con = getConnection();
			if (con == null) {
				throw new BusinessException("","","queryValueByPara(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql.toString());

			while (ret.next()) {
				Double value = ret.getBigDecimal("VAL").doubleValue();
				String para = ret.getString("PARA");

				retMap.put(para, value);
			}
			return gson.toJson(retMap);
		} catch (SQLException e) {
			throw new BusinessException("","","queryValueByPara is fail!"+e.toString());
		} finally {
			try {
				if(ret !=null){
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

