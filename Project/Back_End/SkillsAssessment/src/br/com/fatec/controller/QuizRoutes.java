package br.com.fatec.controller;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.commons.Token;
import br.com.fatec.connection.CorsFilter;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.model.ModelQuiz;


public class QuizRoutes {
	public static void getQuiz() {
		ModelQuiz model = new ModelQuiz();
		
		options("/quiz", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		post("/quiz", (req, res) -> {
			String token = req.headers("token");
			String data = req.body();
			Gson gson = new Gson();
			Quiz quiz = gson.fromJson(data, Quiz.class);
			try {
				TokenInfo tk = Token.verifyToken(token);
				quiz.setUser(tk.getUserId());
				return model.insertQuiz(quiz);
			} catch (Exception e) {
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
		
		get("/getQuizQuestion", (req, res) -> {
			Question question = new Question();
			String token = req.queryParams("token");
			try {
				TokenInfo tk = Token.verifyToken(token);
				System.out.println(tk.getUserId());
				question = model.getQuestion(tk.getUserId());
				return question;
				
			} catch (Exception e) {
				System.out.println("ops, an error with inserting, check the fields! "+e);
				e.printStackTrace();
				return "ops, an error with get the question, check the fields!";
			}
		}, JsonUtil.json());
	}
}
