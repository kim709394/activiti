package com.kim.activiti.process.engine.listener.businesslistener;

import java.util.Map;

/**
 * @author huangjie
 * @description  流程启动时业务逻辑操作，设置审批人的流程变量等
 * @date 2020/6/2
 */
public interface ProcessInstanceStartListener {


    /**
     * 返回流程定义id值
     * */
    String getProcessDefinitionId();


    /**
     * 进行业务逻辑操作
     * */
    void bussinessHandle(Map<String,Object> initProcessVariables,Map<String,Object> bussinessVariables);


}
