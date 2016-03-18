package br.com.fatec.controller;

import static spark.Spark.get; 
import static spark.Spark.put; 
import static spark.Spark.delete; 
import static spark.Spark.post; 
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
				user = login.getLogin(user.getUserName(), user.getPassword());
				if (user != null) {
					token = user.getToken();
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
	}

	public void insertUser() {
		ModelUser insert = new ModelUser();

		post("/insertUser", (req, res) -> {
			data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class);
			try {
				return insert.addUser(user);
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
	}
	
	public void deleteUser() {
		ModelUser deleteUser = new ModelUser();
		delete("/deleteUser", (req, res) -> {
			data = req.body();
			token = req.headers("token");
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class); //not being used at the time
			try {
				return deleteUser.deleteUser(token);
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());
	}
	
	public void updateUser() {
		ModelUser updateUser = new ModelUser();
		put("/updateUser", (req, res) -> {
			data = req.body();
			Gson gson = new Gson();
			User user = gson.fromJson(data, User.class); //not being used at the time
			try {
				return updateUser.updateUser(user);
			} catch (NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());
	}
	
	public void searchUserById() {
		ModelUser login = new ModelUser();
		post("/searchUserById", (req, res) -> {
			Long idUser = Long.parseLong(req.queryParams("idUser"));
			User user = null;
			try {
				user = login.searchUserById(idUser);
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
	}
}
