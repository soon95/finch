package org.leon.finch.demo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Leon Song
 * @date 2021-05-07
 */
public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        String hello = userService.sayHelloTo("leon");

        System.out.println(hello);
    }

}
