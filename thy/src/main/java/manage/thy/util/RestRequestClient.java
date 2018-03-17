package manage.thy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import manage.thy.model.wechat.res.NewsMessage;

import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by ASUS on 2017/8/22.
 */
public class RestRequestClient {

	

    /**
     * restTemplate request
     *
     * @param url
     * @param sPara
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public LinkedHashMap restSubmit(String url, Map<String, String> sPara) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(sPara, headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap) response.getBody();
        return responseMap;
    }

    /**
     * 此方法本质同restSubmit,在此处多进行了一次json的转换!
     * restTemplate request
     *
     * @param url
     * @param sPara
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public LinkedHashMap restSubmitJson(String url, Map<String, String> sPara) {

        JSONObject jsonMap = JSONObject.parseObject(JSON.toJSONString(sPara));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(jsonMap, headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap) response.getBody();
        return responseMap;
    }


    /**
     * 此方法本质同restSubmit,传进来的是一个bean在此处多进行了一次的转换!
     * restTemplate request
     *
     * @param url
     * @param obj 一般为bean
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public LinkedHashMap restSubmitBean(String url, Object obj) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(JSON.toJSON(obj), headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap) response.getBody();
        return responseMap;
    }
    
    /**
     * 提交String类型参数
     * @author ASUS
     * 创建时间  2017年11月13日 下午8:59:34
     * @param url
     * @param obj
     * @return
     */
    public String restSubmitString(String url, String obj) throws Exception{
    	StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));  
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.set("Accept-Charset", "UTF-8");
        HttpEntity entity = new HttpEntity(obj, headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String resutlt = (String) response.getBody();
        return resutlt;
    }
}
