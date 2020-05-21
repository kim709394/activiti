package com.kim.activiti.spring.boot;

/**
 * @author huangjie
 * @description
 * @date 2020/5/22
 */

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * activiti6.0版本不兼容springboot2.0版本，因为activiti6.0出来的时候springboot2.0还没出
 * 需要排除SecurityAutoConfiguration这个类的加载
 * */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class);
    }


}
