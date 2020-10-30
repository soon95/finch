package org.leon.finch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 *
 * @author Leon Song
 * @date 2020-10-30
 */
@SpringBootApplication(scanBasePackages = {"org.leon.finch"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
