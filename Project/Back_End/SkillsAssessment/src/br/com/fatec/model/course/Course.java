package br.com.fatec.model.course;

import java.sql.Date;

public class Course {
	
	private Long code;
	private String name;
	private int situation;
	private String registration_date;
	
	public Course(){};	
	
	public Course(Long code, String name, int situation, String registration_date) {
		super();
		this.code = code;
		this.name = name;
		this.situation = situation;
		this.registration_date = registration_date;
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
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
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}	
}
