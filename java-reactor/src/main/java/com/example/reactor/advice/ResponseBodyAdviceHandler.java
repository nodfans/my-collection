package com.example.reactor.advice;

import com.alibaba.fastjson.JSON;
import com.example.reactor.enums.EncryptEnum;
import com.example.reactor.utils.EncryptUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Map;


@RestControllerAdvice
@Slf4j
public class ResponseBodyAdviceHandler<T> implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Map map = new Gson().fromJson(new Gson().toJson(o), Map.class);
        if (map.get("result").toString() != null) {
            Map resultMap = new Gson().fromJson(map.get("result").toString(), new TypeToken<Map<String, String>>() {
            }.getType());

            if (resultMap.get("name").toString() != null) {
                String name = EncryptUtils.EncryptStr(resultMap.get("name").toString(), EncryptEnum.NAME);
                resultMap.put("name", name);
            }

            if (resultMap.get("phone").toString() != null) {
                String name = EncryptUtils.EncryptStr(String.valueOf(resultMap.get("phone")), EncryptEnum.PHONE);
                resultMap.put("phone", name);
            }

            if (resultMap.get("idNumber").toString() != null) {
                String name = EncryptUtils.EncryptStr(resultMap.get("idNumber").toString(), EncryptEnum.CARD);
                resultMap.put("idNumber", name);
            }

            map.put("result", resultMap);
        }
        Method method = methodParameter.getMethod();
        String url = serverHttpRequest.getURI().toASCIIString();
        log.info("{}.{},url={},result={}", method.getDeclaringClass().getSimpleName(), method.getName(), url, JSON.toJSONString(map));
        return map;
    }
}
