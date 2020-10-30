package org.leon.finch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于健康检查
 *
 * @author Leon Song
 * @date 2020-10-30
 */
@Slf4j
@RestController
@RequestMapping("/api/health")
@Api(tags = "健康监控")
public class HealthController {

    @ApiOperation("检查")
    @GetMapping("/check")
    public String healthCheck() {
        log.info("--------正在进行健康检查--------");
        return "health!";
    }

}
