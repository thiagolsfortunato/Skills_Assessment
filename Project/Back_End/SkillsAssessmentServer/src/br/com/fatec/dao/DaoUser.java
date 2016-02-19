package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.User;

public class DaoUser {
	
	public static User getLogin(String userName, String password){
		User teste = new User(); //
		return teste;
	}
	
	

}
