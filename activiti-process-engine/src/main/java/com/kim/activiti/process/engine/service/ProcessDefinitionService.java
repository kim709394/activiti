package com.kim.activiti.process.engine.service;

import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryOutputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefinitionVO;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

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




}
