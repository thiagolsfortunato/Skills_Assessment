package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoEnrolls;
import br.com.fatec.entity.Enrolls;

public class ModelEnrolls {
	
	public boolean insertEnrolls(Enrolls enrolls){
		try{
			return DaoEnrolls.insertEnrolls(enrolls);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to insert an enrolls");
			return false;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateEnrolls(Enrolls enrolls){
		try{
			return DaoEnrolls.updateEnrolls(enrolls);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to update an enrolls");
			return false;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteEnrolls(Long code){
		try{
			return DaoEnrolls.deleteEnrolls(code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to delete an enrolls");
			return false;
		}
	}

	@SuppressWarnings("finally")
	public Enrolls searchEnrollsByCode(Long code) {
		Enrolls enrolls = null;
		try{
			enrolls = DaoEnrolls.searchEnrollsById(code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a enrolls");
		}finally{
			return enrolls;
		}
	}

	@SuppressWarnings("finally")
	public List<Enrolls> searchAllEnrolls(){
		List<Enrolls> enrolls = null;
		try{
			enrolls = DaoEnrolls.searchAllEnrolls();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a enrolls");
		}finally{
			return enrolls;
		}
	}
}
