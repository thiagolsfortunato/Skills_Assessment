package br.com.fatec.entity;

import java.util.Date;

public class Competence {
	
	private Long code;
	private String type;
	private Date register;
	private int weight;
	private int situation;
	private Long altCode;
	private Long rscCode;
	
	
	public Long getRscCode() {
		return rscCode;
	}

	public void setRscCode(Long rscCode) {
		this.rscCode = rscCode;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAltCode() {
		return altCode;
	}

	public void setAltCode(Long altCode) {
		this.altCode = altCode;
	}

	public int getSituation() {
		return situation;
	}

	public void setSituation(int situation) {
		this.situation = situation;
	}
}
