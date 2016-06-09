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
import br.gov.sp.fatec.entity.FatecAdm;
import br.gov.sp.fatec.entity.Institution;
import br.gov.sp.fatec.model.ModelInstitution;
import br.gov.sp.fatec.entity.Fatec_Courses;

public class InstitutionRoutes {
	
	public static void getInstitution(){
		ModelInstitution model = new ModelInstitution();
		
		options("/map/institution/*", (req, res) -> {
			res.status(200);
			return CorsFilter.getCorsheaders();
		});
		
		post("/map/institution/", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8"); 
			
			FatecAdm fatecAdm = gson.fromJson(value, FatecAdm.class);
			//User adm = gson.fromJson(data, User.class);
	
			boolean sucess = model.insertInstitution(fatecAdm.getFatec(), fatecAdm.getAdm());
			
			if(sucess){
				res.status(200);
				return "successfully inserted";
			}else{
				res.status(400);
				return "failure to enter the institution";
			}
		}, JsonUtil.json());
		
		put("/map/institution/", (req, res) -> {
			String data = req.body();
			Gson gson = new Gson();
			
			byte ptext[] = data.getBytes("ISO-8859-1"); 
			String value = new String(ptext, "UTF-8");
			
			Institution fatec = gson.fromJson(value, Institution.class);
			
			boolean sucess = model.updateInstitution(fatec);
			if(sucess){
				res.status(200);
				return "successfully changed";
			}else{
				res.status(400);
				return "failure to change institution";
			}
		}, JsonUtil.json());
		
		get("/map/institution/find/name", (req, res) -> {
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
		
		get("/map/institution/find/code", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("code") );
				Institution fatec = model.searchInstitutionByCode(code);
				
				if( fatec.getCodeInstitution() == null ){
					res.status(404);
					return "FATEC not found";
				}else{
					return fatec;
				}	
			}
		}, JsonUtil.json());
		
		delete("/map/institution/", (req, res) -> {
			if( req.queryParams("code") == null ){
				res.status(400);
				return "invalid parameter";
			}else{
				Long code = Long.parseLong( req.queryParams("code") );
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
		
		get("/map/institution/find/all", (req, res) -> {
			List<Institution> fatecs = model.searchAllInstitution();
			if(fatecs.size() > 0){
				return fatecs;
			}else{
				res.status(600);
				return "no FATEC registered";
			}
		}, JsonUtil.json());
		
		get("/map/institution/find/all/courses", (req, res) -> {
			List<Fatec_Courses> fatecs = model.searchAllInstitutionCourses();
			if(fatecs.size() > 0){
				return fatecs;
			}else{
				res.status(600);
				return "no FATEC registered";
			}
		}, JsonUtil.json());
		
	}

}
