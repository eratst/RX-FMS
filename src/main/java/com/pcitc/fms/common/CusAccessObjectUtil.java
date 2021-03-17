/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: CusAccessObjectUtil
 * Date:18-1-30 下午4:18
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.common;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import java.io.IOException;

/**
 * Title: ${type_name} Description: ${todo} task mark zhenqiang.zhao ${tags} 获取对象的IP地址等信息
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2018/1/30
 */
public class CusAccessObjectUtil {
  /**
   * 13      * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 14 * 16      *
   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 17      *
   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 18      * 19      * 如：X-Forwarded-For：192.168.1.110,
   * 192.168.1.120, 192.168.1.130, 20      * 192.168.1.100 21      * 22      * 用户真实IP为：
   * 192.168.1.110 23      * 24      * @param request 25      * @return 26
   */
  public static String getIpAddress(HttpServerRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    }
    return ip;
  }


}
