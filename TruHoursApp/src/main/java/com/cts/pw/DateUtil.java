package com.cts.pw;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date getCurrentDateAndTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	public static String formatDateAsString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		return formatter.format(date);
	}

}
