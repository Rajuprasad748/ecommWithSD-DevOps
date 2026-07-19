package com.ecommerce.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String
            DEFAULT_PATTERN =
            "dd-MM-yyyy HH:mm:ss";

    public static String formatDate(
            LocalDateTime dateTime) {

        return dateTime.format(
                DateTimeFormatter.ofPattern(
                        DEFAULT_PATTERN
                )
        );
    }

    public static String formatDate(
            LocalDateTime dateTime,
            String pattern) {

        return dateTime.format(
                DateTimeFormatter.ofPattern(
                        pattern
                )
        );
    }
}