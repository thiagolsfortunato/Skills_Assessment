package br.com.fatec.model.competencies;

import java.util.Date;

public class Competence {
	
	public static final String TABLE = "COMPETENCE";
	public static final String COL_CODE = "COM_CODE";
	public static final String COL_KIND = "COM_KIND";
	public static final String COL_REGISTRATION_DATE = "COM_REGISTRATION_DATE";
	
	
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
