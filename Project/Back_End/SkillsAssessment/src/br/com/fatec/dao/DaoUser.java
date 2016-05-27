package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.commons.Token;
import br.com.fatec.entity.User;

public class DaoUser {
	
	@SuppressWarnings("finally")
	public static User getLogin(Connection conn, String userName, String password) throws SQLException{
		User user = null;
		String query = "SELECT usr_type, usr_code, usr_token,usr_name FROM user WHERE usr_username = ? AND usr_password = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				user = buildLogin( conn, rs );
			}
		} finally {		
			return user;
		}
	}

	@SuppressWarnings("finally")
	private static String updateTokenUser(Connection conn, String id) throws SQLException {
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
		}finally {
			return token;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long insertUser(Connection conn, User user, Long idFatec) throws SQLException{
		
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
				System.out.println("sorte. .");
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
	public static User searchUserById(Connection conn, Long userCode) throws SQLException{ //in process
		User user = null;
		String query = "SELECT usr_type, usr_code, usr_token, usr_name, usr_situation, usr_verified "
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
		} finally {		
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public static User searchStudentById(Connection conn, Long id) throws SQLException{
		User student = null;
		String query = "SELECT user.usr_code, usr_type, usr_name, crs_name, ern_year, ern_period "
						+ "FROM enrolls JOIN user ON (enrolls.usr_code = user.usr_code) "
						+ "JOIN course ON (enrolls.crs_code = course.crs_code) "
						+ "WHERE usr_type = 'student' AND user.usr_code = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				student = buildUser( rs );
			}
			rs.close();
			stmt.close();
		} finally {		
			return student;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<User> searchAllStudents(Connection conn) throws SQLException{
		List<User> students = new LinkedList<User>();
		String query = "SELECT user.usr_code, usr_type, usr_name, crs_name, ern_year, ern_period "
						+ "FROM enrolls JOIN user ON (enrolls.usr_code = user.usr_code) "
						+ "JOIN course ON (enrolls.crs_code = course.crs_code) "
						+ "WHERE usr_type = 'student'";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				students = buildUsers( rs );
			}
			rs.close();
			stmt.close();
		} finally {		
			return students;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<User> searchAllUsers(Connection conn) throws SQLException{ //in process
		List<User> user = new LinkedList<User>();
		String query = "SELECT usr_type, usr_code, usr_token, usr_name, usr_situation, usr_verified "
						+ "FROM user";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				user = buildUsers( rs );
			}
			rs.close();
			stmt.close();
		} finally {		
			return user;
		}
	}

	@SuppressWarnings("finally")
	public static boolean updateUser(Connection conn, User user) throws SQLException{
		boolean returnUpdate = false;
		String sql = "UPDATE user SET usr_userName = ?, usr_password = ?, usr_ra = ?, usr_type = ?, usr_name = ? "
						+ "WHERE usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRa());
			stmt.setString(4, user.getType());
			stmt.setString(5, user.getName());
			stmt.setLong(6, user.getUserCode());
			
			if( stmt.executeUpdate() != 0 ){
				System.out.println("the User has been successfully updated!");
				returnUpdate =  true;	
			}
			stmt.close();
		}finally {
			return returnUpdate;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteUser(Connection conn, Long code) throws SQLException {
		boolean delete = false;
		String sql = "DELETE FROM User WHERE usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, code);
			if ( stmt.executeUpdate() != 0 ) {
				delete = true;
			}
			stmt.close();
		} finally {
			return delete;
		}
	}

	private static User buildLogin(Connection conn, ResultSet rs) throws SQLException {
		User user = new User();
		String token = updateTokenUser(conn, rs.getString("USR_CODE") );
		user.setType( rs.getString("USR_TYPE") );
		user.setUserCode( rs.getLong("USR_CODE") );
		user.setUserName( rs.getString("USR_NAME") );
		user.setToken(token); 
		return user;
	}
	
	private static User buildUser (ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setUserCode(rs.getLong("USR_CODE"));
		user.setUserName(rs.getString("USR_NAME"));
		user.setSituation(rs.getInt("USR_SITUATION"));
		user.setVerification(rs.getInt("USR_VERIFIED"));
		user.setType(rs.getString("USR_TYPE").toLowerCase());//coloca em minusculo
		
		if( user.getType().equals("student") ){
			user.setCourseStudent(rs.getString("CRS_NAME"));
			user.setYearStudent(rs.getInt("ERN_YEAR"));
			user.setPeriodStudent(rs.getInt("ERN_PERIOD"));
		} 
		return user;
	}
	
	private static List<User> buildUsers ( ResultSet rs ) throws SQLException {
		List<User> user = new LinkedList<User>();
		while ( rs.next() ) {
			user.add( buildUser(rs) );
		}
		return user;
	}
	

}
