package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import client.Logger;
import seatInServer.DBModel.Utente;
import com.sun.corba.se.spi.activation.Server;

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
	       System.out.println (("COGNOME: " + rsStmt.getString("cognome")));
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


	public static long GenCodAtt() {
		Random rand = new Random();
		final int Max = 999999;
		final int Min = 100000;
		long randomNum = rand.nextInt((Max - Min) + 1) + Min;
		return randomNum;
	}

	public static void BloccaAccount(Connection conn, String Email) {
		String subject = "Account bloccato SimpleELearning";
		long codAtt = GenCodAtt();
		String tempPSW = GenCodAtt() + "";
		String body = "Account bloccato...\nPassword Temporanea = " + tempPSW + ";\nCodice d'attivazione:" + codAtt
				+ "\n";
		seatInServer.utilities.EmailSender.send_uninsubria_email(Email, subject, body);
		String query = "UPDATE \"Anagrafiche\" SET (\"Verificato\",\"CodiceAttivazione\", \"Password\") =  (false, "
				+ codAtt + ", '" + tempPSW + "'" + ") WHERE \"Email\" LIKE '" + Email + "'";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			seatInServer.utilities.EmailSender.send_uninsubria_email(Email, "Blocco Account SimpleELearning",
					"Account bloccato ... codice di sblocco:" + codAtt);
		} catch (Exception e) {
			System.out.println("Errore nel blocco dell'account");
		}

	}

	public static void CambiaPsw(Connection conn, long Matricola, String newPsw) {
		String query = "UPDATE \"Anagrafiche\" SET \"Password\" = '" + newPsw + "' WHERE \"Matricola\" = " + Matricola;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Errore nella modifica della colonna Verificato");
		}
	}

	public static void ModificaAnagrafica(Connection conn, Utente anagAlterata) {
		try {
			String query = "";
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			Logger.WriteError(e, "AnagraficaController", "ModificaAnagrafica");
		}
	}

	public static void VerificaTrue(Connection conn, long Matricola) {
		String query = "UPDATE \"Anagrafiche\" SET \"Verificato\" = true WHERE \"Matricola\" = " + Matricola;
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Errore nella modifica della colonna Verificato");
		}
	}

	public static boolean VerificaCod(Connection conn, long Matricola, long CodAtt) {
		String query = "SELECT \"CodiceAttivazione\" FROM \"Anagrafiche\" WHERE \"Matricola\" =" + Matricola;
		try {
			long codAttDB = -1;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				codAttDB = rs.getLong(1);
			}
			if (codAttDB == CodAtt) {
				VerificaTrue(conn, Matricola);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Errore in verifica Cod");
		}
		return false;
	}

	public static void UpdateAnagrafica(Connection conn, Utente mod) {
		try {
			System.out.println(mod.getMatricola()+" Matricola");
			String query = "UPDATE \"Anagrafiche\" SET "
					+ "\"Email\" =  '"+mod.getEmail()+"', "
					+ "\"Nome\" = '"+mod.getNome()+"' , "
					+ "\"Cognome\" = '"+mod.getCognome()+"' "
					+ " WHERE \"Matricola\" =  "+mod.getMatricola();
			Statement st = conn.createStatement();//
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Errore UpdateAnagrafica");
		}
	}

	public static boolean CambiaPsw(Connection conn, String email, String newPsw) {
		String query = "UPDATE \"Anagrafiche\" SET \"Password\" = '" + newPsw + "' WHERE \"Email\" LIKE '" + email
				+ "'";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Errore in verifica Cod");
		}
		return false;
	}

}