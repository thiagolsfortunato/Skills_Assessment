package br.com.fatec.commons;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Utils {
	public SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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
	
	public static java.sql.Date convertSqlDateToUtilDate(java.util.Date date){
		if(date != null){
			return new java.sql.Date(date.getTime());
		}
		return null;
	}
}
