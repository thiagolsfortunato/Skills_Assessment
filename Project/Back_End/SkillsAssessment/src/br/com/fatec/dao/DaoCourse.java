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

	@SuppressWarnings("finally")
	public static boolean insertCourse(Course course){
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "INSER INTO COURSE (crs_name, crs_situation, crs_registration_date) VALUES ("+ course.getName() +","+ 																			   course.getSituation() +","+ 
																								   course.getRegistration_date() +");";
		boolean insert = false;
		try{
			connection.conect();
			if(connection.executeSql(sql)){
				insert = true;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			connection.close();
			return insert;
		}
	}
		
	@SuppressWarnings("finally")
	public static boolean deleteCourse(Long code) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "DELETE FROM COURSE WHERE CRS_CODE = " + code + ";";
		boolean delete = false;
		try {
			connection.conect();
			if (connection.executeSql(sql)) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return delete;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateCourse(Course course){
		ConnectionMySql connection =  new ConnectionMySql();
		String sql = "UPDATE COURSES SET crs_name = "+ course.getName() +", "
								 + "crs_situation = "+ course.getSituation()+", "
						 + "crs_registration_date = "+ course.getRegistration_date() +" "
						 		+ "where crs_code = "+ course.getCodeCourse() +";";
		boolean update = false;
		try {
			connection.conect();
			if(connection.executeSql(sql)){
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return update;
		}
	}	

	@SuppressWarnings("finally")
	public static List<Course> searchAllCourse() throws SQLException {
		List<Course> listCourse = null;
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course;";
		try {
			connection.conect();
			if (connection.executeQuery(query)) {
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
			connection.returnRegister().close();
			connection.close();
			return listCourse;
		}
	}
	
	@SuppressWarnings("finally")
	public static Course searchCourseById(Long code) throws SQLException{
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course where crs_code = "+ code +";";
		Course course = new Course();
		try {
			connection.conect();
			if(connection.executeQuery(query)){
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
			connection.returnRegister().close();
			connection.close();
			return course;
		}
	}

}
