package com.yooxinz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UseStateEnum {

    ENABLE(1,"启用"),
    PROHIBIT(0,"禁用");

    private int key;
    private String name;

}
