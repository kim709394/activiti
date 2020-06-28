package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Data
public class InitProcessVariablesVO {


    private String processDefinitionId;
    private Map<String,Object> initProcessVariables;

}
