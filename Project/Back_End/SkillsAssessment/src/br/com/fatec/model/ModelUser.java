package br.com.fatec.model;

import java.sql.SQLException;

import br.com.fatec.dao.DaoUser;
import br.com.fatec.entity.User;

public class ModelUser {
	
	public User getLogin(String login, String password){
		try {
			User user = DaoUser.getLogin(login, password);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error, model user");
		}
		return null;
	}
	
	public boolean addUser(User user){
		boolean returnInsert = false;
		try {
			Long newUser = DaoUser.insertUser(user);
			if(newUser != null)returnInsert =  true;
			else returnInsert =  false;
			
		} catch (SQLException e) {
			System.out.println("Will not it was possible to enter the User");
		}
		return returnInsert;
	}
	
	public void updatePassword(Integer code, String newPassword){
		//atualiza a senha de algum usuario
	}

}
