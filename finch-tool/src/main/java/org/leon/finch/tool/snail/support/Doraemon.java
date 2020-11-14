package org.leon.finch.tool.snail.support;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.leon.finch.common.util.StringUtil;
import org.leon.finch.dal.base.BaseEntity;
import org.leon.finch.tool.snail.command.SnailCommand;
import org.leon.finch.tool.snail.meta.SnailColumn;
import org.leon.finch.tool.snail.meta.SnailTable;

import java.util.ArrayList;
import java.util.List;

/**
 * 哆啦A梦
 * 用于提供模板渲染的参数
 *
 * @author Leon Song
 * @date 2020-11-08
 */
@Data
public class Doraemon {

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 默认与表名相同的驼峰写法
     * 如果表名有一些通用前缀,例如:t_foo ,则需要指定为foo
     */
    private String entityName;

    /**
     * 表注释
     */
    private String tableRemarks;

    /**
     * 表中字段
     */
    private List<SnailColumn> columns;

    /**
     * 表中废弃的字段
     */
    private List<String> ignoreFields;

    /**
     * 当前子类中的字段
     * 因为所有 Entity 都需要继承 BaseEntity
     */
    private List<SnailColumn> selfColumns;

    /**
     * dal模块名
     */
    private String dalModuleName;

    /**
     * dal的主路径
     */
    private String dalPackage;

    /**
     * 实体类所在包
     */
    private String entityPackage;

    /**
     * 类名，首字母大写
     * EntityName
     */
    private String entityUpperClassName;

    /**
     * 类名，首字母小写
     * entityName
     */
    private String entityLowerClassName;

    /**
     * 全类名
     */
    private String entityFullClassName;

    /**
     * 包名 非java 打头的import
     */
    private List<String> entityImportNonJavas;

    /**
     * 包名 java 打头的import
     */
    private List<String> entityImportJavas;

    /**
     * mapper所在包
     */
    private String mapperPackage;

    /**
     * mapper类名，首字母大写
     * EntityNameMapper
     */
    private String mapperUpperClassName;

    /**
     * mapper类名，首字母小写
     * entityNameMapper
     */
    private String mapperLowClassName;

    /**
     * mapper全类名
     */
    private String mapperFullClassName;

    /**
     * 包名 非java 打头的import
     */
    private List<String> mapperImportNonJava;

    /**
     * 包名 java 打头的import
     */
    private List<String> mapperImportJava;

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
     * 将所有有用信息在这里回填
     */
    public Doraemon(@NonNull SnailCommand command, @NonNull SnailTable table) {

        this.tableName = table.getName();
        this.entityName = command.getEntityName();
        this.tableRemarks = table.getRemarks();
        this.columns = table.getColumns();

        this.dalModuleName = command.getDalModuleName();
        this.dalPackage = command.getDalPackage();

        this.entityPackage = command.getEntityPackage();
        this.entityUpperClassName = command.getEntityUpperClassName();
        this.entityLowerClassName = StringUtil.upperCamelToLowerCamel(this.entityUpperClassName);
        this.entityFullClassName = this.entityPackage + "." + this.entityUpperClassName;

        this.mapperPackage = command.getMapperPackage();
        this.mapperUpperClassName = command.getMapperUpperClassName();
        this.mapperLowClassName = StringUtil.upperCamelToLowerCamel(this.mapperUpperClassName);
        this.mapperFullClassName = this.mapperPackage + "." + this.mapperUpperClassName;

        this.xmlFolder = command.getXmlFolder();
        this.xmlName = command.getXmlName();

        this.columns = table.getColumns();
        this.ignoreFields = command.getIgnoreFields();

        this.selfColumns = new ArrayList<>();
        this.columns.forEach((column -> {
            // 这三个字段是在基类中的
            if (StringUtil.equals("id", column.getName()) ||
                    StringUtil.equals("gmt_create", column.getName()) ||
                    StringUtil.equals("gmt_modified", column.getName()) ||
                    this.ignoreFields.contains(column.getName())) {

                this.selfColumns.add(column);

            }
        }));

        // 处理entity需要引入的包
        this.entityImportNonJavas = new ArrayList<>();
        this.entityImportNonJavas.add(Data.class.getName());
        this.entityImportNonJavas.add(EqualsAndHashCode.class.getName());
        this.entityImportNonJavas.add(BaseEntity.class.getName());

        this.entityImportJavas = new ArrayList<>();

        this.selfColumns.forEach((column -> {

            String javaFullClassName = column.getJavaFullClassName();

            // java.lang包下的不需要导入
            if (!StringUtil.startsWith(javaFullClassName, "java.lang")) {

                if (StringUtil.startsWith(javaFullClassName, "java")) {
                    this.entityImportJavas.add(javaFullClassName);
                } else {
                    this.entityImportNonJavas.add(javaFullClassName);
                }
            }
        }));


    }

}
