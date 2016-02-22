package br.com.fatec.model.user;

public class User {
	
	private Integer userCode;
	private String userName;
	private String password;
	private Integer situation;
	private Integer verification;
	private String kindPerson;
	private String token;
	
	
	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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
