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
     * @param processDefinitionId  流程定义id
     * @param  creator 创建人，可以为id，可以为name
     * @param  bussinessVariables  业务变量
     * @return String 流程实例id
     * */

    String startProcessInstanceByProcessDefId(String processDefinitionId,String creator,Map<String,Object> bussinessVariables);


    /**
     * 分页查询流程实例列表
     * @param processInstanceQueryInputVO 分页查询流程实例输入对象
     * @return  ProcessInstanceQueryOutputVO  分页查询流程实例输出对象
     * */
    ProcessInstanceQueryOutputVO queryPagingProcessInstances(ProcessInstanceQueryInputVO processInstanceQueryInputVO);

    /**
     * 删除流程实例
     * @param processInstanceId 流程实例id
     * */
    void deleteProcessInstance(String processInstanceId);











}
