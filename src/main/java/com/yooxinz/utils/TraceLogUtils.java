package com.yooxinz.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-09-03
 */
@Slf4j
public class TraceLogUtils {

    public static String getTraceId() {
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String uniqueId = timestamp + uuid.toString().replace("-", "");
        return uniqueId;
    }
}
