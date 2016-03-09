package br.com.fatec.model;

import java.util.List;

import br.com.fatec.dao.DaoPeriod;
import br.com.fatec.model.period.Period;

public class ModelPeriod {
	
	public boolean insertPeriod(Period period) {
		return DaoPeriod.insertPeriod(period);
	}

	public boolean updatePeriod(Period period) {
		return DaoPeriod.updatePeriod(period);
	}

	public boolean deleteCourse(Long code) {
		return DaoPeriod.deletePeriod(code);
	}

	public Period searchCourseByCode(Long code) {
		return DaoPeriod.searchPeriodById(code);
	}

	public List<Period> searchAllCourse() {
		return	DaoPeriod.searchAllPeriod(); 
	}
}
