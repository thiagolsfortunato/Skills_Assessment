package br.com.fatec.controller;

import static spark.Spark.get;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.dao.DaoStudent;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.user.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserRoutes {

	/* 
	 * CODIGO COMENTADO PARA ALTERAR A CLASSE PARA O NOVO FORMATO DE JSON
	 * PASSANDO COMO PARAMETRO UM OBJETO !!!!!
	 * 

	public void getLogin(){
		
		
		get(new Route("/login/:user/:password"){
			@Override
	         public Object handle(Request request, Response response){
				
				try{
					ModelUser user = new ModelUser();
					User login = user.getLogin(request.params(":user"), request.params(":password"));
					
					if( (login != null) && (login.getKindPerson().equals("student")) ){
						ModelStudent modelSt = new ModelStudent();
						Student stu = modelSt.searchStudentById(login.getUserCode());
						
						JSONArray jsonResult = new JSONArray();
							JSONObject jsonStudent = new JSONObject();

			        		jsonStudent.put("name", stu.getName());
			        		jsonStudent.put("ra", stu.getRa());
			        		jsonStudent.put("kind", login.getKindPerson());
			        		jsonStudent.put("token", login.getToken());
		        		
			        		jsonResult.put(jsonStudent);
		        		
		        		return jsonResult;

					}else if( (login != null) && login.getKindPerson().equals("psicologo")){
						ModelEmployee modelEmp = new ModelEmployee();
						Employee emp = modelEmp.searchEmployeeByCode(login.getUserCode());
						
						JSONArray jsonResult = new JSONArray();
							JSONObject jsonEmployee = new JSONObject();
						
							jsonEmployee.put("name", emp.getName().toLowerCase());
							jsonEmployee.put("cpf", emp.getCpf());
							jsonEmployee.put("tipo", login.getKindPerson());
							jsonEmployee.put("token", login.getToken());
							
							jsonResult.put(jsonEmployee);
							
						return jsonResult;
					}else{
						JSONObject jsonFail = new JSONObject();
						
						jsonFail.put("-2020", "Ops, algo deu errado. LOGIN ou SENHA");
						
						
						return jsonFail;
					}
				}
				catch (JSONException e){
					e.printStackTrace();
					System.out.println("erro, JSON");
				}
			
				return null;
			}
		});
	}
*/
}
