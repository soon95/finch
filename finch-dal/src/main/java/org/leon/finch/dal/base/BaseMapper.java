package org.leon.finch.dal.base;

import java.util.List;

/**
 * 这些方法是每个mapper必备的
 * 另外，分页方法根据实际情况设置
 *
 * @author Leon Song
 * @date 2020-10-31
 */
public interface BaseMapper<T> {

    /**
     * 插入一个对象
     *
     * @param t 对象
     * @return 影响的条数
     */
    int insert(T t);

    /**
     * 批量插入对象
     *
     * @param list 对象列表
     * @return 影响条数
     */
    int batchInsert(List<T> list);

    /**
     * 按id删除对象
     *
     * @param id 主键
     * @return 影响条数
     */
    int deleteById(Long id);

    /**
     * 按id数组批量删除对象
     *
     * @param list 主键列表
     * @return 影响条数
     */
    int deleteByIds(List<Long> list);

    /**
     * 更新对象
     *
     * @param t 对象
     * @return 影响条数
     */
    int update(T t);

    /**
     * 按id查询对象
     *
     * @param id 主键
     * @return 查询结果对象
     */
    T selectById(Long id);

    /**
     * 按id列表查询对象
     *
     * @param list 主键列表
     * @return 查询结果对象列表
     */
    List<T> selectByIds(List<Long> list);

}
