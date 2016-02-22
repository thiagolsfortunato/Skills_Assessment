package br.com.fatec.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.employee.Employee;

public class DaoEmployee {

	public static Employee searchByCode(Integer code) throws SQLException {
		
		Employee employee = new Employee();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select EMP_NAME, EMP_RG from EMPLOYEE where USR_CODE = '" + code +"';"; //MUDEI
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		
		while (rs.next()) {
			employee.setName(rs.getString("EMP_NAME"));
			employee.setCpf(rs.getString("EMP_RG")); //ARRUMAR BANCO
		}
		rs.close();
		
		return employee;
	}
	
	public void searchForName() throws SQLException {
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
}
