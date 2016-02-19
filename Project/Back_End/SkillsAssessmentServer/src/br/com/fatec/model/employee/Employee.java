package br.com.fatec.model.employee;

import java.util.Date;

import br.com.fatec.model.User;

public class Employee extends User{
	
	public static final String TABLE = "FUNCIONARIO";
	public static final String COL_CODIGO = "FUN_CODIGO";
	public static final String COL_NOME = "FUN_NOME";
	public static final String COL_RG = "FUN_RG";
	public static final String COL_NASCIMENTO = "FUN_NASCIMENTO";
	public static final String COL_SEXO = "FUN_SEXO";
	public static final String COL_CEP = "FUN_CEP";
	public static final String COL_ENDERECO = "FUN_ENDERECO";
	public static final String COL_BAIRRO = "FUN_BAIRRO";
	public static final String COL_CIDADE = "FUN_CIDADE";
	public static final String COL_NUMERO = "FUN_NUMERO";
	public static final String COL_TELEFONE = "FUN_TELEFONE";
	public static final String COL_CELULAR = "FUN_CELULAR";
	public static final String COL_DATA_CADASTRO = "FUN_DATA_CADASTRO";
	public static final String COL_USUARIO = "USU_CODIGO";

	
	private Long number;
	private String name;
	private String cpf;
	private Date birthDay;
	private String cep;
	private String address;
	private String neighborhood;
	private String city;
	private String uf;
	private int numberHouse;
	private String complement;
	private String telephone;
	private String cellphone;
	private Date register;
	private Long user_register;
	
	public Employee(Long number, String name, String cpf, Date birthDay, String cep, String address,
			String neighborhood, String city, String uf, int numberHouse, String complement, String telephone,
			String cellphone, Date register, Long user_register) {
		super();
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
