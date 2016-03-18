package br.com.fatec.controller;

import com.google.gson.Gson;

import static spark.Spark.get; // select
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import br.com.fatec.entity.Enrolls;
import br.com.fatec.model.ModelEnrolls;

public class EnrollsRoutes {
	ModelEnrolls modelEnrolls = new ModelEnrolls();
	Gson gson = new Gson();
	
	public void getCourse() {
		post("/insertEnrolls", (req, res) -> {
			String enrollsData = req.body();
			Enrolls enrolls = gson.fromJson(enrollsData, Enrolls.class);
			return modelEnrolls.insertEnrolls(enrolls);
		}, JsonUtil.json());

		delete("/deleteEnrolls", "application/json" , (req, res) -> {
			Long codeEnrolls = Long.parseLong(req.queryParams("codeEnrolls"));
			return modelEnrolls.deleteEnrolls(codeEnrolls);
		}, JsonUtil.json());

		put("/updateEnrolls", (req, res) -> {
			String enrollsData = req.body();
			Enrolls enrolls = gson.fromJson(enrollsData, Enrolls.class);
			return modelEnrolls.updateEnrolls(enrolls);
		}, JsonUtil.json());

		get("/searchAllEnrolls", (req, res) -> {
			return modelEnrolls.searchAllEnrolls();
		}, JsonUtil.json());

		get("/searchEnrollsById", "application/json" ,(req, res) -> {
			Long codeEnrolls = Long.parseLong(req.queryParams("codeEnrolls"));
			return modelEnrolls.searchEnrollsByCode(codeEnrolls);
		}, JsonUtil.json());
	}
}
