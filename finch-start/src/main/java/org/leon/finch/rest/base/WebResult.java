package org.leon.finch.rest.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.leon.finch.common.enums.ResultCode;

/**
 * @author Leon Song
 * @date 2020-11-01
 */
@NoArgsConstructor
@Data
public class WebResult<T> {

    private ResultCode code = ResultCode.OK;

    private T data;

    private String msg = "success";

    /**
     * 请求失败时
     */
    public WebResult(ResultCode code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 请求成功时
     */
    public WebResult(T data) {
        this.data = data;
    }


}
