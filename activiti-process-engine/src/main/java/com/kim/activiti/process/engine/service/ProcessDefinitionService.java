package com.kim.activiti.process.engine.service;

import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryOutputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefinitionVO;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huangjie
 * @description   流程定义服务
 * @date 2020/5/24
 */
public interface ProcessDefinitionService {


    /**
     * 部署流程定义
     * */
    void deployProcessDef(ProcessDefinitionVO processDefinitionVO);

    /**
     * 查询流程定义
     * */
    ProcessDefQueryOutputVO queryPaging(ProcessDefQueryInputVO queryInputVO);

    /**
     * 批量查询全部流程定义
     * */
    List<ProcessDefinitionVO> queryAll();

    /***
     *批量查询指定流程定义id集合的流程定义
     */
    List<ProcessDefinitionVO> queryAllBy(Set<String>processDefinitionIds);


    /**
     * 获取初始流程变量
     * */
    Map<String,Object> getInitProcessVariables(String processDefinitionId);


    /**
     * 设置初始流程变量
     * */
    void setInitProcessVariables(String processDefinitionId,Map<String,Object> initProcessVariables);

}
