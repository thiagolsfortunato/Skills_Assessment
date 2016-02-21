package br.com.fatec.model.user;

public class User {
	
	public static final String TABLE = "USER";
	public static final String COL_CODE = "USR_CODE";
	public static final String COL_USERNAME = "USR_USERNAME";
	public static final String COL_PASSWORD = "USR_PASSWORD";
	public static final String COL_SITUATION = "USR_SITUATION";
	public static final String COL_VERIFIED = "USR_VERIFIED";
	public static final String COL_KIND = "USR_KIND";
	public static final String COL_TOKEN = "USR_TOKEN";
	
	private Integer userCode;
	private String userName;
	private String password;
	private Integer situation;
	private Integer verification;
	private String kindPerson;
	private String token;
	
	public User(){};
	
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
