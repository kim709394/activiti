package com.kim.activiti.process.engine.service;

import com.kim.activiti.process.engine.entity.vo.ProcessInstanceTaskQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessInstanceTaskQueryOutputVO;

/**
 * @author huangjie
 * @description   流程任务服务
 * @date 2020/5/24
 */
public interface ProcessTaskService {


    /**
     * 根据流程id查询所有任务节点
     * */
    ProcessInstanceTaskQueryOutputVO queryPagingTask(ProcessInstanceTaskQueryInputVO processInstanceTaskQueryInputVO);




}
