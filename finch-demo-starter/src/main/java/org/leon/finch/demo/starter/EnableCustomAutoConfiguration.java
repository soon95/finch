package org.leon.finch.demo.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Leon Song
 * @date 2022-04-17
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// 在设置了这个注解时，会自动装配 TagBean 这个类
@Import(TagBean.class)
public @interface EnableCustomAutoConfiguration {


}
