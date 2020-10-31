package org.leon.finch.dal;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leon.finch.common.result.SortDirection;
import org.leon.finch.dal.base.BaseMapper;

/**
 * @author Leon Song
 * @date 2020-10-31
 */
@Mapper
public interface FooMapper extends BaseMapper<Foo> {

    /**
     * 分页查询
     *
     * @param pageNum          页数
     * @param pageSize         页面展示数量
     * @param name             名字，模糊查找
     * @param age              年龄，精确查找
     * @param orderId          id排序
     * @param orderGmtCreate   创建时间排序
     * @param orderGmtModified 修改时间排序
     * @return 分页对象
     */
    Page<Foo> page(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("orderId") SortDirection orderId,
            @Param("orderGmtCreate") SortDirection orderGmtCreate,
            @Param("orderGmtModified") SortDirection orderGmtModified
    );

}
