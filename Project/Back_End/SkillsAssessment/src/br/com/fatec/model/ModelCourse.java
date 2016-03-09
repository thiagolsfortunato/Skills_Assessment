package br.com.fatec.model;

import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.dao.DaoCourse;
import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.model.course.Course;
import br.com.fatec.model.employee.Employee;

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

	public Course searchCourseByCode(Long code) {
		return DaoCourse.searchCourseById(code);
	}

	public List<Course> searchAllCourse() {
		return	DaoCourse.searchAllCourse(); 
	}	

}
