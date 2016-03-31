package br.com.fatec.entity;

public class Institution {
	
	private Long codeInstitution;
	private String company;
	private String cnpj;
	private String city;
	
	public Long getCode() {
		return codeInstitution;
	}
	public void setCode(Long code) {
		this.codeInstitution = code;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	
}
