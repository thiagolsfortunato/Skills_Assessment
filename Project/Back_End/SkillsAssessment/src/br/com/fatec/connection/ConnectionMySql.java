package br.com.fatec.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionMySql {

	private static String user="root";
    private static String password="eduardo";
    private static String ip="192.168.98.128:3306/SkillsAssessment?useSSL=false";
    private static String driver="com.mysql.jdbc.Driver";
    private Connection connection = null;
    private PreparedStatement statement = null;
	private ResultSet resultset = null;
    
    public ConnectionMySql(){};
   
    @SuppressWarnings("finally")
	public boolean conect() throws SQLException{
    	boolean error = true;
    	 System.out.println(">>Connecting to database");
    	try{   		
    		Class.forName(driver);
            connection = DriverManager.getConnection("jdbc:mysql://"+ip+"",user, password);
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
        	if(resultset != null && !resultset.isClosed()){
        		resultset.close();
        	}
        	if(statement != null && !statement.isClosed()){
        		statement.close();
        	}
            if(connection!=null && !connection.isClosed()){
                connection.close();
            }
            System.out.println(">>Connection successfully closed");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //select
    public boolean executeQuery() throws SQLException {
    	resultset = statement.executeQuery(); //executa sql
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
    
    //GETS and SETS
    public Connection getConnection() {
		return connection;
	}
    
    public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getStatement(){
    	return statement;
    }
    
	public ResultSet getResultset() {
		return resultset;
	}

	public void setResultset(ResultSet resultset) {
		this.resultset = resultset;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}
	
    //INSERT, UPDATE E DELETE
    public boolean executeSql() throws SQLException {
        int i = statement.executeUpdate();
        return i == 0 ?  false : true;
    }
        
	public Connection restartConnection() {
		close();
		return getConnection();
	}
}
