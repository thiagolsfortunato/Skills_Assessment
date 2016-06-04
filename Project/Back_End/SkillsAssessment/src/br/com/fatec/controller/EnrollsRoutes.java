package br.com.fatec.controller;

import com.google.gson.Gson;

import static spark.Spark.get; // select
import static spark.Spark.options;
import static spark.Spark.put; // update

import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.connection.CorsFilter;
import br.com.fatec.entity.Enrolls;
import br.com.fatec.entity.Result;
import br.com.fatec.entity.Student;
import br.com.fatec.entity.User;
import br.com.fatec.model.ModelEnrolls;

public class EnrollsRoutes {
	public static void getEnrolls() {
		ModelEnrolls modelEnrolls = new ModelEnrolls();
		Gson gson = new Gson();
		
		options("/enrolls/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		//FUNCIONANDO !!
		post("/enrolls", (req, res) -> {
			String enrollsData = req.body();
			
			byte ptext[] = enrollsData.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			User user = gson.fromJson(value, User.class);
			Enrolls enrolls = gson.fromJson(value, Enrolls.class);
			
			try{
				if( modelEnrolls.insertEnrolls(enrolls, user) ){
					res.status(200);
					return "sucess";
				}else{
					res.status(400);
					return "ops, an error with inserting, check the fields!";
				}
				
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
		
		
		//FUNCIONANDO !!
		post("/enrolls/comment", (req, res) -> {
			String comment = req.body();
			String token = req.headers("token");
			
			byte ptext[] = comment.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8");
			
			Result result = gson.fromJson(value, Result.class);
			
			try{
				//TokenInfo tk = Token.verifyToken(token);
				//quiz.setUser(tk.getUserId());
				if( modelEnrolls.insertComment(result, Long.parseLong(token)) ){
					res.status(200);
					return "sucess";
				}else{
					res.status(400);
					return "ops, an error with inserting, check the fields!";
				}
				
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
		
		
		//FUNCIONANDO !!
		delete("/enrolls/", "application/json" , (req, res) -> {
			Long codeEnrolls = Long.parseLong(req.queryParams("codeEnrolls"));
			try{
				return modelEnrolls.deleteEnrolls(codeEnrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());

		put("/enrolls/", (req, res) -> {
			String enrollsData = req.body();

			byte ptext[] = enrollsData.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8");
			
			Enrolls enrolls = gson.fromJson(value, Enrolls.class);
			
			try{
				return modelEnrolls.updateEnrolls(enrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with updating, check the fields!";
			}
		}, JsonUtil.json());

		get("/enrolls/search/student/code", (req, res) -> {
			Long idStudent = Long.parseLong(req.queryParams("idStudent"));
			Student student = null;
			try{
				student = modelEnrolls.searchStudentById(idStudent);
				return student;
			}catch(NullPointerException e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
		}, JsonUtil.json());

/* 		SE FORMOS UTILIZAR EM ALGUM LUGAR AIVAMOS NOVAMENTE
 * 		
		get("/searchAllEnrolls", (req, res) -> {
			try{
				return modelEnrolls.searchAllEnrolls();
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "It was not possible to find a Enrolls";
			}
		}, JsonUtil.json());

		get("/searchEnrollsById", "application/json" ,(req, res) -> {
			Long codeEnrolls = Long.parseLong(req.queryParams("codeEnrolls"));
			try{
				return modelEnrolls.searchEnrollsByCode(codeEnrolls);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "It wasin't possible find all Enrolls!";
			}
		}, JsonUtil.json());
*/

		get("/enrolls/search/students/all/fatec", (req, res) -> {
			Long idFatec = Long.parseLong(req.queryParams("fatecCode"));
			try{
				return modelEnrolls.searchAllStudents(idFatec);
			}catch(NullPointerException e){
				return "ops, It wasin't possible find all Studentss!";
			}
		}, JsonUtil.json());
	}
}
