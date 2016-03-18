package br.com.fatec.controller;

import static spark.Spark.post;
import static spark.Spark.get;
import com.google.gson.Gson;
import br.com.fatec.entity.User;
import br.com.fatec.model.ModelUser;

public class UserRoutes {
	private String data = null;
	private String token = null;


	public void getLogin() {
		ModelUser login = new ModelUser();
		
		post("/token", (req, res) -> {
			
			data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class);
			try {
				user = login.getLogin(user.getUserName() , user.getPassword());
				token = user.getToken();
				res.header("token",token);
				res.type("application/json");		
				return user;
					//res.status(400);
					//return "It was not possible to find a User";
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
			
		} , JsonUtil.json());
	}
	
	
	public void insertUser() {
		ModelUser insert = new ModelUser();
		
		post("/insertUser", (req, res) -> {
			data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class);
			try {
				boolean respUser = insert.addUser(user);
				return respUser;
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with INSERT, check the fields!";
			}
			
		} , JsonUtil.json());
	}
}
