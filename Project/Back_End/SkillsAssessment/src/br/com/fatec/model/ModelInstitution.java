package br.com.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoInstitution;
import br.com.fatec.entity.Institution;

public class ModelInstitution {
	
	private Connection conn;
	
	@SuppressWarnings("finally")
	public Institution searchInstitutionByCode(Long code){
		Institution institution = null;
		try{
			conn = new ConnectionFactory().getConnection();
			institution = DaoInstitution.searchInstitutionByCode(conn, code);
		}catch(SQLException e){
			e.printStackTrace();
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
		}catch(SQLException e){
			e.printStackTrace();
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
		} catch(SQLException e){
			e.printStackTrace();
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
	public boolean insertInstitution (Institution fatec) {
		boolean status = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			status = DaoInstitution.insertInstitution(conn, fatec);
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
