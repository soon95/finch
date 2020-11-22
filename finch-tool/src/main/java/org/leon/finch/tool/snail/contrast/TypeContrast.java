package org.leon.finch.tool.snail.contrast;

import lombok.Getter;
import org.leon.finch.common.exception.BadRequestException;
import org.leon.finch.common.util.StringUtil;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 类型对照
 *
 * @author Leon Song
 * @date 2020-11-07
 */
public enum TypeContrast {

    VARCHAR("VARCHAR", "VARCHAR", String.class),
    LONGVARCHAR("LONGVARCHAR", "LONGVARCHAR", String.class),
    CHAR("CHAR", "CHAR", String.class),
    /**
     * 二进制大对象
     */
    BLOB("BLOB", "BLOB", byte[].class),
    TEXT("TEXT", "LONGVARCHAR", String.class),
    TINYTEXT("TINYTEXT", "LONGVARCHAR", String.class),
    MEDIUMTEXT("MEDIUMTEXT", "LONGVARCHAR", String.class),
    LONGTEXT("LONGTEXT", "LONGVARCHAR", String.class),
    INT("INT", "INTEGER", Integer.class),
    INT_UNSIGNED("INT UNSIGNED", "Integer", Integer.class),
    INTEGER("INTEGER", "INTEGER", Integer.class),
    INTEGER_UNSIGNED("INTEGER UNSIGNED", "INTEGER", Integer.class),
    TINYINT("TINYINT", "INTEGER", Integer.class),
    TINYINT_UNSIGNED("TINYINT UNSIGNED", "INTEGER", Integer.class),
    SMALLINT("SMALLINT", "INTEGER", Integer.class),
    SMALLINT_UNSIGNED("SMALLINT UNSIGNED", "INTEGER", Integer.class),
    MEDIUMINT("MEDIUMINT", "INTEGER", Integer.class),
    MEDIUMINT_UNSIGNED("MEDIUMINT UNSIGNED", "INTEGER", Integer.class),
    BIGINT("BIGINT", "BIGINT", Integer.class),
    BIGINT_UNSIGNED("BIGINT UNSIGNED", "BIGINT", Integer.class),
    BIT("BIT", "BIT", Boolean.class),
    FLOAT("FLOAT", "FLOAT", Float.class),
    DOUBLE("DOUBLE", "DOUBLE", Double.class),
    DECIMAL("DECIMAL", "DECIMAL", BigDecimal.class),
    BOOLEAN("", "", Boolean.class),
    ID("PK (INTEGER UNSIGNED)", "", Long.class),
    DATE("DATE", "DATE", Date.class),
    TIME("TIME", "TIME", Time.class),
    DATETIME("DATETIME", "TIMESTAMP", Date.class),
    TIMESTAMP("TIMESTAMP", "TIMESTAMP", Date.class),
    YEAR("YEAR", "DATE", Date.class);


    @Getter
    private String sqlType;

    @Getter
    private String jdbcType;

    @Getter
    private Class<?> javaType;

    TypeContrast(String sqlType, String jdbcType, Class<?> javaType) {
        this.sqlType = sqlType;
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static TypeContrast getBySqlType(String sqlType) {
        for (TypeContrast typeContrast : TypeContrast.values()) {
            if (StringUtil.equalsIgnoreCase(typeContrast.getSqlType(), sqlType)) {
                return typeContrast;
            }
        }

        throw new BadRequestException("不支持的类型:{},请在类型对照类TypeContrast中补充!", sqlType);
    }


}
