package br.com.fatec.controller;
import static spark.Spark.get;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.fatec.model.ModelQuestion;
import br.com.fatec.model.question.Question;
import br.com.fatec.model.student.Student;
import spark.Request;
import spark.Response;
import spark.Route;

public class StudentRoutes {
	
	/*public void getStudentCompetencies(){
		
		get(new Route("/competencies/:ra") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	
	            Integer ra = Integer.parseInt(request.params(":ra"));
	        	
	            
	            try {
	            	Student student = model.searchStudentByRA(ra);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObj = new JSONObject();

	        		jsonObj.put("name", student.getName());
	        		jsonObj.put("lidership", student.getCompetencies().getLeadership());
	        		jsonObj.put("communication", student.getCompetencies().getCommunication());
	        		jsonObj.put("values", student.getCompetencies().getValues());
	        		jsonObj.put("workGroup", student.getCompetencies().getWorkGroup());
	        		jsonObj.put("determination", student.getCompetencies().getDetermination());
	        		jsonObj.put("resilience", student.getCompetencies().getResilience());
	        		jsonObj.put("autonomy", student.getCompetencies().getAutonomy());
	        		
	             	jsonResult.put(jsonObj);
	             	
	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });    
	}*/
}
