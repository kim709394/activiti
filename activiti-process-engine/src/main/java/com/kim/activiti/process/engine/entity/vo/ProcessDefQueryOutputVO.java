package com.kim.activiti.process.engine.entity.vo;

import lombok.Data;

/**
 * @author huangjie
 * @description
 * @date 2020/5/27
 */
@Data
public class ProcessDefQueryOutputVO extends BaseQueryOutput<ProcessDefinitionVO> {

    /**名字*/
    private String name;

    /**类型*/
    private String catagory;


}
