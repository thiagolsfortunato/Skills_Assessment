package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.user.User;

public class DaoUser {
	
	public static User getLogin(String userName, String password) throws SQLException{
		
		Connection conn = ConnectionMySql.getConnection();
		String query = "select usu_tipo, usu_codigo, usu_token from usuario where usu_login = '" + userName +"' and usu_senha = '" + password +"';";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		
		User user = new User();
		
		rs.next();
			user.setKindPerson(rs.getString("usu_tipo"));
			user.setUserCode(Integer.parseInt(rs.getString("usu_codigo")));
			user.setToken(rs.getString("usu_token"));
		rs.close();
		
		return user;
	}
	
	

}
