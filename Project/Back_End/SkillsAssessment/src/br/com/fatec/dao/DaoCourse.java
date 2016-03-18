package br.com.fatec.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Course;

public class DaoCourse {
	//FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean insertCourse(Course course) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "INSERT INTO COURSE (crs_name, crs_situation, crs_registration_date) VALUES (?,?,now());";
		boolean insert = false;
		try{
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,course.getName());
			connection.getStatement().setInt(2,course.getSituation());
			if(connection.executeSql()){
				insert = true;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			connection.close();
			return insert;
		}
	}
	//FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean deleteCourse(Long code) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "DELETE FROM COURSE WHERE CRS_CODE = ?;";
		boolean delete = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setLong(1, code);
			if (connection.executeSql()) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return delete;
		}
	}
	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean updateCourse(Course course) {
		ConnectionMySql connection =  new ConnectionMySql();
		String sql = "UPDATE COURSE SET CRS_NAME = ?, CRS_SITUATION = ?, CRS_REGISTRATION_DATE = now() WHERE CRS_CODE = ?;";
		boolean update = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1, course.getName());
			connection.getStatement().setInt(2, course.getSituation());
			connection.getStatement().setLong(3, course.getCodeCourse());
			if(connection.executeSql()){
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return update;
		}
	}	
	//FUNCIONANDO !
	@SuppressWarnings("finally")
	public static List<Course> searchAllCourse() {
		List<Course> listCourse = new ArrayList<>();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course;";
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(query));
			if (connection.executeQuery()) {
				do {
					Course course = new Course();
					course.setCodeCourse(Long.parseLong(connection.returnField("CRS_CODE")));
					course.setName(connection.returnField("CRS_NAME"));
					course.setSituation(Integer.parseInt(connection.returnField("CRS_SITUATION")));
					course.setRegistration_date(connection.returnField("CRS_REGISTRATION_DATE"));
					listCourse.add(course);
				} while (connection.nextRegister());
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return listCourse;
		}
	}
	
	@SuppressWarnings("finally")
	public static Course searchCourseById(Long code) {
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course where crs_code = ?;";
		Course course = new Course();
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(query));
			connection.getStatement().setLong(1, code);
			if(connection.executeQuery()){
				do{					
					course.setCodeCourse(Long.parseLong(connection.returnField("CRS_CODE")));
					course.setName(connection.returnField("CRS_NAME"));
					course.setSituation(Integer.parseInt(connection.returnField("CRS_SITUATION")));
					course.setRegistration_date(connection.returnField("CRS_REGISTRATION_DATE"));
				}while(connection.nextRegister());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return course;
		}
	}
}
