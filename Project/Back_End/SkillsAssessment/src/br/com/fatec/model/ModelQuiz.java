package br.com.fatec.model;

import java.sql.SQLException;

import br.com.fatec.dao.DaoQuiz;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;

public class ModelQuiz {
	
	@SuppressWarnings("finally")
	public boolean insertQuiz(Quiz quiz){
		try {
			return DaoQuiz.insertQuiz(quiz);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("an error occurred while trying to insert a quiz");
			return false;
		}
	}
	
	@SuppressWarnings("finally")
	public Question getQuestion(Long userId){
		Question question = null;
		try {
			question = DaoQuiz.getQuestion(userId);
		} catch (SQLException e) {
			System.out.println("an error occurred while trying to get a quiz");
			e.printStackTrace();
		}finally {
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public Integer getValidQuestions(Long userId){
		Integer question = null;
		try {
			question = DaoQuiz.getValidQuestions(userId);
		} catch (SQLException e) {
			System.out.println("an error occurred to bring the amount of issues: "+e);
			e.printStackTrace();
		}finally {
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public Integer getNumberOfQuestions(){
		Integer question = null;
		try {
			question = DaoQuiz.getQuestionAmount();
		} catch (SQLException e) {
			System.out.println("an error occurred to bring the amount of issues: "+e);
			e.printStackTrace();
		}finally {
			return question;
		}
	}
}
