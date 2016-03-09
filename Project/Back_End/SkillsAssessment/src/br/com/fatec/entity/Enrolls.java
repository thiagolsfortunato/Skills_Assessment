package br.com.fatec.entity;

public class Enrolls {
	
	private Long codeEnrolls;
	private int year;
	private int period;
	private Long codeCourse;
	
	public Enrolls(){};
	
	public Enrolls(Long code, int year, int period, Long codeCourse) {
		this.codeEnrolls = code;
		this.year = year;
		this.period = period;
		this.setCodeCourse(codeCourse);
	}

	public Long getCodePeriod() {
		return codeEnrolls;
	}

	public void setCodePeriod(Long code) {
		this.codeEnrolls = code;
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
}
