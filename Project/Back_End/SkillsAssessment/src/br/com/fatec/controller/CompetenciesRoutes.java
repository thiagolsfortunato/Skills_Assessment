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
			return modelCompetencies.insertCompetence(competence);
		}, JsonUtil.json());
		
		// delete
		delete("/deleteCompetence", "application/json", (req, res) -> {
			Long competenceCode = Long.parseLong(req.queryParams("competenceCode"));
			return modelCompetencies.deleteCompetence(competenceCode);
		}, JsonUtil.json());
		
		// update
		put("/updateCompetence", (req, res) -> {
			String competenceData = req.body();
			Competence competence = gson.fromJson(competenceData, Competence.class);
			return modelCompetencies.updateCompetence(competence);
		}, JsonUtil.json());
		
		// search by code
		get("/searchCompetenciesById", "application/json", (req, res) -> {
			Long competenceCode = Long.parseLong(req.queryParams("competenceCode"));
			return modelCompetencies.searchCompetenceByCode(competenceCode);
		} , JsonUtil.json());
		
		// search all
		get("/searchAllCompetencies", (req, res) -> {
			return modelCompetencies.searchAllCompetence();
		} , JsonUtil.json());
	}
}
