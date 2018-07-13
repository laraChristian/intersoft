package co.com.foundation.intersoft.utils;

import java.util.Date;

public class DateUtils {

	public static Date timeToDate(final long time) {
		return new Date(time);
	}

	public static boolean isBefore(Date start, Date end) {
		return start.before(end);
	}

}
