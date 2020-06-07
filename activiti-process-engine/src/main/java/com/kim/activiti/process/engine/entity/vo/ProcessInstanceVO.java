package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description 流程实例对象
 * @date 2020/6/8
 */
@Data
public class ProcessInstanceVO {


    /**流程实例id*/
    private String processInstanceId;
    /**是否完成*/
    private Boolean isFinished;
    /**创建人*/
    private String creator;
    /**流程定义id*/
    private String processDefinitionId;
    /**流程定义name*/
    private String processDefinitionName;
    /**表单id*/
    private String tenantId;


}
