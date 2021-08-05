package com.example.reactor.utils;


import com.example.reactor.enums.EncryptEnum;
import org.apache.commons.lang3.StringUtils;

public class EncryptUtils {


    public static String EncryptStr(String str, EncryptEnum encryptEnum) {
        if (StringUtils.isBlank(str)) {
            return "加密字符串不能为空";
        }
        String encryptStr = "";
        switch (encryptEnum) {
            case NAME:
                encryptStr = str.replace(str.substring(0, 1), "*");
                break;
            case CARD:
                encryptStr = str.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1****$2");
                break;
            case PHONE:
                encryptStr = str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                break;
            default:
                break;
        }

        return encryptStr.replaceAll(",","").replaceAll("E","");
    }
}
