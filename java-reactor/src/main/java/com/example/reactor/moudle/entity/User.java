package com.example.reactor.moudle.entity;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class User {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Max(value = 100, message = "不能大于100")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;

    @NotBlank(message = "身份证号码不能为空")
    private String idNumber;

    @NotBlank(message = "电话号码不能为空")
    private String phone;

}
