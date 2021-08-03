package com.access.server.util;

import com.access.server.exceptions.BizException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author ZH
 * @date 2021/6/10/010 16:27
 * @description:
 * @autograph: 你知道的越多，你不知道的就越多
 */
@Component
public class UploadFileUtils {

    /**
     * @param multipartFile:文件
     * @param uploadPath       上传路径
     * @Description:文件上传
     * @Author: ZH
     * @Date: 2021/6/10/010 18:56
     * @return: java.lang.String
     **/
    public static String UploadImage(MultipartFile multipartFile, String uploadPath) {
        try {
            //获取文件名
            String fileName = multipartFile.getOriginalFilename();
            //拼接文件路径
            String filePath = uploadPath + DateUtils.getCurrentDate();
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (suffixName == null) {
                throw new BizException("文件后缀名为空!");
            }
            //拼接上传文件路径
            String newFileName = DateUtils.getCurrentDate() + UUID.randomUUID().toString() + suffixName;
            newFileName = newFileName.replaceAll("-", "");
            File newFile = new File(file, newFileName);
            //上传
            multipartFile.transferTo(newFile);
            return filePath + "/" + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传图片
     * @param multipartFile
     * @param uploadPath
     * @param accountId
     * @return
     */
    public static String uploadByByte(MultipartFile multipartFile, String uploadPath, String accountId) {
        try {
            //获取文件名
            String fileName = multipartFile.getOriginalFilename();
            //拼接文件路径
            String filePath = uploadPath + DateUtils.getCurrentDate() + "/" + accountId;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            //拼接上传文件路径
            String newFileName = DateUtils.getCurrentDate() + UUID.randomUUID().toString() + ".jpg";
            newFileName = newFileName.replaceAll("-", "");
            File newFile = new File(file, newFileName);
            //上传
            multipartFile.transferTo(newFile);
            return filePath + "/" + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
