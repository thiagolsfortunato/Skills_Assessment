package br.com.fatec.model.period;

import br.com.fatec.model.course.Course;

public class Period extends Course{
	
	private Long codePeriod;
	private int year;
	private int period;
	
	public Period(){};
	
	public Period(Long code, int year, int period) {
		this.codePeriod = code;
		this.year = year;
		this.period = period;
	}

	public Long getCodePeriod() {
		return codePeriod;
	}

	public void setCodePeriod(Long code) {
		this.codePeriod = code;
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
