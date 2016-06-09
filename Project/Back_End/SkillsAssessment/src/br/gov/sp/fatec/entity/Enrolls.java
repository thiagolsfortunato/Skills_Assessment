package br.gov.sp.fatec.entity;

import java.util.Calendar;

public class Enrolls {
	
	private Long codeEnrolls;
	private int year;
	private int period;
	private Long codeCourse;
	private Long codeUser;
	
	public Enrolls(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;			
		this.year = year;
		this.period = month <= 6 ? 1 : 2 ;		
	}
	
	public Enrolls(Long codeEnrolls, int year, int period, Long codeCourse, Long codeUser) {
		this.codeEnrolls = codeEnrolls;
		this.year = year;
		this.period = period;
		this.codeUser = codeUser;
		this.codeCourse = codeCourse;
	}

	public Long getCodeEnrolls() {
		return codeEnrolls;
	}

	public void setCodeEnrolls(Long codeEnrolls) {
		this.codeEnrolls = codeEnrolls;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Long getCodeCourse() {
		return codeCourse;
	}

	public void setCodeCourse(Long codeCourse) {
		this.codeCourse = codeCourse;
	}

	public Long getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(Long codeUser) {
		this.codeUser = codeUser;
	}	
}
