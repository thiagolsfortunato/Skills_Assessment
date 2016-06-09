package br.gov.sp.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.entity.Fatec_Courses;

public class DaoInstitutionCourse {
	
	@SuppressWarnings("finally")
	public static boolean insertIntitutionCourse(Connection conn, Long idInstitution, Long idCourse) throws SQLException{
		boolean insert = false;
		String sql = "INSERT INTO ist_crs (ist_code, crs_code) values (?, ?)";
		try{
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, idInstitution);
			stmt.setLong(2, idCourse);

			if (stmt.executeUpdate() != 0) {
				insert = true;
			}
			stmt.close();
		} finally {
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteIntitutionCourse(Connection conn, Long idInstitution, Long idCourse) throws SQLException {
		boolean delete = false;
		String sql = "DELETE FROM ist_crs WHERE ist_code = ? AND crs_code = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, idInstitution);
			stmt.setLong(2, idCourse);
			if (stmt.executeUpdate() != 0) {
				delete = true;
			}
			stmt.close();
		} finally {
			return delete;
		}		
	}
	
/*
 * NECESSÁRIO PARA USAR NO DropDownLists Cascade, na tela de registro do aluno
 * Retorno todas Fatecs com todos os cursos respectivos de cada uma.	
 */
	@SuppressWarnings("finally")
	public static List<Fatec_Courses> searchAllFatecCourses(Connection conn){
		List<Fatec_Courses> fatec_courses = null;
		String sql = "SELECT i.ist_code, i.ist_company "
					+ "FROM institution i;";
				
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if( rs.next() ){
				fatec_courses = buildFatecsCourses(conn, rs);
			}
			rs.close();
			stmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return fatec_courses;
		}
	}
	
	 
	private static List<Fatec_Courses> buildFatecsCourses(Connection conn, ResultSet rs) throws SQLException{
		List<Fatec_Courses> fat_cur = new ArrayList<Fatec_Courses>();
		do {
			fat_cur.add( buildFatecCourses(conn, rs) );
		} while (rs.next());
		return fat_cur;
	}
	
	private static Fatec_Courses buildFatecCourses(Connection conn, ResultSet rs) throws SQLException{
		Fatec_Courses fc = new Fatec_Courses();
		fc.setCode(rs.getLong("i.ist_code"));
		fc.setName(rs.getString("i.ist_company"));
		fc.setCourses( DaoCourse.searchCoursesByInstitionId(conn, fc.getCode()) );
		
		return fc;
	}
	
	
}
