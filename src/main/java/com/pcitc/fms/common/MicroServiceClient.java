package com.pcitc.fms.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MicroServiceClient{

	//get请求
	public static String getRequest(String url,String data){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, String.class, data);
	}
	
	public static String postRequest(String url, String collection){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		HttpEntity<String> formEntity = new HttpEntity<String>(collection, headers);
		
		return restTemplate.postForObject(url, formEntity, String.class);
	}
	
	public static String putRequest(String url, String collection){
		
		String result = "";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		HttpEntity<String> formEntity = new HttpEntity<String>(collection, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, formEntity, String.class);
		
		if(responseEntity.hasBody()){
			result = responseEntity.getBody().toString();
		}
		return result;
	}

	public static String deleteRequest(String url){
		
		String result = "";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		HttpEntity<String> formEntity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, formEntity, String.class);
		
		if(responseEntity.hasBody()){
			result = responseEntity.getBody().toString();
		}
		return result;
	}
}
