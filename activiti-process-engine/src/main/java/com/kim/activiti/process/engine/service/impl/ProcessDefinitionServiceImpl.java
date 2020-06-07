package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryOutputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefinitionVO;
import com.kim.activiti.process.engine.service.ProcessDefinitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huangjie
 * @description
 * @date 2020/5/27
 */
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
    @Override
    public void deployProcessDef(ProcessDefinitionVO processDefinitionVO) {

    }

    @Override
    public ProcessDefQueryOutputVO queryPaging(ProcessDefQueryInputVO queryInputVO) {
        return null;
    }


    @Override
    public List<ProcessDefinitionVO> queryAll() {
        return null;
    }

    @Override
    public List<ProcessDefinitionVO> queryAllBy(Set<String> processDefinitionIds) {
        return null;
    }

    @Override
    public Map<String, Object> getInitProcessVariables(String processDefinitionId) {
        return null;
    }

    @Override
    public void setInitProcessVariables(String processDefinitionId, Map<String, Object> initProcessVariables) {

    }
}
