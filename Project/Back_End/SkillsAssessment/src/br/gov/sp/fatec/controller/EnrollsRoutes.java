package br.gov.sp.fatec.controller;

import com.google.gson.Gson;

import static spark.Spark.get; // select
import static spark.Spark.options;
import static spark.Spark.put; // update

import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

import br.gov.sp.fatec.commons.JsonUtil;
import br.gov.sp.fatec.connection.CorsFilter;
import br.gov.sp.fatec.entity.Enrolls;
import br.gov.sp.fatec.entity.Student;
import br.gov.sp.fatec.entity.User;
import br.gov.sp.fatec.model.ModelEnrolls;

public class EnrollsRoutes {
	public static void getEnrolls() {
		ModelEnrolls modelEnrolls = new ModelEnrolls();
		Gson gson = new Gson();
		
		options("/map/enrolls/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		//FUNCIONANDO !!
		post("/map/enrolls/", (req, res) -> {
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
		put("/map/enrolls/comment", (req, res) -> {
			String comment = req.body();
			String token = req.headers("token");
			String code = req.queryParams("studentCode");
			
			Long studentCode = Long.parseLong(code);
			
			byte ptext[] = comment.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8");
			
			//Result result = gson.fromJson(value, Result.class);
			
			try{
				//TokenInfo tk = Token.verifyToken(token);
				//quiz.setUser(tk.getUserId());
				if( modelEnrolls.insertComment(value, studentCode) ){
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
		/*para deletar aluno terá que excluir os dados tambem das tabelas
		 * quiz, result e average fazendo todas validações necessárias!
		 */
		delete("/map/enrolls/", "application/json" , (req, res) -> {
			String userCode = req.queryParams("userCode");
			Long codeEnrolls = Long.parseLong(userCode);
			boolean operacao = false;
			try{
				operacao = modelEnrolls.deleteEnrolls(codeEnrolls);
				if(operacao){
					res.status(200);
				}else{
					res.status(600);
				}
				return operacao;
			}catch(Exception e){
				e.printStackTrace();
				res.status(600);
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());

		put("/map/enrolls/", (req, res) -> {
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

		get("/map/enrolls/search/student/code", (req, res) -> {
			Long idStudent = Long.parseLong(req.queryParams("idStudent"));
			Student student = null;
			try{
				student = modelEnrolls.searchStudentById(idStudent);
				return student;
			}catch(Exception e) {
				e.printStackTrace();
				return "ops, an error with LOGIN, check the fields!";
			}
		}, JsonUtil.json());
		
		post("/map/enrolls/search/student/validate", (req, res) -> {
			String email = req.queryParams("email");
			Long ra = Long.parseLong(req.queryParams("ra"));
			boolean validate = false;
			try{
				validate = modelEnrolls.validateStudent(email, ra);
				
				return validate;
			}catch(Exception e) {
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

		get("/map/enrolls/search/students/all/fatec", (req, res) -> {
			Long idFatec = Long.parseLong(req.queryParams("fatecCode"));
			try{
				return modelEnrolls.searchAllStudents(idFatec);
			}catch(NullPointerException e){
				return "ops, It wasin't possible find all Studentss!";
			}
		}, JsonUtil.json());
	}
}
