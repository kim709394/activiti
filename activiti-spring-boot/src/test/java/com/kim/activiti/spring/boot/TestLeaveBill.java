package com.kim.activiti.spring.boot;

import com.kim.activiti.spring.boot.ActivitiApplication;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
/**
 * @author huangjie
 * @description
 * @date 2020/5/31
 */
/**
 * 离职流程测试
 * 多人审批(顺序)且带结束条件、多人审批(并发)且带结束条件、多人审批(并发)不带结束条件
 * 相对于整个流程的流程监听器(启动和结束)
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class TestLeaveBill {


    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;



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


    /**
     * 执行流程
     * */
    @Test
    public void startProcess(){

        //指定流程定义的key值，即流程定义id值，启动流程，产生一个流程实例
        String processDeId="leaveBill:4:40004";
        //设置审批人的流程变量
        Map<String,Object> audits=new HashMap();
        List<String> managerList=new ArrayList<>();
        managerList.add("张三");
        managerList.add("李四");
        managerList.add("王五");
        audits.put("managerList",managerList);
        List<String> presidentList= new ArrayList();
        presidentList.add("赵六");
        presidentList.add("韩七");
        presidentList.add("林八");
        audits.put("presidentList",presidentList);
        List<String> boss1List= new ArrayList();
        boss1List.add("刘九");
        boss1List.add("秦十");
        boss1List.add("石十一");

        audits.put("boss1List",boss1List);
        audits.put("creator","钟十二");
        audits.put("boss2","庄十三");

        //通过流程定义key值启动流程，取得流程实例，默认是启动该流程定义的最新版本的
        ProcessInstance processInstance=runtimeService
                .startProcessInstanceById(processDeId,"创建人",audits);
        System.out.println("流程实例的id："+processInstance.getId());
        System.out.println("对应流程定义id："+processInstance.getProcessDefinitionId());
    }

    /**
     * 办理任务
     * */
    @Test
    public void complete(){
        //活动中的任务对应的流程实例
        String processInstanceId="42501";
        //指定待办任务id
        String taskId="45019";
        //办理任务,提交审批意见并指定审批类型
        taskService.addComment(taskId,processInstanceId,"审批意见","同意;无补充意见");
        //设置是否同意的流程变量
        Map<String,Object> params=new HashMap();

        params.put("isPresident",1);

        params.put("presidentCount",2);
        //taskService.setVariableLocal(taskId,"operation","user");
        String assignee="韩七";
        /**用于区别系统完成的任务还是人工完成的任务，多人审批时设置了完成条件，没有手工完成的任务将系统自动完成，此时这
        *个字段为null，还有一种情况就是需要跳过某个任务节点时，可以在后台完成这个任务，不需要审批人进行审批
         * 也可根据这个字段进行过滤。用于查询任务时，审批人完成:human,后台完成:system,工作流框架自动完成:null
         * 可以根据这个字段筛选掉系统审批的任务，只查询owner为human的任务*/
        taskService.setOwner(taskId,"human");
        //系统自动完成
        //taskService.setOwner(taskId,"system");
        //设置审批处理时间
        taskService.setDueDate(taskId,new Date());
        //设置审批人
        taskService.setAssignee(taskId,assignee);
        //完成任务，同时设置流程变量
        taskService.complete(taskId,params);
        //根据任务key值执行完成任务的业务监听逻辑
        taskService.createTaskQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
        System.out.println("办理完成");
    }

    /**
     * 查询待办任务
     * */
    @Test
    public void queryUnfinishTask(){
        String assignee="张三";
        //创建一个查询对象
        TaskQuery taskQuery=taskService.createTaskQuery();
        //查询待办人的任务列表，办理人或者候选人
        List<Task> tasks=taskQuery.taskCandidateOrAssigned(assignee)
                .list();
        //遍历代办任务列表
        if(tasks!=null&&tasks.size()>0){
            for (Task task:tasks
            ) {
                System.out.println("任务办理人："+task.getAssignee());
                System.out.println("任务id："+task.getId());
                System.out.println("任务名称："+task.getName());
            }
        }
    }

    /**
     * 查询流程实例对应下的所有任务节点
     * */
    @Test
    public void queryAllTaskByProcessInstanceId(){

        String proicessInstanceId="42501";
        List<HistoricTaskInstance> historicTaskInstances=historyService
                .createHistoricTaskInstanceQuery()
                //.processDefinitionId("addBill:1:35016")
                .processInstanceId(proicessInstanceId)
                .finished()
                //.unfinished()//查询未完成的流程节点，即正在活动中的流程节点
                .list();
        if(null!=historicTaskInstances&&historicTaskInstances.size()>0){
            for (HistoricTaskInstance historicTaskInstance: historicTaskInstances
            ) {
                Map<String, Object> taskLocalVariables = historicTaskInstance.getTaskLocalVariables();
                System.out.println(taskLocalVariables);
                System.out.println(historicTaskInstance.getAssignee());
            }
        }

        //查询所有流程任务
        List<Task> list = taskService.createTaskQuery().processInstanceId(proicessInstanceId).list();
        System.out.println(list);
        //查询审批意见，
        List<Comment> comments=taskService
                .getProcessInstanceComments(proicessInstanceId);
        if(null!=comments&&comments.size()>0){
            for (Comment comment:comments
            ) {
                System.out.println("审批意见:"+comment.getFullMessage());
            }
        }



    }


}
