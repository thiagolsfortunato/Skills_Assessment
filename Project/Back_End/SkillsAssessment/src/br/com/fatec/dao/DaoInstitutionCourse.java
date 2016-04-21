package br.com.fatec.dao;

import java.sql.SQLException;

import br.com.fatec.connection.ConnectionMySql;

public class DaoInstitutionCourse {
	
	@SuppressWarnings("finally")
	public static boolean insertIntitutionCourse(Long idInstitution, Long idCourse) throws SQLException{
		ConnectionMySql connection = new ConnectionMySql();
		boolean insert = false;
		try{
			String sql = "insert into ist_crs (ist_code, crs_code) values (?,?)";
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setLong(1, idInstitution);
			connection.getStatement().setLong(2, idCourse);
			if(connection.executeSql()){
				insert = true;
			}
		} finally {
			connection.close();
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteIntitutionCourse(Long idInstitution, Long idCourse) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		boolean delete = false;
		try{
			String sql = "delete from ist_crs where ist_code = ? and crs_code = ?";
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setLong(1, idInstitution);
			connection.getStatement().setLong(2, idCourse);
			if(connection.executeSql()){
				delete = true;
			}
		} finally {
			connection.close();
			return delete;
		}		
	}
	
	
}
