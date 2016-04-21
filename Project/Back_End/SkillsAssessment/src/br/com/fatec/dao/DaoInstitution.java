package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Institution;

public class DaoInstitution {
	
	@SuppressWarnings("finally")
	public static boolean insertInstitution(Institution institution) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean sucess = false;
		try {
			String insert = "INSERT INTO institution (ist_company ,ist_cnpj, ist_city) VALUES (?,?,?);";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement( insert ) );
			conn.getStatement().setString(1, institution.getCompany());
			conn.getStatement().setString(2, institution.getCnpj());
			conn.getStatement().setString(3, institution.getCity());
			
			if(conn.executeSql()){
				System.out.println("the Institution has been successfully inserted!");
				sucess = true;
			}
		} finally {
			conn.close();
			return sucess;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteInstitution(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean delete = false;
		try {
			conn.conect();
			String sql = "DELETE FROM institution WHERE ist_code = ?;";
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
	
	@SuppressWarnings("finally")
	public static boolean updateInstitution(Institution institution) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean sucess = false;
		try {
			String update = "UPDATE institution SET ist_company = ?, ist_cnpj = ?, ist_city = ? WHERE ist_code = ?;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(update));
			conn.getStatement().setString(1, institution.getCompany());
			conn.getStatement().setString(2, institution.getCnpj());
			conn.getStatement().setString(3, institution.getCity());
			conn.getStatement().setLong(4, institution.getCodeInstitution());
			
			if(conn.executeSql()){
				System.out.println("Institution has been successfully updated!");
					sucess = true;	
			}
		}finally {
			conn.close();
			return sucess;
		}
	}
	
	@SuppressWarnings("finally")
	public static Institution searchInstitutionByCode(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		Institution institution = new Institution();

		try {
			String query = "SELECT * FROM institution WHERE ist_code = ?;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, code);
			if (conn.executeQuery()){
				institution = buildInstitution(conn.getResultset());
			}
		} finally {
			conn.close();
			return institution;
		}
	}
	//usar clausula %like% do banco, para trazer por parte do nome
	@SuppressWarnings("finally")
	public static List<Institution> searchInstitutionByName(String strName) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		List<Institution> institutions = new LinkedList<Institution>();

		try {
			String query = "SELECT * FROM institution WHERE ist_company LIKE '%"+ strName +"%';";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			//conn.getStatement().setString(1, strName);
			if (conn.executeQuery()){
				institutions = buildInstitutions(conn);
			}
		} finally {
			conn.close();
			return institutions;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Institution> searchAllInstitution() throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		List<Institution> institutions = new LinkedList<Institution>();

		try {
			String query = "SELECT * FROM institution;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()){
				institutions = buildInstitutions(conn);
			}
		} finally {
			conn.close();
			return institutions;
		}
	}
	
	private static List<Institution> buildInstitutions(ConnectionMySql conn) throws SQLException{
		List<Institution> institutions = new LinkedList<Institution>();
		do {
			institutions.add(buildInstitution(conn.getResultset()));
		} while (conn.nextRegister());
		return institutions;
	}
	
	private static Institution buildInstitution (ResultSet rs) throws SQLException{
		Institution institution = new Institution();
		institution.setCodeInstitution(rs.getLong("IST_CODE"));
		institution.setCompany(rs.getString("IST_COMPANY"));
		institution.setCnpj(rs.getString("IST_CNPJ"));
		institution.setCity(rs.getString("IST_CITY"));
		return institution;
	}

}
