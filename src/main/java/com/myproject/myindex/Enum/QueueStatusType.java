package com.myproject.myindex.Enum;

public enum QueueStatusType {
    INIT("INIT", "初始化"),

    EXCUTING("EXCUTING", "执行中"),

    FAILD("FAILD", "执行失败"),

    CANCEL("CANCEL", "执行取消"),

    END("END", "执行完成");

    private String value;
    private String desc;

    private QueueStatusType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
