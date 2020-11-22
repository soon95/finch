package org.leon.finch.dal.foo;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leon.finch.common.result.SortDirection;
import org.leon.finch.dal.base.BaseMapper;

/**
 * generated by SnailGenerator
 *
 * @author Leon Song
 */
@Mapper
public interface FooMapper extends BaseMapper<Foo> {

    /**
     * 分页查询
     *
     * @param pageNum    页数
     * @param pageSize    页面展示数量
     * @param age    年龄，精确查找
     * @param name    姓名，模糊查找
     * @param orderId    主键，排序字段
     * @param orderGmtCreate    创建时间，排序字段
     * @param orderGmtModified    修改时间，排序字段
     * @return 分页对象
     */
    Page<Foo> page(
        @Param("pageNum") int pageNum,
        @Param("pageSize") int pageSize,
        @Param("age") Integer age,
        @Param("name") String name,
        @Param("orderId") SortDirection orderId,
        @Param("orderGmtCreate") SortDirection orderGmtCreate,
        @Param("orderGmtModified") SortDirection orderGmtModified
    );

}
