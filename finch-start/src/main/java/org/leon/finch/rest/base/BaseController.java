package org.leon.finch.rest.base;

import org.leon.finch.common.enums.ResultCode;

/**
 * @author Leon Song
 * @date 2020-11-01
 */
public abstract class BaseController {

    protected WebResult success() {
        return new WebResult();
    }

    protected WebResult success(String msg) {
        return new WebResult(ResultCode.OK, msg);
    }

    protected WebResult success(ResultCode code, String msg) {
        return new WebResult(code, msg);
    }

    protected WebResult success(Object object) {
        return new WebResult<>(object);
    }

}
