package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author huangjie
 * @description
 * @date 2020/5/28
 */
@Data
public class BaseQueryOutput<T> {


    protected Integer pageNo;

    protected Integer pageSize=8;

    protected Integer pageCount;

    protected Integer totalPage;

    protected List<T> list;
}
