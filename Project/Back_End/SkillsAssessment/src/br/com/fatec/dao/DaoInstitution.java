package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Institution;

public class DaoInstitution {
	
	@SuppressWarnings("finally")
	public static boolean insertInstitution(Connection conn, Institution institution) throws SQLException {
		
		String insert = "INSERT INTO institution (ist_company ,ist_cnpj, ist_city) VALUES (?,?,?);";
		
		boolean transaction = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(insert);
			stmt.setString(1, institution.getCompany());
			stmt.setString(2, institution.getCnpj());
			stmt.setString(3, institution.getCity());
			
			if(stmt.executeUpdate() != 0){
				transaction = true;
			}
			stmt.close();
		} finally {
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteInstitution(Connection conn, Long code) throws SQLException {
		
		String sql = "DELETE FROM institution WHERE ist_code = ?;";
		
		boolean transaction = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, code);
		
			if (stmt.executeUpdate() != 0) {
				transaction = true;
			}
			stmt.close();
		} finally {
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateInstitution(Connection conn, Institution institution) throws SQLException {
		String update = "UPDATE institution SET ist_company = ?, ist_cnpj = ?, ist_city = ? WHERE ist_code = ?;";

		boolean transaction = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, institution.getCompany());
			stmt.setString(2, institution.getCnpj());
			stmt.setString(3, institution.getCity());
			stmt.setLong(4, institution.getCodeInstitution());
		
			if(stmt.executeUpdate() != 0){
				transaction = true;	
			}
			stmt.close();
		}finally {
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public static Institution searchInstitutionByCode(Connection conn, Long code) throws SQLException {
		Institution institution = new Institution();
		String query = "SELECT * FROM institution WHERE ist_code = ?;";

		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, code);
			ResultSet rs = stmt.executeQuery();
			institution = buildInstitution(rs);
			rs.close();
			stmt.close();
		} finally {
			return institution;
		}
	}
	//usar clausula %like% do banco, para trazer por parte do nome
	@SuppressWarnings("finally")
	public static List<Institution> searchInstitutionByName(Connection conn, String strName) throws SQLException {
		List<Institution> institutions = new LinkedList<Institution>();
		String query = "SELECT * FROM institution WHERE ist_company LIKE '%"+ strName +"%';";
			
		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, strName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				institutions.add(buildInstitution(rs));
			}
			rs.close();
			stmt.close();
		} finally {
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Institution> searchAllInstitution(Connection conn) throws SQLException {
		List<Institution> institutions = new LinkedList<Institution>();
		String query = "SELECT * FROM institution;";

		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				institutions.add(buildInstitution(rs));
			}
		} finally {
			conn.close();
			return institutions;
		}
	}
	
	/*private static List<Institution> buildInstitutions(ConnectionMySql conn) throws SQLException{
		List<Institution> institutions = new LinkedList<Institution>();
		do {
			institutions.add(buildInstitution(conn.getResultset()));
		} while (conn.nextRegister());
		return institutions;
	}*/
	
	private static Institution buildInstitution (ResultSet rs) throws SQLException{
		Institution institution = new Institution();
		institution.setCodeInstitution(rs.getLong("IST_CODE"));
		institution.setCompany(rs.getString("IST_COMPANY"));
		institution.setCnpj(rs.getString("IST_CNPJ"));
		institution.setCity(rs.getString("IST_CITY"));
		return institution;
	}

}
