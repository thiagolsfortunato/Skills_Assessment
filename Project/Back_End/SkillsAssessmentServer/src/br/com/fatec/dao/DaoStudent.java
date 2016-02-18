package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.student.Student;

public class DaoStudent {
	
	public static Student searchStudentByRa (String ra) throws SQLException{
		Student student = new Student();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select alu_nome from aluno where alu_ra = '" + ra +"';";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			student.setName(rs.getString("alu_codigo"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_ra"));
			student.setName(rs.getString("alu_cpf"));
			student.setName(rs.getString("alu_nascimento"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
			student.setName(rs.getString("alu_nome"));
		}
		rs.close();
		
		
		return student;
	}
	
	

}
