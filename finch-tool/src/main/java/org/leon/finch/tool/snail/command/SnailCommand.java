package org.leon.finch.tool.snail.command;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 用户对于生成文件的配置
 *
 * @author Leon Song
 * @date 2020-11-06
 */
@Slf4j
@Data
public class SnailCommand {

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 默认与表名相同的驼峰写法
     * 如果表名有一些通用前缀,例如:t_ ,则需要指定
     */
    private String entityName;

    /**
     * dal模块名
     */
    private String dalModuleName;

    /**
     * 实体类所在包
     */
    private String entityPackage;

    /**
     * mapper所在包
     */
    private String mapperPackage;

    /**
     * xml所在文件
     * 相对于resources文件夹
     * 默认 mybatis/mapper
     */
    private String xmlFolder;

    /**
     * xml文件名
     * 默认 表名的驼峰写法+mapper
     */
    private String xmlName;

    /**
     * 排序字段
     */
    private List<String> pageOrderFields;

    /**
     * like筛选字段
     */
    private List<String> pageLikeFields;

    /**
     * equal筛选字段
     */
    private List<String> pageEqualFields;

    /**
     * 是否需要entity文件
     */
    private boolean needEntityJava = true;

    /**
     * 是否需要form文件
     */
    private boolean needForm = true;

    /**
     * 是否需要mapper.java文件
     */
    private boolean needMapperJava = true;

    /**
     * 是否需要mapper.xml文件
     */
    private boolean needMapperXml = true;


}
