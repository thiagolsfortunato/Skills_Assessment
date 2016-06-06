package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.entity.Enrolls;

import br.com.fatec.entity.Student;


public class DaoEnrolls {

	@SuppressWarnings("finally")
	public static boolean insertEnrolls(Connection conn, Enrolls enrolls, Long codeUser) throws SQLException{

		String sql = "INSERT INTO ENROLLS (ern_year, ern_period, crs_code, usr_code) VALUES (?, ?, ?, ?);";
		boolean insert = false;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, enrolls.getYear());
			stmt.setInt(2, enrolls.getPeriod());
			stmt.setLong(3, enrolls.getCodeCourse());
			stmt.setLong(4, codeUser);
			if( stmt.executeUpdate() != 0 ){
				insert = insertResult(conn, codeUser);
			}
			stmt.close();
		}finally{
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteEnrolls(Connection conn, Long codeUser) throws SQLException {

		String sql = "DELETE FROM enrolls WHERE usr_code = ?;";
		boolean delete = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codeUser);
			if (stmt.executeUpdate() != 0) {
				delete = true;
			}
			stmt.close();
		} finally {
			return delete;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateEnrolls(Connection conn, Enrolls enrolls) throws SQLException{

		String sql = "UPDATE enrolls SET crs_code = ? WHERE usr_code = ?;";
		boolean update = false;
		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, enrolls.getCodeCourse());
			stmt.setLong(2, enrolls.getCodeUser());
			if( stmt.executeUpdate() != 0 ){
				update = true;
			}
			stmt.close();
		} finally {
			return update;
		}
	}	

	@SuppressWarnings("finally")
	public static Student searchStudentById(Connection conn, Long id) throws SQLException{
		Student student = null;
		String query = "SELECT usr.usr_code, usr.usr_userName, usr.usr_password, usr.usr_ra, usr.usr_verified, usr.usr_type, usr.usr_name, usr.usr_register, "
				+ "crs.crs_name, ins.ist_company, enr.ern_year, enr.ern_period "
				+ "FROM  user usr "
				+ "INNER JOIN enrolls enr ON (usr.usr_code = enr.usr_code) "
				+ "INNER JOIN course crs ON (enr.crs_code = crs.crs_code) "
				+ "INNER JOIN ist_crs  icr ON (crs.crs_code = icr.crs_code) "
				+ "INNER JOIN institution ins ON (icr.ist_code = ins.ist_code) "
				+ "WHERE usr.usr_type = 'student' AND usr.usr_code = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				student = buildStudent( rs );
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {		
			return student;
		}
	}
	@SuppressWarnings("finally")
	public static List<Student> searchAllStudents(Connection conn, Long idFatec) throws SQLException{
		List<Student> students = new LinkedList<Student>();
		String query = "SELECT usr.usr_code, usr.usr_userName, usr.usr_password, usr.usr_ra, usr.usr_verified, usr.usr_type, usr.usr_name, usr.usr_register, "
				+ "crs.crs_name, ins.ist_company, enr.ern_year, enr.ern_period "
				+ "FROM  user usr "
				+ "INNER JOIN enrolls enr ON (usr.usr_code = enr.usr_code) "
				+ "INNER JOIN course crs ON (enr.crs_code = crs.crs_code) "
				+ "INNER JOIN ist_crs  icr ON (crs.crs_code = icr.crs_code) "
				+ "INNER JOIN institution ins ON (icr.ist_code = ins.ist_code) "
				+ "WHERE usr.usr_type = 'student' AND ins.ist_code = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, idFatec);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ){
				students = buildStudents( rs );
			}
			rs.close();
			stmt.close();
		} finally {		
			return students;
		}
	}
