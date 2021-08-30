package com.midas.epkorea.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChangeDateTime {

    public static String TimestampToString(Timestamp timestamp){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(timestamp);
        return format;
    }

}
