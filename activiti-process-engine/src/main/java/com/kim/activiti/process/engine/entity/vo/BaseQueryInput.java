package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/6/8
 */
@Data
public class BaseQueryInput {

    protected Integer pageNo;

    protected Integer pageSize=8;

}
