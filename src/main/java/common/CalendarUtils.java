package common;

import java.util.Calendar;

public class CalendarUtils {

  public static Calendar setDMYHMS(int date, int month, int year, int hourOfDay, int minute,
      int second) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DATE, date);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
    return calendar;
  }

  public static Calendar setDMYHMSms(int date, int month, int year, int hourOfDay, int minute,
      int second, int milliSecond) {
    Calendar cal = setDMYHMS(date, month, year, hourOfDay, minute, second);
    cal.set(Calendar.MILLISECOND, milliSecond);
    return cal;
  }

  public static Calendar setHMS(Calendar cal, int hourOfDay, int minute, int second) {
    Calendar calendar = (Calendar) cal.clone();
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
    return calendar;
  }

  public static Calendar setHMSms(Calendar cal, int hourOfDay, int minute, int second,
      int milliSecond) {
    Calendar calendar = setHMS(cal, hourOfDay, minute, second);
    calendar.set(Calendar.MILLISECOND, milliSecond);
    return calendar;
  }

  public static Calendar setZeroHMSms(Calendar cal) {
    return setHMSms(cal, 0, 0, 0, 0);
  }

  public static Calendar addDay(Calendar cal, int day) {
    if (cal != null)
      cal.add(Calendar.DATE, day);
    return cal;
  }

  public static boolean isAfter(Calendar value1, Calendar value2) {
    if (value1 != null && value2 != null)
      return value1.after(value2);
    return false;
  }

  public static boolean isBefore(Calendar value1, Calendar value2) {
    if (value1 != null && value2 != null)
      return value1.before(value2);
    return false;
  }

  public static boolean isBetween(Calendar value, Calendar start, Calendar end) {
    if (value != null && start != null && end != null) {
      return !(value.before(start) || value.after(end));
    }
    return false;
  }

  public static boolean isAfterOnlyDate(Calendar value1, Calendar value2) {
    return isAfter(setZeroHMSms(value1), setZeroHMSms(value2));
  }

  public static boolean isBeforeOnlyDate(Calendar value1, Calendar value2) {
    return isBefore(setZeroHMSms(value1), setZeroHMSms(value2));
  }

  public static boolean isBetweenOnlyDate(Calendar value, Calendar start, Calendar end) {
    return isBetween(setZeroHMSms(value), setZeroHMSms(start), setZeroHMSms(end));
  }

  public static boolean isEqual(Calendar value, Calendar compareTo) {
    return value.equals(compareTo);
  }

  public static boolean isGreater(Calendar value, Calendar compareTo) {
    return isAfter(value, compareTo);
  }

  public static boolean isLessThan(Calendar value, Calendar compareTo) {
    return isBefore(value, compareTo);
  }

  public static boolean isGreaterOrEqual(Calendar value, Calendar compareTo) {
    return isGreater(value, compareTo) || isEqual(value, compareTo);
  }

  public static boolean isLessThanOrEqual(Calendar value, Calendar compareTo) {
    return isLessThan(value, compareTo) || isEqual(value, compareTo);
  }

  public static boolean isEqualOnlyDate(Calendar value, Calendar compareTo) {
    return setZeroHMSms(value).equals(setZeroHMSms(compareTo));
  }

  public static boolean isGreaterOnlyDate(Calendar value, Calendar compareTo) {
    return isAfterOnlyDate(value, compareTo);
  }

  public static boolean isLessThanOnlyDate(Calendar value, Calendar compareTo) {
    return isBeforeOnlyDate(value, compareTo);
  }

  public static boolean isGreaterOrEqualOnlyDate(Calendar value, Calendar compareTo) {
    return isAfterOnlyDate(value, compareTo) || isEqualOnlyDate(value, compareTo);
  }

  public static boolean isLessThanOrEqualOnlyDate(Calendar value, Calendar compareTo) {
    return isBeforeOnlyDate(value, compareTo) || isEqualOnlyDate(value, compareTo);
  }

}
