package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.fatec.entity.Competence;

public class DaoCompetencies {

	// Insert Competence - FUNCIONANDO!!
		@SuppressWarnings("finally")
		public static boolean insertCompetence(Connection conn, Competence competence) throws SQLException {
			
			String sql = "INSERT INTO competence (com_type, com_registration_date) VALUES (?, date_format(now(), '%Y-%m-%d'))";

			boolean transaction = false;
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, competence.getType());			
				if ( stmt.executeUpdate() != 0 ) {
					transaction = true;
				}
				stmt.close();
			} finally {
				return transaction;
			}
		}
		
		// Delete Competence - FUNCIONANDO!!
		@SuppressWarnings("finally")
		public static boolean deleteCompetence(Connection conn, Long code) throws SQLException {
			
			String sql = "DELETE FROM competence WHERE com_code = ?;";		
			boolean transaction = false;
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setLong(1, code);
				if ( stmt.executeUpdate() != 0 ) {
					transaction = true;
				}
				stmt.close();
			} finally {
				return transaction;
			}
		}

		// Search competence by code - FUNCIONANDO!!
		@SuppressWarnings("finally")
		public static Competence searchCompetenceByCode(Connection conn, Long code) throws SQLException {
			Competence competence = new Competence();
			String query = "select COM_CODE, COM_TYPE, DATE_FORMAT(COM_REGISTRATION_DATE, '%d-%m-%Y') as COM_REGISTRATION_DATE from competence where com_code = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setLong(1, code);
				ResultSet rs = stmt.executeQuery();
				if ( rs.next() ) {
						competence.setCode(rs.getLong("COM_CODE"));
						competence.setType(rs.getString("COM_TYPE"));
						competence.setRegistration_date(rs.getString("COM_REGISTRATION_DATE"));
				}
				rs.close();
				stmt.close();
			} finally {
				return competence;
			}
		}

		// Update Competence - FUNCIONANDO!!!
		@SuppressWarnings("finally")
		public static boolean updateCompetence(Connection conn, Competence competence) throws SQLException {
			
			String sql = "update competence set COM_TYPE= ?, COM_REGISTRATION_DATE = date_format(now(), '%Y-%m-%d') where COM_CODE=?;";
			boolean transaction = false;
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, competence.getType());
				stmt.setLong(2, competence.getCode());
				if ( stmt.executeUpdate() != 0 ) {
					transaction = true;
				}
				stmt.close();
			} finally {
				return transaction;
			}
		}
			
		// Search all competences - FUNCIONANDO !!!!
		@SuppressWarnings({ "finally" })
		public static List<Competence> searchAll(Connection conn) throws SQLException {
			List<Competence> listCompetence = new ArrayList<>();
			String sql = "select COM_CODE, COM_TYPE, DATE_FORMAT(COM_REGISTRATION_DATE, '%d-%m-%Y') as COM_REGISTRATION_DATE from COMPETENCE ;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if ( rs.next() ) {
					do {
						Competence competence = new Competence();
						competence.setCode(rs.getLong("COM_CODE"));
						competence.setType(rs.getString("COM_TYPE"));
						competence.setRegistration_date(rs.getString("COM_REGISTRATION_DATE"));
						listCompetence.add(competence);
					} while ( rs.next() );
				}
				rs.close();
				stmt.close();
			} finally {
				return listCompetence;
			}
		}
}