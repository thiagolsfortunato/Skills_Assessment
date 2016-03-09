package br.com.fatec.controller;

import static spark.Spark.get;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.fatec.entity.Competence;
import br.com.fatec.model.ModelCompetencies;

public class CompetenciesRoutes {
	
	public void getCompetencies (){
		//Search by name
		final ModelCompetencies mc = new ModelCompetencies();
		get("/competence/:code", (req, res) -> {		
		try {
			Competence comp = mc.searchCompetenceByCode(Long.parseLong(req.params(":code")));
			if(comp!=null){
				JSONArray jsonResult = new JSONArray();
				JSONObject jsonCompetence = new JSONObject();
				jsonCompetence.put("Code", comp.getNumber());
				jsonCompetence.put("Description", comp.getDescription());
				jsonCompetence.put("Date", comp.getRegister());
				jsonCompetence.put("Weight", comp.getWeight());
				
				jsonResult.put(jsonCompetence);
				
				return jsonResult;
			}
		} catch (JSONException e){
			e.printStackTrace();
			System.out.println("Erro Json!");
		}
		return null;
		});
		
		//Add
		get("/competence/:code/:description/:date/:weight", (req, res) -> {

			Long code = Long.parseLong(req.params(":code"));
			String description = req.params(":description");
			Date register = Date.valueOf(req.params(":date"));
			Integer weight = Integer.parseInt(req.params(":weight"));

			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			
			boolean resultAddCompetence = mc.addCompetence(code, description, register, weight);

			try {
				jsonObj.put("ResultAddEmployee", resultAddCompetence);
				jsonResult.put(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return jsonResult;
		});
		
		//Delete
		get("/competence/delete/:description", (req, res) -> {

			String description = req.params(":description");

			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			boolean resultDeleteCompetence = mc.deleteCompetence(description);

			try {
				jsonObj.put("ResultDeleteCompetence", resultDeleteCompetence);
				jsonResult.put(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return jsonResult;
		});
		
		//Search all
		get("/competence/searchAll", (req, res) -> {

			List<Competence> listCompetence = mc.searchAllCompetence();
			JSONArray jsonResult = new JSONArray();

			for (Competence comp : listCompetence) {
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("code", comp.getNumber());
					jsonObj.put("description", comp.getDescription());
					jsonObj.put("registration_date", comp.getRegister());
					jsonObj.put("weight", comp.getWeight());

					jsonResult.put(jsonObj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return jsonResult;
		});
		
		//Update Competences
		/*get("/competence/update/:competence/:code", (req, res) -> {
			 Competence comp = new Competence();
			 comp = req.params(":competence").getClass();
			 Long cod = Long.parseLong(req.params(":code"));
			 List<Competence> listCompetence = mc.updateCompetence(comp, cod);
			 JSONArray jsonResult = new JSONArray(); 
			 for (Competence competence : listCompetence) {
				 JSONObject jsonObj = new JSONObject();
				 try { 
					 jsonObj.put("code", competence.getNumber());
					 jsonObj.put("description", competence.getDescription());
					 jsonObj.put("registration_date", competence.getRegister());
					 jsonObj.put("weight", competence.getWeight());
					 jsonResult.put(jsonObj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return jsonResult;
		});*/
	}
	
}
