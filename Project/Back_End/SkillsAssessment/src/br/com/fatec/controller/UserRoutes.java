package br.com.fatec.controller;

import static spark.Spark.post;
import static spark.Spark.get;
import com.google.gson.Gson;
import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.user.User;

public class UserRoutes {
	private String loginData = null;
	private String token = null;


	public void getLogin() {
		ModelUser login = new ModelUser();
		
		post("/login", (req, res) -> {
			Employee emp = null;
			Student stu = null;
			loginData = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(loginData, User.class);
			try {
				user = login.getLogin(user.getEmail(), user.getPassword());
				token = user.getToken();
				res.header("token",token);
				switch (user.getKindPerson()) {
				case "student":
					ModelStudent modelSt = new ModelStudent();
					stu = modelSt.getStudentById(user.getUserCode());
					return stu;
				case "employee":
					ModelEmployee modelEpl = new ModelEmployee();
					emp = modelEpl.searchEmployeeByCode(user.getUserCode());
					return emp;
				case "psychologist":
					return "Not yet exist"; 
				default:
					res.status(400);
					return "It was not possible to find a User";
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
			
		} , JsonUtil.json());
	}
}
