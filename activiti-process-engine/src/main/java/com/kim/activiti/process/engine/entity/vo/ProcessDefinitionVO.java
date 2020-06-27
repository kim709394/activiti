package com.kim.activiti.process.engine.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.InputStream;

/**
 * @author huangjie
 * @description
 * @date 2020/5/27
 */
@Data
public class ProcessDefinitionVO {

    /**主键*/
    private String id;

    @JsonIgnore
    /**流程定义zip格式压缩文件*/
    private InputStream zipIn;
    /**名字*/
    private String name;

    /**类型*/
    private String catagory;

    /**表单id*/
    private String tenantId;

    /**流程定义版本*/
    private Integer version;


}
