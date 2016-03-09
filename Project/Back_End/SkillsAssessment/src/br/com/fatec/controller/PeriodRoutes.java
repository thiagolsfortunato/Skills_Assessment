package br.com.fatec.controller;

import com.google.gson.Gson;

import static spark.Spark.get; // select
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import br.com.fatec.model.ModelCourse;
import br.com.fatec.model.ModelPeriod;

public class PeriodRoutes {
	ModelPeriod modelPeriod = new ModelPeriod();
	Gson gson = new Gson();

	public void getCourse() {
		post("/insertCourse", (req, res) -> {
			return null;
		}, JsonUtil.json());

		delete("/deleteCourse", (req, res) -> {
			return null;
		}, JsonUtil.json());

		put("/updateCourse", (req, res) -> {
			return null;
		}, JsonUtil.json());

		get("/searchAllCourses", (req, res) -> {
			return null;
		}, JsonUtil.json());

		get("/searchCourseById", (req, res) -> {
			return null;
		}, JsonUtil.json());
	}
}
