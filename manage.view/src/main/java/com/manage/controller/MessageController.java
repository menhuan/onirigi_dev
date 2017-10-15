package com.manage.controller;

import com.manage.model.HostHolder;
import com.manage.model.MessageBean;
import com.manage.util.BaseUtil;
import com.manage.util.ConditionUtil;
import com.manage.util.RestRequestClient;
import com.manage.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.util.*;

/**
 * 消息Controller
 * Created by ASUS on 2017/9/18.
 */
@Controller
@RequestMapping(value = "/msg")
public class MessageController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    private HostHolder hostHolder;

    /**
     * 获取站内信 通道
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String conversationDetail(Model model) {
        try {
            int localUserId = hostHolder.getUser().getId();
            Map map =new HashMap<>();
            map.put("localUserId",localUserId);

            RestRequestClient  rest=new RestRequestClient();
            LinkedHashMap  linkedHashMap=rest.restSubmitBean(BaseUtil.MESSAGE_SELECT_MSGLIST,map);
            if(ResultUtil.isSuccess(linkedHashMap)){
               List<Map> resultList = (List<Map>) ((Map)linkedHashMap.get("successResult")).get("result");
               model.addAttribute("conversations", resultList);
            }

        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
        }
        return "letter";
    }


    /**
     * 获取详情列表
     * @param model
     * @param conversationId
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public String conversationDetail(Model model, @RequestParam("conversationId")String conversationId) {
        try {

            Map map =new HashMap<>();
            map.put("conversationId",conversationId);

            RestRequestClient  rest=new RestRequestClient();
            LinkedHashMap  linkedHashMap=rest.restSubmitBean(BaseUtil.MESSAGE_SELECT_DETAIL,map);
            if(ResultUtil.isSuccess(linkedHashMap)){
                List<Map> resultList = (List<Map>) ((Map)linkedHashMap.get("successResult")).get("result");
                model.addAttribute("messages", resultList);
            }

        } catch (Exception e) {
            logger.error("获取详情消息失败" + e.getMessage());
        }
        return "letterDetail";
    }

    /**
     * 增加Comment
     * @return
     */
    @RequestMapping(value = "addMessage",method = RequestMethod.GET)
    @ResponseBody
    public String conversationDetail(@RequestParam("formId") int formId,
                                     @RequestParam("toId")int toId,
                                     @RequestParam("content")String content) {
        try {

            MessageBean msg=new MessageBean();
            msg.setContent(content);
            msg.setFromId(formId);
            msg.setToId(toId);
            msg.setCreatedDate(new Date());

            RestRequestClient  rest=new RestRequestClient();
            LinkedHashMap  linkedHashMap=rest.restSubmitBean(BaseUtil.MESSAGE_SELECT_MSGLIST,msg);
            if(ResultUtil.isSuccess(linkedHashMap)){
                Integer id = (Integer) ((Map)linkedHashMap.get("successResult")).get("result");
                return ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE,id.toString());
            }

        } catch (Exception e) {
            logger.error("获取详情消息失败" + e.getMessage());
            return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE,ConditionUtil.INSERT_FALSE_CONTENT);
        }

        return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE,ConditionUtil.INSERT_FALSE_CONTENT);
    }


}
