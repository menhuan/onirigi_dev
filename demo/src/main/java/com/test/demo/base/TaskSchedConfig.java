package com.test.demo.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.test.demo.task")
@EnableScheduling
public class TaskSchedConfig {

}
