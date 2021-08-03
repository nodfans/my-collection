package com.utils.util;

import com.utils.util.date.DateUtil;
import com.utils.util.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadFileUtils {


    /**
     * 上传图片
     * @param multipartFile pic
     * @param uploadPath    path
     * @param accountId     id
     * @return String
     */
    public static String uploadByByte(MultipartFile multipartFile, String uploadPath, String accountId) {
        /*
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cloudImgDto.getBytes());
            MultipartFile file = new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), byteArrayInputStream);
            String uploadImage = UploadFileUtils.uploadByByte(file, this.uploadImage + "temp_img/", cloudImgDto.getAccountId());
            logger.error("uploadImage ------------->{}", uploadImage);
            String substring = uploadImage.substring(uploadImage.lastIndexOf("temp_img"));
            String url = cloudUrl + substring;
         */
        try {
            // 获取文件名
            String fileName = multipartFile.getOriginalFilename();
            // 拼接文件路径
            String filePath = uploadPath + DateUtil.getCurrentDate() + "/" + accountId;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 拼接上传文件路径
            String newFileName = DateUtil.getCurrentDate() + UUID.randomUUID().toString() + ".jpg";
            newFileName = newFileName.replaceAll("-", "");
            File newFile = new File(file, newFileName);
            // 上传
            multipartFile.transferTo(newFile);
            return filePath + "/" + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
