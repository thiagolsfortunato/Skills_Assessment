package br.com.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import com.google.gson.Gson;

import br.com.fatec.entity.Course;
import br.com.fatec.model.ModelCourse;

public class CourseRoutes {
	public static void getCourse() {
		ModelCourse modelCourses = new ModelCourse();
		Gson gson = new Gson();
		
		//FUNCIONANDO !
		post("/insertCourse", (req, res) -> {
			String courseData = req.body();
			Course course = gson.fromJson(courseData, Course.class);
			try{
				return modelCourses.insertCourse(course);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
			
		}, JsonUtil.json());
		//FUNCIONANDO !
		delete("/deleteCourse", "application/json" , (req, res) -> {
			Long codeCourse = Long.parseLong(req.queryParams("codeCourse"));
			try{
				return modelCourses.deleteCourse(codeCourse);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "ops, an error with deleting, check the fields!";
			}
			
		}, JsonUtil.json());
		//FUNCIONANDO !
		put("/updateCourse", (req, res) -> {
			String courseData = req.body();
			Course course = gson.fromJson(courseData, Course.class);
			try{
				return modelCourses.updateCourse(course);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with updating, check the fields!";
			}
		}, JsonUtil.json());
		//FUNCIONANDO !
		get("/searchCourseById", "application/json" , (req, res) -> {
			Long codeCourse = Long.parseLong(req.queryParams("codeCourse"));
			try{
				return  modelCourses.searchCourseByCode(codeCourse);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "It was not possible to find a Course";
			}
		}, JsonUtil.json());
		//FUNCIONANDO !
		get("/searchAllCourses", (req, res) -> {
			try{
				return modelCourses.searchAllCourse();
			}catch(NullPointerException e){
				e.printStackTrace();
				return "It wasin't possible find all Courses!";
			}
		}, JsonUtil.json());
	}
}
