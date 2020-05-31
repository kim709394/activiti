package com.kim.activiti.spring.boot.executionListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author huangjie
 * @description
 * @date 2020/5/30
 */
public class LeaveBillStartListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) {
		System.out.println("离职流程开启");
		
	}

}
