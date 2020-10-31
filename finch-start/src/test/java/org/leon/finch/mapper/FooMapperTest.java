package org.leon.finch.mapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.leon.finch.Foo;
import org.leon.finch.FooMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        log.info("执行测试");

        Foo foo = new Foo();
        foo.setName("leon");
        foo.setAge(18);

        int res = fooMapper.insert(foo);

        log.info("测试结果，修改:{} 条记录", res);
    }

    @Test
    public void testDeleteById() {

        int res = fooMapper.deleteById(2L);

        log.info("测试结果，修改:{} 条记录", res);


    }

}