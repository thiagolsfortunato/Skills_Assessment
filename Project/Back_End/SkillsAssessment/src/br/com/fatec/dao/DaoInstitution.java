package br.com.fatec.dao;

import java.util.LinkedList;
import java.util.List;

import br.com.fatec.entity.Institution;

public class DaoInstitution {
	
	public static boolean insertInstitution(Institution instituition){
		return false;
	}
	
	public static boolean deleteInstitution(Institution instituition){
		return false;
	}
	
	public static boolean updateInstitution(Institution instituition){
		return false;
	}
	
	public static List<Institution> searchAllInstitution(Institution instituition){
		return new LinkedList<Institution>();
	}
	
	public static Institution searchInstitutionById(Institution instituition){
		return new Institution();
	}
	//user %like% do banco, para trazer por parte do nome
	public static List<Institution> searchInstitutionByName(Institution instituition){
		return new LinkedList<Institution>();
	}

}
