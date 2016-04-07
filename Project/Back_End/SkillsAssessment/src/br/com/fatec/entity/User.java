package br.com.fatec.entity;

import java.util.Date;

public class User {
	
	private Long userCode;
	private String name;
	private String userName;
	private String password;
	private String ra;
	private String type;
	private Date register;
	private Integer situation;
	private Integer verification;
	private Integer instCode;
	private String token;
	private Integer unansweredQuestions;
	private Integer questionAmount;
	
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
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
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
	public Integer getInstCode() {
		return instCode;
	}
	public void setInstCode(Integer instCode) {
		this.instCode = instCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getUnansweredQuestions() {
		return unansweredQuestions;
	}
	public void setUnansweredQuestions(Integer unansweredQuestions) {
		this.unansweredQuestions = unansweredQuestions;
	}
	public Integer getQuestionAmount() {
		return questionAmount;
	}
	public void setQuestionAmount(Integer questionAmount) {
		this.questionAmount = questionAmount;
	}	
}
