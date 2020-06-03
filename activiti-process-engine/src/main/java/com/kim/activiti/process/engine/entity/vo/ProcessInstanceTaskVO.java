package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/29
 */
@Data
public class ProcessInstanceTaskVO {

    private String taskId;

    private String name;

    private String assignee;

    private String platform;

    private String common;

    private boolean isFinished;

    private Date dueDate;

    private Date createTime;



}
