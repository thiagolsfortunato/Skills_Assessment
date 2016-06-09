package br.gov.sp.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.options;
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import com.google.gson.Gson;

import br.gov.sp.fatec.commons.JsonUtil;
import br.gov.sp.fatec.connection.CorsFilter;
import br.gov.sp.fatec.entity.Course;
import br.gov.sp.fatec.model.ModelCourse;

public class CourseRoutes {
	public static void getCourse() {
		ModelCourse modelCourses = new ModelCourse();
		Gson gson = new Gson();
		
		options("/map/course/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		//FUNCIONANDO !
		post("/map/course/", (req, res) -> {
			String courseData = req.body();
			String code = req.queryParams("fatecCode");
			Long fatecCode = Long.parseLong(code);
			
			byte ptext[] = courseData.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			Course course = gson.fromJson(value, Course.class);
			
			try{
				boolean operacao = modelCourses.insertCourse(course, fatecCode);
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
		delete("/map/course/", "application/json" , (req, res) -> {
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
		put("/map/course/", (req, res) -> {
			String courseData = req.body();
			
			byte ptext[] = courseData.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8");
			
			Course course = gson.fromJson(value, Course.class);
			
			try{
				return modelCourses.updateCourse(course);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with updating, check the fields!";
			}
		}, JsonUtil.json());
		//FUNCIONANDO !
		get("/map/course/search/code", "application/json" , (req, res) -> {
			Long codeCourse = Long.parseLong(req.queryParams("code"));
			try{
				return  modelCourses.searchCourseByCode(codeCourse);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "It was not possible to find a Course";
			}
		}, JsonUtil.json());
		//FUNCIONANDO !
		get("/map/course/search/all", (req, res) -> {
			String code = req.queryParams("fatecCode");
			Long fatecCode = Long.parseLong(code);
			try{
				return modelCourses.searchAllCourse(fatecCode);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "It wasin't possible find all Courses!";
			}
		}, JsonUtil.json());
		
		get("/map/searchCoursesByInstitutionId", (req, res) -> {
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
