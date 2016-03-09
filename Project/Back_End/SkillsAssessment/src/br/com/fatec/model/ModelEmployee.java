package br.com.fatec.model;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.dao.DaoStudent;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;

public class ModelEmployee {
	
	public boolean addEmployee(Employee employee){
		try{
			if (DaoEmployee.addEmployee(employee)){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("Error when registering");
		}
		return false;
	}
	
	public boolean deleteEmployee(Long code){
		try{
			if (DaoEmployee.deleteEmployee(code)){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("Error delete");
		}
		return false;
	}	
	
	public boolean updateEmployee(Employee employee, Long code){
		try{
			if(DaoEmployee.update(employee, code)){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("Error update");
		}
		return false;
	}
	
	public Employee searchEmployeeByCode(Long code) {
		try {
			Employee employee = new Employee();
			employee = DaoEmployee.searchByCode(code); 			
			return employee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}	
	
	//FAZER O FIND ALL
	public List<Employee> searchAllEmployee() {
		try {
			List<Employee> listEmployee;
			listEmployee = DaoEmployee.searchAll(); 
			return listEmployee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}	
	
}
