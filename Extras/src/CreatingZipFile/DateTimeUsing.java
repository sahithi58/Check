package CreatingZipFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;

public class DateTimeUsing {
	
	private static long DateTimeCheck(){
	LocalDate endofCentury = LocalDate.of(2014, 01, 01);
	String a = "https://www.tellthebell.com/?Source=Email&cn=";
	String a1 = a.concat(String.valueOf(1111));
	System.out.println(a1);
	LocalDate now = LocalDate.now();
	Date date1 = new Date();
	Date date2 = new Date();
	DateTime date3= new DateTime(date2);
	long difference =  (date2.getTime()-date1.getTime());
	System.out.println(Math.abs(difference));
	System.out.println(date2.getTime());
	System.out.println(date3.getMillis());
	return difference;
	}
}
