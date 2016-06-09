package br.gov.sp.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.gov.sp.fatec.commons.Token;
import br.gov.sp.fatec.entity.User;

public class DaoUser {
	
	@SuppressWarnings("finally")
	public static User getLogin(Connection conn, String userName, String password){
		User user = null;
		String query = "SELECT usr_type, ist_code, usr_code, usr_token, usr_name "
				+ "FROM user WHERE usr_username = ? AND usr_password = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				user = buildLogin( conn, rs );
			}
		} catch(SQLException ex){
			ex.printStackTrace();
		}finally {		
			return user;
		}
	}

	@SuppressWarnings("finally")
	private static String updateTokenUser(Connection conn, String id){
		String token = null;
		String query = "UPDATE USER SET usr_token=?  WHERE usr_code=?";
		try {
			token = Token.createJsonWebToken(id, (long) 1);
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, token);
			stmt.setString(2, id);
			
			if( stmt.executeUpdate() != 0 ){
				System.out.println("An existing user was updated successfully!");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			return token;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long insertUser(Connection conn, User user, Long idFatec){
		
		Long idUser = null;
		String sql = "INSERT INTO user (usr_userName ,usr_password ,usr_ra, usr_type, usr_name, usr_register, ist_code) "
				+"VALUES ( ?, ?, ?, ?, ?, date_format(now(), '%Y-%m-%d'), ?);";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// seta os valores
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRa());
			stmt.setString(4, user.getType());
			stmt.setString(5, user.getName());
			stmt.setLong(6, idFatec);
			// executa
			if(  stmt.executeUpdate() != 0 ){
				System.out.println("the User has been successfully inserted!");
				ResultSet rs = stmt.getGeneratedKeys();
				if ( rs.next() ) {
					idUser =  rs.getLong(1);
					rs.close(); //se deu certo fecha aqui msm
				}
			}
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			return idUser;
		}
	}
	
	@SuppressWarnings("finally")
	public static User searchUserById(Connection conn, Long userCode){ //in process
		User user = null;
		String query = "SELECT usr_code, usr_name, usr_userName, usr_password "
				+ "FROM user WHERE usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, userCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				user = buildUser( rs );
			}
			rs.close();
			stmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {		
			return user;
		}
	}
	
	
	@SuppressWarnings("finally")
	public static List<User> searchAllUsers(Connection conn, Long fatecCode){ //in process
		List<User> user = new LinkedList<User>();
		String query = "SELECT usr_code, usr_name, usr_userName, usr_password "
						+ "FROM user WHERE ist_code = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, fatecCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				user = buildUsers( rs );
			}
			rs.close();
			stmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {		
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<User> searchAllPsicologas(Connection conn, Long fatecCode){
		List<User> user = new LinkedList<User>();
		String query = "SELECT usr_code, usr_name, usr_userName, usr_password "
							+ "FROM user WHERE usr_type = 'psicologa' AND ist_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, fatecCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				user = buildUsers( rs );
			}
			rs.close();
			stmt.close();
		} catch(SQLException ex){
			ex.printStackTrace();
		}finally {		
			return user;
		}
	}

	@SuppressWarnings("finally")
	public static boolean updateUser(Connection conn, User user){
		boolean returnUpdate = false;
		String sql = "UPDATE user SET usr_userName = ?, usr_password = ?, usr_name = ? "
						+ "WHERE usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setLong(4, user.getUserCode());
			
			if( stmt.executeUpdate() != 0 ){
				System.out.println("the User has been successfully updated!");
				returnUpdate =  true;	
			}
			stmt.close();
		} catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return returnUpdate;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteUser(Connection conn, Long code){
		boolean delete = false;
		String sql = "DELETE FROM User WHERE usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, code);
			if ( stmt.executeUpdate() != 0 ) {
				delete = true;
			}
			stmt.close();
		} catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			return delete;
		}
	}

	private static User buildLogin(Connection conn, ResultSet rs) throws SQLException {
		User user = new User();
		String token = updateTokenUser(conn, rs.getString("USR_CODE") );
		
		user.setUserCode( rs.getLong("USR_CODE") );
		user.setType( rs.getString("USR_TYPE") );
		user.setName( rs.getString("USR_NAME") );
		user.setInstCode(rs.getLong("IST_CODE"));
		user.setToken(token); 
		return user;
	}
	
	private static User buildUser (ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setUserCode(rs.getLong("USR_CODE"));
		user.setName(rs.getString("USR_NAME"));
		user.setUserName(rs.getString("USR_USERNAME"));
		user.setPassword(rs.getString("USR_PASSWORD"));
		
		return user;
	}
	
	private static List<User> buildUsers ( ResultSet rs ) throws SQLException {
		List<User> user = new LinkedList<User>();
		do {
			user.add( buildUser(rs) );
		}while ( rs.next() );
		return user;
	}
	

}
