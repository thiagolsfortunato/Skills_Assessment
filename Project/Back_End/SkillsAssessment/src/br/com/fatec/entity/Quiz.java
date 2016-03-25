package br.com.fatec.entity;

import java.sql.Time;
import java.util.Date;

public class Quiz {
	private Long code;
	private Long user;
	private Long question;
	private Long answer;
	private Date date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
}
