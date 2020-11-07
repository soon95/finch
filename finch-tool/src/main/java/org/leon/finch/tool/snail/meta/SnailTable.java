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

    private String name;

    private List<SnailColumn> columns = new ArrayList<>();


}
