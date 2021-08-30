package com.midas.epkorea.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ChangeDateTime {

    public static String timestampToString(Timestamp timestamp){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(timestamp);
    }

    private ChangeDateTime(){}

}
