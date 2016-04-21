package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Course;

public class DaoCourse {
	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean insertCourse(Course course) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "INSERT INTO COURSE (crs_name, crs_situation, crs_registration_date) VALUES (?,?,date_format(now(), '%Y-%m-%d'));";
		boolean insert = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1, course.getName());
			connection.getStatement().setInt(2, course.getSituation());
			if (connection.executeSql()) {
				insert = true;
			}
		} finally {
			connection.close();
			return insert;
		}
	}

	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean deleteCourse(Long code) throws SQLException {
		ConnectionMySql connection1 = new ConnectionMySql();
		ConnectionMySql connection2 = new ConnectionMySql();
		boolean delete = false;
		try {
			String sql1 = "DELETE FROM IST_CRS WHERE CRS_COD = ?";
			connection1.conect();
			connection1.setStatement(connection1.getConnection().prepareStatement(sql1));
			connection1.getStatement().setLong(1, code);
			if (connection1.executeSql()) {
				delete = true;
			}

			String sql2 = "DELETE FROM COURSE WHERE CRS_CODE = ?";
			connection2.conect();
			connection2.setStatement(connection1.getConnection().prepareStatement(sql2));
			connection2.getStatement().setLong(1, code);
			if (connection2.executeSql()) {
				delete = true;
			}
		} finally {
			connection1.close();
			connection2.close();
			return delete;
		}
	}

	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean updateCourse(Course course) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "UPDATE COURSE SET CRS_NAME = ?, CRS_SITUATION = ?, CRS_REGISTRATION_DATE = date_format(now(), '%Y-%m-%d') WHERE CRS_CODE = ?;";
		boolean update = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1, course.getName());
			connection.getStatement().setInt(2, course.getSituation());
			connection.getStatement().setLong(3, course.getCodeCourse());
			if (connection.executeSql()) {
				update = true;
			}
		} finally {
			connection.close();
			return update;
		}
	}

	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static List<Course> searchAllCourse() throws SQLException {
		List<Course> listCourse = new ArrayList<>();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select CRS_CODE, CRS_NAME, CRS_SITUATION,  DATE_FORMAT(CRS_REGISTRATION_DATE, '%d-%m-%Y') as CRS_REGISTRATION_DATE from course;";
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
		} finally {
			connection.close();
			return listCourse;
		}
	}

	@SuppressWarnings("finally")
	public static Course searchCourseById(Long code) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select CRS_CODE, CRS_NAME, CRS_SITUATION,  DATE_FORMAT(CRS_REGISTRATION_DATE, '%d-%m-%Y') as CRS_REGISTRATION_DATE from course where crs_code = ?;";
		Course course = new Course();
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(query));
			connection.getStatement().setLong(1, code);
			if (connection.executeQuery()) {
				do {
					course.setCodeCourse(Long.parseLong(connection.returnField("CRS_CODE")));
					course.setName(connection.returnField("CRS_NAME"));
					course.setSituation(Integer.parseInt(connection.returnField("CRS_SITUATION")));
					course.setRegistration_date(connection.returnField("CRS_REGISTRATION_DATE"));
				} while (connection.nextRegister());
			}
		} finally {
			connection.close();
			return course;
		}
	}

	@SuppressWarnings("finally")
	public static List<Course> searchCoursesByInstitionId(Long code) throws SQLException {
		List<Course> listCourse = new ArrayList<>();
		ConnectionMySql connection = new ConnectionMySql();
		try {
			String query = "SELECT COURSE.CRS_CODE, CRS_NAME FROM COURSE JOIN IST_CRS ON (COURSE.CRS_CODE = IST_CRS.CRS_CODE) WHERE IST_CRS.ITC_CODE = ?";
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(query));
			connection.getStatement().setLong(1, code);
			if(connection.executeQuery()){
				do {
					Course course = new Course();
					course.setCodeCourse(Long.parseLong(connection.returnField("CRS_CODE")));
					course.setName(connection.returnField("CRS_NAME"));
					listCourse.add(course);
				} while (connection.nextRegister());
			}
		} finally {
			connection.close();
			return listCourse;
		}
	}
}
