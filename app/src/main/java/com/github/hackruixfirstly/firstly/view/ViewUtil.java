package com.github.hackruixfirstly.firstly.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by trevor on 10/4/15.
 */
public class ViewUtil {

    public static String getFriendlyDate(Date date) {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);

        return df.format(date);
    }

}
