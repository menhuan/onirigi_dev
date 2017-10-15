package com.manage.controller;

import com.manage.model.HostHolder;
import com.manage.util.BaseUtil;
import com.manage.util.ConditionUtil;
import com.manage.util.RestRequestClient;
import com.manage.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 喜欢事件的Controller
 * Created by ASUS on 2017/9/19.
 */
@Controller
public class LikeController {

    /**
     * 日志
     */
    private  static  final Logger logger = LoggerFactory.getLogger(LikeController.class);


    @Autowired
    private HostHolder  hostHolder;

    /**
     * 点击喜欢触发的操作
     * @param newsId
     * @return
     */
    @RequestMapping(value = "like",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("newsId") int newsId){

        int userId =hostHolder.getUser().getId();

        Map map =new HashMap<>();
        map.put("newsId",newsId);
        map.put("userId",userId);

        RestRequestClient  rest=new RestRequestClient();
        LinkedHashMap  linkedHashMap =rest.restSubmitBean(BaseUtil.LIKE_ADD_NUM,map);

        if(ResultUtil.isSuccess(linkedHashMap)){
            linkedHashMap= (LinkedHashMap) linkedHashMap.get("successResult");
            Integer likeCount = (int) linkedHashMap.get("result");

            return  ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE,likeCount.toString());
        }

        return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE,"0");


    }

    /**
     * 不喜欢事件  //需要调整下不登录的情况下提示用户登录
     * @param newsId
     * @return
     */
    @RequestMapping(value = "dislike",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("newsId") int newsId){

        int userId =hostHolder.getUser().getId();

        Map map =new HashMap<>();
        map.put("newsId",newsId);
        map.put("userId",userId);

        RestRequestClient  rest=new RestRequestClient();
        LinkedHashMap  linkedHashMap =rest.restSubmitBean(BaseUtil.DISLIKE_ADD_NUM,map);

        if(ResultUtil.isSuccess(linkedHashMap)){
            linkedHashMap= (LinkedHashMap) linkedHashMap.get("successResult");
            Integer likeCount = (int) linkedHashMap.get("result");

            return  ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE,likeCount.toString());
        }

        return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE,"0");
    }


}
