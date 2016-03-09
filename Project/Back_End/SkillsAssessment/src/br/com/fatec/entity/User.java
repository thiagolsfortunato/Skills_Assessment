package br.com.fatec.entity;

public class User {
	
	private Long userCode;
	private String email;
	private String password;
	private Integer situation;
	private Integer verification;
	private String kindPerson;
	private String token;
	
	
	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSituation() {
		return situation;
	}

	public void setSituation(int situation) {
		this.situation = situation;
	}

	public int getVerification() {
		return verification;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}


	public String getKindPerson() {
		return kindPerson;
	}

	public void setKindPerson(String kindPerson) {
		this.kindPerson = kindPerson.toLowerCase();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
