package com.interest.common.model;

import com.interest.common.enums.ResponseStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回的JSON数据结构标准
 *
 * @param <T>
 */
@Data
public class ResponseWrapper<T> {

    @ApiModelProperty("状态码")
    private String status = ResponseStatus.OK.getValue();

    @ApiModelProperty("信息")
    private String message = "success";

    @ApiModelProperty("接口返回数据")
    private T data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(T data) {
        this.data = data;
    }

    public ResponseWrapper(ResponseStatus status, String message) {
        this.status = status.getValue();
        this.message = message;
    }

    public ResponseWrapper(ResponseStatus status, T data) {
        this.status = status.getValue();
        this.data = data;
    }

    public ResponseWrapper(ResponseStatus status, String message, T data) {
        this.status = status.getValue();
        this.message = message;
        this.data = data;
    }

}
