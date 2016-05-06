package br.com.fatec.model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoQuestion;
import br.com.fatec.entity.Question;

public class ModelQuestion {
	
	//conexão com o banco de dados
	private static Connection conn = null;
	
	@SuppressWarnings("finally")
	public Question searchQuestionByCode(Long code) {
		Question question = null;
		try{
			question = DaoQuestion.searchQuestionByCode(code);
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a Question");
		}finally{
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Question> searchAllQuestion() {
		List<Question> questions = null;
		try{
			questions = DaoQuestion.searchAllQuestion(); 
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("an error occurred while trying to search a Questions");
		}finally{
			return questions;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean insertQuestion(Question question) {
		
		conn = new ConnectionFactory().getConnection();
		boolean transaction = false;
		try {
			conn.setAutoCommit(false);
			transaction = DaoQuestion.insertQuestion(conn, question);
			
			if( transaction ) conn.commit(); //se deu tudo certo comita!
			else conn.rollback();
			
		} catch (SQLException e) {
			if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	            	excep.printStackTrace();//exception do rollback
	            }
	        }
			e.printStackTrace();
			System.out.println("Will not it was possible to insert the Institution");
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return transaction;
		}
	}
	
	public boolean updateQuestion(Question question){
		try {
			return DaoQuestion.updateQuestion(question);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("it was not possible to update the Institution");
			System.out.println(e);
			return false;
		}
	}
	
	public boolean deleteQuestion(Long code) {
		try {
			return DaoQuestion.deleteQuestion(code);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Will not it was possible to delete the Institution");
			return false;
		}
	}
	
}
