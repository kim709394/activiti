package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.*;
import com.kim.activiti.process.engine.listener.businesslistener.CommitTaskListener;
import com.kim.activiti.process.engine.service.ProcessTaskService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author huangjie
 * @description  流程任务服务
 * @date 2020/5/27
 */
@Service

public class ProcessTaskServiceImpl  implements ProcessTaskService {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired(required = false)
    private List<CommitTaskListener> commitTaskListeners;

    @Override
    @Transactional
    public ProcessInstanceTaskQueryOutputVO queryPagingTasks(ProcessInstanceTaskQueryInputVO processInstanceTaskQueryInputVO) {
        //获取历史任务查询对象
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceTaskQueryInputVO.getProcessInstanceId());
        //查询总记录数
        long count=historicTaskInstanceQuery.count();
        //查询分页集合
        List<HistoricTaskInstance> historicTaskInstances = historicTaskInstanceQuery.orderByHistoricTaskInstanceStartTime().asc().listPage(processInstanceTaskQueryInputVO.getPageNo() * processInstanceTaskQueryInputVO.getPageSize(), processInstanceTaskQueryInputVO.getPageSize());
        //集合对象转换
        List<ProcessInstanceTaskVO> processInstanceTaskVOs=convertTask(historicTaskInstances);
        //封装分页输出对象
        ProcessInstanceTaskQueryOutputVO queryOutputVO=new ProcessInstanceTaskQueryOutputVO();
        queryOutputVO.setList(processInstanceTaskVOs);
        queryOutputVO.setPageCount(Integer.parseInt(count+""));
        queryOutputVO.setPageNo(processInstanceTaskQueryInputVO.getPageNo());
        queryOutputVO.setPageSize(processInstanceTaskQueryInputVO.getPageSize());
        queryOutputVO.setTotalPage(getTotal(Integer.parseInt(count+""),processInstanceTaskQueryInputVO.getPageSize()));
        return queryOutputVO;
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
    @Transactional
    private List<ProcessInstanceTaskVO> convertTask(List<HistoricTaskInstance> historicTaskInstances){
        List<ProcessInstanceTaskVO> processInstanceTaskVOs=new ArrayList<>();
        if(historicTaskInstances!=null&&historicTaskInstances.size()>0){
            for (HistoricTaskInstance task:historicTaskInstances
                 ) {
                ProcessInstanceTaskVO processInstanceTaskVO=new ProcessInstanceTaskVO();
                processInstanceTaskVO.setTaskId(task.getId());
                processInstanceTaskVO.setAssignee(task.getAssignee());
                processInstanceTaskVO.setCreateTime(task.getCreateTime());
                processInstanceTaskVO.setDueDate(task.getDueDate());
                processInstanceTaskVO.setCompleteTime(task.getEndTime());
                processInstanceTaskVO.setName(task.getName());
                processInstanceTaskVO.setParentTaskId(task.getParentTaskId());
                processInstanceTaskVO.setFinished(task.getDueDate()!=null||task.getEndTime()!=null);
                processInstanceTaskVO.setPlatform(task.getDescription());
                List<Comment> taskComments = taskService.getTaskComments(task.getId());
                processInstanceTaskVO.setComment(taskComments.get(0).getFullMessage());
            }
        }
        return processInstanceTaskVOs;
    }

    @Override
    @Transactional
    public PendingTaskQueryOutputVO queryPagingPendingTasks(PendingTaskQueryInputVO pendingTaskQueryInputVO) {
        //根据条件获取查询对象
        TaskQuery taskQuery = getTaskQuery(pendingTaskQueryInputVO);
        //获取总记录数
        long count = taskQuery.count();
        //获取分页集合
        List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(pendingTaskQueryInputVO.getPageNo() * pendingTaskQueryInputVO.getPageSize(), pendingTaskQueryInputVO.getPageSize());
        //对象转换
        List<PendingTaskVO> pendingTaskVOs=convertTask(tasks,pendingTaskQueryInputVO.getAssignee());
        //封装分页输出对象
        PendingTaskQueryOutputVO pendingTaskQueryOutputVO=new PendingTaskQueryOutputVO();
        pendingTaskQueryOutputVO.setList(pendingTaskVOs);
        pendingTaskQueryOutputVO.setPageCount(Integer.parseInt(count+""));
        pendingTaskQueryOutputVO.setPageNo(pendingTaskQueryInputVO.getPageNo());
        pendingTaskQueryOutputVO.setPageSize(pendingTaskQueryInputVO.getPageSize());
        pendingTaskQueryOutputVO.setTotalPage(getTotal(Integer.parseInt(count+""),pendingTaskQueryInputVO.getPageSize()));
        return pendingTaskQueryOutputVO;
    }

