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
	public boolean insertCompetence(Competence competence) {
		try {
			return DaoCompetencies.insertCompetence(competence);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("an error occurred while trying to update a competence");
			return false;
		}
	}

	// Delete Competence
	public boolean deleteCompetence(Long code) {
		try {
			return DaoCompetencies.deleteCompetence(code);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("an error occurred while trying to update a competence");
			return false;
		}
	}

	// Update Competence
	public boolean updateCompetence(Competence competence) {
		try{
			return DaoCompetencies.updateCompetence(competence);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to update a competence");
			return false;
		}
	}

	// Search By Code
	@SuppressWarnings("finally")
	public Competence searchCompetenceByCode(Long code) {
		Competence competence = null;
		try{
			competence = DaoCompetencies.searchCompetenceByCode(code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a competence");
		}finally{
			return competence;
		}
	}

	// Search All
	@SuppressWarnings("finally")
	public List<Competence> searchAllCompetence() {
		List<Competence> competencies = null;
		try{
			competencies = DaoCompetencies.searchAll();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a competence");
		}finally{
			return competencies;
		}
	}
}
