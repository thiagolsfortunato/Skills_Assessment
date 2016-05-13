package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.entity.Course;

public class DaoCourse {
	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean insertCourse(Connection conn, Course course) throws SQLException {
		String sql = "INSERT INTO COURSE (crs_name, crs_situation, crs_registration_date) VALUES (?,?,date_format(now(), '%Y-%m-%d'));";
		boolean insert = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getSituation());
			if (stmt.executeUpdate() != 0) {
				insert = true;
			}
			stmt.close();
		} finally {
			return insert;
		}
	}

	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean deleteCourse(Connection conn, Long code) throws SQLException {
		boolean delete = false;
		boolean deleteIstCrs = false;
		boolean deleteCrs = false;
		try {
			String sql1 = "DELETE FROM ist_crs WHERE crs_cod = ?";
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setLong(1, code);
			if (stmt1.executeUpdate() != 0) {
				deleteIstCrs = true;
			}
			stmt1.close();
			
			String sql2 = "DELETE FROM course WHERE crs_code = ?";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setLong(1, code);
			if (stmt2.executeUpdate() == 0) {
				deleteCrs = true;
			}
			stmt2.close();
			
			if (deleteIstCrs && deleteCrs){
				delete = true;
			}
		} finally {
			return delete;
		}
	}

	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static boolean updateCourse(Connection conn, Course course) throws SQLException {
		String sql = "UPDATE COURSE SET CRS_NAME = ?, CRS_SITUATION = ?, CRS_REGISTRATION_DATE = date_format(now(), '%Y-%m-%d') WHERE CRS_CODE = ?;";
		boolean update = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getSituation());
			stmt.setLong(3, course.getCodeCourse());
			if (stmt.executeUpdate() != 0) {
				update = true;
			}
			stmt.close();
		} finally {
			return update;
		}
	}

	// FUNCIONANDO !
	@SuppressWarnings("finally")
	public static List<Course> searchAllCourse(Connection conn) throws SQLException {
		List<Course> listCourse = new ArrayList<>();
		String query = "select CRS_CODE, CRS_NAME, CRS_SITUATION,  DATE_FORMAT(CRS_REGISTRATION_DATE, '%d-%m-%Y') as CRS_REGISTRATION_DATE from course;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					Course course = new Course();
					course.setCodeCourse(rs.getLong("CRS_CODE"));
					course.setName(rs.getString("CRS_NAME"));
					course.setSituation(rs.getShort("CRS_SITUATION"));
					course.setRegistration_date(rs.getString("CRS_REGISTRATION_DATE"));
					listCourse.add(course);
				} while (rs.next());
			}
			rs.close();
			stmt.close();
		} finally {
			return listCourse;
		}
	}

	@SuppressWarnings("finally")
	public static Course searchCourseById(Connection conn, Long code) throws SQLException {
		String query = "select CRS_CODE, CRS_NAME, CRS_SITUATION,  DATE_FORMAT(CRS_REGISTRATION_DATE, '%d-%m-%Y') as CRS_REGISTRATION_DATE from course where crs_code = ?;";
		Course course = new Course();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, code);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					course.setCodeCourse(rs.getLong("CRS_CODE"));
					course.setName(rs.getString("CRS_NAME"));
					course.setSituation(rs.getShort("CRS_SITUATION"));
					course.setRegistration_date(rs.getString("CRS_REGISTRATION_DATE"));
				} while (rs.next());
			}
			rs.close();
			stmt.close();
		} finally {
			return course;
		}
	}

	@SuppressWarnings("finally")
	public static List<Course> searchCoursesByInstitionId(Connection conn, Long code) throws SQLException {
		List<Course> listCourse = new ArrayList<>();
		try {
			String query = "SELECT COURSE.CRS_CODE, CRS_NAME FROM COURSE JOIN IST_CRS ON (COURSE.CRS_CODE = IST_CRS.CRS_CODE) WHERE IST_CRS.ITC_CODE = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, code);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				do {
					Course course = new Course();
					course.setCodeCourse(rs.getLong("CRS_CODE"));
					course.setName(rs.getString("CRS_NAME"));
					listCourse.add(course);
				} while (rs.next());
			}
			rs.close();
			stmt.close();
		} finally {
			return listCourse;
		}
	}
}
