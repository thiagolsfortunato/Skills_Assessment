package br.com.fatec.model;

import java.util.List;

import br.com.fatec.dao.DaoEnrolls;
import br.com.fatec.entity.Enrolls;

public class ModelEnrolls {
	
	public boolean insertPeriod(Enrolls period) {
		return DaoEnrolls.insertPeriod(period);
	}

	public boolean updatePeriod(Enrolls period) {
		return DaoEnrolls.updatePeriod(period);
	}

	public boolean deleteCourse(Long code) {
		return DaoEnrolls.deletePeriod(code);
	}

	public Enrolls searchCourseByCode(Long code) {
		return DaoEnrolls.searchPeriodById(code);
	}

	public List<Enrolls> searchAllCourse() {
		return	DaoEnrolls.searchAllPeriod(); 
	}
}
