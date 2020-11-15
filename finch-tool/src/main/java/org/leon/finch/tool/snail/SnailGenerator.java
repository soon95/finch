package org.leon.finch.tool.snail;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.leon.finch.tool.snail.command.SnailCommand;
import org.leon.finch.tool.snail.support.SnailHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器主类
 *
 * @author Leon Song
 * @date 2020-11-06
 */
@Data
@Slf4j
public class SnailGenerator {

    private DruidDataSource dataSource;

    private List<SnailCommand> snailCommands = new ArrayList<>();

    /**
     * 配置数据源连接信息
     */
    @SneakyThrows
    private void dataSourceConfig() {

        String jdbcUrl = "jdbc:mysql://47.96.74.202:3306/finch?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String userName = "root";
        String password = "123456";

        dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        dataSource.init();
    }

    /**
     * 配置一些生成信息
     */
    private void snailCommandConfig() {

        SnailCommand command = new SnailCommand("foo");
        command.setOverwriteEntity(true);
        command.setOverwriteMapperJava(true);
        /**
         * 生成foo表对应文件
         */
        this.snailCommands.add(command);

    }


    @SneakyThrows
    public static void main(String[] args) {

        SnailGenerator snailGenerator = new SnailGenerator();
        // 初始化一个数据源
        snailGenerator.dataSourceConfig();

        // 配置
        snailGenerator.snailCommandConfig();

        // 调用一个帮助类
        SnailHelper snailHelper = new SnailHelper(snailGenerator);
        snailHelper.start();

    }
}
