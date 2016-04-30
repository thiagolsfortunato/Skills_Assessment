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
	private static Connection conn = null;
	ModelQuiz modelQuiz = new ModelQuiz();
	
	@SuppressWarnings("finally")
	public User getLogin(String login, String password){
		User user = null;
		try {
			user = DaoUser.getLogin(login, password);
			user.setUnansweredQuestions(modelQuiz.getValidQuestions(user.getUserCode()));
			user.setQuestionAmount(modelQuiz.getNumberOfQuestions());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error, model user");
		}finally {
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public Long insertUser(User user){
		conn = new ConnectionFactory().getConnection();
		Long returnInsert = null;
		try {
			returnInsert = DaoUser.insertUser(conn, user);			
		} catch (SQLException e) {
			System.out.println("Will not it was possible to insert the User");
		}
		finally {
			return returnInsert;
		}
	}
	
	public boolean deleteUser(Long userId){
		try {
			return DaoUser.deleteUser(userId);			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Will not it was possible to delete the User");
			return false;
		}
		
	}
	
	public boolean updateUser(User user){
		try {
			return DaoUser.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Will not it was possible to update the User");
			return false;
		}
	}
	
	
	@SuppressWarnings("finally")
	public User searchUserById(Long userId){
		User returnUser = null;
		try {
			returnUser = DaoUser.searchUserById(userId);
		} catch (SQLException e) {
			System.out.println("Will not it was possible to search the User");
		}finally {
			return returnUser;
		}
	}	
	
	@SuppressWarnings("finally")
	public List<User> searchAllStudents(){
		List<User> students = new LinkedList<User>();
		try{
			students = DaoUser.searchAllStudents();
		}catch(SQLException e){
			System.out.println("Will not it was possible to find the Students");
		}finally {
			return students;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> searchAllUsers(){
		List<User> returnUser = new LinkedList<User>();;
		try {
			returnUser = DaoUser.searchAllUsers();
		} catch (SQLException e) {
			System.out.println("Will not it was possible to find the User");
		}finally {
			return returnUser;
		}
	}
	
	@SuppressWarnings("finally")
	public User searchStudentById(Long id) throws SQLException{
		User user = new User();
		try{
			user = DaoUser.searchStudentById(id); 
		}catch (SQLException e ){
			System.out.println("Will not it was possible to find the Student");
		}finally{
			return user;
		}
	}
	
	public void updatePassword(Integer code, String newPassword){
		//atualiza a senha de algum usuario
	}

}
