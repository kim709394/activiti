package com.kim.activiti.process.engine.service;

import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryOutputVO;

import java.util.Map;

/**
 * @author huangjie
 * @description   流程实例服务
 * @date 2020/5/24
 */

public interface ProcessInstancesService {

    /**
     * 根据流程定义id启动一个流程实例
     * */
    String startProcessInstanceByProcessDefId(String processDefinitionId,Map<String,Object> bussinessVariables);


    /**
     * 分页查询流程实例列表
     * */
    ProcessInstanceQueryOutputVO queryPagingProcessInstances(ProcessInstanceQueryInputVO processInstanceQueryInputVO);

    /**
     * 删除流程实例
     * */
    void deleteProcessInstance(String processInstanceId,String deleteReason);











}
