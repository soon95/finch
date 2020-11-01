package org.leon.finch.common.exception;

import org.leon.finch.common.enums.ResultCode;

/**
 * 请求参数异常
 * 通常是用户犯的错，用户可以自行修正
 *
 * @author Leon Song
 * @date 2020-11-01
 */
public class BadRequestException extends UtilException {

    public BadRequestException() {
        super(ResultCode.BAD_REQUEST);
    }

    public BadRequestException(String messagePattern, Object... arguments) {
        super(ResultCode.BAD_REQUEST, messagePattern, arguments);
    }

    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }
}
