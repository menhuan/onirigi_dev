package com.manage.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据返回来的结果验证是否成功
 * <p>
 * Created by ASUS on 2017/8/26.
 */
public class ResultUtil {

    /**
     * 成功编码
     */
    private static final Integer SUCCESS_CODE = 100;

    /**
     * 统一失败编码
     */
    private static final Integer FAIL_COMMON_CODE = 200;

    /**
     * 异常失败编码
     */
    private static final Integer FAIL_EXCEPTION_CODE = 201;


    public static boolean isSuccess(Map map) {
        Object code = map.get("success");
        if (code != null) {
            int success = Integer.parseInt(ObjectUtils.toString(code));
            if (success == SUCCESS_CODE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回结果中的list数据
     *
     * @param map
     * @return
     */
    public static List getResultList(Map map) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) map.get("successResult");
        List list = (List) linkedHashMap.get("result");
        return list;
    }

    /**
     * 返回结果数据中的map数据
     *
     * @param map
     * @return
     */
    public static Map getResultMap(Map map) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) map.get("successResult");
        linkedHashMap = (LinkedHashMap) linkedHashMap.get("result");
        return linkedHashMap;
    }

    /**
     * 返回Object 对象
     * @param map
     * @return
     */
    public static Object getResultObject(Map map) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) map.get("successResult");
        Object object = (Object)linkedHashMap.get("result");
        return object;
    }


    /**
     * 给页面返回json数据
     *
     * @param code 代码编号
     * @return
     */
    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    /**
     * 给页面返回json数据
     *
     * @param code 代码编号
     * @param msg  内容
     * @return
     */
    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    /**
     * 给页面返回json数据
     *
     * @param code 代码编号
     * @param map  包含的内容
     * @return
     */
    public static String getJSONString(int code, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json.toJSONString();
    }
}
