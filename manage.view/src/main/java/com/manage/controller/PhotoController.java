package com.manage.controller;

import com.manage.util.ConditionUtil;
import com.manage.util.QiniuService;
import com.manage.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片Controller
 * Created by ASUS on 2017/8/29.
 */
@Controller
public class PhotoController {


    @Autowired
    private QiniuService qiniuService;

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PhotoController.class);

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = qiniuService.saveImageCloud(file);
            if (fileUrl == null) {
                return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.UPLOAD_IMAGE_FAILED);
            }

            return ResultUtil.getJSONString(ConditionUtil.SUCCESS_CODE,fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.getJSONString(ConditionUtil.FAILE_CODE, ConditionUtil.UPLOAD_IMAGE_FAILED);
    }

    /**
     * 读取图片
     *
     * @param imageName
     * @param response
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public void getImage(@RequestParam("name") String imageName,
                         HttpServletResponse response) {
        try{
           response.setContentType("image/jpeg");
         //   StreamUtils.copy(new FileInputStream(new File(T)));


        }catch (Exception e){
            logger.error("读取图片出现异常"+imageName,e);

        }

        //   StreamUtils.copy(new FileInputStream(new File()));

    }


}
