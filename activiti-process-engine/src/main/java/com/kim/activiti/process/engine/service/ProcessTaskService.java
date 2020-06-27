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
     * @param processInstanceTaskQueryInputVO 分页查询流程任务输入对象
     * @return  ProcessInstanceTaskQueryOutputVO  分页查询流程任务输出对象
     * */
    ProcessInstanceTaskQueryOutputVO queryPagingTasks(ProcessInstanceTaskQueryInputVO processInstanceTaskQueryInputVO);


    /**
     * 根据待办人名字分页查询待办任务列表
     * @param pendingTaskQueryInputVO 分页查询待办任务输入对象
     * @return PendingTaskQueryOutputVO  分页查询待办任务输出对象
     * */
    PendingTaskQueryOutputVO queryPagingPendingTasks(PendingTaskQueryInputVO pendingTaskQueryInputVO);


    /**
     * 办理任务
     * @param commitTaskVO 办理任务对象
     * */
    void commitTask(CommitTaskVO commitTaskVO);

}
