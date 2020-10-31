package org.leon.finch;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.leon.finch.base.BaseEntity;

/**
 * @author Leon Song
 * @date 2020-10-31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Foo extends BaseEntity {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}
