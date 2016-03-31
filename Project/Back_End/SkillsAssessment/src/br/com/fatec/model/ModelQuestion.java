package br.com.fatec.model;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoQuestion;
import br.com.fatec.entity.Question;

public class ModelQuestion {
	
	public Question searchQuestionByCode(Long code) {
		return DaoQuestion.searchQuestionByCode(code);
	}
	
	public List<Question> searchAllQuestion (){
		return DaoQuestion.searchAllQuestion();
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
