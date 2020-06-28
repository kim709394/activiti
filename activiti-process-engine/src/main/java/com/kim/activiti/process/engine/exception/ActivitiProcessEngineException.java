package com.kim.activiti.process.engine.exception;

import com.kim.activiti.process.engine.enumeration.StatusEnum;
import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Data
public class ActivitiProcessEngineException extends RuntimeException {


    private StatusEnum statusEnum;

    private Integer code;
    private String message;

    public ActivitiProcessEngineException(){

    }
    public ActivitiProcessEngineException(StatusEnum statusEnum){
        this.statusEnum = statusEnum;
        this.code= statusEnum.code;
        this.message= statusEnum.message;
    }
    public ActivitiProcessEngineException(Integer code,String message){
        this.code=code;
        this.message=message;
    }



}
