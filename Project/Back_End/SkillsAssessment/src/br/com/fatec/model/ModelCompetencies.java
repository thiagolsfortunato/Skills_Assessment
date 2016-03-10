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
	
	//Add competence
	public boolean insertCompetence(Competence competence ){
		return DaoCompetencies.insertCompetence(competence);
	}
	
	//Delete Competence
	public boolean deleteCompetence(Long code){
		return DaoCompetencies.deleteCompetence(code);
	}
	
	//Update Competence
		public boolean updateCompetence(Competence competence) {
			return DaoCompetencies.updateCompetence(competence);		
		}
	
	//Search By Code
	public Competence searchCompetenceByCode(Long code) throws SQLException {
		return DaoCompetencies.searchCompetenceByCode(code);
	}	
	
	//Search All
	public List<Competence> searchAllCompetence() throws SQLException {
		return DaoCompetencies.searchAll();
	}
}
