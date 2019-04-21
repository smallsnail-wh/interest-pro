package com.interest.blog;

import com.interest.common.utils.DateUtil;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTest {

    @Test
    public void test(){
        String dateT = "1555171200000";
        logTime(dateT);
        logTime(DateUtil.dayEnd(dateT));
        logTime(DateUtil.dayStart(dateT));
    }

    private void logTime(String time){
        Instant instant = Instant.ofEpochMilli(Long.valueOf(time));
        System.out.println(LocalDateTime.ofInstant(instant,ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
    }

}
