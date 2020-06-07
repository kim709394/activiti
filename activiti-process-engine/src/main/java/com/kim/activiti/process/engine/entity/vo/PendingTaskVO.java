package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description  待办任务对象
 * @date 2020/6/7
 */
@Data
public class PendingTaskVO {

    /**任务id*/
    private String taskId;
    /**流程实例id*/
    private String processInstanceId;

    /**流程定义id*/
    private String processDefinitionId;
    /**流程定义名字*/
    private String processDefinitionName;
    /**表单id*/
    private String tenantId;

    /**审批人*/
    private String assignee;





}