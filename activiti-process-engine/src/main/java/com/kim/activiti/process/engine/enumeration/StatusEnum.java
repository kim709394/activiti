package com.kim.activiti.process.engine.enumeration;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
public enum StatusEnum {


    CODE_5010(5010,"系统异常"),
    CODE_0(0,"success");

    public Integer code;
    public String message;

    StatusEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

}
