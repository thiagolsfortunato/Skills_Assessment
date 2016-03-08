package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.dao.Token;
import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.token.TokenInfo;
import br.com.fatec.model.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class EmployeeRoutes {
	Employee emp = null;
	ModelEmployee modelEmployee = new ModelEmployee();
	TokenInfo tk = new TokenInfo();
	private String employee = null;
	private String token = null;
	
	@SuppressWarnings("finally")
	public void getEmployee() {
		
		post("/searchEmployee", (req, res) -> {
			employee = req.body();
			token = req.headers("token");
			Gson gson = new Gson();
			tk = Token.verifyToken(token);
			try {
				emp = gson.fromJson(employee, Employee.class);
				emp = modelEmployee.searchEmployeeByCode(Long.parseLong(tk.getUserId()));
					
			} catch (NullPointerException e) {
				e.printStackTrace();
				res.status(400);
				return "ops, an error with LOGIN, check the fields!";
			} finally {
				res.header("token", token);
				return emp;
			}
		} , JsonUtil.json());

	}
}
