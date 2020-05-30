package com.kim.activiti.process.engine.service;

/**
 * @author huangjie
 * @description   流程实例服务
 * @date 2020/5/24
 */
public interface ProcessInstancesService {

    /**
     * 根据流程定义id启动一个流程实例
     * */
    String startProcessInstanceByProcessDefId(String processDefinitionId);




}
