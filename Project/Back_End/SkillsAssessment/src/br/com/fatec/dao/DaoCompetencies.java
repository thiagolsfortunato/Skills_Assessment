package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Competence;

public class DaoCompetencies {

	// Insert Competence
	@SuppressWarnings("finally")
	public static boolean insertCompetence(Competence competence) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "insert into competence (com_code, com_type, com_registration_date, com_situation) values (?, ?, ?, ?);";

		boolean insert = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,String.valueOf(competence.getCode()));
			connection.getStatement().setString(2,competence.getType());
			connection.getStatement().setString(3,competence.getRegister());
			connection.getStatement().setString(4,competence.getRegister());//wrong
			
			if (connection.executeSql()) {
				insert = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.getStatement().close();
			connection.close();
			return insert;
		}
	}
	
	// Delete Competence
	@SuppressWarnings("finally")
	public static boolean deleteCompetence(Long code) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "delete from competence where com_code = ?;";
		Competence competence = new Competence();
		
		boolean delete = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,String.valueOf(competence.getCode()));
			if (connection.executeSql()) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.getStatement().close();
			connection.close();
			return delete;
		}
	}

	// Search competence by name
	@SuppressWarnings("finally")
	public static Competence searchCompetenceByCode(Long code) throws SQLException {
		Competence competence = new Competence();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select com_code, com_type, com_registration_date, com_situation"
				+ "from competence where com_code = ?'";
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(query));
			connection.getStatement().setString(1,String.valueOf(code));
			if (connection.executeQuery()) {
					competence.setCode(Long.parseLong(connection.returnField("COM_CODE")));
					competence.setType(connection.returnField("COM_TYPE"));
					competence.setRegister(connection.returnField("COM_REGISTRATION_DATE"));
					//competence.setSituation(Integer.parseInt(connection.returnField("COM_SITUATION")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//connection.getResultset().close();
			connection.getStatement().close();
			connection.close();
			return competence;
		}
	}

	// Update Competence
	@SuppressWarnings("finally")
	public static boolean updateCompetence(Competence competence, Long code) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "update competence set COM_CODE=?, COM_TYPE= ?, COM_REGISTRATION_DATE= ?,"
				+ "COM_SITUATION=? where com_code=?;";
		boolean update = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setString(1,String.valueOf(competence.getCode()));
			connection.getStatement().setString(2,competence.getType());
			connection.getStatement().setString(3,competence.getRegister());
			//connection.getStatement().setString(4,String.valueOf(competence.getSituation()));
			connection.getStatement().setString(5, String.valueOf(code));
			if (connection.executeSql()) {
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.getStatement().close();
			connection.close();
			return update;
		}
	}
		
	// Search all competences - MEXER AQUI !!!!
	@SuppressWarnings({ "finally", "null" })
	public static List<Competence> searchAll() throws SQLException {
		List<Competence> listCompetence = null;
		String sql = "select * from COMPETENCE ;";
		ConnectionMySql connection = new ConnectionMySql();
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			if (connection.executeQuery()) {
				do {
					Competence competence = new Competence();
					competence.setCode(Long.parseLong(connection.returnField("COM_CODE")));
					competence.setType(connection.returnField("COM_TYPE"));
					competence.setRegister(connection.returnField("COM_REGISTRATION_DATE"));
					//competence.setSituation(Integer.parseInt(connection.returnField("COM_SITUATION")));
					listCompetence.add(competence);
				} while (connection.nextRegister());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.returnRegister().close();
			connection.getStatement().close();
			connection.close();
			return listCompetence;
		}
	}	
}