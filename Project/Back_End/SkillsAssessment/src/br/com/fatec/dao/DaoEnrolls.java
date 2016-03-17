package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Course;
import br.com.fatec.entity.Enrolls;

public class DaoEnrolls {
	
	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) 
	@SuppressWarnings("finally")
	public static boolean insertPeriod(Enrolls enrolls){
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "INSER INTO ENROLLS (prd_year, prd_period, crs_code) VALUES (?,?,?"+ enrolls.getYear() +","+
																				    enrolls.getPeriod() +","+
																				    enrolls.getCodeCourse() +");";
		boolean insert = false;
		try{
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setInt(1,enrolls.getYear());
			connection.getStatement().setInt(2,enrolls.getPeriod());
			connection.getStatement().setLong(3,enrolls.getCodeCourse());
			if(connection.executeSql()){
				insert = true;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			connection.close();
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deletePeriod(Long code) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "DELETE FROM ENROLLS WHERE PRD_CODE = ?;";
		boolean delete = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setLong(1,code);
			if (connection.executeSql()) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return delete;
		}
	}
	
	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) --- VERIFICAR AQUI
	@SuppressWarnings("finally")
	public static boolean updatePeriod(Enrolls enrolls){
		ConnectionMySql connection =  new ConnectionMySql();
		String sql = "UPDATE COURSES SET crs_code = ?"+ enrolls.getCodeEnrolls() +", "
								      + "prd_year = ?"+ enrolls.getYear()+", "
						            + "prd_period = ?"+ enrolls.getPeriod()+", "
								      + "crs_code = ?"+ enrolls.getCodeCourse()+", "
						 		+ "where prd_code = ?"+ enrolls.getCodeEnrolls() +";";
		boolean update = false;
		try {
			connection.conect();
			connection.setStatement(connection.getConnection().prepareStatement(sql));
			connection.getStatement().setLong(1, enrolls.getCodeEnrolls());
			if(connection.executeSql()){
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return update;
		}
	}	

	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) 
	@SuppressWarnings("finally")
	public static List<Enrolls> searchAllPeriod() {
		List<Enrolls> listPeriod = new ArrayList<>();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from period;";
		try {
			connection.conect();
			if (connection.executeQuery(query)) {
				do {
					Enrolls period = new Enrolls();
					period.setCodeEnrolls(Long.parseLong(connection.returnField("PRD_CODE")));
					period.setYear(Integer.parseInt(connection.returnField("PRD_YEAR")));
					period.setPeriod(Integer.parseInt(connection.returnField("PRD_PERIOD")));
					listPeriod.add(period);
				} while (connection.nextRegister());
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return listPeriod;
		}
	}
	
	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) 
	@SuppressWarnings("finally")
	public static Enrolls searchPeriodById(Long code){
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course where crs_code = "+ code +";";
		Enrolls period = new Enrolls();
		try {
			connection.conect();
			if(connection.executeQuery(query)){
				do{					
					period.setCodeEnrolls(Long.parseLong(connection.returnField("PRD_CODE")));
					period.setYear(Integer.parseInt(connection.returnField("PRD_YEAR")));
					period.setPeriod(Integer.parseInt(connection.returnField("PRD_PERIOD")));
				}while(connection.nextRegister());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return period;
		}
	}
}

