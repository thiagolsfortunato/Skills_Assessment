package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.course.Course;

public class DaoCourse {
	
 	
	public static List<Course> searchAllCourse() throws SQLException {
		List<Course> listCourse = new ArrayList<>();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from course;";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			Course course = new Course();
			course.setCode(Long.parseLong(rs.getString("CRS_CODE")));
			course.setName(rs.getString("CRS_NAME"));
			course.setSituation(Integer.parseInt(rs.getString("CRS_SITUATION")));			
			course.setRegistration_date(rs.getString("CRS_REGISTRATION_DATE"));
			
			listCourse.add(course);
		}
		
		rs.close();
		return listCourse;
	}
	
}
