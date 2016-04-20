package br.com.fatec.controller;

import com.google.gson.Gson;

import static spark.Spark.get; // select
import static spark.Spark.put; // update

import java.util.Calendar;

import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.entity.Enrolls;
import br.com.fatec.model.ModelEnrolls;

public class EnrollsRoutes {
	public static void getEnrolls() {
		ModelEnrolls modelEnrolls = new ModelEnrolls();
		Gson gson = new Gson();
		//FUNCIONANDO !!
		post("/insertEnrolls", (req, res) -> {
			String enrollsData = req.body();
			Enrolls enrolls = gson.fromJson(enrollsData, Enrolls.class);	
			try{
				return modelEnrolls.insertEnrolls(enrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
		//FUNCIONANDO !!
		delete("/deleteEnrolls", "application/json" , (req, res) -> {
			Long codeEnrolls = Long.parseLong(req.queryParams("codeEnrolls"));
			try{
				return modelEnrolls.deleteEnrolls(codeEnrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());

		put("/updateEnrolls", (req, res) -> {
			String enrollsData = req.body();
			Enrolls enrolls = gson.fromJson(enrollsData, Enrolls.class);
			try{
				return modelEnrolls.updateEnrolls(enrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with updating, check the fields!";
			}
		}, JsonUtil.json());

		get("/searchAllEnrolls", (req, res) -> {
			try{
				return modelEnrolls.searchAllEnrolls();
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "It was not possible to find a Enrolls";
			}
		}, JsonUtil.json());

		get("/searchEnrollsById", "application/json" ,(req, res) -> {
			Long codeEnrolls = Long.parseLong(req.queryParams("codeEnrolls"));
			try{
				return modelEnrolls.searchEnrollsByCode(codeEnrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "It wasin't possible find all Enrolls!";
			}
		}, JsonUtil.json());
	}
}
