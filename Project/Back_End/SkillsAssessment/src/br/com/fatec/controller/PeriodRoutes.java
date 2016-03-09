package br.com.fatec.controller;

import com.google.gson.Gson;

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
