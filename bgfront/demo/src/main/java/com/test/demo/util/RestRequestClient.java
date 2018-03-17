package com.test.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static com.test.demo.util.ConstantsUtil.*;


/**
 * Created by ASUS on 2017/8/22.
 */
public class RestRequestClient {


    /**
     *  restTemplate request
     * @param url
     * @param sPara
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public LinkedHashMap restSubmit(String url, Map<String, String> sPara){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(sPara,headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        return responseMap;
    }

    /**
     * 此方法本质同restSubmit,在此处多进行了一次json的转换!
     *  restTemplate request
     * @param url
     * @param sPara
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public LinkedHashMap restSubmitJson(String url,Map<String, String> sPara){

        JSONObject jsonMap = JSONObject.parseObject(JSON.toJSONString(sPara));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(jsonMap,headers);
        ResponseEntity response = restTemplate.exchange(url,HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        return responseMap;
    }


    /**
     * 此方法本质同restSubmit,传进来的是一个bean在此处多进行了一次的转换!
     *  restTemplate request
     * @param url
     * @param obj 一般为bean
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public LinkedHashMap restSubmitBean(String url,Object obj){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(JSON.toJSON(obj),headers);
        ResponseEntity response = restTemplate.exchange(url,HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        return responseMap;
    }
    
    /**
     * 设置 代理 
     * @author ASUS
     * 创建时间  2017年11月18日 下午1:56:44
     * @param ip
     * @param host
     */
    public SimpleClientHttpRequestFactory setHttpClientProxy(String ip , String host ) {
    	SimpleClientHttpRequestFactory client = new SimpleClientHttpRequestFactory() ;
    	SocketAddress address = new InetSocketAddress(ip, Integer.valueOf(host) );
    	Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
    	client.setProxy(proxy);
    	
    	return client;
    }
    
    /**
     * 爬虫 专用 代理
     * @author ASUS
     * 创建时间  2017年11月18日 下午2:13:11
     * @param url  需要访问的web 
     * @param ip  代理ip
     * @param host 端口号
     * @param content  上传的内容
     * @return
     */
    public String restSubmitObject(String url ,String ip ,String host ,Object content) {
    	 SimpleClientHttpRequestFactory client = this.setHttpClientProxy(ip ,host);
    	 RestTemplate restTemplate = new RestTemplate(client);
         HttpHeaders headers = new HttpHeaders();
         headers.set("User-Agent", USER_AGENT_ARRAY[new Random().nextInt(USER_AGENT_ARRAY.length)]  );
//         headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
//         headers.set("Accept-Charset", "UTF-8");
         HttpEntity entity = new HttpEntity(JSON.toJSON(content),headers);
         ResponseEntity response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
         String result = (String) response.getBody();
         return result ;
    }
    
    /**
     * 爬取 36氢的 信息爬虫使用
     * @author ASUS
     * 创建时间  2017年11月18日 下午2:13:11
     * @param url  需要访问的web 
     * @param ip  代理ip
     * @param host 端口号
     * @param content  上传的内容
     * @return
     */
    public String restSubmitObject(String url  ,Object content) {
    	
    	 RestTemplate restTemplate = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         headers.set("User-Agent", USER_AGENT_ARRAY[new Random().nextInt(USER_AGENT_ARRAY.length)]  );
         HttpEntity entity = new HttpEntity(JSON.toJSON(content),headers);
         ResponseEntity response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
         String result = (String) response.getBody();
         return result ;
    }
    
    
    /**
     * 爬取 火币网 信息 使用的请求Get
     * @author ASUS
     * 创建时间  2018年3月11日 下午3:20:16
     * @param url
     * @param content
     * @return
     */
    public String restGetHuoBi(String url  ,Object content) {
    	
   	 	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", USER_AGENT_ARRAY[new Random().nextInt(USER_AGENT_ARRAY.length)]  );
        HttpEntity entity = new HttpEntity(JSON.toJSON(content),headers);
        ResponseEntity response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
        String result = (String) response.getBody();
        return result ;
   }
}
