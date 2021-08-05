package com.example.reactor.exception;

import com.example.reactor.enums.ErrorEnum;
import com.example.reactor.utils.WrapMapper;
import com.example.reactor.utils.Wrapper;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice(annotations = RestController.class)
public class GlobalDefinedException extends RuntimeException {

    // http异常处理器
    @ExceptionHandler(HttpClientErrorException.class)
    public Wrapper defaultExceptionHandler(HttpServletResponse response, HttpClientErrorException e) {
        response.setStatus(e.getRawStatusCode());
        return WrapMapper.wrap(e.getRawStatusCode(), e.getMessage());
    }

    // 参数校验异常处理器
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Wrapper MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return WrapMapper.wrap(ErrorEnum.PARAMS_ERROR.getCode(), objectError.getDefaultMessage());
    }
}