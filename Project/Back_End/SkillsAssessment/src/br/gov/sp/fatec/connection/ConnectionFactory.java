package br.gov.sp.fatec.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	
	private static String USER = "root"; //OPEN-SHIFT: adminukuHuEu LOCAL: root
    private static String PASSWORD = "inacio"; //OPEN-SHIFT:2yNiFeFmg7Uh LOCAL: inacio
    private static String IP = "localhost:3306/SkillsAssessment?useSSL=false"; //OPEN-SHIFT: 127.4.226.130:3306 LOCAL: localhost
    private static String DRIVER = "com.mysql.jdbc.Driver";
    
    public Connection getConnection(){
    	
    	System.out.println(">> Connecting to database");
    	
    	try{   		
    		
    		Class.forName(DRIVER);
            
    		return DriverManager.getConnection("jdbc:mysql://" + IP , USER, PASSWORD);
    	
    	}catch(ClassNotFoundException ex){
    		
    		Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE,null, ex);
            
    	}catch (SQLException e) {
            
    		throw new RuntimeException(e);
    	}
		return null;           	
    }

}
