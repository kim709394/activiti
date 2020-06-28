package com.kim.activiti.process.engine.test;

import com.kim.activiti.process.engine.ActivitiProcessEngineApplication;
import com.kim.activiti.process.engine.exception.ActivitiProcessEngineException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huangjie
 * @description
 * @date 2020/6/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitiProcessEngineApplication.class)
public class ActivitiTest {

    @Autowired
    RepositoryService repositoryService;

    @Test
    /**
     * 部署流程定义
     * */
    public void deployProcDef(){
        //获取部署对象
        Deployment deployment=repositoryService.createDeployment()//创建deploymentBuilder对象
                .addClasspathResource("processes/leaveBill.bpmn")//加载类路径的流程定义bpmn文件
                .addClasspathResource("processes/leaveBill.jpg")
                .name("离职流程")//给部署的流程定义取个名字
                .category("办公流程")//给部署的定成定义设个类别
                .tenantId("表单id")

                .deploy();//部署
        System.out.println("部署的id："+deployment.getId());
        System.out.println("部署的名字："+deployment.getName());
        System.out.println("部署的类别："+deployment.getCategory());
    }


}
