package br.com.fatec.entity;

import java.util.Date;

public class Course {
	
	private Long codeCourse;
	private String name;
	private int situation;
	private Date registration_date;
	
	public Course(){};	
	
	public Course(Long code, String name, int situation, Date registration_date) {
		super();
		this.codeCourse = code;
		this.name = name;
		this.situation = situation;
		this.registration_date = registration_date;
	}
	
	public Long getCodeCourse() {
		return codeCourse;
	}
	public void setCodeCourse(Long code) {
		this.codeCourse = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSituation() {
		return situation;
	}
	public void setSituation(int situation) {
		this.situation = situation;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}	
}
