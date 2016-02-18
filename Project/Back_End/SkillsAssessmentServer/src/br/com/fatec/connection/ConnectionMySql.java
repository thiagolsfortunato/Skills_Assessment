package br.com.fatec.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionMySql {

	private static String user="root";
    private static String password="inacio";
    private static String database="SkillsAssessment";
    private static String ip="localhost:3306/SkillsAssessment?useSSL=false";
    private static String driver="com.mysql.jdbc.Driver";
    private static Connection connection = null;

    //padrao singleton
    public static Connection getConnection() {
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

            closeConnection();
            throw new RuntimeException(e);
        }

    }

    public static void closeConnection(){
        try{
            if(connection!=null && !connection.isClosed()){
                connection.close();
                System.out.println(">>Connection successfully closed");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static java.sql.Connection restartConnection() {
		closeConnection();
		return ConnectionMySql.getConnection();
	}

}
