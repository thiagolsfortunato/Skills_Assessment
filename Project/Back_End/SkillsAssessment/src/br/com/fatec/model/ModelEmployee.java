package br.com.fatec.model;
import java.sql.SQLException;
import java.util.List;
import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.entity.Employee;


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
			System.out.println("Error when registering "+e);
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
			System.out.println("Error delete "+e);
		}
		return false;
	}	
	
	public boolean updateEmployee(Employee employee){
		try{
			if(DaoEmployee.update(employee)){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("Error update "+e);
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
			System.out.println("Could not find data "+e);
		}
		return null;
	}	
	
	public List<Employee> searchAllEmployee() {
		try {
			List<Employee> listEmployee;
			listEmployee = DaoEmployee.searchAll(); 
			return listEmployee;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could not find data "+e);
		}
		return null;
	}	
	
}
