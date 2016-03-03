package br.com.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.put; // update

import java.util.List;

import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.fatec.model.ModelCourse;
import br.com.fatec.model.course.Course;
import br.com.fatec.model.employee.Employee;

public class CourseRoutes {
	ModelCourse modelCourses = new ModelCourse();
	Gson gson = new Gson();
	
	public void getCourse() {

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
	}
}
