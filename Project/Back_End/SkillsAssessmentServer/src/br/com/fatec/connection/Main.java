package br.com.fatec.connection;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;

import br.com.fatec.controller.UserRoutes;
import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.dao.DaoStudent;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.model.ModelQuestion;
import br.com.fatec.model.Competencies.Competence;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.question.Answer;
import br.com.fatec.model.question.Competencies;
import br.com.fatec.model.question.Question;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.user.User;



public class Main {
	
	//static ModelQuestion model = new ModelQuestion();
	
	public static void main(String[] args) throws JSONException{
		
		final UserRoutes rest = new UserRoutes();
		
		rest.getLogin();

/*		try {
			User teste = new DaoUser().getLogin("joao@gmail.com", "12342");
			
			System.out.println(" tipo = " + teste.getKindPerson()
							+ "\n codigo = " + teste.getUserCode()
							+ "\n "
							+ "token: " + teste.getToken() );

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ops, algum erro no SQL");
		}
*/
		
/*		
		//initializeModel();
		
		
		//REST controller = new REST(model); 
		
		//controller.getStudentCompetencies();
		//controller.getQuestionByNumber();
		//ConnectionMySql connection = new ConnectionMySql();
		//connection.getConnection();
		
		DaoStudent dao = new DaoStudent();
		try {
			Student st = dao.searchStudentByRa("123456789");
			System.out.println(st.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void initializeModel(){
*/		
	}

}
