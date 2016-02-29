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

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoCompetencies;
import br.com.fatec.model.question.Competence;

public class ModelCompetencies {
	
	//Add competence
	public boolean addCompetence(long code, String description, Date date, int weight){
		try {
			if(DaoCompetencies.addCompetence(code, description, date, weight)) return true;
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Competence not added!");
		}
		return false;
	}
	
	//Search By Name
	public Competence searchCompetenceByCode(Long code) {
		try {
			Competence compe = new Competence();
			compe = DaoCompetencies.searchCompetenceByCode(code);
			return compe;
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Competence not found!");
		}
		return null;
	}
	
	//Update Competence
	public boolean updateCompetence(Competence comp, Long code) {
		try{
			if(DaoCompetencies.updateCompetence(comp, code)) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Error!");
		}
		return false;
		
	}
	
	//Delete Competence
	public boolean deleteCompetence(String description){
		try {
			if(DaoCompetencies.deleteCompetence(description)) return true;
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Competence not deleted!");
		}
		return false;
	}
	
	//Search All
	public List<Competence> searchAllCompetence() {
		try {
			List<Competence> listCompetence;
			listCompetence = DaoCompetencies.searchAll(); 
			return listCompetence;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}
}
