package com.kim.activiti.process.engine.service;

import com.kim.activiti.process.engine.entity.vo.InitProcessVariablesVO;
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
     * @param processDefinitionVO 流程定义对象
     * @return 流程定义id
     * */
    String deployProcessDef(ProcessDefinitionVO processDefinitionVO);

    /**
     * 分页查询流程定义
     * @param queryInputVO 分页查询流程定义输入对象
     * @return ProcessDefQueryOutputVO 分页查询流程定义输出对象
     * */
    ProcessDefQueryOutputVO queryPaging(ProcessDefQueryInputVO queryInputVO);

    /**
     * 批量查询全部流程定义
     * @return 流程定义集合
     * */
    List<ProcessDefinitionVO> queryAll();

    /***
     *批量查询指定流程定义id集合的流程定义
     * @param processDefinitionIds  流程定义id集合
     * @return  List<ProcessDefinitionVO>  流程定义对象集合
     */
    List<ProcessDefinitionVO> queryAllBy(Set<String> processDefinitionIds);


    /**
     * 获取初始流程变量
     * @param processDefinitionId 流程定义id
     * @return Map<String,Object> 初始流程变量
     * */
    Map<String,Object> getInitProcessVariables(String processDefinitionId);


    /**
     * 设置初始流程变量
     * @param initProcessVariables 初始流程变量对象
     * */
    void setInitProcessVariables(InitProcessVariablesVO initProcessVariables);

}
