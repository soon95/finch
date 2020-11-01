package org.leon.finch.service.tinygame;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Leon Song
 * @date 2020-11-01
 */
@Service
public class DiceService {

    /**
     * 掷骰子，随机返回一个1-6的数字
     */
    public int roll() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
