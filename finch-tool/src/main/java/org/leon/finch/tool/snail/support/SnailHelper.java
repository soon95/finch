package org.leon.finch.tool.snail.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.leon.finch.tool.snail.command.SnailCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon Song
 * @date 2020-11-06
 */
@Slf4j
public class SnailHelper {

    public final static String TEMPLATE_ENTITY_JAVA = "TemplateEntityJava.vm";
    public final static String TEMPLATE_FORM = "TemplateForm.vm";
    public final static String TEMPLATE_MAPPER_JAVA = "TemplateMapperJava.vm";
    public final static String TEMPLATE_MAPPER_XML = "TemplateMapperXml.vm";


    /**
     * 接收所有指令
     */
    private List<SnailCommand> snailCommands = new ArrayList<>();


    /**
     * 真正进行处理的地方
     */
    public void process() {

        log.info("Snail开始执行");

        // 校验用户输入数据，并进行默认值回填


        // 获取所有 表元信息 和 字段元信息


        // 根据配置的信息，生成对应表的代码


        log.info("Snail执行完毕");


    }


    public void start() {

        try {
            this.process();
        } catch (Throwable throwable) {
            log.error("Snail代码生成过程出错！");
            log.error(ExceptionUtils.getRootCauseMessage(throwable));
        }
    }

}
