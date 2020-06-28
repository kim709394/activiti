package com.kim.activiti.process.engine.controller;

import com.kim.activiti.process.engine.entity.vo.ProcessInstanceQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ResultVO;
import com.kim.activiti.process.engine.entity.vo.StartProcessInstanceVO;
import com.kim.activiti.process.engine.service.ProcessInstancesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Api(description = "流程实例接口服务")
@RestController
@RequestMapping("/activiti/instances")
public class ProcessInstancesController {

    @Autowired
    private ProcessInstancesService processInstancesService;


    @ApiOperation(value="启动一个流程实例",httpMethod = "POST")
    @PostMapping("/start")
    public ResultVO startProcessInstance(@RequestBody StartProcessInstanceVO startProcessInstanceVO){
        return ResultVO.success(processInstancesService.startProcessInstanceByProcessDefId(startProcessInstanceVO));
    }

    @ApiOperation(value="分页查询流程实例",httpMethod = "POST")
    @PostMapping("/query/paging")
    public ResultVO queryPagingProcessInstances(@RequestBody ProcessInstanceQueryInputVO processInstanceQueryInputVO){
        return ResultVO.success(processInstancesService.queryPagingProcessInstances(processInstanceQueryInputVO));
    }

    @ApiOperation(value="删除流程实例",httpMethod = "DELETE")
    @DeleteMapping("/delete/{processInstanceId}")
    public ResultVO delete(@PathVariable(value="processInstanceId") String processInstanceId){
        processInstancesService.deleteProcessInstance(processInstanceId);
        return ResultVO.success();
    }



}
