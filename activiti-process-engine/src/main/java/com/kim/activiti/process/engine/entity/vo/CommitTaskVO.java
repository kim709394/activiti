package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author huangjie
 * @description 完成任务对象
 * @date 2020/6/8
 */
@Data
public class CommitTaskVO {
    /**任务id*/
    private String taskId;
    /**流程实例id*/
    private String processInstanceId;
    /**审批意见*/
    private String comment;
    /**审批人*/
    private String assignee;
    /**流程变量*/
    private Map<String,Object> processVariables;
    /**审批平台*/
    private String platform;
    /**审批类别:human:手动，system:系统自动*/
    private String owner;


}
