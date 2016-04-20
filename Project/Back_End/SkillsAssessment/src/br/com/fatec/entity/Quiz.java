package br.com.fatec.entity;

import java.sql.Time;
import java.util.Date;

public class Quiz {
	private Long code;
	private Long user;
	private Long question;
	private Long answer;
	private String date_register;
	private String duration;
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public Long getQuestion() {
		return question;
	}
	public void setQuestion(Long question) {
		this.question = question;
	}
	public Long getAnswer() {
		return answer;
	}
	public void setAnswer(Long answer) {
		this.answer = answer;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDate_register() {
		return date_register;
	}
	public void setDate_register(String date_register) {
		this.date_register = date_register;
	}
}
