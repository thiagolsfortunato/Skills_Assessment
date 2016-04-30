package br.com.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoEnrolls;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.entity.Enrolls;
import br.com.fatec.entity.User;

public class ModelEnrolls {
	
	//conexão com o banco de dados
	private static Connection conn = null;
	
	@SuppressWarnings("finally")
	public boolean insertEnrolls(Enrolls enrolls, User user){
		
		conn = new ConnectionFactory().getConnection();
		boolean transaction = false;
		try{
			conn.setAutoCommit(false);
			
			Long codeUser = DaoUser.insertUser(conn, user);
			//resultado da inserção do usuario + estudante
			transaction = DaoEnrolls.insertEnrolls(conn, enrolls, codeUser);
			
			if(transaction && (codeUser != null) ) conn.commit(); //se deu tudo certo comita!
			
		}catch(SQLException e){
			if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	            	excep.printStackTrace();//exception do rollback
	            }
	        }
			e.printStackTrace();
			System.out.println("an error occurred while trying to insert an enrolls");
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return transaction;
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
