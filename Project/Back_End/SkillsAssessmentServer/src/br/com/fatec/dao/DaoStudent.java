package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
			student.setNumber(Long.parseLong(rs.getString("alu_codigo"))); //CONVERT TO LONG
			student.setName(rs.getString("alu_nome"));
			student.setRa(rs.getString("alu_ra"));
			student.setCpf(rs.getString("alu_cpf"));
			student.setBirthDay(new Date(rs.getString("alu_nascimento"))); //CONVERTER TO DATE
			student.setCep(rs.getString("alu_cep"));
			student.setAddress(rs.getString("alu_endereco"));
			student.setNeighborhood(rs.getString("alu_bairro"));
			student.setCity(rs.getString("alu_cidade"));
			student.setUf(rs.getString("alu_uf"));
			student.setNumberHouse(Integer.parseInt(rs.getString("alu_casa")));
			student.setComplement(rs.getString("alu_complemento"));
			student.setTelephone(rs.getString("alu_telefone"));
			student.setCellphone(rs.getString("alu_celular"));
			student.setRegister(new Date(rs.getString("alu_registro")));
			student.setUser_register(Long.parseLong(rs.getString("alu_user_register")));
			student.getUser().setUserCode(Integer.parseInt(rs.getString("uso_codigo")));
			student.getUser().setUserName(rs.getString("uso_login"));
			student.getUser().setPassword(rs.getString("uso_senha"));
			student.getUser().setSituation(Integer.parseInt(rs.getString("uso_situacao")));
			student.getUser().setVerification(Integer.parseInt(rs.getString("uso_verificacao")));
			student.getUser().setKindPerson(rs.getString("uso_tipo"));
			student.getUser().setToken(rs.getString("uso_token"));
		}
		rs.close();
		
		
		return student;
	}
	
	

}
