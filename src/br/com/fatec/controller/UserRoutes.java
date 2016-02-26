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
	
	public void getLogin(){
		ModelUser login = new ModelUser();
				
		get("/login/:email/:password", (req, res) -> {
			  String email = req.params(":email");
			  String senha = req.params(":password");
			  User user = login.getLogin(email, senha);
			  
			  if ( (user.getKindPerson() != null) && (user.getKindPerson().equals("student")) ) {
				  System.out.println("aqui funfou");
				  ModelStudent modelSt = new ModelStudent();
				  Student stu = modelSt.searchStudentById(user.getUserCode());
				  Object studentLogin = null;
				  return stu;
			  }
			  else if( (user.getKindPerson() != null) && (user.getKindPerson().equals("psicologo")) ){
				  ModelEmployee modelEmp = new ModelEmployee();
				  Employee emp = modelEmp.searchEmployeeByCode(user.getUserCode());
				  return emp;
			  }
			  res.status(400);
			  return "ops, algum erro com LOGIN";
			}, JsonUtil.json());
	}

}
