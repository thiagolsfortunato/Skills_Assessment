package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.util.List;

import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.connection.CorsFilter;
import br.com.fatec.entity.Question;
import br.com.fatec.model.ModelQuestion;

public class QuestionRoutes{
	
	public static void getQuestions(){
		ModelQuestion model = new ModelQuestion();
		
		options("/question", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		post("/question", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			Question question = gson.fromJson(value, Question.class);
	
			boolean sucess = model.insertQuestion(question);
			if(sucess){
				res.status(200);
				return "successfully inserted";
			}else{
				res.status(400);
				return "failure to enter the question";
			}
		}, JsonUtil.json());
		
		get("/question/find/code", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("code") );
				Question question = model.searchQuestionByCode(code);
				
				if( question.getCode() == null ){
					res.status(404);
					return "Question not found";
				}else{
					return question;
				}	
			}
		}, JsonUtil.json());
		
		get("/question/find/all", (req, res) -> {
		
			List<Question> questions = model.searchAllQuestion();
			
			return questions;
			
		}, JsonUtil.json());
	
		put("/question", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			Question question = gson.fromJson(value, Question.class);
			
			boolean sucess = model.updateQuestion(question);
			if(sucess){
				res.status(200);
				return "successfully changed";
			}else{
				res.status(400);
				return "failure to change question";
			}
		}, JsonUtil.json());
		
		delete("/question", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("code") );
				boolean sucess = model.deleteQuestion(code);
				if(sucess){
					res.status(200);
					return "successfully removed";
				}else{
					res.status(400);
					return "Invalid Question code";
				}
			}
		}, JsonUtil.json());
	
	}		
}
