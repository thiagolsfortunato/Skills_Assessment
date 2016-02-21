package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.token.TokenInfo;
import br.com.fatec.model.user.User;

public class DaoUser {

	public static User getLogin(String userName, String password) throws SQLException {

		Connection conn = ConnectionMySql.getConnection();
		User user = new User();
		
		ResultSet rs = null;
		try {

			String query = "select usu_tipo, usu_codigo, usu_token from usuario where usu_login = '" + userName
					+ "' and usu_senha = '" + password + "';";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			rs = cmd.executeQuery();
			if (rs != null) {
				rs.next();
				updateTokenUser(rs.getString(User.COL_CODIGO));
				user.setKindPerson(rs.getString(User.COL_TIPO));
				user.setUserCode(Integer.parseInt(rs.getString(User.COL_CODIGO)));
				user.setToken(rs.getString(User.COL_TOKEN));
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			rs.close();
			conn.close();
			return user;
		}
	}

	private static void updateTokenUser(String id) throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String tk = Token.createJsonWebToken(id, (long) 1);
		String query = "UPDATE usuario SET usu_token=?  WHERE usu_codigo=?";

		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		cmd.setString(1, tk);
		cmd.setString(2, id);
		int rowsUpdated = cmd.executeUpdate();
		if (rowsUpdated > 0){
			System.out.println("An existing user was updated successfully!");
		}
	}

}
