package com.mall.enums;

public enum DataNum {
    DATA_NUM(1);

    final int type;

    DataNum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
