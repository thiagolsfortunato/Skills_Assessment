package br.com.fatec.connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;

import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.model.ModelQuestion;
import br.com.fatec.model.Competencies.Competence;
import br.com.fatec.model.question.Answer;
import br.com.fatec.model.question.Competencies;
import br.com.fatec.model.question.Question;
import br.com.fatec.model.student.Student;



public class Main {
	
	static ModelQuestion model = new ModelQuestion();
	
	public static void main(String[] args) throws JSONException{
		
		
		
		
		
		//initializeModel();
			
		//REST controller = new REST(model); 
		
		//controller.getStudentCompetencies();
		//controller.getQuestionByNumber();
		//ConnectionMySql connection = new ConnectionMySql();
		//connection.getConnection();
		
		DaoEmployee dao = new DaoEmployee();
		try {
			dao.searchForName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void initializeModel(){
		
	}
	
}
