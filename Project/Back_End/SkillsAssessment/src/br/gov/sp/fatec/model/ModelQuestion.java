package br.gov.sp.fatec.model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.gov.sp.fatec.connection.ConnectionFactory;
import br.gov.sp.fatec.dao.DaoQuestion;
import br.gov.sp.fatec.entity.Question;

public class ModelQuestion {
	
	//conexão com o banco de dados
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public Question searchQuestionByCode(Long code) {
		Question question = null;
		try{
			conn = new ConnectionFactory().getConnection();
			question = DaoQuestion.searchQuestionByCode(conn, code);
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a Question");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Question> searchAllQuestion() {
		List<Question> questions = null;
		try{
			conn = new ConnectionFactory().getConnection();
			questions = DaoQuestion.searchAllQuestion(conn); 
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a Questions");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return questions;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean insertQuestion(Question question) {
		boolean transaction = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoQuestion.insertQuestion(conn, question);
			
			if( transaction ) {
				conn.commit(); //se deu tudo certo comita!
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			System.out.println("Will not it was possible to insert the Institution");
			
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
	public boolean updateQuestion(Question question){
		boolean transaction = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoQuestion.updateQuestion(conn, question);
			if( transaction ) {
				conn.commit(); //se deu tudo certo comita!
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			System.out.println("it was not possible to update the Institution");
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
	public boolean deleteQuestion(Long code) {
		boolean transaction = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoQuestion.deleteQuestion(conn, code);
			if( transaction ) {
				conn.commit(); //se deu tudo certo comita!
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			System.out.println("Will not it was possible to delete the Institution");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}
	
}
