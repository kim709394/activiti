package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/6/3
 */
@Data
public class ProcessInstanceTaskQueryInputVO {

    private Integer pageNo;

    private Integer pageSize=8;

    private String processInstanceId;

}
