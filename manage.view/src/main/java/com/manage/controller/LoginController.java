package com.manage.controller;

import com.manage.base.ConditionBase;
import com.manage.util.BaseUtil;
import com.manage.util.ConditionUtil;
import com.manage.util.RestRequestClient;
import com.manage.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 登录的控制器
 * Created by ASUS on 2017/8/26.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    /**
     * 注册
     *
     * @param model
     * @param userName   用户昵称
     * @param password   用户密码
     * @param rememberme 是否要记住账号
     * @param response   回应体
     * @return
     */
    @RequestMapping(value = "/reg", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String regist(Model model, @RequestParam("username") String userName
            , @RequestParam("password") String password,
                         @RequestParam(value = "rember", defaultValue = "0") int rememberme,
                         HttpServletResponse response) {
        RestRequestClient rest = new RestRequestClient();

        Map map = new HashMap();
        map.put("userName", userName);
        map.put("password", password);
        map.put("rememberMe", rememberme);

        try {
            LinkedHashMap linkedHashMap = rest.restSubmitBean(BaseUtil.USER_REGIST, map);
            if (ResultUtil.isSuccess(linkedHashMap)) {

                String result = (String) ResultUtil.getResultObject(linkedHashMap);
                if (!result.isEmpty() && result !=null) {
                    Cookie cookie = new Cookie("ticket", result);
                    cookie.setPath("/");
                    if (rememberme > 0) {
                        cookie.setMaxAge(ConditionBase.COOK_TIME_VALID_PERIOD);
                    }
                    response.addCookie(cookie);
                    return ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE, ConditionUtil.REGIST_SUCCESS_CONTENT);
                } else {
                    return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.REGIST_SUCCESS_CONTENT);
                }

            } else {
                return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.REGIST_SUCCESS_CONTENT);
            }
        } catch (Exception e) {
            logger.error("注册异常" + e);
            return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.REGIST_FAILE_CONTENT);
        }

    }

    /**
     * 登录
     *
     * @param model
     * @param userName   用户名字
     * @param password   用密码
     * @param rememberme 是否记住我
     * @param response
     * @return
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Model model, @RequestParam("username") String userName
            , @RequestParam("password") String password,
                        @RequestParam(value = "rember", defaultValue = "0") int rememberme,
                        HttpServletResponse response) {
        RestRequestClient rest = new RestRequestClient();

        Map map = new HashMap();
        map.put("userName", userName);
        map.put("password", password);
        map.put("rememberMe", rememberme);

        try {
            LinkedHashMap linkedHashMap = rest.restSubmitBean(BaseUtil.USER_LOGIN, map);
            if (ResultUtil.isSuccess(linkedHashMap)) {

                Map resultMap = ResultUtil.getResultMap(linkedHashMap);
                if (resultMap.containsKey("ticket")) {
                    Cookie cookie = new Cookie("ticket", resultMap.get("ticket").toString());
                    cookie.setPath("/");
                    if (rememberme > 0) {
                        cookie.setMaxAge(ConditionBase.COOK_TIME_VALID_PERIOD);
                    }
                    response.addCookie(cookie);
                    return ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE, ConditionUtil.LOGIN_SUCCESS_CONTENT);
                } else {
                    return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.LOGIN_FALSE_CONTENT);
                }

            } else {
                return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.LOGIN_FALSE_CONTENT);
            }
        } catch (Exception e) {
            logger.error("登录异常" + e);
            return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.LOGIN_FALSE_CONTENT);
        }

    }

    /**
     * 登录退出
     *
     * @param ticket
     * @return
     */
    public String logout(@RequestParam("ticket") String ticket) {

        Map map = new HashMap();
        map.put("ticket", ticket);

        RestRequestClient rest = new RestRequestClient();
        LinkedHashMap linkedHashMap = rest.restSubmitBean(BaseUtil.USER_LOGINOUT, map);

        return "redirect:/";
    }


}
