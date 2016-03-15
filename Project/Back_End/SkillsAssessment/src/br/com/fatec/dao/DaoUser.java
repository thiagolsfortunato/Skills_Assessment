package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			conn.getResultset().close();
			conn.getStatement().close();
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
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return token;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long insertUser(User user) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		ResultSet idUser = null;
		Long returnInsert = null;
		try {
			String insert = "insert into user (usr_userName ,usr_password ,usr_kind) values (?,?,?);";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement((insert),Statement.RETURN_GENERATED_KEYS));
			conn.getStatement().setString(1, user.getEmail());
			conn.getStatement().setString(2, user.getPassword());
			conn.getStatement().setString(3, user.getKindPerson());
			
			if(conn.executeSql()){
				System.out.println("the User has been successfully inserted!");
				idUser = conn.getStatement().getGeneratedKeys();
				if (idUser.next()) {
					System.out.println(idUser.getLong(1));
					returnInsert =  idUser.getLong(1);
				}
			}
		} catch (Exception e) {
			System.out.println("erro "+e);
			throw new RuntimeException(e);
		}finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return returnInsert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteUser(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean delete = false;
		try {
			conn.conect();
			String sql = "delete from User where USR_CODE = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(sql));
			conn.getStatement().setLong(1, code);
			if (conn.executeSql()) {
				delete = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return delete;
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
