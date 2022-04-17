package org.leon.finch.demo;

import org.leon.finch.demo.starter.EnableCustomAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 自定义的自动装配启动器
@EnableCustomAutoConfiguration
public class FinchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinchDemoApplication.class, args);
    }

}
