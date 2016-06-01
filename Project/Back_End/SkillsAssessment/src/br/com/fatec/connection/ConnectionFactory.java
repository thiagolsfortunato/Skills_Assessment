package br.com.fatec.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	
	private static String USER = "root";
    private static String PASSWORD = "eduardo";
    private static String IP = "192.168.98.135:3306/SkillsAssessment?useSSL=false";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    
    public Connection getConnection(){
    	
    	System.out.println(">> Connecting to database");
    	
    	try{   		
    		
    		Class.forName(DRIVER);
            
    		return DriverManager.getConnection("jdbc:mysql://" + IP , USER, PASSWORD);
    	
    	}catch(ClassNotFoundException ex){
    		System.out.println("Falha na conexão com o banco "+ex);
    		Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE,null, ex);
            
    	}catch (SQLException e) {
            System.out.println("Falha de sql "+e);
    		throw new RuntimeException(e);
    	}
		return null;           	
    }

}
