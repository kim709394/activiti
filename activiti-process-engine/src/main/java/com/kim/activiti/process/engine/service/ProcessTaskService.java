package com.kim.activiti.process.engine.service;

import com.kim.activiti.process.engine.entity.vo.*;

/**
 * @author huangjie
 * @description   流程任务服务
 * @date 2020/5/24
 */
public interface ProcessTaskService {


    /**
     * 根据流程实例id分页查询所有任务节点
     * */
    ProcessInstanceTaskQueryOutputVO queryPagingTasks(ProcessInstanceTaskQueryInputVO processInstanceTaskQueryInputVO);


    /**
     * 根据待办人名字分页查询待办任务列表
     * */
    PendingTaskQueryOutputVO queryPagingPendingTasks(PendingTaskQueryInputVO pendingTaskQueryInputVO);


    /**
     * 办理任务
     * */
    void commitTask(CommitTaskVO commitTaskVO);

}
