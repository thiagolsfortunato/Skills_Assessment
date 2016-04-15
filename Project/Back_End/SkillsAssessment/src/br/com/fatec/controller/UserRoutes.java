package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.post;
import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.commons.Token;
import br.com.fatec.entity.Enrolls;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.entity.User;
import br.com.fatec.model.ModelEnrolls;
import br.com.fatec.model.ModelUser;

public class UserRoutes {	
	
	public static void getUser() {
		ModelUser modelUser = new ModelUser();
		ModelEnrolls modelEnrolls = new ModelEnrolls();
		
		post("/token", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class);
			try {
				user = modelUser.getLogin(user.getUserName(), user.getPassword());
				if (user != null) {
					String token = user.getToken();
					res.header("token", token);
					res.type("application/json");
					return user;
				} else {
					res.status(400);
					return "It was not possible to find a User";
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}

		}, JsonUtil.json());

		post("/insertUser", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class);
			Enrolls enrolls = gson.fromJson(data, Enrolls.class);
			try {
				Long codeUser = modelUser.insertUser(user);
				enrolls.setCodeUser(codeUser);
				if (codeUser != null && user.getType().equals("Student")) {
					return modelEnrolls.insertEnrolls(enrolls);
				}
				return false;
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());

		delete("/deleteUser", (req, res) -> {
			String data = req.body();
			String token = req.headers("token");
			Gson gson = new Gson();
			//User user = gson.fromJson(data, User.class); // not being used at											// the time
			try {
				TokenInfo tokenInfo = Token.verifyToken(token);
				Long id = tokenInfo.getUserId();				
				User user = modelUser.searchUserById(id);
				if(user.getType().equals("Student")){
					if(modelEnrolls.deleteEnrolls(id)){
						return modelUser.deleteUser(id);
					}else{
						return false;
					}
				}
				return modelUser.deleteUser(id);
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());

		put("/updateUser", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class);
			Enrolls enrolls = gson.fromJson(data, Enrolls.class);
			try {
				enrolls.setCodeUser(user.getUserCode());
				if(user.getType().equals("Student")){
					if(modelEnrolls.updateEnrolls(enrolls)){
						return modelUser.updateUser(user);
					}else{
						return false;
					}
				}
				return modelUser.updateUser(user);
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with updating, check the fields!";
			}
		}, JsonUtil.json());

		get("/searchUserById", (req, res) -> {
			Long idUser = Long.parseLong(req.queryParams("idUser"));
			User user = null;
			try {
				user = modelUser.searchUserById(idUser);
				if (user != null) {
					res.type("application/json");
					return user;
				} else {
					res.status(400);
					return "It was not possible to find a User";
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/searchStudentsByCode", (req, res) -> {
			Long idStudent = Long.parseLong(req.queryParams("idStudent"));
			User user = null;
			try{
				user = modelUser.searchStudentById(idStudent);
				return user;
			}catch(NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/searchAllUsers", (req, res) -> {
			try{
				return modelUser.searchAllUsers();
			}catch(NullPointerException e){
				return "ops, It wasin't possible find all Users!";
			}
		}, JsonUtil.json());
		
		get("/searchAllStudents", (req, res) -> {
			try{
				return modelUser.searchAllStudents();
			}catch(NullPointerException e){
				return "ops, It wasin't possible find all Studentss!";
			}
		}, JsonUtil.json());
	}
}

