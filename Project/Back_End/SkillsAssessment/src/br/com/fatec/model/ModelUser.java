package br.com.fatec.model;

import java.sql.SQLException;

import br.com.fatec.dao.DaoUser;
import br.com.fatec.model.user.User;

public class ModelUser {
	
	public User getLogin(String login, String password){
		try {
			User user = new User();
			user = DaoUser.getLogin(login, password);
			return user;
			//return DaoUser.getLogin(login, password); ->FUNCIONA<-
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error, model user");
		}
		
		return null;
	}
	
	public void updatePassword(Integer code, String newPassword){
		
	}

}
