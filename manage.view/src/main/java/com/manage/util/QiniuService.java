package com.manage.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 七牛云服务
 * Created by ASUS on 2017/8/29.
 */
@Service
public class QiniuService {

    //日志文件
    private Logger logger = LoggerFactory.getLogger(QiniuService.class);

    //构造一个带指定Zone对象的配置类  注意 自己的存储空间在哪里 不要用错了文件
    Configuration cfg = new Configuration(Zone.zone2());

    //...其他参数参考类注释
    //...生成上传凭证，然后准备上传
    //如果是Windows情况下，格式是 D:\\qiniu\\test.png
    //默认不指定key的情况下，以文件内容的hash值作为文件名
    UploadManager uploadManager = new UploadManager(cfg);

    String accessKey = "zb-UWFMHmfBse-H_uj7jyJnoaocwv-YaZIKEQagS";
    String secretKey = "O5njhiu-5BdxoooSSgvESh2VNWBOmOyxDZNCDiDj";
    String bucket = "photo";
    Auth auth = Auth.create(accessKey, secretKey);

    /**
     * 设置返回的 图片格式
     */
    public static final String CONTENT_TYPE = "image/jpeg";

    /**
     * 云存储路径前缀
     */
    private static final String CLOUD_NAME = "http://ovhyhup3o.bkt.clouddn.com/";

    String localFilePath = "/home/qiniu/";

    /***
     * 允许上传的图片格式
     */
    public static String[] IMAGE_FILE_EXTD = new String[]{"png", "jpg", "bmp", "jpeg"};


    /**
     * 使用简单上传类型
     *
     * @return
     */
    public String getUptoken() {
        return auth.uploadToken(bucket);
    }

    /**
     * 保存图片
     *
     * @param file 图片文件
     * @return
     * @throws Exception
     */
    public String saveImageCloud(MultipartFile file) throws Exception {
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            return null;
        }

        String imageSuffix = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        if (!isAllowedUpload(imageSuffix)) {
            return null;
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + imageSuffix;
        try {
            Response response = uploadManager.put(file.getBytes(), fileName, getUptoken());

            if (response.isOK() && response.isJson()) {
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return CLOUD_NAME + putRet.key;
            } else {
                logger.error("七牛异常:" + response.body());
                return null;
            }

        } catch (QiniuException ex) {
            logger.error("七牛异常:", ex);
            return null;
        }
    }

    /**
     * 判断上传的文件是否是符合我们要求的图片格式
     *
     * @param imageSuffix 需要上传的文件图片后缀名称
     * @return
     */
    public boolean isAllowedUpload(String imageSuffix) {

        for (String suffix : IMAGE_FILE_EXTD) {
            if (suffix.equals(imageSuffix)) {
                return true;
            }
        }

        return false;
    }

}
