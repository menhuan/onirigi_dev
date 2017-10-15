package com.manage.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manage.model.HostHolder;
import com.manage.model.LoginTicketBean;
import com.manage.model.UserBean;
import com.manage.util.BaseUtil;
import com.manage.util.DateUtil;
import com.manage.util.RestRequestClient;
import com.manage.util.ResultUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * Created by ASUS on 2017/9/23.
 */
@Component
public class PassportInterceptor implements HandlerInterceptor{

    @Autowired
    private HostHolder  hostHolder;

    /**
     * 进入控制器之前拦截
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket= null;

        if(httpServletRequest.getCookies() !=null){
            for(Cookie cookie : httpServletRequest.getCookies()){
                if("ticket".equals(cookie.getName())){
                    ticket =cookie.getValue();
                    break;
                }
            }
        }

        if(ticket != null){

            RestRequestClient  rest =new RestRequestClient();
            LinkedHashMap  linkedHashMap =rest.restSubmitBean(BaseUtil.HANDLE_LOGIN_USER,ticket);
            if(ResultUtil.isSuccess(linkedHashMap)){
                Map  resultMap = (Map) ResultUtil.getResultMap(linkedHashMap);
                JSONObject  object =JSON.parseObject(JSON.toJSONString(resultMap.get("loginTicket")) );

                String expire = object.getString("expireDate").replaceAll("\"","");

                Date   expireDate = DateUtil.StringToDate(expire);


                if(object == null || expireDate.before(new Date())  || object.getInteger("status") !=0){
                    return true ;
                }

                UserBean  userBean = JSON.parseObject(JSON.toJSONString(resultMap.get("userBean")),UserBean.class) ;
                if(userBean != null){
                    hostHolder.setUsers(userBean);
                }
            }
        }

        return true;
    }

    /**
     * 在渲染页面之前拦截
     * 将当前用户 加入到页面中
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView !=null  && hostHolder.getUser()!=null){
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    /**
     * 渲染完成后拦截
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
         hostHolder.clear();
    }
}
