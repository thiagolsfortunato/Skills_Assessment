package br.gov.sp.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.util.List;

import com.google.gson.Gson;

import br.gov.sp.fatec.commons.JsonUtil;
import br.gov.sp.fatec.connection.CorsFilter;
import br.gov.sp.fatec.entity.Question;
import br.gov.sp.fatec.model.ModelQuestion;

public class QuestionRoutes{
	
	public static void getQuestions(){
		ModelQuestion model = new ModelQuestion();
		
		options("/map/question/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		post("/map/question/", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			Question question = gson.fromJson(value, Question.class);
	
			boolean sucess = model.insertQuestion(question);
			return sucess;
		}, JsonUtil.json());
		
		get("/map/question/find/code", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("code") );
				Question question = model.searchQuestionByCode(code);
				
				return question;
					
			}
		}, JsonUtil.json());
		
		get("/map/question/find/all", (req, res) -> {
		
			List<Question> questions = model.searchAllQuestion();
			
			return questions;
			
		}, JsonUtil.json());
	
		put("/map/question/", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			Question question = gson.fromJson(value, Question.class);
			
			boolean sucess = model.updateQuestion(question);

			return sucess;
			
		}, JsonUtil.json());
		
		delete("/map/question/", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("code") );
				boolean sucess = model.deleteQuestion(code);

				return sucess;
				
			}
		}, JsonUtil.json());
	
	}		
}
