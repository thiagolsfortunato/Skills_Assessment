package br.com.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoCourse;
import br.com.fatec.dao.DaoInstitutionCourse;
import br.com.fatec.entity.Course;

public class ModelCourse {
	
	private Connection conn;

	@SuppressWarnings("finally")
	public boolean insertCourse(Course course){
		boolean status = false;
		Long idCurso = null;
		boolean institution = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			idCurso = DaoCourse.insertCourse(conn, course);
			institution = DaoInstitutionCourse.insertIntitutionCourse(conn, course.getCodeInstitution(), idCurso);
			if ( (idCurso!=null) && institution){
				conn.commit();
				status = true;
			}else{
				conn.rollback();
			}
		}catch (SQLException e) {
			conn.rollback();
			System.out.println("Will not it was possible to enter the Course");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
		}		
	}

	@SuppressWarnings("finally")
	public boolean updateCourse(Course course){
		boolean status = false;
		boolean curso = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			curso = DaoCourse.updateCourse(conn, course);
			if (curso){
				conn.commit();
				status = true;
			}else{
				conn.rollback();
			}
		}catch (SQLException e) {
			conn.rollback();
			System.out.println("Will not it was possible to delete the Course");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteCourse(Long idCourse, Long idFatec){
		boolean status = false;
		boolean curso = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			curso = DaoCourse.deleteCourse(conn, idCourse, idFatec);
			if (curso){
				conn.commit();
				status = true;
			}else{
				conn.rollback();
			}
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
		}
	}

	@SuppressWarnings("finally")
	public Course searchCourseByCode(Long code){
		Course course = null;
		try{
			conn = new ConnectionFactory().getConnection();
			course = DaoCourse.searchCourseById(conn, code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a course");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return course;
		}
	}

	@SuppressWarnings("finally")
	public List<Course> searchAllCourse(){ 
		List<Course> courses = null;
		try{
			conn = new ConnectionFactory().getConnection();
			courses = DaoCourse.searchAllCourse(conn); 
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a course");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return courses;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Course> searchCoursesByInstitionId(Long codeInstitution){
		List<Course> courses = null;
		try{
			conn = new ConnectionFactory().getConnection();
			courses = DaoCourse.searchCoursesByInstitionId(conn, codeInstitution); 
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a course");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return courses;
		}
	}	
}
