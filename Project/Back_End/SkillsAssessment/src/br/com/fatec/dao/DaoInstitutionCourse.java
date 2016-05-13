package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoInstitutionCourse {
	
	@SuppressWarnings("finally")
	public static boolean insertIntitutionCourse(Connection conn, Long idInstitution, Long idCourse) throws SQLException{
		boolean insert = false;
		String sql = "INSERT INTO ist_crs (ist_code, crs_code) values (?, ?)";
		try{
			
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
		String sql = "DELETE FROM ist_crs WHERE ist_code = ? AND crs_code = ?";
		try{
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
