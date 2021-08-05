package com.example.reactor.enums;

public enum EncryptEnum {

    NAME("姓名"),
    CARD("身份证"),
    PHONE("电话号码")


    ;

    EncryptEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

}
