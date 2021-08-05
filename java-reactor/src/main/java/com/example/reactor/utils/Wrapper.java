package com.example.reactor.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;


/**
 * The class Wrapper.
 *
 * @param <T> the type parameter @author paascloud.net@gmail.com
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Wrapper<T> implements Serializable {

    private static final long serialVersionUID = 4893280118017319089L;

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_MESSAGE = "服务异常";
    public static final int ILLEGAL_ARGUMENT_CODE_ = 100;
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";


    @ApiModelProperty(value = "编码", name = "code")
    private int code;
    @ApiModelProperty(value = "信息", name = "message")
    private String message;
    @ApiModelProperty(value = "数据", name = "T")
    private T result;

    /**
     * Instantiates a new wrapper. default code=200
     */
    Wrapper() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * Instantiates a new wrapper.
     * @param code    the code
     * @param message the message
     */
    Wrapper(int code, String message) {
        this(code, message, null);
    }


    /**
     * Instantiates a new wrapper.
     * @param code    the code
     * @param message the message
     * @param result  the result
     */
    Wrapper(int code, String message, T result) {
        super();
        this.code(code).message(message).result(result);
    }

    /**
     * Sets the code
     * @param code the new code
     * @return the wrapper
     */
    private Wrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    /**
     * Sets the message
     * @param message the new message
     * @return the wrapper
     */
    private Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the result
     * @param result the new result
     * @return the wrapper
     */
    public Wrapper<T> result(T result) {
        this.setResult(result);
        return this;
    }

    /**
     * code =200,true; otherwise false.
     * @return  boolean
     */
    @JsonIgnore
    public boolean success() {
        return Wrapper.SUCCESS_CODE == this.code;
    }

    /**
     * code !=200,true; otherwise false.
     * @return boolean
     */
    @JsonIgnore
    public boolean error() {
        return !success();
    }

}
