package br.com.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoQuiz;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;
import br.com.fatec.entity.Result;

public class ModelQuiz {
	
	private Connection conn;
	
	@SuppressWarnings("finally")
	public boolean insertQuiz(Quiz quiz){
		boolean transaction = false;
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoQuiz.insertQuiz(conn, quiz);
			if (transaction){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("an error occurred while trying to insert a quiz");
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
	public Question getQuestion(Long userId){
		Question question = null;
		try {
			conn = new ConnectionFactory().getConnection();
			question = DaoQuiz.getQuestion(conn, userId);
			question.setUnansweredQuestions(this.getValidQuestions(userId));
			question.setQuestionAmount(this.getNumberOfQuestions());
		} catch (SQLException e) {
			System.out.println("an error occurred while trying to get a quiz");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public Result getAverage(Long userId){
		Result result = null;
		try {
			conn = new ConnectionFactory().getConnection();
			result = DaoQuiz.getAverage(conn, userId);
		} catch (SQLException e) {
			System.out.println("an error occurred while trying to get a average");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Result> getAverages(Long instCode){
		List<Result> result = new LinkedList<>();
		try {
			conn = new ConnectionFactory().getConnection();
			result = DaoQuiz.getResultStudents(conn, instCode);
		} catch (SQLException e) {
			System.out.println("an error occurred while trying to get a average");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public Integer getValidQuestions(Long userId){
		
		Integer question = null;
		try {
			conn = new ConnectionFactory().getConnection();
			question = DaoQuiz.getValidQuestions(conn, userId);
			
		} catch (SQLException e) {
			System.out.println("an error occurred to bring the amount of issues: "+e);
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public Integer getNumberOfQuestions(){
		Integer question = null;
		try {
			conn = new ConnectionFactory().getConnection();
			question = DaoQuiz.getQuestionAmount(conn);
		} catch (SQLException e) {
			System.out.println("an error occurred to bring the amount of issues: "+e);
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return question;
		}
	}
}
