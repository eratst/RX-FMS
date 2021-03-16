package com.pcitc.fms.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.pcitc.fms.exception.BusinessException;

import redis.clients.jedis.Jedis;

public class CacheRentInfo {

	private static Map<String, Map<String, List<Map<String, String>>>> cache = new HashMap<>();

	public static void put(Map<String, Map<String, List<Map<String, String>>>> cacheParam) {
		cache.putAll(cacheParam);
	}

	public static synchronized Map<String, Map<String, List<Map<String, String>>>> get() {
		return cache;
	}

	public static List<String> getOrgCodes(String rentCode, String bizCode) {
		List<String> orgCodeList = new ArrayList<>();
		if (System.getenv("USE_REDIS") == null || !System.getenv("USE_REDIS").equals("1")) {
			Map<String, Map<String, List<Map<String, String>>>> caches = CacheRentInfo.get();
			Map<String, List<Map<String, String>>> bizs = caches.get(rentCode);
			List<Map<String, String>> orgs = bizs.get(bizCode);
			if (orgs != null) {
				for (Map<String, String> orgMaps : orgs) {
					for (Map.Entry<String, String> orgMap : orgMaps.entrySet()) {
						orgCodeList.add(orgMap.getValue());
					}
				}
			}
		} else {
			Jedis jedis = RedisUtil.getJedis();
			String orgStr = jedis.get("orgs:" + rentCode + ":" + bizCode);
			if (StringUtils.isNotEmpty(orgStr)) {
				orgCodeList.addAll(Arrays.asList(orgStr.split(",")));
			}
		}

		return orgCodeList;
	}

	public static List<String> getOrgCodes(String rentCode) {
		List<String> orgCodeList = new ArrayList<>();
		if (System.getenv("USE_REDIS") == null || !System.getenv("USE_REDIS").equals("1")) {
			Map<String, Map<String, List<Map<String, String>>>> caches = CacheRentInfo.get();
			Map<String, List<Map<String, String>>> bizs = caches.get(rentCode);
			if (bizs != null) {
				for (Map.Entry<String, List<Map<String, String>>> biz : bizs.entrySet()) {
					List<Map<String, String>> orgs = biz.getValue();
					for (Map<String, String> orgMaps : orgs) {
						for (Map.Entry<String, String> orgMap : orgMaps.entrySet()) {
							orgCodeList.add(orgMap.getValue());
						}
					}
				}
			}
		} else {
			Jedis jedis = RedisUtil.getJedis();
			String bizStr = jedis.get("bizs:" + rentCode);
			if (StringUtils.isNotEmpty(bizStr)) {
				String[] bizArray = bizStr.split(",");
				for (int i = 0; i < bizArray.length; i++) {
					String orgStr = jedis.get("orgs:" + rentCode + ":" + bizArray[i]);
					if (StringUtils.isNotEmpty(orgStr)) {
						orgCodeList.addAll(Arrays.asList(orgStr.split(",")));
					}
				}
			}
		}
		return orgCodeList;
	}

	public static List<String> getBizCodes(String rentCode) {
		List<String> bizCodeList = new ArrayList<>();
		if (System.getenv("USE_REDIS") == null || !System.getenv("USE_REDIS").equals("1")) {
			Map<String, Map<String, List<Map<String, String>>>> caches = CacheRentInfo.get();
			Map<String, List<Map<String, String>>> bizs = caches.get(rentCode);
			if (bizs != null) {
				for (Map.Entry<String, List<Map<String, String>>> biz : bizs.entrySet()) {
					bizCodeList.add(biz.getKey());
				}
			}
		} else {
			Jedis jedis = RedisUtil.getJedis();
			String bizStr = jedis.get("bizs:" + rentCode);
			if (StringUtils.isNotEmpty(bizStr)) {
				bizCodeList.addAll(Arrays.asList(bizStr.split(",")));
			}
		}
		return bizCodeList;
	}

