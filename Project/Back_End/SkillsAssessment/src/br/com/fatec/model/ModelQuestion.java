package br.com.fatec.model;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoQuestion;
import br.com.fatec.entity.Question;

public class ModelQuestion {
	
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
	
	public boolean insertQuestion(Question question) {
		try {
			return DaoQuestion.insertQuestion(question);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Will not it was possible to insert the Institution");
			return false;
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
