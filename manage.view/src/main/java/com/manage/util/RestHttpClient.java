package com.manage.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

/**
 * http工具类
 *
 * @author dell
 */
public class RestHttpClient {

    /**
     * 客户端
     */
    OkHttpClient client = new OkHttpClient();

//	public static final 

    /**
     * 设置传输模式 string
     */
    public static final MediaType JSON =
            MediaType.parse("application/json;charset=utf-8");

    /**
     * get方法
     *
     * @return
     * @throws IOException
     */
    public Object doGet(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url).build();

        try (Response response = client.newCall(request)
                .execute()) {
            return JSONObject.toJSON(response.body().string());
        }
    }

    /**
     * 得到map数据
     *
     * @param url
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public Map doGetMap(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url).build();

        try (Response response = client.newCall(request)
                .execute()) {
            return (Map) JSONObject.toJSON(response.body().string());
        }
    }

    /**
     * post方法 使用String的方式传输数据　如果数据较大应该使用流的方式
     *
     * @param url  网址
     * @param json 传输的数据给url
     * @return
     * @throws IOException
     */
    public Object doPostString(String url, String json) throws IOException {

        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url).post(requestBody).build();
        try (Response response = client.newCall(request).execute()) {
            return JSONObject.toJSON(response.body().string());
        }

    }

    /**
     * 得到map数据
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public Map doMapPostJson(String url, String json) throws IOException {

        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url).post(requestBody).build();
        try (Response response = client.newCall(request).execute()) {
            return (Map) JSONObject.toJSON(response.body().string());
        }

    }


    /**
     * 流的方式提交数据post
     *
     * @return
     */
    public Object doPostStream() {
        return "";
    }

    @Test
    public void test1() {
        String string;
        try {
            string = new RestHttpClient().doGet("http://f.apiplus.net/cqssc-1.json").toString();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
