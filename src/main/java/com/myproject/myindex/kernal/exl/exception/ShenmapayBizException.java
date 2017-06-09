package com.myproject.myindex.kernal.exl.exception;

public class ShenmapayBizException extends Exception {
    private static final long serialVersionUID = -8035734708073886518L;
    private String            code;

    public ShenmapayBizException(String message) {
        super(message);
        this.code = BizExceptionCode.INTERNAL_ERROR.getCode();
    }

    public ShenmapayBizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ShenmapayBizException(BizExceptionCode bizExceptionCode) {
        super(bizExceptionCode.getMsg());
        this.code = bizExceptionCode.getCode();
    }

    public ShenmapayBizException(String message, Throwable throwable) {
        super(message, throwable);
        this.code = BizExceptionCode.INTERNAL_ERROR.getCode();
    }

    public ShenmapayBizException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public ShenmapayBizException(BizExceptionCode bizExceptionCode, Throwable throwable) {
        super(bizExceptionCode.getMsg(), throwable);
        this.code = bizExceptionCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
