package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.util.List;

import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.entity.Question;
import br.com.fatec.model.ModelQuestion;

public class QuestionRoutes{
	
	public static void getQuestions(){
		ModelQuestion model = new ModelQuestion();
		
		post("/question/insert", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			Question question = gson.fromJson(data, Question.class);
	
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
			if(questions.size() > 0){
				return questions;
			}else{
				res.status(404);
				return "no Questions registered";
			}
		}, JsonUtil.json());
	
		put("/question/update", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			Question question = gson.fromJson(data, Question.class);
			
			boolean sucess = model.updateQuestion(question);
			if(sucess){
				res.status(200);
				return "successfully changed";
			}else{
				res.status(400);
				return "failure to change question";
			}
		}, JsonUtil.json());
		
		delete("/question/delete", (req, res) -> {
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
