package br.com.fatec.controller;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.commons.Token;
import br.com.fatec.connection.CorsFilter;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;
import br.com.fatec.entity.Result;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.model.ModelQuiz;


public class QuizRoutes {
	public static void getQuiz() {
		ModelQuiz model = new ModelQuiz();
		
		options("/quiz/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});

		post("/quiz/", (req, res) -> {
			String token = req.headers("token");
			String data = req.body();
			Gson gson = new Gson();
			Quiz quiz = gson.fromJson(data, Quiz.class);
			try {
				//TokenInfo tk = Token.verifyToken(token);
				//quiz.setUser(tk.getUserId());
				quiz.setUser( Long.parseLong(token) );
				return model.insertQuiz(quiz);
			} catch (Exception e) {
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/quiz/question", (req, res) -> {
			Question question = new Question();
			String token = req.headers(("token"));
			try {
				//TokenInfo tk = Token.verifyToken(token);
				//System.out.println(tk.getUserId());
				//question = model.getQuestion(tk.getUserId());
				question = model.getQuestion( Long.parseLong(token) );
				return question;
				
			} catch (Exception e) {
				System.out.println("ops, an error with inserting, check the fields! "+e);
				e.printStackTrace();
				return "ops, an error with get the question, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/quiz/generate/average", (req, res) -> {
			boolean result = false;
			String token = req.headers("token");
			try {
				//TokenInfo tk = Token.verifyToken(token);
				//System.out.println(tk.getUserId());
				//question = model.getQuestion(tk.getUserId());
				result = model.setAverage(Long.parseLong(token));
				if(result = true){
					res.status(200);
					return "SUCESS";
				}
				else{
					res.status(400);
					return "FAIL";
				}
				
			} catch (Exception e) {
				System.out.println("ops, an error with result, check the fields! "+e);
				e.printStackTrace();
				return "ops, an error with get the result, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/quiz/result/students", (req, res) -> {
			List<Result> result = new LinkedList<>();
			String instCode = req.queryParams("instCode");
			try {
				//TokenInfo tk = Token.verifyToken(token);
				//System.out.println(tk.getUserId());
				//question = model.getQuestion(tk.getUserId());
				result = model.getAveragesStudents(Long.parseLong(instCode));
				return result;
				
			} catch (Exception e) {
				System.out.println("ops, an error with result, check the fields! "+e);
				e.printStackTrace();
				return "ops, an error with get the result, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/quiz/result/student", (req, res) -> {
			Result result = new Result();
			String token = req.headers("token");
			try {
				//TokenInfo tk = Token.verifyToken(token);
				//System.out.println(tk.getUserId());
				//question = model.getQuestion(tk.getUserId());
				result = model.getAveragesStudent(Long.parseLong(token));
				return result;
				
			} catch (Exception e) {
				System.out.println("ops, an error with result, check the fields! "+e);
				e.printStackTrace();
				return "ops, an error with get the result, check the fields!";
			}
		}, JsonUtil.json());
	}
}
