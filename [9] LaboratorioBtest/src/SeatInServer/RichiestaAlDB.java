package SeatInServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.*; // import postgresql-42.1.4.jar

public class RichiestaAlDB {
	
	private String query;
	private String userId;
	private String password;
	
	
	/**
	 * Costruttore per compiere una query sul db
	 */
	protected RichiestaAlDB() {
		this.query = query;
	}

	
	/**
	 * Costruttore che inizializza la query
	 */
	protected RichiestaAlDB(String query) {
		this.query = query;
	}

	
	/**
	 *  <p style="color:darkblue"><b>    Effettuo una query di test sul DB    </b></p> 
	 */
	public void testQueryOnDB() {
	      Connection c = null;
	      try {
	    	  
	    	 /* CARICO IL DRIVER DA UTILIZZARE*/
	         Class.forName("org.postgresql.Driver");		
	         
	    
	       /* USO IL DRIVER PER CONNETTERE IL PROGRAMMA AL DATABASE  VOLUTO */
	       /* chi ospita il database, userID, password */
	       c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "postgres");
	       
	       
	       /* ESEGUO UNA QUERY */
	       Statement stmt = c.createStatement();
	       
	       			
	       /* ESEGUO DELLA QUERY */
	       ResultSet  rsStmt = stmt.executeQuery ("select * from public.\"TestTable\" where id = 1");
	       
	       
	       /* MOSTRO IL RISULTATO DLLA QUERY*/
	       while (rsStmt.next()) 
	       { 
	       System.out.println (("ID: " + rsStmt.getInt("id"))); 
	       System.out.println (("NOME: " + rsStmt.getString("name"))); 
	       System.out.println (("CONOME: " + rsStmt.getString("cognome"))); 
	       System.out.println (("ETA: " + rsStmt.getString("eta"))); 
	       System.out.println (("ALTEZZA: " + rsStmt.getString("altezza"))); 
	       System.out.println("");;
	       } 
	      
	       
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully!");
	   }
}