    @Transactional
    private List<PendingTaskVO> convertTask(List<Task> tasks,String assignee){
        List<PendingTaskVO> pendingTaskVOs=new ArrayList<>();
        if(tasks!=null&&tasks.size()>0){
            for (Task task:tasks
                 ) {
                PendingTaskVO pendingTaskVO=new PendingTaskVO();
                pendingTaskVO.setTaskId(task.getId());
                pendingTaskVO.setAssignee(task.getAssignee()==null?assignee:task.getAssignee());
                pendingTaskVO.setProcessDefinitionId(task.getProcessDefinitionId());
                ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
                pendingTaskVO.setProcessDefinitionName(processDefinition.getName());
                pendingTaskVO.setProcessInstanceId(task.getProcessInstanceId());
                pendingTaskVO.setTenantId(processDefinition.getTenantId());
                pendingTaskVO.setCreateTime(task.getCreateTime());
                pendingTaskVO.setTaskName(task.getName());
                pendingTaskVO.setTaskKey(task.getTaskDefinitionKey());
                pendingTaskVOs.add(pendingTaskVO);
            }
        }
        return pendingTaskVOs;
    }

    @Transactional
    private TaskQuery getTaskQuery(PendingTaskQueryInputVO queryInputVO){
        TaskQuery taskQuery = taskService.createTaskQuery();
        if(StringUtils.isNotEmpty(queryInputVO.getAssignee())){
            taskQuery=taskQuery.taskCandidateOrAssigned(queryInputVO.getAssignee());
        }
        if(StringUtils.isNotEmpty(queryInputVO.getProcessDefinitionId())){
            taskQuery=taskQuery.processDefinitionId(queryInputVO.getProcessDefinitionId());
        }
        return taskQuery;
    }

    @Override
    @Transactional
    public void commitTask(CommitTaskVO commitTaskVO) {
        //获取任务对象
        Task task = taskService.createTaskQuery().taskId(commitTaskVO.getTaskId()).singleResult();
        //获取任务对应的流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        //获取办理任务业务监听器
        CommitTaskListener commitTaskListener=null;
        if(commitTaskListeners!=null&&commitTaskListeners.size()>0){
            for (CommitTaskListener listener: commitTaskListeners
                 ) {
                if(StringUtils.equals(listener.getTaskDefinitionKey(),task.getTaskDefinitionKey())
                &&(StringUtils.equals(listener.getProcessDefinitionIdOrKey(),task.getProcessDefinitionId())||StringUtils.equals(listener.getProcessDefinitionIdOrKey(),processDefinition.getKey()))){
                    commitTaskListener=listener;
                    break;
                }
            }
        }
        //办理任务前逻辑操作
        if(commitTaskListener!=null){
            commitTaskListener.commitBeforeBusniessHandle(commitTaskVO);

        }
        //办理任务
        String taskId=task.getId();
        taskService.setOwner(taskId,commitTaskVO.getOwner());
        taskService.setDueDate(taskId,new Date());
        taskService.setAssignee(taskId,commitTaskVO.getAssignee());
        task.setDescription(commitTaskVO.getPlatform());
        taskService.addComment(taskId,commitTaskVO.getProcessInstanceId(),commitTaskVO.getComment());
        taskService.complete(taskId,commitTaskVO.getProcessVariables());
        //办理任务后业务逻辑操作
        if(commitTaskListener!=null){
            commitTaskListener.commitAfterBusniessHandle(commitTaskVO);
        }
    }
}
