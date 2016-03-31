package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;
import br.com.fatec.dao.DaoCourse;
import br.com.fatec.entity.Course;

public class ModelCourse {

	@SuppressWarnings("finally")
	public boolean insertCourse(Course course) {
		boolean insert = false;
		try{
			insert = DaoCourse.insertCourse(course);
			return insert;
		}catch (SQLException e) {
			System.out.println("Will not it was possible to enter the Course");
		}finally {
			return insert;
		}		
	}

	@SuppressWarnings("finally")
	public boolean updateCourse(Course course) {
		boolean update = false;
		try{
			update = DaoCourse.updateCourse(course);
		}catch (SQLException e) {
			System.out.println("Will not it was possible to delete the Course");
		}finally {
			return update;
		}
	}

	public boolean deleteCourse(Long code) throws SQLException {
		return DaoCourse.deleteCourse(code);
	}

	public Course searchCourseByCode(Long code) throws SQLException{
		return DaoCourse.searchCourseById(code);
	}

	public List<Course> searchAllCourse() throws SQLException{
		return	DaoCourse.searchAllCourse(); 
	}	

}
