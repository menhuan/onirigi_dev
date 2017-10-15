package com.manage.util;

import java.security.SecureRandom;

/**
 * Created by ASUS on 2017/8/23.
 */
public class BaseUtil {

    /**
     * 基础url
     */
    public static final String BASE_URL = "http://localhost:8081/";

    /**
     * 用户登录
     */
    public static final String USER_LOGIN=BASE_URL+"loginRest/login";

    /**
     * 用户注册
     */
    public static final String USER_REGIST=BASE_URL+"loginRest/registered";

    /**
     * 用户登录退出
     */
    public static final  String USER_LOGINOUT=BASE_URL+"loginRest/logout";

    /**
     *  拦截器 查询的信息
     */
    public static final String  HANDLE_LOGIN_USER=BASE_URL+"loginRest/loginUser";

    /**
     * 得到新闻rul
     */
    public static final String SELECT_NEWS = BASE_URL + "newsRest/getLastesNews";

    /**
     * 查询详细信息
     */
    public static final String USER_GET_NEWS_DETAIL=BASE_URL+"newsRest/newsDetail";

    /**
     * 增加News
     */
    public static final String USER_ADD_NEWS=BASE_URL +"newsRest/addNews";

    /**
     * 增加评论
     */
    public static final String USER_ADD_ADDCOMMENT=BASE_URL +"newsRest/addComment";

    /**
     * message List 查询
     */
    public static final String MESSAGE_SELECT_MSGLIST=BASE_URL +"messageRest/msgList";

    /**
     * 查询详情
     */
    public static final String MESSAGE_SELECT_DETAIL=BASE_URL +"messageRest/getConversationDetail";

    /**
     * 增加喜欢 的人数
     */
    public static final String LIKE_ADD_NUM =BASE_URL+"likeRest/like";

    /**
     * 增加不喜欢的人数
     */
    public static final  String DISLIKE_ADD_NUM =BASE_URL+"likeRest/dislike";




}
