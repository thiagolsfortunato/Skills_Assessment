package br.com.fatec.model;
import java.sql.SQLException;

import br.com.fatec.dao.DaoStudent;
import br.com.fatec.model.student.Student;

public class ModelStudent {
	
	public boolean addStudent(Student comp){
		return false;
	}
	
	public Student searchStudentById(String ra) {
		Student student = new Student();
		
		try {
			student = DaoStudent.searchStudentByRa(ra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}
	
	
	public Long updateStudent(Student comp, Long code){
		return null;
	}
	
	public Boolean getLogin(String ra){
		return false;
	}
}
