package org.leon.finch.demo.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leon Song
 * @date 2022-04-17
 */
@RestController
@RequestMapping("/custom")
public class CustomController {

    @Autowired(required = false)
    CustomBean customBean;

    @RequestMapping("/hello")
    public String hello() {
        if (customBean != null) {
            return customBean.sayHello();
        }
        return "nobody there";
    }

}
