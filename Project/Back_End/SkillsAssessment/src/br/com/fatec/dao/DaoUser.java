package br.com.fatec.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.User;

public class DaoUser {

	@SuppressWarnings("finally")
	public static User getLogin(String email, String password) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		User user = null;

		try {
			String query = "select usr_kind, usr_code, usr_token from user where usr_username = ? and usr_password = ?;";
			
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, email);
			conn.getStatement().setString(2, password);
			
			if (conn.executeQuery()) user = buildLogin(conn.returnRegister());
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {		
			conn.close();
			return user;
		}
	}

	@SuppressWarnings("finally")
	private static String updateTokenUser(String id) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		String token = null;
		try {
			token = Token.createJsonWebToken(id, (long) 1);
			String query = "UPDATE USER SET usr_token=?  WHERE usr_code=?";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, token);
			conn.getStatement().setString(2, id);
			
			if(conn.executeSql()){
				System.out.println("An existing user was updated successfully!");
				return token;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			conn.close();
			return token;
		}
	}
	

	private static User buildLogin(ResultSet rs) throws SQLException {
		User user = new User();
		String token = updateTokenUser(rs.getString("USR_CODE"));
		user.setKindPerson(rs.getString("USR_KIND"));
		user.setUserCode(Long.parseLong(rs.getString("USR_CODE")));
		user.setToken(token); 
		return user;
	}

}