	public static List<String> getOrgCodesRelationArea(String rentCode, String bizCode) throws SQLException {
		List<String> orgCodes = getOrgCodes(rentCode, bizCode);
		List<String> orgCodesResult = getOrgCodesBySql(orgCodes);
		return orgCodesResult;
	}

	public static Map<String, List<String>> getBizCodesAndOrgCodes(String rentCode) {
		Map<String, List<String>> maps = new HashMap<>();
		List<String> orgCodeList = new ArrayList<>();
		List<String> bizCodeList = new ArrayList<>();

		if (System.getenv("USE_REDIS") == null || !System.getenv("USE_REDIS").equals("1")) {
			Map<String, Map<String, List<Map<String, String>>>> caches = CacheRentInfo.get();
			Map<String, List<Map<String, String>>> bizs = caches.get(rentCode);
			if (bizs != null) {
				for (Map.Entry<String, List<Map<String, String>>> biz : bizs.entrySet()) {
					bizCodeList.add(biz.getKey());
					List<Map<String, String>> orgs = biz.getValue();
					for (Map<String, String> orgMaps : orgs) {
						for (Map.Entry<String, String> orgMap : orgMaps.entrySet()) {
							orgCodeList.add(orgMap.getValue());
						}
					}
				}
			}
		} else {
			Jedis jedis = RedisUtil.getJedis();
			String bizStr = jedis.get("bizs:" + rentCode);
			if (StringUtils.isNotEmpty(bizStr)) {
				bizCodeList.addAll(Arrays.asList(bizStr.split(",")));
				String[] bizArray = bizStr.split(",");
				for (int i = 0; i < bizArray.length; i++) {
					String orgStr = jedis.get("orgs:" + rentCode + ":" + bizArray[i]);
					if (StringUtils.isNotEmpty(orgStr)) {
						orgCodeList.addAll(Arrays.asList(orgStr.split(",")));
					}
				}
			}
		}
		maps.put("orgCodes", orgCodeList);
		maps.put("bizCodes", bizCodeList);
		return maps;
	}

	public static Map<String, List<String>> getBizCodesAndOrgCodesRelationArea(String rentCode) throws SQLException {

		Map<String, List<String>> result = new HashMap<>();
		Map<String, List<String>> orgCodeAndBizCodes = getBizCodesAndOrgCodes(rentCode);
		for (Map.Entry<String, List<String>> orgCodeAndBizCode : orgCodeAndBizCodes.entrySet()) {
			if (orgCodeAndBizCode.getKey().equals("orgCodes")) {
				if (!orgCodeAndBizCode.getValue().isEmpty()) {

					List<String> orgCodesResult = getOrgCodesBySql(orgCodeAndBizCode.getValue());
					result.put("orgCodes", orgCodesResult);
				}
			} else {
				result.put("bizCodes", orgCodeAndBizCode.getValue());
			}
		}
		return result;
	}

	public static List<String> getOrgCodesBySql(List<String> orgCodes) throws SQLException {

		List<String> result = new ArrayList<>();

		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;

		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (String str : orgCodes) {
			sb.append("'" + str + "',");
		}

		String inInfo = sb.toString().substring(0, sb.lastIndexOf(",")) + ")";

		String sql = "select distinct o.ORG_CODE from T_PM_AREA a,T_PM_ORG o where a.ORG_ID = o.ORG_ID and o.ORG_CODE in "
				+ inInfo + "";

		try {
			con = new JDBCUtil().getConnection();
			if (con == null) {
				throw new BusinessException("", "", "获取连接失败!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql);
			while (ret.next()) {
				Object obj = ret.getObject("ORG_CODE");
				String orgCode = (String) obj;
				if (StringUtils.isNotEmpty(orgCode)) {
					result.add(orgCode);
				}
			}
			System.out.println(sql);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ret != null) {
				ret.close();
			} else if (pst != null) {
				pst.close();
			} else if (con != null) {
				con.close();
			}
		}
		return result;
	}

	public static void remove() {
		CacheRentInfo.cache.clear();
	}
}
