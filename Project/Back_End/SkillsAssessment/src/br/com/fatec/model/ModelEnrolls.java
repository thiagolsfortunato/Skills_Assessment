package br.com.fatec.model;

import java.util.List;

import br.com.fatec.dao.DaoEnrolls;
import br.com.fatec.entity.Enrolls;

public class ModelEnrolls {
	
	public boolean insertEnrolls(Enrolls enrolls) {
		return DaoEnrolls.insertEnrolls(enrolls);
	}

	public boolean updateEnrolls(Enrolls enrolls) {
		return DaoEnrolls.updateEnrolls(enrolls);
	}

	public boolean deleteEnrolls(Long code) {
		return DaoEnrolls.deleteEnrolls(code);
	}

	public Enrolls searchEnrollsByCode(Long code) {
		return DaoEnrolls.searchEnrollsById(code);
	}

	public List<Enrolls> searchAllEnrolls() {
		return	DaoEnrolls.searchAllEnrolls(); 
	}
}
