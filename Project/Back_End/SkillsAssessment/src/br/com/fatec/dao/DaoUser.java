package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.commons.Token;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.User;

public class DaoUser {

	@SuppressWarnings("finally")
	public static User getLogin(String userName, String password) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		User user = null;
		try {
			String query = "select usr_type, usr_code, usr_token,usr_name from user where usr_username = ? and usr_password = ?;";		
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, userName);
			conn.getStatement().setString(2, password);			
			if (conn.executeQuery()){
				user = buildLogin(conn.returnRegister());
			}
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
		}finally {
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
			String insert = "insert into user (usr_userName ,usr_password ,usr_ra, usr_type, usr_name, usr_register, ist_code) values (?,?,?,?,?,date_format(now(), '%Y-%m-%d'),?);";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement((insert),Statement.RETURN_GENERATED_KEYS));
			conn.getStatement().setString(1, user.getUserName());
			conn.getStatement().setString(2, user.getPassword());
			conn.getStatement().setString(3, user.getRa());
			conn.getStatement().setString(4, user.getType());
			conn.getStatement().setString(5, user.getName());
			conn.getStatement().setInt(6, user.getInstCode());
			if(conn.executeSql()){
				System.out.println("the User has been successfully inserted!");
				idUser = conn.getStatement().getGeneratedKeys();
				if (idUser.next()) {
					returnInsert =  idUser.getLong(1);
				}
			}
		}finally {
			conn.close();
			return returnInsert;
		}
	}
	
	@SuppressWarnings("finally")
	public static User searchUserById(Long userCode) throws SQLException{ //in process
		ConnectionMySql conn = new ConnectionMySql();
		User user = null;
		try {
			String query = "select usr_type, usr_code, usr_token, usr_name, usr_situation, usr_verified from user where usr_code = ?;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, userCode);
			if (conn.executeQuery()){
				user = buildUser(conn.returnRegister());
			}
		} finally {		
			conn.close();
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public static User searchStudentById(Long id) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		User student = null;
		try{
			String query = "select user.usr_code, usr_type, usr_name, crs_name, ern_year, ern_period from enrolls join user on (enrolls.usr_code = user.usr_code) join course on (enrolls.crs_code = course.crs_code) where usr_type = 'student' and user.usr_code = ?";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, id);
			if(conn.executeQuery()){
				student = buildUser(conn.returnRegister());
			}
		} finally {		
			conn.close();
			return student;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<User> searchAllStudents() throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		List<User> students = new LinkedList<User>();
		try{
			String query = "select user.usr_code, usr_type, usr_name, crs_name, ern_year, ern_period from enrolls join user on (enrolls.usr_code = user.usr_code) join course on (enrolls.crs_code = course.crs_code) where usr_type = 'student'";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if(conn.executeQuery()){
				students = buildUsers(conn);
			}
		} finally {		
			conn.close();
			return students;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<User> searchAllUsers() throws SQLException{ //in process
		ConnectionMySql conn = new ConnectionMySql();
		List<User> user = new LinkedList<User>();

		try {
			String query = "select usr_type, usr_code, usr_token, usr_name, usr_situation, usr_verified from user";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()){
				user = buildUsers(conn);
			}
		} finally {		
			conn.close();
			return user;
		}
	}

	@SuppressWarnings("finally")
	public static boolean updateUser(User user) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		boolean returnUpdate = false;
		try {
			String update = "update user set usr_userName = ?, usr_password = ?, usr_ra = ?, usr_type = ?, usr_name = ? where usr_code = ?;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(update));
			conn.getStatement().setString(1, user.getUserName());
			conn.getStatement().setString(2, user.getPassword());
			conn.getStatement().setString(3, user.getRa());
			conn.getStatement().setString(4, user.getType());
			conn.getStatement().setString(5, user.getName());
			conn.getStatement().setLong(6, user.getUserCode());
			
			if(conn.executeSql()){
				System.out.println("the User has been successfully updated!");
					returnUpdate =  true;	
			}
		}finally {
			conn.close();
			return returnUpdate;
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
		} finally {
			conn.close();
			return delete;
		}
	}

	private static User buildLogin(ResultSet rs) throws SQLException {
		User user = new User();
		String token = updateTokenUser(rs.getString("USR_CODE"));
		user.setType(rs.getString("USR_TYPE"));
		user.setUserCode(Long.parseLong(rs.getString("USR_CODE")));
		user.setUserName(rs.getString("USR_NAME"));
		user.setToken(token); 
		return user;
	}
	
	private static User buildUser (ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserCode(Long.parseLong(rs.getString("USR_CODE")));
		user.setUserName(rs.getString("USR_NAME"));
		user.setSituation(Integer.parseInt(rs.getString("USR_SITUATION")));
		user.setVerification(Integer.parseInt(rs.getString("USR_VERIFIED")));
		
		String userType = rs.getString("USR_TYPE").toLowerCase();
		user.setType(userType);
		if(userType.equals("student")){
			user.setCourseStudent(rs.getString("CRS_NAME"));
			user.setYearStudent(rs.getInt("ERN_YEAR"));
			user.setPeriodStudent(rs.getInt("ERN_PERIOD"));
		} 
		return user;
	}
	
	private static List<User> buildUsers (ConnectionMySql conn) throws SQLException {
		List<User> user = new LinkedList<User>();
		do {
			user.add(buildUser(conn.returnRegister()));
		} while (conn.nextRegister());
		return user;
	}
	

}
