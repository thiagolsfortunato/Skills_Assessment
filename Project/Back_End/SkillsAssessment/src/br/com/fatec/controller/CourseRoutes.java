package br.com.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.fatec.model.ModelCourse;
import br.com.fatec.model.course.Course;

public class CourseRoutes {
	ModelCourse modelCourses = new ModelCourse();
	Gson gson = new Gson();
	
	public void getCourse() {
		
		post("/insertCourse", (req, res) -> {
			String courseData = req.body();
			
			Course course = gson.fromJson(courseData, Course.class);
						
			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			boolean resultInsert= modelCourses.insertCourse(course);

			try {
				jsonObj.put("resultDelete", resultInsert);
				jsonResult.put(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jsonResult;
		});
		
		delete("/deleteCourse", (req, res) -> {
			Long code = Long.parseLong(req.body());

			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			boolean resultDelete = modelCourses.deleteCourse(code);

			try {
				jsonObj.put("resultDelete", resultDelete);
				jsonResult.put(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jsonResult;
		});
		
		get("/searchAllCourses", (req, res) -> {
			List<Course> listCourses = modelCourses.searchAllCourse();					
			return listCourses; 
		}, JsonUtil.json());
		
		get("/searchCourseById", (req, res) -> {
			Long code = Long.parseLong(req.body());
			return  modelCourses.searchCourseByCode(code);					 
		}, JsonUtil.json());
	}
}
