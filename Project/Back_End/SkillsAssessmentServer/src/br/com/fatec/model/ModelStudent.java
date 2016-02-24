package br.com.fatec.model;
import java.sql.SQLException;

import br.com.fatec.dao.DaoStudent;
import br.com.fatec.model.student.Student;

public class ModelStudent {
	
	public boolean addStudent(Student comp){
		return false;
	}
	
	public Student searchStudentById(Long code) { //MUDEI
		
		try {
			Student student = new Student();
			student = DaoStudent.searchStudentByCode(code); //MUDEI
			
			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not find data");
			return null;
		}
		
	}
	
	public Long updateStudent(Student comp, Long code){
		return null;
	}
	
}
