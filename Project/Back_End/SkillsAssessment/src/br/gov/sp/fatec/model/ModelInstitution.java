package br.gov.sp.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.gov.sp.fatec.connection.ConnectionFactory;
import br.gov.sp.fatec.dao.DaoInstitution;
import br.gov.sp.fatec.dao.DaoInstitutionCourse;
import br.gov.sp.fatec.dao.DaoUser;
import br.gov.sp.fatec.entity.Fatec_Courses;
import br.gov.sp.fatec.entity.Institution;
import br.gov.sp.fatec.entity.User;

public class ModelInstitution {
	
	private Connection conn;
	
	@SuppressWarnings("finally")
	public List<Fatec_Courses> searchAllInstitutionCourses(){
		List<Fatec_Courses> fatecCourses = null;
		try{
			conn = new ConnectionFactory().getConnection();
			fatecCourses = DaoInstitutionCourse.searchAllFatecCourses(conn);
		}finally{
			try{
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return fatecCourses;
		}
	}
	
	@SuppressWarnings("finally")
	public Institution searchInstitutionByCode(Long code){
		Institution institution = null;
		try{
			conn = new ConnectionFactory().getConnection();
			institution = DaoInstitution.searchInstitutionByCode(conn, code);
		}finally{
			try{
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return institution;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Institution> searchInstitutionByName(String name) {
		List<Institution> institutions = null;
		try{
			conn = new ConnectionFactory().getConnection();
			institutions = DaoInstitution.searchInstitutionByName(conn, name);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Institution> searchAllInstitution() {
		List<Institution> institutions = null;
		try{
			conn = new ConnectionFactory().getConnection();
			institutions = DaoInstitution.searchAllInstitution(conn);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean insertInstitution (Institution fatec, User adm) {
		boolean status = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			Long idFatec = DaoInstitution.insertInstitution(conn, fatec);
			Long idUser  = DaoUser.insertUser(conn, adm, idFatec);
			if( idFatec != null && idUser != null ){
				status = true;
			}
			if (status) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return status;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateInstitution (Institution fatec) throws SQLException{
		boolean status = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			status = DaoInstitution.updateInstitution(conn, fatec);
			if (status) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch (SQLException e) {
			conn.rollback();
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
	public boolean deleteInstitution (Long code) throws SQLException {
		boolean status = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			status = DaoInstitution.deleteInstitution(conn, code);
			if (status) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
		}
	}

}
