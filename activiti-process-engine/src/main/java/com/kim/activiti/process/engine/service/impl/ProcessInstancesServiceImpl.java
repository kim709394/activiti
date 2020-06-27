package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryOutputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessInstanceVO;
import com.kim.activiti.process.engine.listener.businesslistener.ProcessInstanceStartListener;
import com.kim.activiti.process.engine.service.ProcessDefinitionService;
import com.kim.activiti.process.engine.service.ProcessInstancesService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huangjie
 * @description   流程实例服务
 * @date 2020/5/27
 */
@Service
@Transactional
public class ProcessInstancesServiceImpl implements ProcessInstancesService {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private ProcessDefinitionService processDefinitionService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private List<ProcessInstanceStartListener> processInstanceStartListeners;

	@Override
	public String startProcessInstanceByProcessDefId(String processDefinitionId,String creator,Map<String,Object> bussinessVariables) {
		Map<String, Object> initProcessVariables = processDefinitionService.getInitProcessVariables(processDefinitionId);
		if(processInstanceStartListeners!=null&&processInstanceStartListeners.size()>0){
			for (ProcessInstanceStartListener listener:processInstanceStartListeners
				 ) {
				if(StringUtils.equals(processDefinitionId,listener.getProcessDefinitionId())){
					listener.bussinessHandle(initProcessVariables,bussinessVariables);
					break;
				}
			}
		}

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionId, creator, initProcessVariables);
		return processInstance.getId();
	}

	@Override
	public ProcessInstanceQueryOutputVO queryPagingProcessInstances(ProcessInstanceQueryInputVO processInstanceQueryInputVO) {
		//计算总记录数
		long count=getQueryByCondition(processInstanceQueryInputVO).count();
		//计算分页集合
		List<HistoricProcessInstance> historicProcessInstances = getQueryByCondition(processInstanceQueryInputVO).orderByProcessInstanceStartTime().desc().listPage(processInstanceQueryInputVO.getPageNo() * processInstanceQueryInputVO.getPageSize(), processInstanceQueryInputVO.getPageSize());
		//对象转换
		List<ProcessInstanceVO> processInstanceVOs = convertProcessInstances(historicProcessInstances);
		//封装分页输出对象
		ProcessInstanceQueryOutputVO processInstanceQueryOutputVO=new ProcessInstanceQueryOutputVO();
		processInstanceQueryOutputVO.setList(processInstanceVOs);
		processInstanceQueryOutputVO.setPageNo(processInstanceQueryInputVO.getPageNo());
		processInstanceQueryOutputVO.setPageSize(processInstanceQueryInputVO.getPageSize());
		processInstanceQueryOutputVO.setTotalPage(getTotal(Integer.parseInt(count+""),processInstanceQueryInputVO.getPageSize()));
		processInstanceQueryOutputVO.setPageCount(Integer.parseInt(count+""));
		processInstanceQueryOutputVO.setCreator(processInstanceQueryInputVO.getCreator());
		processInstanceQueryOutputVO.setIsFinished(processInstanceQueryInputVO.getIsFinished());
		processInstanceQueryOutputVO.setProcessDefinitionId(processInstanceQueryInputVO.getProcessDefinitionId());
		return processInstanceQueryOutputVO;
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

	//对象转换
	private List<ProcessInstanceVO> convertProcessInstances(List<HistoricProcessInstance> historicProcessInstances){
		List<ProcessInstanceVO> processInstanceVOs=new ArrayList<>();
		if(historicProcessInstances!=null&&historicProcessInstances.size()>0){
			for (HistoricProcessInstance history:historicProcessInstances
				 ) {
				ProcessInstanceVO processInstanceVO=new ProcessInstanceVO();
				processInstanceVO.setProcessDefinitionId(history.getProcessDefinitionId());
				processInstanceVO.setCreator(history.getBusinessKey());
				processInstanceVO.setProcessDefinitionName(history.getProcessDefinitionName());
				processInstanceVO.setProcessInstanceId(history.getId());
				processInstanceVO.setTenantId(history.getTenantId());
				ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(history.getId()).singleResult();
				processInstanceVO.setIsFinished(processInstance==null);
				processInstanceVOs.add(processInstanceVO);
			}
		}
		return processInstanceVOs;
	}

	//根据查询条件获取查询对象
	private HistoricProcessInstanceQuery getQueryByCondition(ProcessInstanceQueryInputVO processInstanceQueryInputVO){
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		if(StringUtils.isNotEmpty(processInstanceQueryInputVO.getProcessDefinitionId())){
			historicProcessInstanceQuery=historicProcessInstanceQuery.processDefinitionId(processInstanceQueryInputVO.getProcessDefinitionId());
		}
		if(StringUtils.isNotEmpty(processInstanceQueryInputVO.getCreator())){
			historicProcessInstanceQuery=historicProcessInstanceQuery.processInstanceBusinessKey(processInstanceQueryInputVO.getCreator());
		}
		if(processInstanceQueryInputVO.getIsFinished()!=null){
			historicProcessInstanceQuery=processInstanceQueryInputVO.getIsFinished()?historicProcessInstanceQuery.finished():historicProcessInstanceQuery.unfinished();
		}
		return historicProcessInstanceQuery;
	}

	@Override
	public void deleteProcessInstance(String processInstanceId) {
		historyService.deleteHistoricProcessInstance(processInstanceId);
	}


}
