package br.com.fatec.model.student;

import java.util.Date;

import br.com.fatec.model.competencies.Competence;
import br.com.fatec.model.user.User;

public class Student extends User{
	
	public static final String TABLE = "STUDENT";
	public static final String COL_CODE = "STD_CODE";
	public static final String COL_NAME = "STD_NAME";
	public static final String COL_RA = "STD_RA";
	public static final String COL_CPF = "STD_CPF";
	public static final String COL_BIRTH = "STD_BIRTH";
	public static final String COL_CEP = "STD_CEP";
	public static final String COL_ADDRESS = "STD_ADDRESS";
	public static final String COL_NEIGHBORHOOD = "STD_NEIGHBORHOOD";
	public static final String COL_CITY = "STD_CITY";
	public static final String COL_UF = "STD_UF";
	public static final String COL_NUMBER = "STD_NUMBER";
	public static final String COL_COMPLEMENT = "STD_COMPLEMENT";
	public static final String COL_TELEPHONE = "STD_TELEPHONE";
	public static final String COL_CELLPHONE = "STD_CELLPHONE";
	public static final String COL_REGISTRATION_DATE = "STD_REGISTRATION_DATE";
	public static final String COL_USER_REGISTER = "STD_USER_REGISTER";
	public static final String COL_USER_CODE = "USU_CODE";
		
	private Long number;
	private String name;
	private String ra;
	private String cpf;
	private Date birthDay;
	private String cep;
	private String address;
	private String neighborhood;
	private String city;
	private String uf;
	private Integer numberHouse;
	private String complement;
	private String telephone;
	private String cellphone;
	private Date register;
	private Long user_register;
	private User user;
	
	public Student(){};
		
	public Student(Long number, String name, String ra, String cpf, Date birthDay, String cep, String address,
			String neighborhood, String city, String uf, int numberHouse, String complement, String telephone,
			String cellphone, Date register, Long user_register, User user) {

		this.number = number;
		this.name = name;
		this.ra = ra;
		this.cpf = cpf;
		this.birthDay = birthDay;
		this.cep = cep;
		this.address = address;
		this.neighborhood = neighborhood;
		this.city = city;
		this.uf = uf;
		this.numberHouse = numberHouse;
		this.complement = complement;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.register = register;
		this.user_register = user_register;
		this.user = user;
	}


	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(int numberHouse) {
		this.numberHouse = numberHouse;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
	}

	public Long getUser_register() {
		return user_register;
	}

	public void setUser_register(Long user_register) {
		this.user_register = user_register;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
