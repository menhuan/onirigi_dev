package com.manage.util;

/**
 * 编码 code 成功失败 等状态 键值对
 * Created by ASUS on 2017/8/27.
 */
public class ConditionUtil {

    /*******************************成功信息***********************************/

    /**
     * 成功编码
     */
    public static final Integer SUCCESS_CODE = 100;

    public static final String REGIST_SUCCESS_CONTENT = "注册成功";

    public static final String UPLOAD_IMAGE_SUCCESS = "图片上传成功";

    public static final String LOGIN_SUCCESS_CONTENT ="登录成功。正在跳转请稍后";

    /**
     * 增加分享信息成功
     */
    public static final String ADD_USER_NEWS_SUCCESS ="增加分享信息成功";


    /*******************************失败信息*******************************/

    /**
     * 失败编码
     */
    public static final Integer FAILE_CODE = 200;

    public static final String REGIST_FAILE_CONTENT = "注册失败";

    public static final String UPLOAD_IMAGE_FAILED = "图片上传失败";

    public static final String INSERT_FALSE_CONTENT="插入评论失败";

    public  static final String LOGIN_FALSE_CONTENT ="登录失败。请重新尝试";

    /**
     * 增加分享信息失败
     */
    public static final String ADD_USER_NEWS_FALSE ="增加分享信息失败";


}
