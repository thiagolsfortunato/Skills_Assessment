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

	public static boolean deleteCourse(Long code) throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String sql = "DELETE FROM COURSE WHERE CRS_CODE = " + code + ";";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(sql);

		if (cmd.execute()) {
			cmd.close();
			return true;
		} else {
			cmd.close();
			return false;
		}
	}

	public static List<Course> searchAllCourse() throws SQLException {
		List<Course> listCourse = new ArrayList<>();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course;";
		connection.conect();
		if (connection.executeQuery(query)) {
			do {
				Course course = new Course();
				course.setCode(Long.parseLong(connection.returnField("CRS_CODE")));
				course.setName(connection.returnField("CRS_NAME"));
				course.setSituation(Integer.parseInt(connection.returnField("CRS_SITUATION")));
				course.setRegistration_date(connection.returnField("CRS_REGISTRATION_DATE"));

				listCourse.add(course);
			} while (connection.nextRegister());
			connection.close();
			return listCourse;
		}else{
			connection.close();
			return null;
		}
	}

}
