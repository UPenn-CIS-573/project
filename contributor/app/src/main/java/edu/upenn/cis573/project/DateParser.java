package edu.upenn.cis573.project;

import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.Date;

public class DateParser {

    private final SimpleDateFormat oldDateFormatter;
    private final SimpleDateFormat newDateFormatter;

    public DateParser(String oldFormat, String newFormat){
        this.oldDateFormatter = new SimpleDateFormat(oldFormat);
        this.newDateFormatter = new SimpleDateFormat(newFormat);
    }

    public String convertDateToNewFormat(String dateInOldFormat) throws ParseException {
        Date date = oldDateFormatter.parse(dateInOldFormat);
        return newDateFormatter.format(date);
    }
}
