package br.com.fatec.model;

import java.sql.SQLException;

import br.com.fatec.dao.DaoQuiz;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;

public class ModelQuiz {
	
	@SuppressWarnings("finally")
	public boolean insertQuiz(Quiz quiz){
		boolean quizReturn = false;
		try {
			quizReturn = DaoQuiz.insertQuiz(quiz);
		} catch (SQLException e) {
			System.out.println("an error occurred while trying to insert a quiz");
		}finally {
			return quizReturn;
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
}
