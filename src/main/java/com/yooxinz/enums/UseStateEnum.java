package com.yooxinz.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UseStateEnum {

    ENABLE(1,"启用",true),
    PROHIBIT(0,"禁用",false);

    private int key;
    private String name;
    private boolean value;

    public static UseStateEnum getByKey(int key) {
        for (UseStateEnum c : UseStateEnum.values()) {
            if (c.getKey() == key) {
                return c;
            }
        }
        return null;
    }
}
