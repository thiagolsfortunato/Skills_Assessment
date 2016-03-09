package br.com.fatec.model.period;

import br.com.fatec.model.course.Course;

public class Period extends Course{
	
	private Long code;
	private int year;
	private int period;
	
	public Period(){};
	
	public Period(Long code, int year, int period) {
		this.code = code;
		this.year = year;
		this.period = period;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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
}
