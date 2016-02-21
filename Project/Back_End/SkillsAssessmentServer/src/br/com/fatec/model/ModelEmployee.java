package br.com.fatec.model;
import java.sql.SQLException;

import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.dao.DaoStudent;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;

public class ModelEmployee {
	
	public boolean addEmployee(Employee comp){
		return false;
	}
	
	public Employee searchEmployeeByCode(Integer code) {
		try {
			Employee employee = new Employee();
			employee = DaoEmployee.searchByCode(code); //MUDEI
			
			return employee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}	
	
	
	public Long updateEmployee(Employee comp, Long code){
		return null;
	}
}
