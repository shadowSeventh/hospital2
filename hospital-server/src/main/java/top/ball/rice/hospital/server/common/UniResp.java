package top.ball.rice.hospital.server.common;

import java.util.Date;
import java.util.List;

/**
 * 统一格式的JSON响应。
 * <p>
 * 大多数内容参考了 BasicErrorController 中的设置。
 *
 * @see org.springframework.boot.autoconfigure.web.BasicErrorController
 * @see org.springframework.boot.autoconfigure.web.DefaultErrorAttributes
 */
public class UniResp<T> {


    private Integer status;


    private T data;


    private Date timestamp;


    private String error;


    private String exception;


    private String message;

    /**
     * BindingResult 异常中的错误。
     * <p>
     * 可选，出错时才可能有值。
     *
     * @see org.springframework.validation.BindingResult
     * @see org.springframework.validation.ObjectError
     */

    private List<Object> errors;


    private String trace;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

}
