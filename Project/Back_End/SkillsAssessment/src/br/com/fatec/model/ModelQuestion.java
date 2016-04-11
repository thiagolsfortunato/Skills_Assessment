package br.com.fatec.model;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoQuestion;
import br.com.fatec.entity.Question;

public class ModelQuestion {
	
	@SuppressWarnings("finally")
	public Question searchQuestionByCode(Long code) throws SQLException{
		Question question = null;
		try{
			question = DaoQuestion.searchQuestionByCode(code);
		} finally{
			return question;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Question> searchAllQuestion() throws SQLException {
		List<Question> questions = null;
		try{
			questions = DaoQuestion.searchAllQuestion(); 
		}finally{
			return questions;
		}
	}
	
	public boolean insertQuestion(Question question) {
		try {
			return DaoQuestion.insertQuestion(question);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateQuestion(Question question){
		try {
			return DaoQuestion.updateQuestion(question);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteQuestion(Long code){
		try {
			return DaoQuestion.deleteQuestion(code);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
