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


    protected Integer pageNo;//页码

    protected Integer pageSize=8;//每页多少条

    protected Integer pageCount;//总记录数

    protected Integer totalPage;//总页数

    protected List<T> list;
}
