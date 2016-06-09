package br.gov.sp.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.post;
import static spark.Spark.options;
import com.google.gson.Gson;

import br.gov.sp.fatec.commons.JsonUtil;
import br.gov.sp.fatec.commons.Token;
import br.gov.sp.fatec.connection.CorsFilter;
import br.gov.sp.fatec.entity.Enrolls;
import br.gov.sp.fatec.entity.TokenInfo;
import br.gov.sp.fatec.entity.User;
import br.gov.sp.fatec.model.ModelEnrolls;
import br.gov.sp.fatec.model.ModelUser;

public class UserRoutes {	
	
	public static void getUser() {
		ModelUser modelUser = new ModelUser();
		ModelEnrolls modelEnrolls = new ModelEnrolls();
		
		options("/map/token", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		options("/map/user/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		post("/map/token", (req, res) -> {
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

		post("/map/user/", (req, res) -> {
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

		delete("/map/user/", (req, res) -> {
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

		put("/map/user/", (req, res) -> {
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

		get("/map/searchUserById", (req, res) -> {
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

			} catch (Exception e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
		}, JsonUtil.json());
		
		
		get("/map/user/search/all/psicologas", (req, res) -> {
			String fatecCode = req.queryParams("fatecCode");
			Long code = Long.parseLong(fatecCode);
			try{
				return modelUser.searchAllPsicologas(code);
			}catch(Exception e){
				return "ops, It wasin't possible find all Users!";
			}
		}, JsonUtil.json());
				
		
	}
}

