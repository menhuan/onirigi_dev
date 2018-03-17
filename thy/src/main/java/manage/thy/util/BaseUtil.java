package manage.thy.util;

/**
 * Created by ASUS on 2017/8/23.
 */
public class BaseUtil {

    /**
     * 基础url
     */
	public static final String BASE_URL = "http://localhost:8081/";

//	public static final String BASE_URL = "http://119.29.41.47:8081/";
	
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


    /*********************************************微信相关的链接***********************************************/
    
    /**
     * 微信统一下单
     */
    public static final String WECHAT_UNIFORMORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";  


    /**********************************************图灵机器人***************************************************/
    
    public static final String TULING_ROBOT_API = "http://www.tuling123.com/openapi/api";
    
    
    /******************************************imooc 相关的内容  ***********************************************/
    
    /**
     * 广告增加
     */
    public static final String IMOOC_AD_ADD = BASE_URL + "ad/add";
    
    /**
     * 广告栏目 查询
     */
    public static final String IMOOC_AD_SELECT = BASE_URL + "ad/select";
    
    /**
     * 广告移除
     */
    public static final String IMOOC_AD_REMOVE = BASE_URL + "ad/remove" ;
    
    /**
     * 广告修改
     */
    public static final String IMOOC_AD_MODITY = BASE_URL + "ad/modify";
    
    /**
     * 新增商户列表
     */
    public static final String IMOOC_BUSINESS_ADD = BASE_URL + "business/add";
    
    /**
     * 删除商户列表
     */
    public static final String IMOOC_BUSINESS_REMOVE = BASE_URL +"business/remove";
    
    /**
     * 查询商户信息
     */
    public static final String IMOOC_BUSINESS_SELECT = BASE_URL +"business/select";

    /**
     * 更新 商户信息
     */
    public static final String IMOOC_BUSINESS_UPDATE = BASE_URL +"business/update" ;

    /**
     * 根据主键id查询
     */
    public static final String IMOOC_BUSINESS_SELECT_BYID = BASE_URL +"business/selectByid";

    /**
     * 查询字典信息
     */
    public static  final String IMOOC_DIS_SELECT = BASE_URL + "dis/select" ;


}


