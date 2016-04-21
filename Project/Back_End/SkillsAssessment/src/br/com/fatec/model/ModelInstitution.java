package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoInstitution;
import br.com.fatec.entity.Institution;

public class ModelInstitution {
	
	@SuppressWarnings("finally")
	public Institution searchInstitutionByCode(Long code){
		Institution institution = null;
		try{
			institution = DaoInstitution.searchInstitutionByCode(code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a institution");
		}finally{
			return institution;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Institution> searchInstitutionByName(String name) {
		List<Institution> institutions = null;
		try{
			institutions = DaoInstitution.searchInstitutionByName(name);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a institutions");
		}finally{
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Institution> searchAllInstitution() {
		List<Institution> institutions = null;
		try{
			institutions = DaoInstitution.searchAllInstitution();
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a institutions");
		}finally{
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean insertInstitution (Institution fatec) {
		try{
			return DaoInstitution.insertInstitution(fatec);
		} catch (SQLException e) {
			System.out.println("Will not it was possible to insert the Institution");
			return false;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateInstitution (Institution fatec) throws SQLException{
		try{
			return DaoInstitution.updateInstitution(fatec); 
		}catch (SQLException e) {
			System.out.println("Will not it was possible to update the Institution");
			return false;
		}
	}
		
	@SuppressWarnings("finally")
	public boolean deleteInstitution (Long code) throws SQLException {
		boolean delete = false;
		try{
			delete = DaoInstitution.deleteInstitution(code);
		}catch (SQLException e) {
			System.out.println("Will not it was possible to delete the Institution");
		}finally{
			return delete;
		}
	}

}
