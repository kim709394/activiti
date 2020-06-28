package com.kim.activiti.process.engine;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangjie
 * @description
 * @date 2020/5/24
 */
@SpringBootApplication
@EnableSwagger2Doc
public class ActivitiProcessEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiProcessEngineApplication.class);
    }

}
