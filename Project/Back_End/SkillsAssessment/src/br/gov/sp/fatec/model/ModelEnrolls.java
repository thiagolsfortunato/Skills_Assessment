package br.gov.sp.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.gov.sp.fatec.connection.ConnectionFactory;
import br.gov.sp.fatec.dao.DaoEnrolls;
import br.gov.sp.fatec.dao.DaoUser;
import br.gov.sp.fatec.entity.Enrolls;
import br.gov.sp.fatec.entity.Student;
import br.gov.sp.fatec.entity.User;

public class ModelEnrolls {
	
	//conexão com o banco de dados
	private Connection conn = null;
	
	@SuppressWarnings("finally")
	public boolean insertComment(String txt, Long userCode){
		boolean transaction = false;
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			boolean idUser = DaoEnrolls.insertComment(conn, txt, userCode);
			boolean status = DaoEnrolls.setVerified(conn, userCode, 2);
			
			if( (idUser != false) && status ) {
				conn.commit(); 
				transaction = true;
			} else {
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean insertEnrolls(Enrolls enrolls, User user){
		
		boolean statusEnrolls = false;
		boolean transaction = false;
		
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			Long idUser = DaoUser.insertUser(conn, user, user.getInstCode());
			//resultado da inserção do usuario + estudante
			if ( idUser != null ){
				statusEnrolls = DaoEnrolls.insertEnrolls(conn, enrolls, idUser);
			}
			
			if( statusEnrolls ) {
				conn.commit(); //se deu tudo certo comita!
				transaction = true;
			} else {
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateEnrolls(Enrolls enrolls){
		boolean transaction = false;
		
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction =  DaoEnrolls.updateEnrolls(conn, enrolls);
			
			if (transaction) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteEnrolls(Long code){
		
		boolean transaction = false;
		
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoEnrolls.deleteEnrolls(conn, code);
			transaction = DaoUser.deleteUser(conn, code);
			
			if (transaction) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}

/*	SE FOR NECESSARIO EM ALGUM LUGAR ATIVAMOS NOVAMENTE
 * 	
	@SuppressWarnings("finally")
	public Enrolls searchEnrollsByCode(Long code) {
		Enrolls enrolls = null;
		try{
			conn = new ConnectionFactory().getConnection();
			enrolls = DaoEnrolls.searchEnrollsById(conn, code);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return enrolls;
		}
	}

	@SuppressWarnings("finally")
	public List<Enrolls> searchAllEnrolls(){
		List<Enrolls> enrolls = null;
		try{
			conn = new ConnectionFactory().getConnection();
			enrolls = DaoEnrolls.searchAllEnrolls(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return enrolls;
		}
	}
*/	
	@SuppressWarnings("finally")
	public boolean validateStudent(String email, Long ra){
		boolean validate = false;
		try{
			conn = new ConnectionFactory().getConnection();
			validate = DaoEnrolls.validate(conn, email, ra);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return validate;
		}
	}
	
	@SuppressWarnings("finally")
	public Student searchStudentById(Long id) throws SQLException{
		Student student = new Student();
		try{
			conn = new ConnectionFactory().getConnection();
			student = DaoEnrolls.searchStudentById(conn, id); 
		}catch (SQLException e ){
			System.out.println("Will not it was possible to find the Student");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return student;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Student> searchAllStudents(Long idFatec){
		List<Student> students = new LinkedList<Student>();
		try{
			conn = new ConnectionFactory().getConnection();
			students = DaoEnrolls.searchAllStudents(conn, idFatec);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Will not it was possible to find the Students");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return students;
		}
	}
}
