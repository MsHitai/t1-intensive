package com.t1.intensive.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class KafkaUtils {

    public static String getKafkaKey() {
        return UUID.randomUUID().toString();
    }
}
