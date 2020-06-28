package com.kim.activiti.process.engine.controller;

import com.kim.activiti.process.engine.entity.vo.CommitTaskVO;
import com.kim.activiti.process.engine.entity.vo.PendingTaskQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessInstanceTaskQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ResultVO;
import com.kim.activiti.process.engine.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Api(description = "流程任务接口服务")
@RestController
@RequestMapping("/activiti/task")
public class ProcessTaskController {

    @Autowired
    private ProcessTaskService taskService;

    @ApiOperation(value = "根据流程实例id分页查询所有任务节点",httpMethod = "POST")
    @PostMapping("/query/paging/tasks")
    public ResultVO queryPagingTasks(@RequestBody ProcessInstanceTaskQueryInputVO processInstanceTaskQueryInputVO){
        return ResultVO.success(taskService.queryPagingTasks(processInstanceTaskQueryInputVO));
    }

    @ApiOperation(value="根据待办人名字分页查询待办任务列表",httpMethod = "POST")
    @PostMapping("/query/pending/paging")
    public ResultVO queryPagingPendingTasks(@RequestBody PendingTaskQueryInputVO pendingTaskQueryInputVO){
        return ResultVO.success(taskService.queryPagingPendingTasks(pendingTaskQueryInputVO));
    }

    @ApiOperation(value="办理任务",httpMethod = "POST")
    @PostMapping("/commit/task")
    public ResultVO commitTask(@RequestBody CommitTaskVO commitTaskVO){
        taskService.commitTask(commitTaskVO);
        return ResultVO.success();
    }

}
