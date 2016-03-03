package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.token.TokenInfo;
import br.com.fatec.model.user.User;

public class DaoUser {

	@SuppressWarnings("finally")
	public static User getLogin(String userName, String password) throws SQLException {

		Connection conn = ConnectionMySql.getConnection();
		User user = new User();
		
		ResultSet rs = null;
		try {
			String query = "select usr_kind, usr_code, usr_token from user where usr_username = '" + userName + "' and usr_password = '" + password + "';";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			rs = cmd.executeQuery();
			if (rs != null) {
				rs.next();
				String token = updateTokenUser(rs.getString("USR_CODE"));
				user.setKindPerson(rs.getString("USR_KIND"));
				user.setUserCode(Long.parseLong(rs.getString("USR_CODE")));
				user.setToken(rs.getString(token));
			}

		} catch (SQLException e) {
			// TODO: handle exceptions
		} finally {
			rs.close();
			conn.close();
			return user;
		}
	}

	private static String updateTokenUser(String id) throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String tk = Token.createJsonWebToken(id, (long) 1);
		String query = "UPDATE USER SET usr_token=?  WHERE usr_code=?";

		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		cmd.setString(1, tk);
		cmd.setString(2, id);
		int rowsUpdated = cmd.executeUpdate();
		if (rowsUpdated > 0){
			System.out.println("An existing user was updated successfully!");
			return tk;
		}
		return null;
	}

}
