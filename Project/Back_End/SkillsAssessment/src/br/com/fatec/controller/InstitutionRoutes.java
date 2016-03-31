package br.com.fatec.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.entity.Institution;
import br.com.fatec.model.ModelInstitution;

public class InstitutionRoutes {
	
	public static void getInstitution(){
		ModelInstitution model = new ModelInstitution();
		
		post("/institution/insert", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			Institution fatec = gson.fromJson(data, Institution.class);
	
			boolean sucess = model.insertInstitution(fatec);
			if(sucess){
				res.status(200);
				return "successfully inserted";
			}else{
				res.status(400);
				return "failure to enter the institution";
			}
		}, JsonUtil.json());
		
		put("/institution/update", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			Institution fatec = gson.fromJson(data, Institution.class);
			
			boolean sucess = model.updateInstitution(fatec);
			if(sucess){
				res.status(200);
				return "successfully changed";
			}else{
				res.status(400);
				return "failure to change institution";
			}
		}, JsonUtil.json());
		
		get("/institution/find/name", (req, res) -> {
			String name =  req.queryParams("name") ;
			List<Institution> fatecs;
			if( name != null ){
				fatecs = model.searchInstitutionByName(name);
				if(fatecs.size() > 0){
					return fatecs;
				}else{
					res.status(404);
					return "FATEC not found";
				}
			}else{
				res.status(400);
				return "invalid parameter";
			} 
			
		}, JsonUtil.json());
		
		get("/institution/find/code", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("codeInstitution") );
				Institution fatec = model.searchInstitutionByCode(code);
				
				if( fatec.getCode() == null ){
					res.status(404);
					return "FATEC not found";
				}else{
					return fatec;
				}	
			}
		}, JsonUtil.json());
		
		get("/institution/delete", (req, res) -> {
			if( req.queryParams("codeInstitution") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("codeInstitution") );
				boolean sucess = model.deleteInstitution(code);
				if(sucess){
					res.status(200);
					return "successfully removed";
				}else{
					res.status(400);
					return "Invalid FATEC code";
				}
			}
		}, JsonUtil.json());
		
		get("/institution/find/all", (req, res) -> {
			List<Institution> fatecs = model.searchAllInstitution();
			if(fatecs.size() > 0){
				return fatecs;
			}else{
				res.status(400);
				return "no FATEC registered";
			}
		}, JsonUtil.json());
		
	}

}
