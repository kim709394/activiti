package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryOutputVO;
import com.kim.activiti.process.engine.service.ProcessInstancesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author huangjie
 * @description
 * @date 2020/5/27
 */
@Service
@Transactional
public class ProcessInstancesServiceImpl implements ProcessInstancesService {

	@Override
	public String startProcessInstanceByProcessDefId(String processDefinitionId,Map<String,Object> bussinessVariables) {
		return null;
	}

	@Override
	public ProcessInstanceQueryOutputVO queryPagingProcessInstances(ProcessInstanceQueryInputVO processInstanceQueryInputVO) {
		return null;
	}

	@Override
	public void deleteProcessInstance(String processInstanceId, String deleteReason) {

	}


}
