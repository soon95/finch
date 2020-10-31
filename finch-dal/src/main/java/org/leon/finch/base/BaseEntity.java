package org.leon.finch.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leon Song
 * @date 2020-10-31
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate = new Date();

    /**
     * 修改时间
     */
    private Date gmtModified = new Date();

}
