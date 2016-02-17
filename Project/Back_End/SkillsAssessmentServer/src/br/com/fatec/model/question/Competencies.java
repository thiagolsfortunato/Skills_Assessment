package br.com.fatec.model.question;

import java.util.Date;

public class Competencies {
	
	private String name;
	private Integer value;
	private Date register;
	private Integer weight;
	
	public Competencies(String name, Integer value, Date register, Integer weight) {
		this.name = name;
		this.value = value;
		this.register = register;
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
