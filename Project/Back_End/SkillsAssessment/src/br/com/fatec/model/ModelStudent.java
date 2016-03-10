package br.com.fatec.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.dao.DaoStudent;
import br.com.fatec.entity.Student;

public class ModelStudent {
	
	public boolean addStudent(Student comp){
		return false;
	}
	
	public Student getStudentById(String code) {
		try {
			return DaoStudent.getStudentById(code);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could not find data");
			return null;
		}
	}
	
	public Long updateStudent(Student comp, Long code){
		return null;
	}
	
	
	public List<Student> findAll() {
		try {
			return DaoStudent.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could not find data");
		}
		return null;
	}
	
	
}
