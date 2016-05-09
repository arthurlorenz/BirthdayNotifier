package com.example.arthur.birthdaynotifier;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by arthur on 11.04.16.
 */
public class Utils {

    public static GregorianCalendar setDate(String s) throws ParseException {
        String dateString = s;
        String formatString = "yyyy-MM-dd";
        if (s.matches("(..)\\.(..)\\.(....)")) {
            formatString = "dd.MM.yyyy";
        }
        if (s.matches("(...) (..), (....)")) {
            int monthBday = 1;
            String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            for (int i = 0; i < months.length; i++) {
                if (s.substring(0, 3).equals(months[i]))
                    monthBday += i;
            }
            if (monthBday < 10)
                dateString = s.substring(8) + "-0" + monthBday + "-" + s.substring(4, 6);
            else
                dateString = s.substring(8) + "-" + monthBday + "-" + s.substring(4, 6);

        }
        SimpleDateFormat sdfToDate = new SimpleDateFormat(formatString);
        Date date = sdfToDate.parse(dateString);
        GregorianCalendar gregCal = new GregorianCalendar();
        gregCal.setTime(date);
        return gregCal;
    }

}
