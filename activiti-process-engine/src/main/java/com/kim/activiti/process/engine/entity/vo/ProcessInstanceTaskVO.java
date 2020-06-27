package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author huangjie
 * @description   历史流程任务对象
 * @date 2020/5/29
 */
@Data
public class ProcessInstanceTaskVO {
    /**任务id*/
    private String taskId;
    /**紧跟着的上一级任务id*/
    private String parentTaskId;
    /**任务名字*/
    private String name;
    /**审批人*/
    private String assignee;
    /**操作平台，windows、mac、微信、企业微信、安卓、ios等*/
    private String platform;
    /**审批意见*/
    private String comment;
    /**当前节点是否完成*/
    private boolean isFinished;
    /**审批处理时间*/
    private Date dueDate;
    /**任务创建时间*/
    private Date createTime;
    /**任务结束时间*/
    private Date completeTime;



}
