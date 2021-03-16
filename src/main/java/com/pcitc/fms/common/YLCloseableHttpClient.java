package com.pcitc.fms.common;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.ext.web.RoutingContext;

public class YLCloseableHttpClient {

	public static String post(String url, String pojo,RoutingContext routingContext) throws ClientProtocolException, IOException, BusiException {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(pojo, "utf-8");
		httpPost.setEntity(entity);
		CloseableHttpResponse addResponse = client.execute(httpPost);
		HttpEntity responseEntity = addResponse.getEntity();
		result = EntityUtils.toString(responseEntity);
		System.out.println(result);
		if (routingContext != null) {
			routingContext.response().setStatusCode(addResponse.getStatusLine().getStatusCode());
		}
		client.close();
		return result;
	}

	public static String put(String url, String pojo,RoutingContext routingContext) throws ClientProtocolException, IOException, BusiException {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		StringEntity entity = new StringEntity(pojo, "utf-8");
		httpPut.setEntity(entity);
		CloseableHttpResponse addResponse = client.execute(httpPut);
		HttpEntity responseEntity = addResponse.getEntity();
		result = EntityUtils.toString(responseEntity);
		System.out.println(result);
		if (routingContext != null) {
			routingContext.response().setStatusCode(addResponse.getStatusLine().getStatusCode());
		}
		client.close();
		return result;
	}

	public static String delete(String url,RoutingContext routingContext) throws ClientProtocolException, IOException, BusiException {
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpDelete httpDelet = new HttpDelete(url);
		CloseableHttpResponse addResponse = client.execute(httpDelet);
		HttpEntity responseEntity = addResponse.getEntity();
		result = EntityUtils.toString(responseEntity,"utf-8");
		System.out.println(result);
		if (routingContext != null) {
			routingContext.response().setStatusCode(addResponse.getStatusLine().getStatusCode());
		}
		client.close();
		return result;
	}

	public static boolean get(String url) throws ClientProtocolException, IOException, BusiException {
		String result = "";
		boolean hasData = false;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse addResponse = client.execute(httpGet);
		HttpEntity responseEntity = addResponse.getEntity();
		result = EntityUtils.toString(responseEntity, "utf-8");
		if (StringUtils.isNotEmpty(result)) {
			JSONObject jSONObject = JSONObject.parseObject(result);
			JSONObject object1 = jSONObject.getJSONObject("collection");
			JSONArray jsonArray = object1.getJSONArray("items");
			if (!jsonArray.isEmpty()) {
				hasData = true;
			}
			System.out.println(result);
			client.close();
		}

		return hasData;
	}

	public static String getDate(String url) throws ClientProtocolException, IOException, BusiException {
		String result = "";
		String exceptionMsg = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse addResponse = client.execute(httpGet);
		HttpEntity responseEntity = addResponse.getEntity();
		result = EntityUtils.toString(responseEntity, "utf-8");
		System.out.println(result);
		client.close();
		return result;
	}

	// 获取异常信息
	public static String exceptionMsg(String result) {
		JSONObject jSONObject = JSONObject.parseObject(result);
		JSONObject object1 = jSONObject.getJSONObject("collection");
		String msg1 = (String) object1.get("href");
		if (msg1.contains("rtdb")) {
			msg1 = "RTDB配置: ";// 注意修改exceptionMsg
		}
		if (msg1.contains("lims")) {
			msg1 = "LIMS配置: ";
		}
		JSONObject object2 = object1.getJSONObject("error");
		String msg2 = (String) object2.get("message");
		return msg1 + msg2;
	}

	public static Object getObject(String url,RoutingContext routingContext) throws ClientProtocolException, IOException, BusiException {

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpGet);
		HttpEntity responseEntity = response.getEntity();
		String result = EntityUtils.toString(responseEntity,"utf-8");
		if (routingContext != null) {
			routingContext.response().setStatusCode(response.getStatusLine().getStatusCode());
		}
		client.close();

		return result;
	}
}
