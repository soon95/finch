package org.leon.finch;

import org.apache.ibatis.annotations.Mapper;
import org.leon.finch.base.BaseMapper;

/**
 * @author Leon Song
 * @date 2020-10-31
 */
@Mapper
public interface FooMapper extends BaseMapper<Foo> {
}
