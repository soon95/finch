package org.leon.finch.domain.base;

/**
 * @author Leon Song
 * @date 2021-03-14
 */
public interface Identifiable<ID extends Identifier> {

    /**
     * 获取主键
     *
     * @return 主键
     */
    ID getId();
}
