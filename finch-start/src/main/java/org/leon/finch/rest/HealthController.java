package org.leon.finch.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.leon.finch.rest.base.BaseController;
import org.leon.finch.rest.base.WebResult;
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
public class HealthController extends BaseController {

    @ApiOperation("检查")
    @GetMapping("/check")
    public WebResult healthCheck() {
        log.info("--------健康状态：良好！--------");
        return success("health");
    }

}
