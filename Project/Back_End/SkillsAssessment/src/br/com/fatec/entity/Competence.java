package br.com.fatec.entity;

import java.util.Date;

public class Competence {
	
	private Long number;
	private String description;
	private Date register;
	private int weight;
	
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
