package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.question.Competence;

public class DaoCompetencies {
	
	static Connection conn = null;
	static Date dataHora = new Date();
	
	//Search competence by name
	@SuppressWarnings("finally")
	public static Competence searchCompetenceByCode (Long code) throws SQLException {
		Competence compe = new Competence();
		conn = (Connection) ConnectionMySql.getConnection();
		ResultSet rs = null;
		try{
			String query = "select com_code, com_kind, com_registration_date, com_weight from competence where com_code = '"
				+code;
			PreparedStatement cmd = (PreparedStatement) conn.prepareStatement(query);
			rs = cmd.executeQuery();
			while(rs.next()){
				rs.next();
				compe.setNumber(Long.parseLong(rs.getString("COM_CODE")));
				compe.setDescription(rs.getString("COM_KIND"));
				compe.setRegister(rs.getDate("COM_REGISTRATION_DATE"));
				compe.setWeight(Integer.parseInt("COM_WEIGHT"));
			}
		} catch (SQLException e){
			// TODO: handle exception
		} finally {
			rs.close();
			conn.close();
			return compe;
		}
	}
	
	//Search all competences
	@SuppressWarnings("null")
	public static List<Competence> searchAll() throws SQLException {
		List<Competence> listCompetence = null;
		
		Connection conn = (Connection) ConnectionMySql.getConnection();
		String query = "select * from COMPETENCE ;";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		
		while (rs.next()) {
			Competence compet = new Competence();	
			compet.setNumber(Long.parseLong("COM_CODE"));
			compet.setDescription("COM_KIND");
			compet.setRegister(rs.getTime("COM_REGISTRATION_DATE"));
			compet.setWeight(Integer.parseInt("COM_WEIGHT"));
			
			
			listCompetence.add(compet);
		}
		rs.close();
		return listCompetence;
	}
	
	//Add Competence
	public static boolean addCompetence(long code, String description, Date date, int weight) throws SQLException {
		conn = (Connection) ConnectionMySql.getConnection();
		String query = "insert into competence (com_code, com_kind, com_registration_date, com_weight) values (" 
	+code + "," +description+", " +date+", "+weight+");";
		PreparedStatement cmd = (PreparedStatement) conn.prepareStatement(query);
		if(cmd.execute()) {
			cmd.close();
			conn.close();
			return true;
		} else {
			cmd.close();
			conn.close();
			return false;
		}
	}
	
	//Update Competence
	public static boolean updateCompetence(Competence comp, Long code) throws SQLException{
		Connection conn = (Connection) ConnectionMySql.getConnection();
		String sql = "update competence set com_code='"+comp.getNumber()+"', com_kind='"+comp.getDescription()+
				"', com_registration_date='"+comp.getRegister()+", com_weight="+comp.getWeight()+"where com_code = "+code;
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(sql);
		if(cmd.execute()){
			cmd.close();
			conn.close();
			return true;
		}else{
			conn.close();
			return false;
		}
	}
	
	//Delete Competence
	public static boolean deleteCompetence (String description) throws SQLException {
		conn = (Connection) ConnectionMySql.getConnection();
		String query = "delete from competence where com_kind = "+description;
		PreparedStatement cmd = (PreparedStatement) conn.prepareStatement(query);
		if(cmd.execute()){
			cmd.close();
			conn.close();
			return true;
		}
		else {
			conn.close();
			return false;
		}
		
	}
}