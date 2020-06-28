package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Data
public class StartProcessInstanceVO {

    /**流程定义id*/
    private String processDefinitionId;
    /**创建人*/
    private String creator;
    /**业务流程变量*/
    private Map<String,Object> bussinessVariables;
}
