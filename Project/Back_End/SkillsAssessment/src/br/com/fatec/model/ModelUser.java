package br.com.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.entity.User;

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
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error, get login");
		}finally {
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
		} catch (SQLException e) {
			System.out.println("Will not it was possible to search the User");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnUser;
		}
	}	
	
	@SuppressWarnings("finally")
	public List<User> searchAllStudents(){
		List<User> students = new LinkedList<User>();
		try{
			conn = new ConnectionFactory().getConnection();
			students = DaoUser.searchAllStudents(conn);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Will not it was possible to find the Students");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return students;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> searchAllUsers(){
		List<User> returnUser = new LinkedList<User>();;
		try {
			conn = new ConnectionFactory().getConnection();
			returnUser = DaoUser.searchAllUsers(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Will not it was possible to find the User");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnUser;
		}
	}
	
	@SuppressWarnings("finally")
	public User searchStudentById(Long id) throws SQLException{
		User user = new User();
		try{
			conn = new ConnectionFactory().getConnection();
			user = DaoUser.searchStudentById(conn, id); 
		}catch (SQLException e ){
			System.out.println("Will not it was possible to find the Student");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
	}
	
	public void updatePassword(Integer code, String newPassword){
		//atualiza a senha de algum usuario
	}

}
