package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoInstitutionCourse {
	
	@SuppressWarnings("finally")
	public static boolean insertIntitutionCourse(Connection conn, Long idInstitution, Long idCourse) throws SQLException{
		boolean insert = false;
		try{
			String sql = "insert into ist_crs (ist_code, crs_code) values (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, idInstitution);
			stmt.setLong(2, idCourse);

			if (stmt.executeUpdate() != 0) {
				insert = true;
			}
			stmt.close();
		} finally {
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteIntitutionCourse(Connection conn, Long idInstitution, Long idCourse) throws SQLException {
		boolean delete = false;
		try{
			String sql = "delete from ist_crs where ist_code = ? and crs_code = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, idInstitution);
			stmt.setLong(2, idCourse);
			if (stmt.executeUpdate() != 0) {
				delete = true;
			}
			stmt.close();
		} finally {
			return delete;
		}		
	}
	
	
}
