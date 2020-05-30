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

    private List<String> assigneeId;

    private String platform;

    private boolean isFinished;

    private Date dueDate;

    private Date createTime;



}
