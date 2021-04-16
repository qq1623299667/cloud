package com.example.elastic.job.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /**
     * 将时间戳转换成时间字符串
     * @param timeStamp
     * @return
     */
    public static String mill2Time(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = simpleDateFormat.format(new Date(timeStamp));
        return timeString;
    }
}
