package br.com.fatec.controller;

import static spark.Spark.post;
import static spark.Spark.get;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.dao.DaoStudent;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.user.User;
import br.com.fatec.model.user.UserLogin;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserRoutes {
	String loginData = null;
	public void getLogin() {
		ModelUser login = new ModelUser();

		post("/token", (req, res) -> {
			loginData = req.body();
			Gson gson = new Gson();

			UserLogin usu = gson.fromJson(loginData, UserLogin.class);
			System.out.println(usu.getPassword());

			/*
			 * User user = login.getLogin(usu.getUserName(), usu.getPassword());
			 * 
			 * if ((user.getKindPerson() != null) &&
			 * (user.getKindPerson().equals("student"))) { System.out.println(
			 * "aqui funfou"); ModelStudent modelSt = new ModelStudent();
			 * Student stu = modelSt.searchStudentById(user.getUserCode());
			 * return stu; } else if ((user.getKindPerson() != null) &&
			 * (user.getKindPerson().equals("psicologo"))) { ModelEmployee
			 * modelEmp = new ModelEmployee(); Employee emp =
			 * modelEmp.searchEmployeeByCode(user.getUserCode()); return emp; }
			 */
			// res.status(400);
			return "ops, algum erro com LOGIN";
		} , JsonUtil.json());
	}

}
