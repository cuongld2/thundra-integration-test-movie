package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

  public static Date parseDate(String date, String pattern) throws ParseException {
    if (UtilsClass.isNOTNullEmpty(date) && UtilsClass.isNOTNullEmpty(pattern)) {
      return new SimpleDateFormat(pattern).parse(date);
    }
    return null;
  }

  public static Date parseDateExactlyPattern(String date, String pattern) throws ParseException {
    if (UtilsClass.isNOTNullEmpty(date) && UtilsClass.isNOTNullEmpty(pattern)) {
      DateFormat format = new SimpleDateFormat(pattern);
      format.setLenient(false);
      return format.parse(date);
    }
    return null;
  }

  /**
   * Format a string as date
   * 
   * @param date provide date
   * @param inputFormat current format of provide date
   * @param outputFormat new format of provide date
   * @return provide date with new format e.g: if you wish to change the format of 18/04/2018 to
   *         2018-04-18 then the method should look like: formatDate("18/04/2018", "dd/MM/2018",
   *         "yyyy-MM-dd") return value would be 2018-04-18
   */
  public static String formatDate(String date, String inputFormat, String outputFormat)
      throws ParseException {
    Date inputDate = parseDateExactlyPattern(date, inputFormat);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputFormat);
    return simpleDateFormat.format(inputDate);
  }

  public static Date setHMS(Date date, int hourOfDay, int minute, int second) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
    cal.set(Calendar.MINUTE, minute);
    cal.set(Calendar.SECOND, second);
    return cal.getTime();
  }

  public static Date setHMSms(Date date, int hourOfDay, int minute, int second, int milliSecond) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(setHMS(date, hourOfDay, minute, second));
    cal.set(Calendar.MILLISECOND, milliSecond);
    return cal.getTime();
  }

  public static Date setZeroHMSms(Date date) {
    return setHMSms(date, 0, 0, 0, 0);
  }

  public static Date addDay(Date date, int day) {
    if (date != null) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      CalendarUtils.addDay(cal, day);
      return cal.getTime();
    }
    return date;
  }

  public static Date addDay(String date, String pattern, int day) throws ParseException {
    Date dt = parseDate(date, pattern);
    if (dt != null) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(dt);
      CalendarUtils.addDay(cal, day);
      return cal.getTime();
    }
    return dt;
  }

  public static boolean isAfter(Date value1, Date value2) {
    if (value1 != null && value2 != null)
      return value1.after(value2);
    return false;
  }

  public static boolean isBefore(Date value1, Date value2) {
    if (value1 != null && value2 != null)
      return value1.before(value2);
    return false;
  }

  public static boolean isBetween(Date value, Date start, Date end) {
    if (value != null && start != null && end != null) {
      return !(value.before(start) || value.after(end));
    }
    return false;
  }

  public static boolean isAfterOnlyDate(Date value1, Date value2) {
    return isAfter(setZeroHMSms(value1), setZeroHMSms(value2));
  }

  public static boolean isBeforeOnlyDate(Date value1, Date value2) {
    return isBefore(setZeroHMSms(value1), setZeroHMSms(value2));
  }

  public static boolean isBetweenOnlyDate(Date value, Date start, Date end) {
    return isBetween(setZeroHMSms(value), setZeroHMSms(start), setZeroHMSms(end));
  }

  public static boolean isEqual(Date value, Date compareTo) {
    return value.equals(compareTo);
  }

  public static boolean isGreater(Date value, Date compareTo) {
    return isAfter(value, compareTo);
  }

  public static boolean isLessThan(Date value, Date compareTo) {
    return isBefore(value, compareTo);
  }

  public static boolean isGreaterOrEqual(Date value, Date compareTo) {
    return isGreater(value, compareTo) || isEqual(value, compareTo);
  }

  public static boolean isLessThanOrEqual(Date value, Date compareTo) {
    return isLessThan(value, compareTo) || isEqual(value, compareTo);
  }

  public static boolean isEqualOnlyDate(Date value, Date compareTo) {
    return setZeroHMSms(value).equals(setZeroHMSms(compareTo));
  }

  public static boolean isGreaterOnlyDate(Date value, Date compareTo) {
    return isAfterOnlyDate(value, compareTo);
  }

  public static boolean isLessThanOnlyDate(Date value, Date compareTo) {
    return isBeforeOnlyDate(value, compareTo);
  }

  public static boolean isGreaterOrEqualOnlyDate(Date value, Date compareTo) {
    return isAfterOnlyDate(value, compareTo) || isEqualOnlyDate(value, compareTo);
  }

  public static boolean isLessThanOrEqualOnlyDate(Date value, Date compareTo) {
    return isBeforeOnlyDate(value, compareTo) || isEqualOnlyDate(value, compareTo);
  }

  public static Date parseFromUnixTimeStamp(Long time) {
    if (time != null) {
      return new Date((long) time * 1000);
    }
    return null;
  }

}
