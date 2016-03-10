package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Student;

public class DaoStudent {

	public static Student getStudentById(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		 //join table with User to bring the rest of the information
		String query = "SELECT * FROM student s INNER JOIN "
						+ "user u ON s.usr_code = u.usr_code WHERE s.std_code = " + code + ";";
		Student student = new Student();
		try{
			conn.conect();
			
			if(conn.executeQuery(query)){
				student = buildStudent(conn.returnRegister());
			}
			return student;
		} catch (Exception e){
			throw new RuntimeException(e);
		
		} finally {
			conn.returnRegister().close();
			conn.getPreparedStatement().close();
			conn.close();
		}
		
	}

	public static List<Student> findAll() throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		String query = "SELECT * FROM student s INNER JOIN "
		 				+ "USER u WHERE s.usr_code = u.usr_code;";// ORDER BY std_code DESC
		try{
			 conn.conect();
			 if(conn.executeQuery(query)){
				 return buildStudents(conn);
			 }
		 } catch (Exception e){
			 throw new RuntimeException(e);
		 
		 } finally {
			 conn.returnRegister().close();
			 conn.getPreparedStatement().close();
			 conn.close();;
		 }
		return null;
	}

	private static List<Student> buildStudents(ConnectionMySql conn) throws SQLException{
		List<Student> students = Lists.newArrayList();
		do{					
				students.add( buildStudent(conn.returnRegister()) );
		}while(conn.nextRegister());
		return students;
	}
	
	private static Student buildStudent(ResultSet rs) throws SQLException {
		Student student = new Student();
			student.setIdStudent( Long.parseLong(rs.getString("STD_CODE")) );
			student.setName( rs.getString("STD_NAME") );
			
		return student;
	} 
	
	public static boolean addStudent(Student student) {
		
		return false;
	}

}