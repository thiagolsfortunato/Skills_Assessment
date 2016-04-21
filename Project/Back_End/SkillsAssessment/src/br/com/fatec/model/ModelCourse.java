package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;
import br.com.fatec.dao.DaoCourse;
import br.com.fatec.dao.DaoInstitutionCourse;
import br.com.fatec.entity.Course;

public class ModelCourse {

	@SuppressWarnings("finally")
	public boolean insertCourse(Course course){
		boolean insert = false;
		try{
			insert = DaoCourse.insertCourse(course);
			insert = DaoInstitutionCourse.insertIntitutionCourse(course.getCodeInstitution(), course.getCodeCourse());
			return insert;
		}catch (SQLException e) {
			System.out.println("Will not it was possible to enter the Course");
		}finally {
			return insert;
		}		
	}

	public boolean updateCourse(Course course){
		try{
			return DaoCourse.updateCourse(course);
		}catch (SQLException e) {
			System.out.println("Will not it was possible to delete the Course");
			return false;
		}
	}

	public boolean deleteCourse(Long code){
		try{
			 return DaoCourse.deleteCourse(code);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("finally")
	public Course searchCourseByCode(Long code){
		Course course = null;
		try{
			course = DaoCourse.searchCourseById(code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a course");
		}finally{
			return course;
		}
	}

	@SuppressWarnings("finally")
	public List<Course> searchAllCourse(){
		List<Course> courses = null;
		try{
			courses = DaoCourse.searchAllCourse(); 
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a course");
		}finally{
			return courses;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Course> searchCoursesByInstitionId(Long codeInstitution){
		List<Course> courses = null;
		try{
			courses = DaoCourse.searchCoursesByInstitionId(codeInstitution); 
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a course");
		}finally{
			return courses;
		}
	}	
}
