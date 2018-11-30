package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.*; // import postgresql-42.1.4.jar

public class DBHandler {
	private static Connection connection;

	public static void main(String args[]) {
	      connection = null;
	      try {
	    	  
	    	 /* CARICO IL DRIVER DA UTILIZZARE*/
	         Class.forName("org.postgresql.Driver");		
	         
	    
	       /* USO IL DRIVER PER CONNETTERE IL PROGRAMMA AL DATABASE  VOLUTO */
	       /* chi ospita il database, userID, password */
	       connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "postgres");
	       
	       
	       /* ESEGUO UNA QUERY */
	       Statement stmt = connection.createStatement();
	       
	       			
	       /* ESEGUO DELLA QUERY */
	       ResultSet  rsStmt = stmt.executeQuery ("select * from public.\"TestTable\" where id = 1");
	       
	       
	       /* MOSTRO IL RISULTATO DLLA QUERY*/
	       while (rsStmt.next()) 
	       { 
	       System.out.println ((rsStmt.getInt("id"))); 
	       System.out.println ((rsStmt.getString("name"))); 
	       } 
	      
	       
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully!");
	   }
}