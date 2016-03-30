package br.com.fatec.controller;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fatec.commons.JsonUtil;
import br.com.fatec.entity.Institution;
import br.com.fatec.model.ModelInstitution;

public class InstitutionRoutes {
	
	public static void getInstitution(){
		ModelInstitution model = new ModelInstitution();
		 
		get("/Institution/find", (req, res) -> {
			Long code = Long.parseLong( req.queryParams("code") );
			String name =  req.queryParams("name") ;
			Map<Integer, String> mapa = new HashMap<Integer, String>();
			
			if(  name!=null ){
				List<Institution> fatecs = model.searchInstitutionByName(name);
				return fatecs;
			}else if( (code!=null) && (name==null) ){
				Institution fatec = model.searchInstitutionByCode(code);
				return fatec;
			}else{	
				mapa.put(404, "nenhuma fatec encontrada");
				return mapa;
			}
			 
			
		}, JsonUtil.json());
		
		get("/Institution/delete", (req, res) -> {
			Map<Integer, String> mapa = new HashMap<Integer, String>();
			Long code = Long.parseLong( req.queryParams("code") );
			boolean sucess = model.deleteInstitution(code);
			if(sucess){
				mapa.put(200, "removido com sucesso");
			}else{
				mapa.put(400, "codigo fatec inválida");
			}
			return mapa;
		}, JsonUtil.json());
		
		get("/Institution/findAll", (req, res) -> {
			Map<Integer, String> mapa = new HashMap<Integer, String>();
			List<Institution> fatecs = model.searchAllInstitution();
			if(fatecs.size() > 0){
				return fatecs;
			}else{
				mapa.put(400, "nenhuma fatec cadastrada");
				return mapa;
			}
		}, JsonUtil.json());
		
	
		
	}

}
