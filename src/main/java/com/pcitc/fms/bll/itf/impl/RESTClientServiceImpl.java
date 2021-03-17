package com.pcitc.fms.bll.itf.impl;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.fms.bll.itf.RESTClientService;

import io.vertx.core.Handler;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
import pcitc.imp.common.ettool.utils.RestfulTool;

public abstract class RESTClientServiceImpl  implements RESTClientService {
	@Override
	public Object getOne(String url) throws Exception {
		List<Object> list = getList(url);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	@Override
	public Object getOne(StringBuffer url) throws Exception {
		return getOne(url.toString());
	}

	/**
	 * 根据URL发起get请求，获得泛型指定的类构成的List。
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<Object> getList(String url) throws Exception {
		String str = get(url);
		
		List<Object> l = new ArrayList<>();
		l.add(str);
//		@SuppressWarnings("unchecked")
//		List<T> list = (List<T>) RestfulTool.toResourceRepList(str, getTClass());
		
		return l;
	}

	/**
	 * 获取泛型类型的class
	 * @return
	 */
	private Class<Object> getTClass() {
		Type t = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) t).getActualTypeArguments();
        @SuppressWarnings("unchecked")
		Class<Object> cls = (Class<Object>) params[0];
		return cls;
	}
	
	/**
	 * 根据URL发起get请求，获得泛型指定的类构成的List。
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<Object> getList(StringBuffer url) throws Exception {
		return getList(url.toString());
	}
	

	@Override
	public JSONArray getJSONArray(StringBuffer url) throws Exception {
		String str = get(url.toString());
		return collectionToNomal(str);
	}
	
	protected void getJSONArrayAsync(StringBuffer url, Handler<JSONArray> callback) {
		getAsync(url.toString(), content -> {
			JSONArray array = null;
			try{
				array = collectionToNomal(content);
			} catch(Exception e) {
				System.out.println("转换json array时遇到错误，url："+ url);
				array = new JSONArray();
			}
			callback.handle(array);
		});
	}
	
	//将JSON串转换为一般的JSON串（可以认为是数组）
	private JSONArray collectionToNomal(String collectionStr) {

		JSONArray result = new JSONArray();
		JSONObject obj = JSON.parseObject(collectionStr);
		JSONArray items;
		try {
			items = obj.getJSONObject("collection").getJSONArray("items");
			if (items == null) {
				return result;
			}
			for (int i = 0; i < items.size(); i++) {
				JSONObject item = items.getJSONObject(i);
				JSONArray data = item.getJSONArray("data");
				JSONObject resultItem = new JSONObject();
				for (int j = 0; j < data.size(); j++) {
					JSONObject o = data.getJSONObject(j);
					resultItem.put(o.getString("name"), o.get("value"));
				}
				result.add(resultItem);
			}
  
		} catch (Exception e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
		}

		return result;

	}
	
	protected void getListAsync(String url, Handler<List<Object>> handler) {
//		getAsync(url, content -> {
//			try {
//				@SuppressWarnings("unchecked")
//				List<Object> list = (List<Object>)RestfulTool.toResourceRepList(content, getTClass());
//				handler.handle(list);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//				System.out.println("异步访问接口遇到错误，URL："+url);
//				handler.handle(new ArrayList<>());
//			}
//		});
	}

	private void getAsync(String url, Handler<String> callback) {
		CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
		HttpGet get = new HttpGet(url);
		CountDownLatch latch = new CountDownLatch(1);
		client.start();
		client.execute(get, new FutureCallback<HttpResponse>() {

			@Override
			public void cancelled() {
				// TODO Auto-generated method stub

			}

			@Override
			public void completed(HttpResponse response) {
				latch.countDown();
				try {
					String content = EntityUtils.toString(response.getEntity(), "UTF-8");
					client.close();
					callback.handle(content);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Exception arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	/**
	 * 发起get请求
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected String get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse resp = client.execute(get);
		HttpEntity respEntity = resp.getEntity();
		String result = EntityUtils.toString(respEntity);
		client.close();
		return result;
	}
}
