package br.com.fatec.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	
	private static String USER = "root";
    private static String PASSWORD = "inacio";
    private static String IP = "localhost:3306/SkillsAssessment?useSSL=false";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    
    public Connection getConnection(){
    	
    	System.out.println(">> Connecting to database");
    	
    	try{   		
    		
    		Class.forName(DRIVER);
            
    		return DriverManager.getConnection("jdbc:mysql://" + IP , USER, PASSWORD);
    	
    	}catch(ClassNotFoundException ex){
    		
    		Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE,null, ex);
            
    	}catch (SQLException e) {
            
    		throw new RuntimeException(e);
    	}
		return null;           	
    }

}
