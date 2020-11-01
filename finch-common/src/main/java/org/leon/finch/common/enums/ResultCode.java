package org.leon.finch.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 所有自定义的错误码
 *
 * @author Leon Song
 * @date 2020-11-01
 */
public enum ResultCode {

    /**
     * 请求成功
     */
    OK(HttpStatus.OK, "成功"),
    /**
     * 请求错误
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "请求不合法"),
    /**
     * 内容找不到
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "内容不存在"),
    /**
     * 未知错误
     */
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "服务器未知错误");

    @Getter
    private HttpStatus httpStatus;

    @Getter
    private String message;

    ResultCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
