/*package br.com.fatec.model;

import br.com.fatec.model.question.Competence;
import br.com.fatec.model.question.Question;

public class ModelCompetencies {
	
	public boolean addCompetence(Competence comp){
		return false;
	}
	
	
	public Competence searchCompetenceByCode(Long code) {
		return null;
	}
	
	
	public Long updateCompetence(Competence comp, Long code){
		return null;
	}
}*/

package br.gov.sp.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.gov.sp.fatec.connection.ConnectionFactory;
import br.gov.sp.fatec.dao.DaoCompetencies;
import br.gov.sp.fatec.entity.Competence;

public class ModelCompetencies {

	private Connection conn;
	
	// Add competence
	@SuppressWarnings("finally")
	public boolean insertCompetence(Competence competence) {
		boolean status = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			status = DaoCompetencies.insertCompetence(conn, competence);
			if (status ){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	// Delete Competence
	@SuppressWarnings("finally")
	public boolean deleteCompetence(Long code) {
		boolean status = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			status = DaoCompetencies.deleteCompetence(conn, code);
			if (status){
				conn.commit();
			}
			else{
				conn.rollback();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	// Update Competence
	@SuppressWarnings("finally")
	public boolean updateCompetence(Competence competence) {
		boolean status = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			status = DaoCompetencies.updateCompetence(conn, competence);
			if (status){
				conn.commit();
			}
			else{
				conn.rollback();				
			}
		}catch(SQLException e){
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

	// Search By Code
	@SuppressWarnings("finally")
	public Competence searchCompetenceByCode(Long code) {
		Competence competence = null;
		try{
			conn = new ConnectionFactory().getConnection();
			competence = DaoCompetencies.searchCompetenceByCode(conn, code);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return competence;
		}
	}

	// Search All
	@SuppressWarnings("finally")
	public List<Competence> searchAllCompetence() {
		List<Competence> competencies = null;
		try{
			conn = new ConnectionFactory().getConnection();
			competencies = DaoCompetencies.searchAll(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return competencies;
		}
	}
}
