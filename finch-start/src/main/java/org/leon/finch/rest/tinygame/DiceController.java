package org.leon.finch.rest.tinygame;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.leon.finch.rest.base.BaseController;
import org.leon.finch.rest.base.WebResult;
import org.leon.finch.service.tinygame.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leon Song
 * @date 2020-11-01
 */
@Slf4j
@Api(tags = "筛子小游戏")
@RequestMapping("/api/dice")
@RestController
public class DiceController extends BaseController {

    @Autowired
    private DiceService diceService;

    @ApiOperation("掷骰子")
    @GetMapping("/roll")
    public WebResult<Integer> roll() {

        log.info("开始投掷筛子");

        Integer num = diceService.roll();

        log.info("投掷筛子结果为:{}", num);

        return success(num);

    }

}
