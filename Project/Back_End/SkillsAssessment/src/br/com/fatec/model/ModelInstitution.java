package br.com.fatec.model;

import java.util.List;

import br.com.fatec.dao.DaoInstitution;
import br.com.fatec.entity.Institution;

public class ModelInstitution {
	
	public Institution searchInstitutionByCode (Long code){
		return DaoInstitution.searchInstitutionByCode(code);
	}
	
	public List<Institution> searchInstitutionByName (String name){
		return DaoInstitution.searchInstitutionByName(name);
	}
	
	public List<Institution> searchAllInstitution(){
		return DaoInstitution.searchAllInstitution();
	}
	
	public boolean insertInstitution (Institution fatec){
		return DaoInstitution.insertInstitution(fatec);
	}
	
	public boolean updateInstitution (Institution fatec){
		return DaoInstitution.updateInstitution(fatec);
	}
	
	public boolean deleteInstitution (Long code){
		return DaoInstitution.deleteInstitution(code);
	}

}
