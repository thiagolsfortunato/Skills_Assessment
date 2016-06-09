package br.gov.sp.fatec.entity;

import java.util.ArrayList;
import java.util.List;

public class Fatec_Courses {
	
	private Long code;
	private String name;
	private List<Course> courses;
	
	public Fatec_Courses(){
		this.courses = new ArrayList<Course>();
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	

}
