package com.yooxinz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum UseStateEnum {

    ENABLE(1,"启用"),
    PROHIBIT(0,"禁用");

    private int key;
    private String name;

    public static UseStateEnum getByKey(int key) {
        for (UseStateEnum c : UseStateEnum.values()) {
            if (c.getKey() == key) {
                return c;
            }
        }
        return null;
    }
}
