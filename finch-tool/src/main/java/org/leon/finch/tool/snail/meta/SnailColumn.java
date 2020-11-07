package org.leon.finch.tool.snail.meta;

import lombok.Data;
import org.leon.finch.common.util.StringUtil;
import org.leon.finch.tool.snail.contrast.TypeContrast;

/**
 * 表字段 元信息
 *
 * @author Leon Song
 * @date 2020-11-07
 */
@Data
public class SnailColumn {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段注释
     */
    private String remark;

    /**
     * 表中字段类型
     */
    private String sqlType;

    /**
     * 是否为主键
     */
    private boolean primaryKey = false;

    /**
     * 小写驼峰
     */
    private String lowerCamelName;

    /**
     * 大写驼峰
     */
    private String upperCamelName;

    /**
     * jdbc类型与java类型映射关系
     */
    private TypeContrast typeContrast;

    /**
     * jdbc类型
     */
    private String jdbcType;

    /**
     * java类型
     */
    private Class<?> javaType;

    /**
     * 是否可排序
     */
    private boolean canSort = false;


    public SnailColumn(String name, String remark, String sqlType) {

        this.name = name;
        this.remark = remark;
        this.sqlType = sqlType;

        this.lowerCamelName = StringUtil.underscoreToLowerCamel(name);
        this.upperCamelName = StringUtil.underscoreToUpperCamel(name);


        // 处理类型映射关系
        updateTypeContrast();

    }

    /**
     * 处理类型映射关系
     */
    private void updateTypeContrast() {

        this.typeContrast = TypeContrast.getBySqlType(sqlType);

        this.jdbcType = typeContrast.getJdbcType();
        this.javaType = typeContrast.getJavaType();

    }
}
