package br.gov.sp.fatec.entity;

public class Course {
	
	private Long codeCourse;
	private String name;
	private int situation;
	private String registration_date;
	private Long codeInstitution;
	
	public Course(){};	
	
	public Course(Long code, String name, int situation, String registration_date) {
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
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public Long getCodeInstitution() {
		return codeInstitution;
	}

	public void setCodeInstitution(Long codeInstitution) {
		this.codeInstitution = codeInstitution;
	}	
}
