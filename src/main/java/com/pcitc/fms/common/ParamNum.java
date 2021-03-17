/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ParamNum
 * Date:18-3-5 下午4:51
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Param num.
 */
public class ParamNum {
  private static Logger log = LoggerFactory.getLogger(ParamNum.class);
  /**
   * The constant paramMaps.
   */
  private static HashMap<String, String> paramMaps = new HashMap<String, String>();

  /**
   * Gets pamra num.
   *
   * @param name the name
   * @return the pamra num
   */
//查找方法名，看是否在缓存中
  public static String getPamraNum(String name) throws IOException {
    String num = paramMaps.get(name);
    if (num != null) {
      return num;
    } else {
      return getNumForCacache(name);
    }

  }

  /**
   * Gets num for cacache.
   *
   * @param name the name
   * @return the num for cacache
   */
//读取配置文件，添加到缓存中
  private static String getNumForCacache(String name) throws IOException {
    //==获得配置文件
    Properties proper = new Properties();
    InputStream in = null;
    String num = null;
    try {
      in = ParamNum.class.getResourceAsStream("/paramNum.properties");
      proper.load(in);
      num = proper.getProperty(name);
      paramMaps.put(name, num);
    } catch (FileNotFoundException e) {
      log.error(e.getMessage());
    } catch (IOException e) {
      log.error(e.getMessage());
    }finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          log.error(e.getMessage());
        }
      }
    }

    return num;
  }
}
