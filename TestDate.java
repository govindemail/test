
import java.util.*;
import java.util.GregorianCalendar;
import java.util.TimeZone;

class MyCalendar extends GregorianCalendar {
  MyCalendar(TimeZone tz) {
    super(tz);
  }

  public long getTimeInMillis() {
    return super.getTimeInMillis();
  }
}

public class TestDate{

public static void main(String args[]){
  TestDate.Date2035();
  TestDate.Date1032();
  TestDate.Date2035();
}

public static void Date2035() {
    String testCaseID = "Date2035";
    MyCalendar calendar = new MyCalendar(TimeZone.getDefault());
    Date d = new Date();
    calendar.setTime(d);

    int[] years = {
      calendar.getLeastMaximum(Calendar.YEAR)
    };

    
    for(int i = 0; i < years.length; i++) {
//      System.out.println("getleastmaximum year="+ years[i]);
      int year = years[i] - 1900;
      boolean exceptionThrown = false;
      Date date = new Date(d.getTime());
      
//      System.out.println("date="+date);
      
      calendar.setTime(date);
      
      calendar.set(Calendar.YEAR, year + 1900);
      
      int expectedYear = calendar.get(Calendar.YEAR) - 1900;
      long expectedMillis = calendar.getTimeInMillis();

      date.setYear(year);
      int resultYear = date.getYear();
//      date.setTime(expectedMillis);
      long resultMillis = date.getTime();
//      System.out.println("expectedMillis="+expectedMillis +","+ "resultMillis="+resultMillis);
//      System.out.println("difference="+(expectedMillis-resultMillis));
//
//      
//      if (expectedMillis != resultMillis) {
//        System.out.println("------" + testCaseID + "------");
//        System.out.println("date = " + date);
//        System.out.println("year set by setYear = " + year);
//        System.out.println("expected millis = " + expectedMillis);
//        System.out.println(
//          "Date.getTime() returns wrong result : " + resultMillis
//        );
//      }
      if (resultYear != year) {
         System.out.println("------" + testCaseID + "------");
         System.out.println("date = " + date);
         System.out.println("year set by setYear = " + year);
         System.out.println("year returned by getYear = " + resultYear);
         System.out.println("expected year = " + expectedYear);
         System.out.println(
            "Date.setYear(year) works wrong"
          );
        } else if (resultYear != expectedYear) {
         System.out.println("------" + testCaseID + "------");
         System.out.println("date = " + date);
         System.out.println("year set by setYear = " + year);
         System.out.println("year returned by getYear = " + resultYear);
         System.out.println("expected year = " + expectedYear);
         System.out.println(
            "Date.getYear() works wrong"
          );
        }
      System.out.println("OK-2035");
    }
}
public static void Date1032() {
    long[] ms = {
      Long.MAX_VALUE
    };

    Calendar calendar = new GregorianCalendar(TimeZone.getDefault());

    Date date = new Date();

    for(int i = 0; i < ms.length; i++) {
      long millis = ms[i];

      date.setTime(millis);
//      System.out.println("date after date.setTime(millis)="+date);
      int resultYear = date.getYear();

      calendar.setTime(date);
      int expectedYear = calendar.get(Calendar.YEAR) - 1900;

//      System.out.println("expectedYear="+expectedYear +","+"resultYear=" +resultYear);
      if (expectedYear != resultYear) {
        System.out.println("date = " + date);
        System.out.println("millis = " + millis);
        System.out.println("expected year = " + expectedYear);
        System.out.println("getYear() returns wrong result : " + resultYear);
      }
    }
   System.out.println("OK-1032");
}
}