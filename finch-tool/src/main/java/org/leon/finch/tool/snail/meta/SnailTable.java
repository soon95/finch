package org.leon.finch.tool.snail.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 表 元信息
 *
 * @author Leon Song
 * @date 2020-11-07
 */
@Data
@AllArgsConstructor
public class SnailTable {

    /**
     * 表名
     */
    private String name;

    /**
     * 表注释
     */
    private String remarks;

    /**
     * 表中字段
     */
    private List<SnailColumn> columns = new ArrayList<>();


}
