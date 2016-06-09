package br.gov.sp.fatec.entity;

public class Competence {
	
	private Long code;
	private String type;
	private String registration_date;
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

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
}
