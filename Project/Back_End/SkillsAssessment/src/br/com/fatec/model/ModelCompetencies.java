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

package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;
import br.com.fatec.dao.DaoCompetencies;
import br.com.fatec.entity.Competence;

public class ModelCompetencies {

	// Add competence
	@SuppressWarnings("finally")
	public boolean insertCompetence(Competence competence) throws SQLException {
		boolean insert = false;
		try {
			insert = DaoCompetencies.insertCompetence(competence);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("an error occurred while trying to update a competence");
		} finally {
			return insert;
		}
	}

	// Delete Competence
	@SuppressWarnings("finally")
	public boolean deleteCompetence(Long code) throws SQLException {
		boolean delete = false;
		try {
			delete = DaoCompetencies.deleteCompetence(code);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("an error occurred while trying to update a competence");
		} finally {
			return delete;
		}
	}

	// Update Competence
	@SuppressWarnings("finally")
	public boolean updateCompetence(Competence competence) {
		boolean update = false;
		try{
			update = DaoCompetencies.updateCompetence(competence);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to update a competence");
		}finally{
			return update;
		}
	}

	// Search By Code
	@SuppressWarnings("finally")
	public Competence searchCompetenceByCode(Long code) throws SQLException {
		Competence competence = null;
		try{
			competence = DaoCompetencies.searchCompetenceByCode(code);
		}finally{
			return competence;
		}
	}

	// Search All
	@SuppressWarnings("finally")
	public List<Competence> searchAllCompetence() throws SQLException {
		List<Competence> competencies = null;
		try{
			competencies = DaoCompetencies.searchAll();
		}finally{
			return competencies;
		}
	}
}
