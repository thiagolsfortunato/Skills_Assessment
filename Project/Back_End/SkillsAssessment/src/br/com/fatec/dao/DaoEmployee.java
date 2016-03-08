package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.employee.Employee;

public class DaoEmployee {

	// VERIFICAR SE ESTA CORRETO
	public static boolean addEmployee(Employee employee) throws SQLException {
		Connection conn = ConnectionMySql.getConnection();

		String sql = "insert into EMPLOYEE (emp_name, emp_cpf, emp_birth, emp_cep, emp_address, emp_neighborhood,"
				+ "emp_city, emp_number, emp_telephone, emp_cellphone, emp_registration_date) " + "values ("
				+ employee.getName() + "," + employee.getCpf() + "," + employee.getBirthDay() + "," + employee.getCep() + "," 
				+ employee.getAddress() + "," + employee.getNeighborhood() + "," + employee.getCity() + "," 
				+ employee.getNumberHouse() + "," + employee.getTelephone() + "," + employee.getCellphone() + "," 
				+ employee.getRegistration_date() + "," + employee.getUserCode() + ")"; //verificar como vai ser feito
		
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(sql);

		if (cmd.execute()) {
			cmd.close();
			return true;
		} else {
			cmd.close();
			return false;
		}
	}

	// VERIFICAR SE ESTA CORRETO
	public static boolean deleteEmployee(Long code) throws SQLException{		
		Connection conn = ConnectionMySql.getConnection();
		String sql = "delete from Employee where EMP_CODE = "+code+",";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(sql);
		
		if (cmd.execute()) {
			cmd.close();
			return true;
		} else {
			return false;
		}
	}

	//BUSCAR TODOS
	@SuppressWarnings("null")
	public static List<Employee> searchAll() throws SQLException {
		List<Employee> listEmployee = null;
		
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from EMPLOYEE ;";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		
		while (rs.next()) {
			Employee employee = new Employee();	
			employee.setCode(Long.parseLong("EMP_CODE"));
			employee.setName("EMP_NAME");
			employee.setCpf("EMP_CPF");
			employee.setBirthDay(Date.valueOf("EMP_BIRTH"));
			employee.setCep("EMP_CEP");
			employee.setAddress("EMP_ADDRESS");
			employee.setNeighborhood("EMP_NEIGHBORHOOD");
			employee.setNumberHouse(Integer.parseInt("EMP_NUMBER"));
			employee.setTelephone("EMP_TELEFONE");
			employee.setCellphone("EMP_CELLPHONE");
			employee.setRegistration_date(Date.valueOf("EMP_REGISTRATION_DATE"));
			employee.setUserCode(Long.parseLong("USR_CODE"));
			
			listEmployee.add(employee);
		}
		rs.close();
		return listEmployee;
	}
	
	//BUSCA POR CODE
	public static Employee searchByCode(Long code) throws SQLException {
		Employee employee = new Employee();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select EMP_NAME, EMP_CPF from EMPLOYEE where USR_CODE = " + code + ";"; 
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			employee.setName(rs.getString("EMP_NAME"));
			employee.setCpf(rs.getString("EMP_CPF")); 
		}
		rs.close();

		return employee;
	}

	//BUSCA POR NOME 
	public static void searchForName() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from EMPLOYEE ;";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("EMP_NAME"));
		}
		rs.close();
	}
	
	
	public static boolean update(Employee employee, Long code) throws SQLException{
		Connection conn = ConnectionMySql.getConnection();
		String sql = "update employee set emp_name='"+employee.getName()+"', emp_cpf='"+employee.getCpf()+"', emp_birth='"+employee.getBirthDay()+"', emp_cep='"+employee.getCep()+"', emp_address='"+employee.getAddress()+"', emp_neighborhood='"+employee.getNeighborhood()+"',"
				+ "emp_city='"+employee.getCity()+"', emp_number='"+employee.getNumberHouse()+"', emp_telephone='"+employee.getTelephone()+"', emp_cellphone'"+employee.getCellphone()+"', emp_registration_date='"+employee.getRegistration_date()+"' where code="+code+";";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(sql);
		if(cmd.execute()){
			return true;
		}else{
			return false;
		}
	}
	
}
