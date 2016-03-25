package commons;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Utils {
	private Time hour;

	public void setHour(String hour) {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			this.hour = new java.sql.Time(format.parse(hour).getTime());
		} catch (Exception e) {
			System.out.println("an error occurred when converting the date: "+e);
			throw new RuntimeException(e.getMessage());
		}
	}
}
