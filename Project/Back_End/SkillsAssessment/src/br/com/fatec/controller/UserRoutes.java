package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.post;
import static spark.Spark.options;
import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.commons.Token;
import br.com.fatec.connection.CorsFilter;
import br.com.fatec.entity.Enrolls;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.entity.User;
import br.com.fatec.model.ModelEnrolls;
import br.com.fatec.model.ModelUser;

public class UserRoutes {	
	
	public static void getUser() {
		ModelUser modelUser = new ModelUser();
		ModelEnrolls modelEnrolls = new ModelEnrolls();
		
		options("/token", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		options("/user/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
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

		post("/user/", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			User user = gson.fromJson(value, User.class);

			try {
				return modelUser.insertUser(user);

			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());

		delete("/user/", (req, res) -> {
			//String token = req.headers("token");
			String idUser = req.queryParams("userCode");
			Long code = Long.parseLong(idUser);
			boolean status = false;											// the time
			try {
				//TokenInfo tokenInfo = Token.verifyToken(token);
				//Long id = tokenInfo.getUserId();				
				
				status = modelUser.deleteUser(code);
				
				if (status){
					res.status(200);
				} else {
					res.status(400);
				}
				return status;
			} catch (Exception e) {
				e.printStackTrace();
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());

		put("/user/", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			User user = gson.fromJson(value, User.class);
			Enrolls enrolls = gson.fromJson(value, Enrolls.class);
			boolean operacao = false;
			try {
				enrolls.setCodeUser(user.getUserCode());
				if(user.getType().toLowerCase().equals("student")){
					if(modelEnrolls.updateEnrolls(enrolls)){
						operacao = modelUser.updateUser(user); 
						res.status(200); 
					}else{
						res.status(400);
					}
				}
				operacao = modelUser.updateUser(user); 
				if(operacao){
					res.status(200);
				} else {
					res.status(600);
				}
				return operacao;
			} catch (Exception e) {
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
		
		
		get("/searchAllUsers", (req, res) -> {
			try{
				return modelUser.searchAllUsers();
			}catch(NullPointerException e){
				return "ops, It wasin't possible find all Users!";
			}
		}, JsonUtil.json());
		
		
	}
}

