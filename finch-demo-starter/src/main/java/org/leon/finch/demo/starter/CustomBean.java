package org.leon.finch.demo.starter;

import lombok.Data;

/**
 * @author Leon Song
 * @date 2022-04-17
 */
@Data
public class CustomBean {

    private String name;

    public String sayHello() {
        return "Hello, " + this.name;
    }
}
