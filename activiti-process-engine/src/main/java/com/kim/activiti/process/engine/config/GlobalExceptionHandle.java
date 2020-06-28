package com.kim.activiti.process.engine.config;

import com.kim.activiti.process.engine.entity.vo.ResultVO;
import com.kim.activiti.process.engine.exception.ActivitiProcessEngineException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author huangjie
 * @description
 * @date 2019/10/22
 */
@Slf4j
@RestControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandle {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO handleGlobalException(Exception exception){
        log.error("exception : " + exception,exception);

        return ResultVO.error();
    }

    @ExceptionHandler(ActivitiProcessEngineException.class)
    @ResponseBody
    public ResultVO handleGlobalException(ActivitiProcessEngineException exception){
        log.error("exception : " + exception,exception);

        return ResultVO.response(exception.getCode(),exception.getMessage());
    }


}
