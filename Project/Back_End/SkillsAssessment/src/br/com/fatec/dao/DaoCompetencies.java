package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Competence;

public class DaoCompetencies {

	// Insert Competence
	@SuppressWarnings("finally")
	public static boolean insertCompetence(Competence competence) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "insert into competence (com_kind, com_registration_date, com_weight) values ("
				+ competence.getKind() + "," + competence.getRegister() + "," + competence.getWeight() + ");";

		boolean insert = false;
		try {
			connection.conect();
			if (connection.executeSql(sql)) {
				insert = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return insert;
		}
	}
	
	// Delete Competence
	@SuppressWarnings("finally")
	public static boolean deleteCompetence(Long code) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "delete from competence where com_code = " + code + ";";

		boolean delete = false;
		try {
			connection.conect();
			if (connection.executeSql(sql)) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return delete;
		}
	}

	// Search competence by name
	@SuppressWarnings("finally")
	public static Competence searchCompetenceByCode(Long code) throws SQLException {
		Competence competence = new Competence();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select com_code, com_kind, com_registration_date, com_weight from competence where com_code = '"
				+ code + "';";
		try {
			connection.conect();
			if (connection.executeQuery(query)) {
				do {
					competence.setCode(Long.parseLong(connection.returnField("COM_CODE")));
					competence.setKind(connection.returnField("COM_KIND"));
					competence.setRegister(connection.returnField("COM_REGISTRATION_DATE"));
					competence.setWeight(Integer.parseInt(connection.returnField("COM_WEIGHT")));
				} while (connection.nextRegister());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.returnRegister().close();
			connection.close();
			return competence;
		}
	}

	// Update Competence
	@SuppressWarnings("finally")
	public static boolean updateCompetence(Competence competence) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "update competence set com_kind='" + competence.getKind() + "', " + "com_registration_date='"
				+ competence.getRegister() + ", " + "com_weight=" + competence.getWeight() + "where com_code ="
				+ competence.getCode() + ";";
		boolean update = false;
		try {
			connection.conect();
			if (connection.executeSql(sql)) {
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return update;
		}
	}
		
	// Search all competences
	@SuppressWarnings("finally")
	public static List<Competence> searchAll() throws SQLException {
		List<Competence> listCompetence = null;
		String query = "select * from COMPETENCE ;";
		ConnectionMySql connection = new ConnectionMySql();
		try {
			connection.conect();
			if (connection.executeQuery(query)) {
				do {
					Competence competence = new Competence();
					competence.setCode(Long.parseLong(connection.returnField("COM_CODE")));
					competence.setKind(connection.returnField("COM_KIND"));
					competence.setRegister(connection.returnField("COM_REGISTRATION_DATE"));
					competence.setWeight(Integer.parseInt(connection.returnField("COM_WEIGHT")));
					listCompetence.add(competence);
				} while (connection.nextRegister());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.returnRegister().close();
			connection.close();
			return listCompetence;
		}
	}	
}