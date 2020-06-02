package com.kim.activiti.process.engine.listener.businesslistener;

import java.util.Map;

/**
 * @author huangjie
 * @description  完成任务时业务逻辑处理
 * @date 2020/6/2
 */


public interface CommitTaskListener {

    /**
     * 返回任务定义key值(流程设计器中的usertask的id值)
     * */
    String getTaskDefinitionKey();

    /**
     * 返回流程定义id或者流程定义key值
     * */
    String getProcessDefinitionIdOrKey();



    /**
     * 进行业务逻辑处理
     * */
    void busniessHandle(String processInstanceId, Map<String,Object> processVariables,String taskId,String assignee);

}
