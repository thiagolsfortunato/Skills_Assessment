package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Student;
import br.com.fatec.entity.User;

public class DaoStudent {

	public static Student getStudentByCode(Long code) throws SQLException {
		Connection conn = null;
		PreparedStatement cmd = null;
		String query = "select STD_CODE,STD_NAME from STUDENT S inner join USER U on S.USR_CODE = U.USR_CODE WHERE s.usr_code = " + code + ";"; //join table with User to bring the rest of the information
		try{
			conn = ConnectionMySql.getConnection();
			cmd = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = cmd.executeQuery();
			return buildStudent(rs);
		
		} catch (Exception e){
			throw new RuntimeException(e);
		
		} finally {
			cmd.close();
			conn.close();
		}
		
	}

	public static List<Student> findAll() throws SQLException {
		 Connection conn = null;
		 PreparedStatement findAll = null;
		 String query = "SELECT * FROM student s INNER JOIN USER u WHERE s.usr_code = u.usr_code;";
		 try{
			 conn = ConnectionMySql.getConnection();
			 findAll = (PreparedStatement) conn.prepareStatement(query);
			 ResultSet rs = findAll.executeQuery();
			 return buildStudents(rs);
		 
		 } catch (Exception e){
			 throw new RuntimeException(e);
		 
		 } finally {
			 findAll.close();
			 conn.close();;
		 }
		
	}

	private static List<Student> buildStudents(ResultSet rs) throws SQLException{
		List<Student> students = Lists.newArrayList();
		while(rs.next()){
			students.add(buildStudent(rs)); //chama o método criar objeto
		}
		return students;
	}
	
	private static Student buildStudent(ResultSet rs) throws SQLException {
		Student retorno = new Student();
			rs.next();
			retorno.setName(rs.getString("STD_NAME"));
			retorno.setNumber(Long.parseLong(rs.getString("STD_CODE"))); // CONVERT TO LONG
			/*retorno.setName(rs.getString("STD_NAME"));
			retorno.setRa(rs.getString("STD_RA"));
			retorno.setCpf(rs.getString("STD_CPF"));
			//retorno.setBirthDay(new Date(rs.getString("STD_BIRTH"))); // CONVERTER TO DATE
			retorno.setCep(rs.getString("STD_CEP"));
			retorno.setAddress(rs.getString("STD_ADDRESS"));
			retorno.setNeighborhood(rs.getString("STD_NEIGHBORHOOD"));
			retorno.setCity(rs.getString("STD_CITY"));
			retorno.setUf(rs.getString("STD_UF"));
			retorno.setNumberHouse(Integer.parseInt(rs.getString("STD_NUMBER")));
			retorno.setComplement(rs.getString("STD_COMPLEMENT"));
			retorno.setTelephone(rs.getString("STD_TELEPHONE"));
			retorno.setCellphone(rs.getString("STD_CELLPHONE"));
			//retorno.setRegister(new Date(rs.getString("STD_REGISTRATION_DATE")));
			retorno.setUser_register(Long.parseLong(rs.getString("STD_USER_REGISTER")));
			retorno.setUserCode(Long.parseLong(rs.getString("STUDENT.USR_CODE")));
			retorno.setPassword(rs.getString("USR_PASSWORD"));
			retorno.setEmail(rs.getString("USR_USERNAME"));
			retorno.setSituation(Integer.parseInt(rs.getString("USR_SITUATION")));
			retorno.setVerification(Integer.parseInt(rs.getString("USR_VERIFIED")));
			retorno.setKindPerson(rs.getString("USR_KIND"));
			retorno.setToken(rs.getString("USR_TOKEN"));*/
		return retorno;
	} 
	
	public static boolean addStudent(Student student) {
		
		return false;
	}

}