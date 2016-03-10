package br.com.fatec.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionMySql {

	private static String user="root";
    private static String password="inacio";
    private static String ip="localhost:3306/SkillsAssessment?useSSL=false";
    private static String driver="com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultset = null;
    
    public ConnectionMySql(){};
    
    //padrao singleton
    public Connection getConnection() {
        System.out.println(">>Connecting to database");
        try {
            Class.forName(driver);
            if(connection==null || connection.isClosed()){
            	connection=DriverManager.getConnection("jdbc:mysql://"+ip+"",user, password);
                //connection=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+database+"",user, password);
            }
            return connection; 
        }catch (ClassNotFoundException e) {    
            throw new RuntimeException(e);    
        }catch (SQLException e) {

            close();
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("finally")
	public boolean conect() throws SQLException{
    	boolean error = true;
    	 System.out.println(">>Connecting to database");
    	try{   		
    		Class.forName(driver);
    		// realizando conexão
            connection = /*(Connection)*/ DriverManager.getConnection("jdbc:mysql://"+ip+"",user, password);
            // criando canal para execução de sql
            statement = /*(Statement)*/ connection.createStatement();
            // retorna resultado da conexão      
    	}catch(ClassNotFoundException ex){
    		Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE,null, ex);
            error = false;
    	}catch (SQLException e) {
            close();
            throw new RuntimeException(e);
    	}finally{
    		return error;	
    	}            	
    }
    
    public void close(){
        try{
            if(connection!=null && !connection.isClosed()){
                connection.close();
                statement.close(); 
                System.out.println(">>Connection successfully closed");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //select
    public boolean executeQuery(String query) throws SQLException{
    	resultset = statement.executeQuery(query); //executa sql
        return resultset.next(); //aponta para primeiro registro da consulta
    }
    
    public ResultSet returnRegister() throws SQLException {
        return resultset;
    }
    
    public String returnField(String campo) throws SQLException {
        return resultset.getString(campo);
    }
    
    public boolean nextRegister() throws SQLException {
        return resultset.next();
    }
    
    public Statement getStatement(){
    	return statement;
    }
    
    //INSERT, UPDATE E DELETE
    public boolean executeSql(String SQL) throws SQLException {
        int i = statement.executeUpdate(SQL);
        return i == 0 ?  false : true;
    }
    
	public Connection restartConnection() {
		close();
		return getConnection();
	}

}
