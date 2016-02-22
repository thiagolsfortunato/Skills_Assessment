package br.com.fatec.model.employee;

import java.util.Date;

import br.com.fatec.model.user.User;

public class Employee extends User{
	
	private Long number;
	private String name;
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
	private Date registration_date;
	private User user;
	
	public Employee(){}

	public Employee(Long number, String name, String cpf, Date birthDay, String cep, String address,
			String neighborhood, String city, String uf, Integer numberHouse, String complement, String telephone,
			String cellphone, Date registration_date, User user) {
		this.number = number;
		this.name = name;
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
		this.registration_date = registration_date;
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

	public Integer getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(Integer numberHouse) {
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

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	};
	
	
}
