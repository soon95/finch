package org.leon.finch.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.leon.finch.common.enums.ResultCode;
import org.leon.finch.common.util.StringUtil;

/**
 * 自定义的异常类的父类
 *
 * @author Leon Song
 * @date 2020-11-01
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UtilException extends RuntimeException {

    protected ResultCode code;
    protected String message;

    public UtilException(String messagePattern, Object... arguments) {

        super(StringUtil.format(messagePattern, arguments));

        this.code = ResultCode.UNKNOWN;
        this.message = StringUtil.format(messagePattern, arguments);
    }

    public UtilException(ResultCode resultCode) {
        super(resultCode.getMessage());

        this.code = resultCode;
        this.message = resultCode.getMessage();
    }

    public UtilException(Throwable throwable) {
        super(throwable);

        this.code = ResultCode.UNKNOWN;
        this.message = throwable.getMessage();
    }


    public UtilException(ResultCode resultCode, String messagePattern, Object... arguments) {
        super(StringUtil.format(messagePattern, arguments));

        this.code = resultCode;
        this.message = StringUtil.format(messagePattern, arguments);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return StringUtil.format("{} code={} message={}", this.getClass().getSimpleName(), this.code, this.message);
    }

}
