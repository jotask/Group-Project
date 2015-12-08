package com.github.jotask.groupproject.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jose Vives on 08/12/2015.
 *
 * @author Jose Vives.
 * @since 08/12/2015
 */
public class Util {

    public static java.sql.Date toSQLDate(java.util.Date javaDate){
        return  new java.sql.Date(javaDate.getTime());
    }

    public static java.util.Date toJavaDate(java.sql.Date sqlDate){
        return new java.util.Date(sqlDate.getTime());
    }

    public static Timestamp stringToTimetamp(String text){
        Timestamp timestamp = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(text);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){//this generic but you can control another types of exception
            // TODO catch exception
        }
        return timestamp;
    }

}
