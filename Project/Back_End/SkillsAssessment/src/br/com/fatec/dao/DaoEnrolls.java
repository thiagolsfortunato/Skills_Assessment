package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.entity.Enrolls;

public class DaoEnrolls {
	//ESPERANDO METODO DO MARCELO
	@SuppressWarnings("finally")
	public static boolean insertEnrolls(Connection conn, Enrolls enrolls, Long codeUser) throws SQLException{

		String sql = "INSERT INTO ENROLLS (ern_year, ern_period, crs_code, usr_code) VALUES (?, ?, ?, ?);";
		boolean insert = false;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, enrolls.getYear());
			stmt.setInt(2, enrolls.getPeriod());
			stmt.setLong(3, enrolls.getCodeCourse());
			stmt.setLong(4, codeUser);
			if( stmt.executeUpdate() != 0 ){
				insert = insertResult(conn, codeUser);
			}
			stmt.close();
		}finally{
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteEnrolls(Connection conn, Long codeEnrolls) throws SQLException {

		String sql = "DELETE FROM ENROLLS WHERE ERN_CODE = ?;";
		boolean delete = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codeEnrolls);
			if (stmt.executeUpdate() != 0) {
				delete = true;
			}
			stmt.close();
		} finally {
			return delete;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateEnrolls(Connection conn, Enrolls enrolls) throws SQLException{

		String sql = "UPDATE ENROLLS SET ERN_YEAR = ?, ERN_PERIOD = ?, CRS_CODE = ?, USR_CODE = ? where ERN_CODE = ?;";
		boolean update = false;
		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, enrolls.getYear());
			stmt.setInt(2, enrolls.getPeriod());
			stmt.setLong(3, enrolls.getCodeCourse());
			stmt.setLong(4, enrolls.getCodeUser());
			stmt.setLong(5, enrolls.getCodeEnrolls());
			if( stmt.executeUpdate() != 0 ){
				update = true;
			}
			stmt.close();
		} finally {
			return update;
		}
	}	

	@SuppressWarnings("finally")
	public static List<Enrolls> searchAllEnrolls(Connection conn) throws SQLException {
		
		List<Enrolls> listEnrolls = new ArrayList<>();
		String query = "SELECT * FROM ENROLLS;";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listEnrolls.add(buildEnroll(rs));
			}
		} finally {
			conn.close();
			return listEnrolls;
		}
	}
	
	@SuppressWarnings("finally")
	public static Enrolls searchEnrollsById(Connection conn, Long codeEnrolls) throws SQLException {

		String query = "SELECT * FROM ENROLLS WHERE ERN_CODE = ?;";
		Enrolls enrolls = new Enrolls();
		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			enrolls = buildEnroll(rs);
		} finally {
			conn.close();
			return enrolls;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long searchEnrollsByUserId(Connection conn, Long idUser) throws SQLException {

		String query = "SELECT ERN_CODE FROM ENROLLS WHERE USR_CODE = ?";
		Long idEnrolls = null;
		try{
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				idEnrolls = rs.getLong("ERN_CODE");
			}
		} finally {
			conn.close();
			return idEnrolls;
		}
		
	}
	
	@SuppressWarnings("finally")
	public static boolean insertResult(Connection conn, Long codeUser) throws SQLException{
		
		String sql = "INSERT INTO RESULT (usr_code) VALUES (?);";
		boolean insert = false;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codeUser);
			if( stmt.executeUpdate() != 0 ){
				insert = true;
			}
			stmt.close();
		}finally{
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long getResult(Connection conn, Long idUser) throws SQLException {

		String query = "SELECT RST_CODE FROM RESULT WHERE USR_CODE = ?";
		Long result_code = null;
		try{
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, idUser);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result_code = rs.getLong("RST_CODE");
			}
		} finally {
			return result_code;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateResult(Connection conn, Long userCode) throws SQLException{

		String sql = "UPDATE RESULT SET RST_DATE_FINAL = date_format(now(), '%Y-%m-%d'), RST_COMPLETED = ? where USR_CODE = ?;";
		boolean update = false;
		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setLong(2, userCode);
	
			if( stmt.executeUpdate() != 0 ){
				update = true;
			}
			stmt.close();
		} finally {
			return update;
		}
	}	
	
	private static Enrolls buildEnroll (ResultSet rs) throws SQLException{
		Enrolls enrolls = new Enrolls(); 
		enrolls.setCodeEnrolls(rs.getLong("ERN_CODE"));
		enrolls.setYear(rs.getInt("ERN_YEAR"));
		enrolls.setPeriod(rs.getInt("ERN_PERIOD"));
		enrolls.setCodeCourse(rs.getLong("CRS_CODE"));
		enrolls.setCodeUser(rs.getLong("USR_CODE"));
		return enrolls;
	}
	
}

