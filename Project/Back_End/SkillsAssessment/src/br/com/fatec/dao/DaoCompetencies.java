package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Competence;

public class DaoCompetencies {

	// Insert Competence - FUNCIONANDO!!
	@SuppressWarnings("finally")
	public static boolean insertCompetence(Competence competence) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "insert into competence (com_type, com_registration_date) values (?, date_format(now(), '%Y-%m-%d'))";

		boolean insert = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,competence.getType());			
			if (connection.executeSql()) {
				insert = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return insert;
		}
	}
	
	// Delete Competence - FUNCIONANDO!!
	@SuppressWarnings("finally")
	public static boolean deleteCompetence(Long code) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "delete from competence where com_code = ?;";		
		boolean delete = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,String.valueOf(code));
			if (connection.executeSql()) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return delete;
		}
	}

	// Search competence by code - FUNCIONANDO!!
	@SuppressWarnings("finally")
	public static Competence searchCompetenceByCode(Long code) throws SQLException {
		Competence competence = new Competence();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select COM_CODE, COM_TYPE, DATE_FORMAT(COM_REGISTRATION_DATE, '%d-%m-%Y') as COM_REGISTRATION_DATE from competence where com_code = ?";
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(query));
			connection.getStatement().setString(1,String.valueOf(code));
			if (connection.executeQuery()) {
					competence.setCode(Long.parseLong(connection.returnField("COM_CODE")));
					competence.setType(connection.returnField("COM_TYPE"));
					competence.setRegistration_date(connection.returnField("COM_REGISTRATION_DATE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return competence;
		}
	}

	// Update Competence - FUNCIONANDO!!!
	@SuppressWarnings("finally")
	public static boolean updateCompetence(Competence competence) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "update competence set COM_TYPE= ?, COM_REGISTRATION_DATE = date_format(now(), '%Y-%m-%d') where COM_CODE=?;";
		boolean update = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,competence.getType());
			connection.getStatement().setString(2, String.valueOf(competence.getCode()));
			if (connection.executeSql()) {
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return update;
		}
	}
		
	// Search all competences - FUNCIONANDO !!!!
	@SuppressWarnings({ "finally" })
	public static List<Competence> searchAll() throws SQLException {
		List<Competence> listCompetence = new ArrayList<>();
		String sql = "select COM_CODE, COM_TYPE, DATE_FORMAT(COM_REGISTRATION_DATE, '%d-%m-%Y') as COM_REGISTRATION_DATE from COMPETENCE ;";
		ConnectionMySql connection = new ConnectionMySql();
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			if (connection.executeQuery()) {
				do {
					Competence competence = new Competence();
					competence.setCode(Long.parseLong(connection.returnField("COM_CODE")));
					competence.setType(connection.returnField("COM_TYPE"));
					competence.setRegistration_date(connection.returnField("COM_REGISTRATION_DATE"));
					listCompetence.add(competence);
				} while (connection.nextRegister());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return listCompetence;
		}
	}
}