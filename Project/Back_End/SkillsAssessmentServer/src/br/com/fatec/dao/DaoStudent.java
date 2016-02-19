package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.User;
import br.com.fatec.model.student.Student;

public class DaoStudent {
	
	@SuppressWarnings("deprecation")
	public static Student searchStudentByRa (String ra) throws SQLException{
		Student student = new Student();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select alu_nome from aluno where alu_ra = '" + ra +"';";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			student.setNumber(Long.parseLong(rs.getString(Student.COL_CODIGO))); //CONVERT TO LONG
			student.setName(rs.getString(Student.COL_NOME));
			student.setRa(rs.getString(Student.COL_RA));
			student.setCpf(rs.getString(Student.COL_CPF));
			student.setBirthDay(new Date(rs.getString(Student.COL_NASCIMENTO))); //CONVERTER TO DATE
			student.setCep(rs.getString(Student.COL_CEP));
			student.setAddress(rs.getString(Student.COL_ENDERECO));
			student.setNeighborhood(rs.getString(Student.COL_BAIRRO));
			student.setCity(rs.getString(Student.COL_CIDADE));
			student.setUf(rs.getString(Student.COL_UF));
			student.setNumberHouse(Integer.parseInt(rs.getString(Student.COL_NUMERO)));
			student.setComplement(rs.getString(Student.COL_COMPLEMENTO));
			student.setTelephone(rs.getString(Student.COL_TELEFONE));
			student.setCellphone(rs.getString(Student.COL_CELULAR));
			student.setRegister(new Date(rs.getString(Student.COL_DATA_CADASTRO)));
			student.setUser_register(Long.parseLong(rs.getString(Student.COL_USUARIO_CADASTRO)));
			student.getUser().setUserCode(Integer.parseInt(rs.getString(Student.COL_USUARIO_CODIGO)));
			student.getUser().setUserName(rs.getString(User.COL_LOGIN));
			student.getUser().setPassword(rs.getString(User.COL_SENHA));
			student.getUser().setSituation(Integer.parseInt(rs.getString(User.COL_SITUACAO)));
			student.getUser().setVerification(Integer.parseInt(rs.getString(User.COL_VERIFICACAO)));
			student.getUser().setKindPerson(rs.getString(User.COL_TIPO));
			student.getUser().setToken(rs.getString(User.COL_TOKEN));
		}
		rs.close();	
		
		return student;
	}
	
	

}
