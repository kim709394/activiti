package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.*;
import com.kim.activiti.process.engine.service.ProcessTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author huangjie
 * @description
 * @date 2020/5/27
 */
@Service
@Transactional
public class ProcessTaskServiceImpl  implements ProcessTaskService {
    @Override

    public ProcessInstanceTaskQueryOutputVO queryPagingTasks(ProcessInstanceTaskQueryInputVO processInstanceTaskQueryInputVO) {
        return null;
    }

    @Override
    public PendingTaskQueryOutputVO queryPagingPendingTasks(PendingTaskQueryInputVO pendingTaskQueryInputVO) {
        return null;
    }

    @Override
    public void commitTask(CommitTaskVO commitTaskVO) {

    }
}
