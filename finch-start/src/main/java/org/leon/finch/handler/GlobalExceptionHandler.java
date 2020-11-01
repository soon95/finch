package org.leon.finch.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.leon.finch.common.enums.ResultCode;
import org.leon.finch.common.exception.UtilException;
import org.leon.finch.common.util.StringUtil;
import org.leon.finch.rest.base.WebResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局的异常处理
 *
 * @author Leon Song
 * @date 2020-11-01
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({UtilException.class})
    @ResponseBody
    public WebResult handleUtilException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         UtilException exception) {
        // 按照各自的异常进行返回
        response.setStatus(exception.getCode().getHttpStatus().value());

        WebResult webResult = new WebResult(exception.getCode(), exception.getMessage());

        log.error("------ {} ------", exception.getClass().getName());
        log.error(ExceptionUtils.getRootCauseMessage(exception), exception);

        return webResult;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public WebResult handleException(Throwable throwable) {

        WebResult webResult = new WebResult(ResultCode.UNKNOWN, StringUtil.format("未知异常,{}", ExceptionUtils.getRootCauseMessage(throwable)));

        log.error("------ {} ------", throwable.getClass().getName());
        log.error(ExceptionUtils.getRootCauseMessage(throwable), throwable);

        return webResult;
    }

}
