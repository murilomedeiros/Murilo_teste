/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.deafio.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author muril
 */
public class DateUtils {

    public static Timestamp convertToTimestamp(String dt) {
        Date dateTime = new Date(dt);
        Timestamp ts = new Timestamp(dateTime.getTime());
        return ts;
    }

    public static String formatDate(Date date, String type) {
        SimpleDateFormat sdf = null;
        if (type.equals("date")) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        } else if (type.equals("time")) {
            sdf = new SimpleDateFormat("HH:mm:ss");
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        return sdf.format(date);
    }

}
