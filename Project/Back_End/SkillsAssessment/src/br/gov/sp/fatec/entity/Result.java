package br.gov.sp.fatec.entity;

import java.util.List;

public class Result {
	
	private Long userCode;
	private String name;
	private String ra;
	private Integer period;
	private Integer year;
	private String course;
	private String institution;
	private List<Competence> competencies;
	private String comments;
	
	public Long getUserCode() {
		return userCode;
	}
	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public List<Competence> getCompetencies() {
		return competencies;
	}
	public void setCompetencies(List<Competence> competencies) {
		this.competencies = competencies;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
