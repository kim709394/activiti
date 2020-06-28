package com.kim.activiti.process.engine.controller;

import com.kim.activiti.process.engine.entity.vo.InitProcessVariablesVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefQueryInputVO;
import com.kim.activiti.process.engine.entity.vo.ProcessDefinitionVO;
import com.kim.activiti.process.engine.entity.vo.ResultVO;
import com.kim.activiti.process.engine.service.ProcessDefinitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

/**
 * @author huangjie
 * @description
 * @date 2020/6/28
 */
@Api(description = "流程定义接口服务")
@RestController
@RequestMapping("/activiti/definition")
public class ProcessDefinitionController {


    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @ApiOperation(value="部署流程定义",httpMethod = "POST")
    @PostMapping("/deploy")
    public ResultVO deployProcessDef(@RequestParam("processDefZipFile") MultipartFile processDefZipFile,@RequestParam("name") String name,
                                     @RequestParam("catagory") String catagory,@RequestParam("tenantId") String tenantId) throws IOException {
        ProcessDefinitionVO processDefinitionVO=new ProcessDefinitionVO();
        processDefinitionVO.setTenantId(tenantId);
        processDefinitionVO.setName(name);
        processDefinitionVO.setCatagory(catagory);
        processDefinitionVO.setZipIn(processDefZipFile.getInputStream());
        String processDefinitionId = processDefinitionService.deployProcessDef(processDefinitionVO);
        return ResultVO.success(processDefinitionId);

    }

    @ApiOperation(value="分页查询流程定义",httpMethod = "POST")
    @PostMapping("/query/paging")
    public ResultVO queryPaging(@RequestBody ProcessDefQueryInputVO queryInputVO){
        return ResultVO.success(processDefinitionService.queryPaging(queryInputVO));
    }

    @ApiOperation(value="查询所有流程定义",httpMethod = "GET")
    @GetMapping("/query/all")
    public ResultVO queryAll(){
        return ResultVO.success(processDefinitionService.queryAll());
    }

    @ApiOperation(value="根据指定流程定义的id集合查询所有流程定义",httpMethod = "POST")
    @PostMapping("/query/by/ids")
    public ResultVO queryByIds(@RequestBody Set<String> processDefinitionIds){
        return ResultVO.success(processDefinitionService.queryAllBy(processDefinitionIds));
    }

    @ApiOperation(value="获取初始流程变量",httpMethod = "GET")
    @GetMapping("/get/init/processvariables")
    public ResultVO getInitProcessVariables(@RequestParam("processDefinitionId") String processDefinitionId){
        return ResultVO.success(processDefinitionService.getInitProcessVariables(processDefinitionId));
    }

    @ApiOperation(value="设置初始流行变量",httpMethod = "POST")
    @PostMapping("/set/init/processvariables")
    public ResultVO setInitProcessVariables(@RequestBody InitProcessVariablesVO initProcessVariables){
        processDefinitionService.setInitProcessVariables(initProcessVariables);
        return ResultVO.success();
    }




}
