package br.com.fatec.model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.dao.DaoUser;
import br.com.fatec.dao.Token;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.entity.User;

public class ModelUser {
	
	@SuppressWarnings("finally")
	public User getLogin(String login, String password){
		User user = null;
		try {
			user = DaoUser.getLogin(login, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error, model user");
		}finally {
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean addUser(User user){
		boolean returnInsert = false;
		try {
			Long newUser = DaoUser.insertUser(user);
			if(newUser != null)returnInsert =  true;
			else returnInsert =  false;
			
		} catch (SQLException e) {
			System.out.println("Will not it was possible to enter the User");
		}
		finally {
			return returnInsert;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean deleteUser(String token){
		boolean returnDelete = false;
		try {
			TokenInfo tk = Token.verifyToken(token);
			returnDelete = DaoUser.deleteUser(Long.parseLong(tk.getUserId()));
			return returnDelete;
			
		} catch (SQLException e) {
			System.out.println("Will not it was possible to enter the User");
		}finally {
			return returnDelete;
		}
		
	}
	
	@SuppressWarnings("finally")
	public boolean updateUser(User user){
		boolean returnUpdade = false;
		try {
			returnUpdade = DaoUser.updateUser(user);
			return returnUpdade;
			
		} catch (SQLException e) {
			System.out.println("Will not it was possible to enter the User");
		}
		finally {
			return returnUpdade;
		}
	}
	
	@SuppressWarnings("finally")
	public User searchUserById(Long userId){
		User returnUser = null;
		try {
			returnUser = DaoUser.searchUserById(userId);
		} catch (SQLException e) {
			System.out.println("Will not it was possible to enter the User");
		}finally {
			return returnUser;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> searchAllUsers(){
		List<User> returnUser = new LinkedList<User>();;
		try {
			returnUser = DaoUser.searchAllUsers();
		} catch (SQLException e) {
			System.out.println("Will not it was possible to enter the User");
		}finally {
			return returnUser;
		}
	}
	
	public void updatePassword(Integer code, String newPassword){
		//atualiza a senha de algum usuario
	}

}
