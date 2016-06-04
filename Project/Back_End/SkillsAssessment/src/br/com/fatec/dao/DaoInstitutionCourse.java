package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.entity.Course;
import br.com.fatec.entity.Fatec_Courses;

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
		String sql = "SELECT i.ist_code, i.ist_company, c.crs_code, c.crs_name "
				+ "FROM institution i INNER JOIN ist_crs ic ON i.ist_code = ic.ist_code "
				+ "INNER JOIN course c ON ic.crs_code = c.crs_code;";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if( rs.next() ){
				fatec_courses = buildFatecsCourses(rs);
			}
			rs.close();
			stmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return fatec_courses;
		}
	}
	
	 
	private static List<Fatec_Courses> buildFatecsCourses(ResultSet rs) throws SQLException{
		List<Fatec_Courses> fat_cur = new ArrayList<Fatec_Courses>();
		do {
			fat_cur.add( buildFatecCourses(rs) );
		} while (rs.next());
		return fat_cur;
	}
	
	private static Fatec_Courses buildFatecCourses(ResultSet rs) throws SQLException{
		Fatec_Courses fc = new Fatec_Courses();
		fc.setCode(rs.getLong("i.ist_code"));
		fc.setName(rs.getString("i.ist_company"));
		fc.setCourses( buildCourses(rs) );
		
		return fc;
	}
	
	private static List<Course> buildCourses(ResultSet rs) throws SQLException{
		List<Course> courses = new ArrayList<Course>();
		do {
			courses.add( buildCourse(rs) );
		}while(rs.next());
		return courses;
	}
	private static Course buildCourse(ResultSet rs) throws SQLException{
		Course course = new Course();
		course.setCodeCourse(rs.getLong("c.crs_code"));
		course.setName(rs.getString("c.crs_name"));
		return course;
	}
	
}