/*	SE FORMOS UTILIZAR EM ALGUM LUGAR AIVAMOS NOVAMENTE
 * 
	@SuppressWarnings("finally")
	public static List<Enrolls> searchAllEnrolls(Connection conn) throws SQLException {
		
		List<Enrolls> listEnrolls = new ArrayList<>();
		String query = "SELECT * FROM ENROLLS;";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listEnrolls.add(buildEnroll(rs));
			}
		} finally {
			conn.close();
			return listEnrolls;
		}
	}
	
	@SuppressWarnings("finally")
	public static Enrolls searchEnrollsById(Connection conn, Long codeEnrolls) throws SQLException {

		String query = "SELECT * FROM ENROLLS WHERE ERN_CODE = ?;";
		Enrolls enrolls = new Enrolls();
		try {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			enrolls = buildEnroll(rs);
		} finally {
			conn.close();
			return enrolls;
		}
	}
*/	
	@SuppressWarnings("finally")
	public static Long searchEnrollsByUserId(Connection conn, Long idUser) throws SQLException {

		String query = "SELECT ERN_CODE FROM ENROLLS WHERE USR_CODE = ?";
		Long idEnrolls = null;
		try{
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				idEnrolls = rs.getLong("ERN_CODE");
			}
		} finally {
			conn.close();
			return idEnrolls;
		}
		
	}
	
	@SuppressWarnings("finally")
	public static boolean insertResult(Connection conn, Long codeUser) throws SQLException{
		
		String sql = "INSERT INTO RESULT (usr_code) VALUES (?);";
		boolean insert = false;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codeUser);
			if( stmt.executeUpdate() != 0 ){
				insert = true;
			}
			stmt.close();
		}finally{
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean insertComment(Connection conn, String txt, Long codeUser) throws SQLException{

		String sql = "UPDATE result SET rst_comment = ? WHERE usr_code = ?;";
		boolean insert = false;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, txt);
			stmt.setLong(2, codeUser);
			if( stmt.executeUpdate() != 0 ){
				insert = true;
			}
			stmt.close();
		} catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long getResult(Connection conn, Long idUser) throws SQLException {

		String query = "SELECT RST_CODE FROM RESULT WHERE USR_CODE = ?";
		Long result_code = null;
		try{
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, idUser);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result_code = rs.getLong("RST_CODE");
			}
		} finally {
			return result_code;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateResult(Connection conn, Long userCode) throws SQLException{

		String sql = "UPDATE RESULT SET RST_DATE_FINAL = date_format(now(), '%Y-%m-%d'), RST_COMPLETED = ? where USR_CODE = ?;";
		boolean update = false;
		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setLong(2, userCode);
	
			if( stmt.executeUpdate() != 0 ){
				update = true;
			}
			stmt.close();
		} finally {
			return update;
		}
	}	
	
	@SuppressWarnings("finally")
	public static boolean setVerified(Connection conn, Long userCode, Integer status){
		String sql = "UPDATE user SET usr_verified = ? WHERE usr_code = ?;";
		boolean update = false;
		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, status);
			stmt.setLong(2, userCode);
	
			if( stmt.executeUpdate() != 0 ){
				update = true;
			}
			stmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			return update;
		}
	}
	private static List<Student> buildStudents ( ResultSet rs ) throws SQLException {
		List<Student> students = new LinkedList<Student>();
		
		do {
			students.add( buildStudent(rs) );
		} while (rs.next() );
		
		return students;
	}
	
	private static Student buildStudent (ResultSet rs){
		Student student = new Student();
		try {
			student.setRa(rs.getString("usr.usr_ra"));
			student.setYear(rs.getInt("enr.ern_year"));
			student.setPeriod(rs.getInt("enr.ern_period"));
			student.setCourse(rs.getString("crs.crs_name"));
			student.setInstitution(rs.getString("ins.ist_company"));
			student.setRegistration_date(rs.getString("usr.usr_register"));
			student.setVerification(rs.getInt("usr.usr_verified"));
			student.getUser().setName(rs.getString("usr.usr_name"));
			student.getUser().setUserName(rs.getString("usr.usr_userName"));
			student.getUser().setPassword(rs.getString("usr.usr_password"));
			student.getUser().setType(rs.getString("usr.usr_type"));
			student.getUser().setUserCode(rs.getLong("usr.usr_code"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
}

