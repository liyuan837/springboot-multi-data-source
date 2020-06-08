package com.liyuan.demo.domain.response;

import io.swagger.annotations.ApiModelProperty;

public class ResponseEntity<T> {

    @ApiModelProperty(value = "状态: ok 成功, fail 失败")
    private String result;

    @ApiModelProperty(value = "状态码: 200 成功, 201 失败")
    private Integer rescode;

    @ApiModelProperty(value = "备注原因")
    private String msg;

    @ApiModelProperty(value = "返回对象")
    private T data;

    public ResponseEntity(String result, Integer rescode, String msg, T data) {
        this.result = result;
        this.rescode = rescode;
        this.msg = msg;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRescode() {
        return rescode;
    }

    public void setRescode(Integer rescode) {
        this.rescode = rescode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
