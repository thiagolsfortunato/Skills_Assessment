package br.com.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import com.google.gson.Gson;

import br.com.fatec.entity.Course;
import br.com.fatec.model.ModelCourse;

public class CourseRoutes {
	ModelCourse modelCourses = new ModelCourse();
	Gson gson = new Gson();
	
	public void getCourse() {
		//FUNCIONANDO !
		post("/insertCourse", (req, res) -> {
			String courseData = req.body();
			Course course = gson.fromJson(courseData, Course.class);
			return modelCourses.insertCourse(course);
		}, JsonUtil.json());
		//FUNCIONANDO !
		delete("/deleteCourse", "application/json" ,(req, res) -> {
			Long codeCourse = Long.parseLong(req.queryParams("codeCourse"));
			return modelCourses.deleteCourse(codeCourse);
		}, JsonUtil.json());
		//FUNCIONANDO !
		put("/updateCourse", (req, res) -> {
			String courseData = req.body();
			Course course = gson.fromJson(courseData, Course.class);
			return modelCourses.updateCourse(course);
		}, JsonUtil.json());
		//FUNCIONANDO !
		get("/searchCourseById/:code", (req, res) -> {
			Long codeCourse = Long.parseLong(req.params("code"));
			return  modelCourses.searchCourseByCode(codeCourse);					 
		}, JsonUtil.json());
		//FUNCIONANDO !
		get("/searchAllCourses", (req, res) -> {
			return modelCourses.searchAllCourse();					
		}, JsonUtil.json());
	}
}
