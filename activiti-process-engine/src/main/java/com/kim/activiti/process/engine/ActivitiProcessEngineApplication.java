package com.kim.activiti.process.engine;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangjie
 * @description
 * @date 2020/5/24
 */
@EnableSwagger2Doc
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiProcessEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiProcessEngineApplication.class);
    }

}
