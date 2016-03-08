package br.com.fatec.controller;
import static spark.Spark.get;
import static spark.Spark.post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.fatec.model.ModelQuestion;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.question.Question;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.user.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class StudentRoutes {
	
	public void getStage(){
		//retorna a pergunta onde o aluno parou
	}
	
	public void getStudentById(){
		//retorna um aluno pelo id usuario
	}

	public void findAll(){
		//retorn todos alunos
	}

	public void getStudentCompetencies(){
		//retorna as competencias do aluno em questão
	}
}
