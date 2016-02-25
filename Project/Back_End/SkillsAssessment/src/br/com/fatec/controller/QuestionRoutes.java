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


public class QuestionRoutes{
	
	private ModelQuestion model;
	
	
	public QuestionRoutes(ModelQuestion model){
		this.model = model;
	}
	
	/*public void getQuestionByNumber(){
		
		get(new Route("/questions/:number") {
	         @Override
	         public Object handle(Request request, Response response) {
	        	
	            Integer number = Integer.parseInt(request.params(":number"));
	        	
	            
	            try {
	            	Question question = model.searchQuestionByCode(number);
	            	
	            	JSONArray jsonResult = new JSONArray(); //first
	         	    JSONObject jsonObjQuestion = new JSONObject();

	         	    jsonObjQuestion.put("number", question.getNumber());
	        		jsonObjQuestion.put("introduction", question.getIntroduction());
	        		jsonObjQuestion.put("question", question.getQuestion());
	        		jsonObjQuestion.put("answer", question.getAnswers());
    	
	             	jsonResult.put(jsonObjQuestion);

	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });
	}*/
		
}
