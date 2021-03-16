package com.pcitc.fms.bll.stormItf;


import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.service.stormModel.CalcCtlVO;
import com.pcitc.fms.service.stormModel.TupleVO;
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

/**
 * spout使用的关系数据库操作类
 *
 * @author haiwen.wang
 */
public class TankCalcSpoutHelper {

	/**
	 * The constant LOGGER.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(TankCalcSpoutHelper.class);

	/**
	 * Instantiates a new Tank calc spout helper.
	 */
	public TankCalcSpoutHelper() {
		
	}

	/**
	 * 采用宇宙中性能最高的hikari连接池！牛逼的很。
	 *
	 * @return connection
	 */
	private Connection getConnection()  {
		DataSource dataSource = (DataSource) ApplicationContextTool.getBean("dataSource");
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return con;
//		return ConnectionFactory.getHikariConnection(dbParam);
	}

	/**
	 * 查询符合了触发条件的罐
	 *
	 * @return string
	 * @throws BusinessException the business exception
	 * @throws SQLException the sql exception
	 */
	public String queryConfig() throws BusinessException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select a.agent_id, a.app_code,  a.NODE_ID as nodeID, a.CONFIG_ID as configID, a.START_TIME as startTime, a.CALC_FREQUENCY as frequency, a.NEXT_TIME as nextTime ");
		sql.append("from T_TANKCALC_CONFIG a ");
		sql.append("where a.enabled = 1 ");

		List<TupleVO> result = new ArrayList<TupleVO>();

		// 执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if (con == null) {
				throw new BusinessException("", "", "queryConfig(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql.toString());

			while (ret.next()) {

				String configID = ret.getString("configID");
				int frequency = ret.getInt("frequency");
				String nodeID = ret.getString("nodeID");
				int agentid = Integer.parseInt(ret.getString("agent_id"));
				String appcode = ret.getString("app_code");

				TupleVO tupleVO = new TupleVO();
				CalcCtlVO calcCtlVO = new CalcCtlVO();
				calcCtlVO.setConfigID(configID);
				calcCtlVO.setFrequency(frequency);
				calcCtlVO.setTankID(nodeID);
				calcCtlVO.setAppcode(appcode);
				calcCtlVO.setAgentid(agentid);

				tupleVO.setTankid(nodeID);
				tupleVO.setCalcCtlVO(calcCtlVO);
				tupleVO.setFinish(false);

				result.add(tupleVO);
			}
			return gson.toJson(result);
		} catch (SQLException e) {
			throw new BusinessException("", "", "queryConfig is fail!");
		} finally {
			try {
				if (ret != null) {
					ret.close();
				}

			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}finally {
				try {
					if (pst != null) {
            pst.close();
          }
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}finally {
					if (con != null) {
						con.close();
					}
				}

			}
		}
	}

}

