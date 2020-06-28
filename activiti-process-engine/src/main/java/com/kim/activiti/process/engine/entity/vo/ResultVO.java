package com.kim.activiti.process.engine.entity.vo;

import com.kim.activiti.process.engine.enumeration.StatusEnum;
import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Data
public class ResultVO<T> {

    /**状态码*/
    private Integer code;
    /**信息*/
    private String message;
    /**数据*/
    private T data;

    public ResultVO(){

    }

    public ResultVO(Integer code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }
    public static ResultVO success(){
        return success(null);
    }

    public static <T> ResultVO<T> success(T data){
        return response(StatusEnum.CODE_0.code,StatusEnum.CODE_0.message,data);
    }

    public static <T> ResultVO<T> error(){
        return response(StatusEnum.CODE_5010.code,StatusEnum.CODE_5010.message);
    }

    public static <T> ResultVO<T> response(Integer code,String message,T data){
        return new ResultVO(code,message,data);
    }

    public static <T> ResultVO<T> response(Integer code,String message){
        return response(code,message,null);
    }





}
