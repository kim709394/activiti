package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/6/4
 */
@Data
public class PendingTaskQueryInputVO  extends BaseQueryInput{



    private String assignee;

    /**流程定义id*/
    private String processDefinitionId;

}
