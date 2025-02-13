package util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class UtilDate {

    public static int countDaysBetweenDates(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null");
        }

        long diffInMillis = endDate.getTime() - startDate.getTime();
        int days = (int) TimeUnit.MILLISECONDS.toDays(diffInMillis);

        return days;
    }
}