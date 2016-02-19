package br.com.fatec.model.Competencies;

import java.util.Date;

public class Competence {
	
	public static final String TABLE = "COMPETENCIA";
	public static final String COL_CODIGO = "COM_CODIGO";
	public static final String COL_TIPO = "COM_TIPO";
	public static final String COL_DATA_CADASTRO = "COM_DATA_CADASTRO";
	
	
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
