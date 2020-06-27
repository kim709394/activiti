package com.kim.activiti.process.engine.service.impl;

import com.kim.activiti.process.engine.entity.vo.*;
import com.kim.activiti.process.engine.service.ProcessTaskService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author huangjie
 * @description  流程任务服务
 * @date 2020/5/27
 */
@Service
@Transactional
public class ProcessTaskServiceImpl  implements ProcessTaskService {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;

    @Override
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
    public PendingTaskQueryOutputVO queryPagingPendingTasks(PendingTaskQueryInputVO pendingTaskQueryInputVO) {
        return null;
    }

    @Override
    public void commitTask(CommitTaskVO commitTaskVO) {

    }
}
