package com.kim.activiti.spring.boot.tasklistener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description
 * @date 2020/5/30
 */

public class BossCompletionTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("老板审批完成时");
    }
}
