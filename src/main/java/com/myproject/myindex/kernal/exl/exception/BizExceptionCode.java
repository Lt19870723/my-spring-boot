package com.myproject.myindex.kernal.exl.exception;

public enum BizExceptionCode {

    SUCCESS("2000", "成功"), 
    
    INTERNAL_ERROR("9999", "内部错误"), 
    
    CONNET_TIMEOUT("9001", "网络连接超时"), 
    
    AUTH_FAIL("9002", "网络认证失败"),
    
    CONNECT_ERROR("9003", "网络连接异常"),

    LOAD_CONFIG_FAIL("9100", "加载配置异常"),

    ENCODE_FAIL("8001", "编码异常"), 
    
    DECODE_FAIL("8002", "解码异常"), 
    
    SIGNATURE_FAIL("8003", "签名异常"), 
    
    VERIFY_SIGNATURE_FAIL("8004", "签名验证异常"), 
    
    SIGNATURE_ERROR("8005", "签名错误"), 
    
    PACKET_ERROR("8006", "报文格式错误"), 
    
    CHARSET_ERROR("8007","charset错误"),

    SQL_ERROR("10001", "数据库异常"),

    ;

    private String code;

    private String msg;

    BizExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
