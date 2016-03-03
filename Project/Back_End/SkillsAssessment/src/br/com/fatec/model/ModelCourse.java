package br.com.fatec.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.dao.DaoCourse;
import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.model.course.Course;
import br.com.fatec.model.employee.Employee;

public class ModelCourse {

	public boolean addCourse(Course course) {
		return false;
	}

	public Long updateCourse(Course comp, Long code) {
		return null;
	}

	public boolean deleteCourse(Long code) {
		return false;
	}

	public Course searchCourseByCode(Long code) {
		return null;
	}

	public List<Course> searchAllCourse() {
		try {
			List<Course> listCourse;
			listCourse = DaoCourse.searchAllCourse(); 
			return listCourse;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}	

}
