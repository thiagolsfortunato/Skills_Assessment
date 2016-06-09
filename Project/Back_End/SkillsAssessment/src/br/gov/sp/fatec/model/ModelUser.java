package br.gov.sp.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.gov.sp.fatec.connection.ConnectionFactory;
import br.gov.sp.fatec.dao.DaoUser;
import br.gov.sp.fatec.entity.User;

public class ModelUser {
	
	//conexão com o banco de dados
	private static Connection conn;
	ModelQuiz modelQuiz = new ModelQuiz();
	
	@SuppressWarnings("finally")
	public User getLogin(String login, String password){
		User user = null;
		try {
			conn = new ConnectionFactory().getConnection();
			user = DaoUser.getLogin(conn, login, password);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public Long insertUser(User user){
		Long returnInsert = null;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			returnInsert = DaoUser.insertUser(conn, user, user.getInstCode());	
			if(returnInsert != null) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Will not it was possible to insert the User");
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnInsert;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean deleteUser(Long userId){
		boolean transaction = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoUser.deleteUser(conn, userId);
			if ( transaction ){
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			System.out.println("Will not it was possible to delete the User");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
		
	}
	
	@SuppressWarnings("finally")
	public boolean updateUser(User user){
		boolean transaction = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoUser.updateUser(conn, user);
			if ( transaction ){
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			System.out.println("Will not it was possible to update the User");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}
	
	
	@SuppressWarnings("finally")
	public User searchUserById(Long userId){
		User returnUser = null;
		try {
			conn = new ConnectionFactory().getConnection();
			returnUser = DaoUser.searchUserById(conn, userId);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnUser;
		}
	}	
	
	
	@SuppressWarnings("finally")
	public List<User> searchAllPsicologas(Long fatecCode){
		List<User> returnUser = new LinkedList<User>();
		try {
			conn = new ConnectionFactory().getConnection();
			returnUser = DaoUser.searchAllPsicologas(conn, fatecCode);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnUser;
		}
	}
	
	
	public void updatePassword(Integer code, String newPassword){
		//atualiza a senha de algum usuario
	}

}
