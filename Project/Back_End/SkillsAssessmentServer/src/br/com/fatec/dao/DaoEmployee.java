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
		String query = "select fun_nome, fun_rg from funcionario where usu_codigo = '" + code +"';"; //MUDEI
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		
		while (rs.next()) {
			employee.setName(rs.getString("fun_nome"));
			employee.setCpf(rs.getString("fun_rg")); //ARRUMAR BANCO
		}
		rs.close();
		
		return employee;
	}
	
	public void searchForName() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from funcionario;";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("fun_nome"));
		}
		rs.close();
	}
}
