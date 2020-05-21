package com.kim.activiti.spring.boot.tasklistener;


import com.kim.activiti.spring.boot.util.SpringApplicationContext;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.List;

/**
 * @author king
 * 2019/3/17
 */

/**
 * 为任务节点设置监听类
 * */
public class BoessTaskListener implements TaskListener {



    @Override
    //工作流自动传入当前任务对象
    public void notify(DelegateTask delegateTask) {
        //从spring上下文获取TaskService对象
        TaskService taskService= SpringApplicationContext.getBean(TaskService.class);
        String taskId=delegateTask.getId();
        //获取本任务审批人的流程变量
        List<String> candidates= (List<String>)taskService.getVariable(taskId,"bosses");
        //为本任务节点增加候选人
        delegateTask.addCandidateUsers(candidates);
    }




}
