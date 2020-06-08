package com.liyuan.demo.domain.exception;

public class MultiDataSourceException extends RuntimeException {

    private static final long serialVersionUID = -6202759931628287239L;
    private static final int DEFAULT_ERROR_CODE = 201;

    /* 错误码,用于返回接口code */
    private int errCode;

    public MultiDataSourceException() {
        super();
    }

    public MultiDataSourceException(String msg) {
        super(msg);
        this.errCode = DEFAULT_ERROR_CODE;
    }

    public MultiDataSourceException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public MultiDataSourceException(String msg, Throwable e) {
        super(msg, e);
        this.errCode = DEFAULT_ERROR_CODE;
    }

    public MultiDataSourceException(int errCode, String msg, Throwable e) {
        super(msg, e);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}