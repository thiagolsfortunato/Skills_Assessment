package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.dao.DaoCourse;
import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.entity.Course;
import br.com.fatec.entity.Employee;

public class ModelCourse {

	public boolean insertCourse(Course course) {
		return DaoCourse.insertCourse(course);
	}

	public boolean updateCourse(Course course) {
		return DaoCourse.updateCourse(course);
	}

	public boolean deleteCourse(Long code) {
		return DaoCourse.deleteCourse(code);
	}

	public Course searchCourseByCode(Long code) throws SQLException {
		return DaoCourse.searchCourseById(code);
	}

	public List<Course> searchAllCourse() throws SQLException {
		return	DaoCourse.searchAllCourse(); 
	}	

}
