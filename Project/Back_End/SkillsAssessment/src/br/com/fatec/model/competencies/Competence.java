package br.com.fatec.model.competencies;

import java.util.Date;

public class Competence {
	
	private int number;
	private String description;
	private Date register;
	
	public Competence(int number, String description, Date register) {
		super();
		this.number = number;
		this.description = description;
		this.register = register;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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
}
