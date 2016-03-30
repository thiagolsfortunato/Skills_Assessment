package br.com.fatec.controller;

import static spark.Spark.get; // select
import static spark.Spark.put; // update
import static spark.Spark.delete; // delete
import static spark.Spark.post; // insert

/*import java.sql.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.fatec.entity.Course;
*/
import com.google.gson.Gson;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.entity.Competence;

import br.com.fatec.model.ModelCompetencies;

public class CompetenciesRoutes {
	
	public static void getCompetencies() {
		ModelCompetencies modelCompetencies = new ModelCompetencies();
		Gson gson = new Gson();
		
		// insert
		post("/insertCompetence", (req, res) -> {
			String competenceData = req.body();
			Competence competence = gson.fromJson(competenceData, Competence.class);
			try{
				return modelCompetencies.insertCompetence(competence);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with inserting, check the fields!";
			}
		}, JsonUtil.json());
		
		// delete
		delete("/deleteCompetence", "application/json", (req, res) -> {
			Long competenceCode = Long.parseLong(req.queryParams("competenceCode"));
			try{
				return modelCompetencies.deleteCompetence(competenceCode);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "ops, an error with deleting, check the fields!";
			}
		}, JsonUtil.json());
		
		// update
		put("/updateCompetence", (req, res) -> {
			String competenceData = req.body();
			Competence competence = gson.fromJson(competenceData, Competence.class);
			try{
				return modelCompetencies.updateCompetence(competence);
			}catch(NullPointerException e){
				e.printStackTrace();
				return "ops, an error with updating, check the fields!";
			}
		}, JsonUtil.json());
		
		// search by code
		get("/searchCompetenciesById", "application/json", (req, res) -> {
			Long competenceCode = Long.parseLong(req.queryParams("competenceCode"));
			try{
				return modelCompetencies.searchCompetenceByCode(competenceCode);
			}catch(NullPointerException e){
				e.printStackTrace();
				res.status(400);
				return "It was not possible to find a Competence";
			}
		} , JsonUtil.json());
		
		// search all
		get("/searchAllCompetencies", (req, res) -> {
			try{
				return modelCompetencies.searchAllCompetence();
			}catch(NullPointerException e){
				e.printStackTrace();
				return "It wasin't possible find all Competences!";
			}
		} , JsonUtil.json());
	}
}
