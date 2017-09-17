package com.example.xyzreader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vijayalakshmi on 9/17/2017.
 */

public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");

    public static Date parsePublishedDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            return new Date();
        }
    }
}
