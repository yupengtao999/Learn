package com.krry.entity;

/**
 * 工单状态枚举类
 */
public enum EnumWorkStatus {

    NOSEDN("未派单"),SENDED("已派单"),RECEIVE("待接单"),HANDLER("待处理"),OVER("已回单"),CANCEL("已撤单"),
    NODOWN("未完成"), DOWN("已完成"),HISTORY("历史工单");

    private String name;

    EnumWorkStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
