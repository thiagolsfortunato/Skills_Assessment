package br.com.fatec.model;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoEnrolls;
import br.com.fatec.entity.Enrolls;

public class ModelEnrolls {
	
	@SuppressWarnings("finally")
	public boolean insertEnrolls(Enrolls enrolls) {
		boolean insert = false;
		try{
			insert = DaoEnrolls.insertEnrolls(enrolls);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to update an enrolls");
		}finally{
			return insert;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateEnrolls(Enrolls enrolls) {
		boolean update = false;
		try{
			update = DaoEnrolls.updateEnrolls(enrolls);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to update an enrolls");
		}finally{
			return update;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteEnrolls(Long code) {
		boolean delete = false;
		try{
			delete = DaoEnrolls.deleteEnrolls(code);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to update an enrolls");
		}finally{
			return delete;
		}
	}

	@SuppressWarnings("finally")
	public Enrolls searchEnrollsByCode(Long code) throws SQLException {
		Enrolls enrolls = null;
		try{
			enrolls = DaoEnrolls.searchEnrollsById(code);
		}finally{
			return enrolls;
		}
	}

	@SuppressWarnings("finally")
	public List<Enrolls> searchAllEnrolls() throws SQLException {
		List<Enrolls> enrolls = null;
		try{
			enrolls = DaoEnrolls.searchAllEnrolls();
		}finally{
			return enrolls;
		}
	}
}
