package br.gov.sp.fatec.entity;


public class User {
	
	private Long userCode;
	private String name;
	private String courseStudent; //aluno
	private Integer yearStudent; //aluno
	private Integer periodStudent; //aluno
	private String userName;
	private String password;
	private String ra; //aluno
	private String type;
	private String registration_date;
	private Integer situation;
	private Integer verification; //aluno
	private Long instCode; //aluno
	private String token;
	
	//FUNCIONA PELO AMOR
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSituation() {
		return situation;
	}
	public void setSituation(Integer situation) {
		this.situation = situation;
	}
	public Integer getVerification() {
		return verification;
	}
	public void setVerification(Integer verification) {
		this.verification = verification;
	}
	public Long getInstCode() {
		return instCode;
	}
	public void setInstCode(Long instCode) {
		this.instCode = instCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getCourseStudent() {
		return courseStudent;
	}
	public void setCourseStudent(String courseStudent) {
		this.courseStudent = courseStudent;
	}
	public Integer getYearStudent() {
		return yearStudent;
	}
	public void setYearStudent(Integer yearStudent) {
		this.yearStudent = yearStudent;
	}
	public Integer getPeriodStudent() {
		return periodStudent;
	}
	public void setPeriodStudent(Integer periodStudent) {
		this.periodStudent = periodStudent;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}	
}
