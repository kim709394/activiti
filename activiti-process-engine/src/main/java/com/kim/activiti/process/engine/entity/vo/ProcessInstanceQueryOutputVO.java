package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/6/8
 */
@Data
public class ProcessInstanceQueryOutputVO extends BaseQueryOutput<ProcessInstanceVO> {

    /**流程定义id*/
    private String processDefinitionId;
    /**创建人*/
    private String creator;
    /**是否完成*/
    private Boolean isFinished;

}
