package com.manage.controller;

import com.manage.model.HostHolder;
import com.manage.util.BaseUtil;
import com.manage.util.RestRequestClient;
import com.manage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/8/23.
 */
@Controller
public class IndexController {


    @Autowired
    private HostHolder  hostHolder;

    /**
     * 查询首页
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = {"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(@PathVariable(value = "userId") int userId, Model model) {
        RestRequestClient rest = new RestRequestClient();
        Map map = new HashMap<>();
        map.put("userId", userId);
        Map resultMap = rest.restSubmitBean(BaseUtil.SELECT_NEWS, map);

        List<Map> list = new ArrayList();
        if(ResultUtil.isSuccess(resultMap)){
            list  =ResultUtil.getResultList(resultMap);

        }

        model.addAttribute("vos", list);
        return "home";
    }

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(@RequestParam(value = "pop", defaultValue="0") int pop, Model model) {
        Map map = new HashMap<>();
        map.put("userId", pop);

        map.put("hostId",hostHolder.getUser()!=null?hostHolder.getUser().getId() : 0);

        RestRequestClient rest = new RestRequestClient();
        Map resultMap = rest.restSubmitBean(BaseUtil.SELECT_NEWS, map);

        List<Map> list = new ArrayList();
        if (!resultMap.isEmpty()) {
            resultMap  = (Map) resultMap.get("successResult");

            list = (List) resultMap.get("result");
        }
        if(hostHolder.getUser() != null){
            pop=0;
        }
        model.addAttribute("vos",list);
        model.addAttribute("pop",pop);
        return "home";
    }


}
