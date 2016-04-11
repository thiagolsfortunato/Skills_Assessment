package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoInstitution;
import br.com.fatec.entity.Institution;

public class ModelInstitution {
	
	@SuppressWarnings("finally")
	public Institution searchInstitutionByCode(Long code) throws SQLException{
		Institution institution = null;
		try{
			institution = DaoInstitution.searchInstitutionByCode(code);
		}finally{
			return institution;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Institution> searchInstitutionByName(String name) throws SQLException {
		List<Institution> institutions = null;
		try{
			institutions = DaoInstitution.searchInstitutionByName(name);
		}finally{
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Institution> searchAllInstitution() throws SQLException {
		List<Institution> institutions = null;
		try{
			institutions = DaoInstitution.searchAllInstitution();
		} finally{
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean insertInstitution (Institution fatec) throws SQLException {
		boolean insert = false;
		try{
			insert =  DaoInstitution.insertInstitution(fatec);
		} finally{
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateInstitution (Institution fatec) throws SQLException{
		boolean update = false;
		try{
			update = DaoInstitution.updateInstitution(fatec); 
		}finally{
			return update;
		}
	}
		
	@SuppressWarnings("finally")
	public boolean deleteInstitution (Long code) throws SQLException {
		boolean delete = false;
		try{
			delete = DaoInstitution.deleteInstitution(code);
		}finally{
			return delete;
		}
	}

}
