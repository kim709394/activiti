package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.InitProcessVariablesVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryOutputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefinitionVO;
import com.kim.activiti.process.engine.service.ProcessDefinitionService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

/**
 * @author huangjie
 * @description  流程定义服务
 * @date 2020/5/27
 */
@Service

public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {


    @Autowired
    RepositoryService repositoryService;

    @Override
    @Transactional
    public String deployProcessDef(ProcessDefinitionVO processDefinitionVO) {
        Deployment deployment = repositoryService.createDeployment()//创建deploymentBuilder对象
                .addZipInputStream(new ZipInputStream(processDefinitionVO.getZipIn()))
                .name(processDefinitionVO.getName())//给部署的流程定义取个名字
                .category(processDefinitionVO.getCatagory())//给部署的定成定义设个类别
                .tenantId(processDefinitionVO.getTenantId())//表单id
                .deploy();//部署
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(processDefinitionVO.getTenantId()).processDefinitionName(processDefinitionVO.getName()).processDefinitionCategory(processDefinitionVO.getCatagory()).latestVersion().singleResult();
        return processDefinition.getId();
    }

    @Override
    @Transactional
    public ProcessDefQueryOutputVO queryPaging(ProcessDefQueryInputVO queryInputVO) {
        //总记录数
        long count = getProcessDefsByConditions(queryInputVO).count();
        //条件查询
        List<ProcessDefinition> processDefinitions = getProcessDefsByConditions(queryInputVO).orderByDeploymentId().desc().latestVersion().listPage(queryInputVO.getPageNo() * queryInputVO.getPageSize(), queryInputVO.getPageSize());
        //对象转换
        List<ProcessDefinitionVO> processDefinitionVOs = convertProcessDef(processDefinitions);
        //封装分页输出对象
        ProcessDefQueryOutputVO processDefQueryOutputVO = new ProcessDefQueryOutputVO();
        processDefQueryOutputVO.setCatagory(queryInputVO.getCatagory());
        processDefQueryOutputVO.setName(queryInputVO.getName());
        processDefQueryOutputVO.setList(processDefinitionVOs);
        processDefQueryOutputVO.setPageNo(queryInputVO.getPageNo());
        processDefQueryOutputVO.setPageCount(Integer.parseInt(count + ""));
        processDefQueryOutputVO.setPageSize(queryInputVO.getPageSize());
        processDefQueryOutputVO.setTotalPage(getTotal(Integer.parseInt(count + ""), queryInputVO.getPageSize()));
        return processDefQueryOutputVO;
    }

    //分页查询
    private ProcessDefinitionQuery getProcessDefsByConditions(ProcessDefQueryInputVO queryInputVO){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if(StringUtils.isNotEmpty(queryInputVO.getName())){
            processDefinitionQuery=processDefinitionQuery.processDefinitionNameLike(queryInputVO.getName());
        }
        if(StringUtils.isNotEmpty(queryInputVO.getCatagory())){
            processDefinitionQuery=processDefinitionQuery.processDefinitionCategoryLike(queryInputVO.getCatagory());
        }
        return processDefinitionQuery;

    }

    //计算总页数
    private int getTotal(int count, int pageSize) {
        int total = count / pageSize;
        int left = count % pageSize;
        if (left > 0) {
            total++;
        }
        return total;
    }

    //转换对象
    private List<ProcessDefinitionVO> convertProcessDef(List<ProcessDefinition> processDefinitions) {
        List<ProcessDefinitionVO> processDefinitionVOs = new ArrayList<>();
        if (processDefinitions != null && processDefinitions.size() > 0) {
            for (ProcessDefinition processDef : processDefinitions) {
                ProcessDefinitionVO processDefinitionVO = new ProcessDefinitionVO();
                processDefinitionVO.setId(processDef.getId());
                Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDef.getDeploymentId()).singleResult();
                processDefinitionVO.setCatagory(deployment.getCategory());
                processDefinitionVO.setName(processDef.getName());
                processDefinitionVO.setTenantId(processDef.getTenantId());
                processDefinitionVO.setVersion(processDef.getVersion());
                processDefinitionVOs.add(processDefinitionVO);
            }
        }
        return processDefinitionVOs;
    }

    @Override
    @Transactional
    public List<ProcessDefinitionVO> queryAll() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc().latestVersion().list();
        return convertProcessDef(processDefinitions);
    }

    @Override
    @Transactional
    public List<ProcessDefinitionVO> queryAllBy(Set<String> processDefinitionIds) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionIds(processDefinitionIds).orderByDeploymentId().desc().latestVersion().list();
        return convertProcessDef(processDefinitions);
    }

    @Override
    @Transactional
    public Map<String, Object> getInitProcessVariables(String processDefinitionId) {
        //从数据库查
        return null;
    }

    @Override
    @Transactional
    public void setInitProcessVariables(InitProcessVariablesVO initProcessVariables) {
        //存入数据库
    }
}
