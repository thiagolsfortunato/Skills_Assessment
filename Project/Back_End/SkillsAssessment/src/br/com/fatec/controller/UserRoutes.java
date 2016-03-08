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
import br.com.fatec.dao.Token;
import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.token.TokenInfo;
import br.com.fatec.model.user.User;
//import br.com.fatec.model.user.UserLogin;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserRoutes {
	private String loginData = null;
	private String token = null;

	public void getLogin() {
		ModelUser login = new ModelUser();
		post("/login", (req, res) -> {
			loginData = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(loginData, User.class);
			Object returnUser = null;
			try {
				user = login.getLogin(user.getEmail(), user.getPassword());
				switch (user.getKindPerson()) {
				case "student":
					ModelStudent modelSt = new ModelStudent();
					returnUser = modelSt.getStudentById(user.getUserCode());
					break;
				case "employee":
					ModelEmployee modelEpl = new ModelEmployee();
					returnUser = modelEpl.searchEmployeeByCode(user.getUserCode());
					break;
				case "psychologist":
					break;
				default:
					res.status(400);
					break;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			} finally {
				token = user.getToken();
				res.header("token",token);
				return returnUser;
			}
		} , JsonUtil.json());
	}
}
