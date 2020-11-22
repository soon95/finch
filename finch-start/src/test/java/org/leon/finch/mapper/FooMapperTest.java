package org.leon.finch.mapper;


import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.leon.finch.common.result.SortDirection;
import org.leon.finch.dal.foo.Foo;
import org.leon.finch.dal.foo.FooMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon Song
 * @date 2020-10-31
 */
@Slf4j
@SpringBootTest
public class FooMapperTest {

    @Autowired
    public FooMapper fooMapper;

    @Test
    public void testInsert() {

        Foo foo = new Foo();
        foo.setName("test");
        foo.setAge(25);

        int res = fooMapper.insert(foo);

        log.info("测试结果，修改:{} 条记录", res);
    }

    @Test
    public void testBatchInsert() {

        Foo foo1 = new Foo();
        foo1.setName("test2");
        foo1.setAge(18);

        Foo foo2 = new Foo();
        foo2.setName("test3");
        foo2.setAge(1000);

        List<Foo> foos = new ArrayList<>();
        foos.add(foo1);
        foos.add(foo2);

        int res = fooMapper.batchInsert(foos);

        log.info("测试结果，修改:{} 条记录", res);

    }



    @Test
    public void testDeleteById() {

        int res = fooMapper.deleteById(36L);

        log.info("测试结果，修改:{} 条记录", res);

    }

    @Test
    public void testDeleteByIds() {

        List<Long> ids = new ArrayList<>();
        ids.add(37L);
        ids.add(38L);

        int res = fooMapper.deleteByIds(ids);

        log.info("测试结果，修改:{} 条记录", res);

    }

    @Test
    public void testUpdate() {

        Foo foo = new Foo();
        foo.setId(7L);
        foo.setAge(1000);
        foo.setName("齐天大圣");

        int res = fooMapper.update(foo);

        log.info("测试结果，修改:{} 条记录", res);

    }

    @Test
    public void testSelectById() {

        Foo foo = fooMapper.selectById(6L);

        log.info("测试结果，foo:{}", foo);

    }

    @Test
    public void testSelectByIds() {

        List<Long> ids = new ArrayList<>();
        ids.add(6L);
        ids.add(7L);

        List<Foo> foos = fooMapper.selectByIds(ids);

        log.info("测试结果，foos:{}", foos);

    }

    @Test
    public void testPage() {

        Page<Foo> page = fooMapper.page(
                1,
                5,
                24,
                null,
                SortDirection.DESC,
                null,
                null);

        log.info("测试结果，page:{}", page);

    }

}