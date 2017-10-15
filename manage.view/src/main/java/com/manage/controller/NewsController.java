package com.manage.controller;

import com.manage.base.ConditionBase;
import com.manage.model.CommentBean;
import com.manage.model.HostHolder;
import com.manage.model.NewsBean;
import com.manage.model.ViewObject;
import com.manage.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

/**
 * Created by ASUS on 2017/9/10.
 */
@Controller
public class NewsController {

    /**
     * 日志
     */
    private static final  Logger  logger= LoggerFactory.getLogger(NewsController.class);


    @Autowired
    HostHolder  hostHolder;

    /**
     * 根据用户id 查询 详情
     * @param newsId
     * @param model
     * @return
     */
    @RequestMapping(path = "/news/{newsId}",method = RequestMethod.GET)
    public String newsDetail(@PathVariable("newsId") int newsId, Model model){

        try {
            Map map=new HashMap();
            map.put("newsId",newsId);

            RestRequestClient  rest =new RestRequestClient();
            Map  resultMap=rest.restSubmitBean(BaseUtil.USER_GET_NEWS_DETAIL,map);

            if(ResultUtil.isSuccess(resultMap)){
                resultMap  = (Map) resultMap.get("successResult");

                List<Map>  list = (List) resultMap.get("comments");

                model.addAttribute("comments",list);
                model.addAttribute("news",resultMap.get("news"));
                model.addAttribute("owner",resultMap.get("owner"));
            }
        }catch (Exception e){
            logger.error("-----根据用户id 查询详情出错----",e);
        }

        return "detail";
    }

    /**
     * 增加News
     * @param image 图片
     * @param title 标题
     * @param link 链接
     * @return
     */
    @RequestMapping(path = "/user/addNews/",method = RequestMethod.POST)
    @ResponseBody
    public String addNews(@RequestParam("image") String image,
                          @RequestParam("title") String title,
                          @RequestParam("link") String link){
        logger.info("增加News");
        NewsBean  bean =new NewsBean();
        bean.setCreatedDate(new Date());
        bean.setTitle(title);
        bean.setImage(image);
        bean.setLink(link);
        bean.setLikeCount(ConditionBase.NEW_BEAN_LIKE_COUNT);
        bean.setCommentCount(ConditionBase.NEW_BEAN_COMMENT_COUNT);

        if(hostHolder.getUser()!=null){
            bean.setUserId(hostHolder.getUser().getId());
        }else {
            //设置一个匿名用户
            bean.setUserId(3);
        }

        RestRequestClient  rest=new RestRequestClient();
        LinkedHashMap  linkedHashMap =rest.restSubmitBean(BaseUtil.USER_ADD_NEWS,bean);
        if(ResultUtil.isSuccess(linkedHashMap)){
            return ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE,ConditionUtil.ADD_USER_NEWS_SUCCESS);
        }

        return  ResultUtil.getJSONString(ConditionUtil.FAILE_CODE,ConditionUtil.ADD_USER_NEWS_FALSE);

    }

    /**
     * 增加comment
     * @param newsId  新闻id
     * @param content  内容
     * @return
     */
    @RequestMapping(path = "/addComment",method = {RequestMethod.GET,RequestMethod.POST})
    public String addComment(@RequestParam("newsId") int newsId,
                          @RequestParam("content") String content ){
       try{
           content = HtmlUtils.htmlEscape(content);
           //过滤content
           CommentBean  comment =new CommentBean();
           comment.setUserId(hostHolder.getUser().getId());
           comment.setContent(content);
           comment.setEntityId(newsId);
           comment.setEntityType(EntityTypeUtil.ENTITY_NEWS);
           comment.setCreatedDate(new Date());
           comment.setStatus(0);

           RestRequestClient  rest=new RestRequestClient();
           LinkedHashMap  linkedHashMap =rest.restSubmitBean(BaseUtil.USER_ADD_ADDCOMMENT,comment);


       }catch (Exception e){
            logger.error("-----增加评论出错------",e);

       }

        return "redirect:/news/"+String.valueOf(newsId);
    }

}
