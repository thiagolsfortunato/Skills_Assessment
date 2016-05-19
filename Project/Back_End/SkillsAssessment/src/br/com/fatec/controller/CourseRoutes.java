package br.com.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.options;
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.connection.CorsFilter;
import br.com.fatec.entity.Course;
import br.com.fatec.model.ModelCourse;

public class CourseRoutes {
	public static void getCourse() {
		ModelCourse modelCourses = new ModelCourse();
		Gson gson = new Gson();
		
		options("/course", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		//FUNCIONANDO !
		post("/course", (req, res) -> {
			String courseData = req.body();
			Course course = gson.fromJson(courseData, Course.class);
			try{
				boolean operacao = modelCourses.insertCourse(course);
				if (operacao){
					return "SUCESS";
				}else{
					res.status(600);
					return "FAIL";
				}
				
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
			
		}, JsonUtil.json());
		//FUNCIONANDO !
		delete("/course", "application/json" , (req, res) -> {
			Long codeCourse = Long.parseLong(req.queryParams("codeCourse"));
			Long codeFatec = Long.parseLong(req.queryParams("codeFatec"));
			try{
				return modelCourses.deleteCourse(codeCourse, codeFatec);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "ops, an error with deleting, check the fields!";
			}
			
		}, JsonUtil.json());
		//FUNCIONANDO !
		put("/course", (req, res) -> {
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
		
		get("/searchCoursesByInstitutionId", (req, res) -> {
			Long codeInstitution = Long.parseLong(req.queryParams("codeInstitution"));
			try{
				return modelCourses.searchCoursesByInstitionId(codeInstitution);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "It wasin't possible find all Courses!";
			}
		}, JsonUtil.json());
		
	}
}
