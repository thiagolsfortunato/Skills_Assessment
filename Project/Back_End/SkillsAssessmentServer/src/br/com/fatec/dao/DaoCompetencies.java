package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fatec.connection.ConnectionMySql;

public class DaoCompetencies {
	
	public void insertCompetence() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from competences;";
	}
	
	public void updateCompetence() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from competences;";
	}
	
	public void searchCompetence() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from competences;";
	}
	
	public void deleteCompetence() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from competences;";
	}

}
