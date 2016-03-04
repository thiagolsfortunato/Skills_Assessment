package br.com.fatec.connection;

import org.json.JSONException;

import br.com.fatec.controller.CourseRoutes;

public class Main {
	
	//static ModelQuestion model = new ModelQuestion();
	
	public static void main(String[] args) throws JSONException{
		 
		 CorsFilter.apply();
		 CourseRoutes rest = new CourseRoutes();
		 rest.getCourse();
		//rest.getLogin(); CODIGO COMENTADO POR CONTA DOS OUTROS COMENTARIOS

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
