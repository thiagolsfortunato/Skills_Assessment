package br.gov.sp.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

//import br.com.fatec.connection.ConnectionMySql;
import br.gov.sp.fatec.entity.Institution;

public class DaoInstitution {
	
	@SuppressWarnings("finally")
	public static Long insertInstitution(Connection conn, Institution institution){
		
		String insert = "INSERT INTO institution (ist_company ,ist_cnpj, ist_city) VALUES (?, ?, ?);";
		
		Long idInstitution = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, institution.getCompany());
			stmt.setString(2, institution.getCnpj());
			stmt.setString(3, institution.getCity());
			
			if(stmt.executeUpdate() != 0){
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if( generatedKeys.next() ){
					idInstitution = generatedKeys.getLong(1);
				}
				generatedKeys.close();
			}
			stmt.close();
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {
			return idInstitution;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteInstitution(Connection conn, Long code){
		
		String sql = "DELETE FROM institution WHERE ist_code = ?;";
		
		boolean transaction = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, code);
		
			if (stmt.executeUpdate() != 0) {
				transaction = true;
			}
			stmt.close();
		} catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateInstitution(Connection conn, Institution institution){
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
		} catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public static Institution searchInstitutionByCode(Connection conn, Long code){
		Institution institution = new Institution();
		String query = "SELECT * FROM institution WHERE ist_code = ?;";

		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, code);
			ResultSet rs = stmt.executeQuery();
			if( rs.next() ){
				institution = buildInstitution(rs);
			}
			rs.close();
			stmt.close();
		} catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return institution;
		}
	}
	//usar clausula %like% do banco, para trazer por parte do nome
	@SuppressWarnings("finally")
	public static List<Institution> searchInstitutionByName(Connection conn, String strName){
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
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Institution> searchAllInstitution(Connection conn){
		List<Institution> institutions = new LinkedList<Institution>();
		String query = "SELECT * FROM institution;";

		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				institutions.add(buildInstitution(rs));
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {
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
