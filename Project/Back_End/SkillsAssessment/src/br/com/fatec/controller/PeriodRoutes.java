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

		}, JsonUtil.json());

		delete("/deleteCourse", (req, res) -> {

		}, JsonUtil.json());

		put("/updateCourse", (req, res) -> {

		}, JsonUtil.json());

		get("/searchAllCourses", (req, res) -> {

		}, JsonUtil.json());

		get("/searchCourseById", (req, res) -> {

		}, JsonUtil.json());
	}
}
