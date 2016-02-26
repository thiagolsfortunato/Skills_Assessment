package br.com.fatec.model.student;

import java.util.Date;

import br.com.fatec.model.question.Competence;
import br.com.fatec.model.user.User;

public class Student extends User{
	
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

}
