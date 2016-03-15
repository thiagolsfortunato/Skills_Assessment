package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import br.com.fatec.dao.Token;
import br.com.fatec.entity.Employee;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.model.ModelEmployee;
import com.google.gson.Gson;


public class EmployeeRoutes {
	Employee empObj = null;
	ModelEmployee modelEmployee = new ModelEmployee();
	TokenInfo tk = new TokenInfo();
	private String employee = null;
	private String token = null;
	
	@SuppressWarnings("finally")
	public void getEmployee() {
		
		post("/searchEmployeeById", (req, res) -> {
			employee = req.body();
			token = req.headers("token");
			Gson gson = new Gson();
			tk = Token.verifyToken(token);
			try {
				empObj = gson.fromJson(employee, Employee.class);
				empObj = modelEmployee.searchEmployeeByCode(Long.parseLong(tk.getUserId()));
					
			} catch (NullPointerException e) {
				e.printStackTrace();
				res.status(400);
				return "ops, an error with LOGIN, check the fields!";
			} finally {
				res.header("token", token);
				return empObj;
			}
		} , JsonUtil.json());
		
		get("/insertEmployee", (req, res) -> {
			boolean response = false;
			employee = req.body();
			token = req.headers("token");
			Gson gson = new Gson();
			try {
				empObj = gson.fromJson(employee, Employee.class);
				response = modelEmployee.addEmployee(empObj);
					
			} catch (NullPointerException e) {
				e.printStackTrace();
				res.status(400);
				return "ops, an error with INSERT, check the fields!";
			} finally {
				res.header("token", token);
				return response;
			}
		} , JsonUtil.json());
		
		post("/deleteEmployee", (req, res) -> {
			boolean response = false;
			employee = req.body();
			token = req.headers("token");
			Gson gson = new Gson();
			tk = Token.verifyToken(token);
			try {
				empObj = gson.fromJson(employee, Employee.class);
				response = modelEmployee.deleteEmployee(Long.parseLong(tk.getUserId()));
					
			} catch (NullPointerException e) {
				e.printStackTrace();
				res.status(400);
				return "ops, an error with DELETE, check the fields!";
			} finally {
				//res.header("token", token);
				return response;
			}
		} , JsonUtil.json());
		
		post("/updateEmployee", (req, res) -> {
			boolean response = false;
			employee = req.body();
			token = req.headers("token");
			Gson gson = new Gson();
			tk = Token.verifyToken(token);
			try {
				empObj = gson.fromJson(employee, Employee.class);
				response = modelEmployee.updateEmployee(empObj);
						
			} catch (NullPointerException e) {
				e.printStackTrace();
				res.status(400);
				return "ops, an error with update, check the fields!";
			} finally {
				res.header("token", token);
				return response;
			}
		} , JsonUtil.json());
	}
}
