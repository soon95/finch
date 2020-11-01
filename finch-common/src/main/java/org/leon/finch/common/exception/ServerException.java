package org.leon.finch.common.exception;

import org.leon.finch.common.enums.ResultCode;

/**
 * 服务器异常
 * 出现这个异常说明代码有问题，开发者需要修改代码避免异常
 *
 * @author Leon Song
 * @date 2020-11-01
 */
public class ServerException extends UtilException {

    public ServerException() {
        super(ResultCode.UNKNOWN);
    }

    public ServerException(String messagePattern, Object... arguments) {
        super(ResultCode.UNKNOWN, messagePattern, arguments);
    }

    public ServerException(ResultCode resultCode) {
        super(resultCode);
    }

}
