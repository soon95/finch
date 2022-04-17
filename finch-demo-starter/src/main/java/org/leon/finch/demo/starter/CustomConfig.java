package org.leon.finch.demo.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Leon Song
 * @date 2022-04-17
 */
@Data
@ConfigurationProperties("custom")
public class CustomConfig {

    private String name;

}
