package com.myproject.myindex.Enum;

/**
 * 代付通道枚举
 *
 */
public enum PayChannelEnum {
    ZJ("ZJ", "中金代付"), 
    ;

    /**  枚举值*/
    private String value;

    /**  描述*/
    private String desc;

    /**
     *  构造方法
     * @param value
     * @param desc
     */
    private PayChannelEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获取枚举值
     * @return
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 获取枚举描述
     * @return
     */
    public String getDesc() {
        return this.desc;
    }
}
