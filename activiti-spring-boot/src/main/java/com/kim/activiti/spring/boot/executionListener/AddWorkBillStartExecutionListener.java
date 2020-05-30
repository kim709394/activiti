package com.kim.activiti.spring.boot.executionListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description
 * @date 2020/5/30
 */

public class AddWorkBillStartExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("加班流程执行开始");
    }
}
