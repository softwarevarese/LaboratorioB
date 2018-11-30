package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seatInServer.DBModel.Corso;
import seatInServer.DBModel.CorsoDiLaurea;
import seatInServer.DBModel.Dipartimento;
import seatInServer.utilities.CommonList;

/**
 * Classe che contiene tutti i metodi per i corsi e le operazioni a loro relativi per il DB
 */
public class CorsiLaureaController {

	private static ArrayList<String> getStudentiEmailByCorso(Connection conn, long idCorso) {
		try {
			ArrayList<String> listaEmails = new ArrayList<String>();
			String query = "Select * from \"StudentiXCorsi\" JOIN \"Anagrafiche\" ON \"StudentiXCorsi\".\"Studente\" = \"Anagrafiche\".\"Matricola\""
					+ " Where \"Corso\" = " + idCorso;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			String mail;
			while (rs.next()) {
				mail = rs.getString("Email");
				listaEmails.add(mail);
			}
			return listaEmails;
		} catch (Exception e) {
			System.out.println("Errore getStudentiByCorso");
		}
		return new ArrayList<String>();
	}

	public static ArrayList<Corso> GetCorsiByDocente(Connection conn, long idDocente) {
		try {
			ArrayList<Corso> lista = new ArrayList<Corso>();
			String query = "SELECT * FROM \"CorsiMateria\" JOIN \"DocentiXCorsi\" ON \"CorsiMateria\".\"ID\" = \"DocentiXCorsi\".\"Corso\""
					+ " WHERE \"DocentiXCorsi\".\"Docente\" = " + idDocente;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			Corso corso = null;
			while (rs.next()) {
				corso = new Corso();
				corso.setIdCorso(rs.getInt("ID"));
				corso.setCdLCorso(rs.getLong("CorsoLaurea"));
				corso.setNome(rs.getString("Nome"));
				corso.setAnnoAttivazione(rs.getInt("AnnoAtt"));
				corso.setDescrizione(rs.getString("Descrizione"));
				lista.add(corso);
			}
		} catch (Exception e) {
			System.out.println("Err GetCorsiByDocente");
		}
		return new ArrayList<Corso>();
	}

	public static void SendNewsLetter(Connection conn, long idCorso, String oggetto, String messaggio) {
		try {
			ArrayList<String> emails = getStudentiEmailByCorso(conn, idCorso);
			for (String account : emails) {
				seatInServer.utilities.EmailSender.send_uninsubria_email(account, oggetto, messaggio);
			}
		} catch (Exception e) {

		}
	}

	public static ArrayList<CorsoDiLaurea> GetAllCorsiLaurea(Connection conn) {
		ArrayList<CorsoDiLaurea> listaCorsiLaurea = new ArrayList<CorsoDiLaurea>();
		CorsoDiLaurea newCorso = null;
		try {
			String Query = "Select * From \"CorsiLaurea\"";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(Query);
			while (rs.next()) {
				newCorso = new CorsoDiLaurea();
				newCorso.setId(rs.getInt(1));
				newCorso.setNome(rs.getString(2));
				// Dipartimento WIP!!
				newCorso.setDipartimento(CommonList.getDipartimento(rs.getInt(3)));
				listaCorsiLaurea.add(newCorso);
				//
			}
			return listaCorsiLaurea;
		} catch (Exception e) {
			System.out.println("Errore query per reperire tutti i corsi");
		}

		
		return null;
	}

	public static void NuovoCorsoLaurea(Connection conn, CorsoDiLaurea nuovoCorso) {
		try {
			String query = "INSERT INTO \"Dipartimento\" (\"Nome\", \"Dipartimento\") VALUES (\'" + nuovoCorso.getNome()
					+ "\'," + nuovoCorso.getDipartimento() + ")";
			Statement s = conn.createStatement();
			s.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Errore inserimento nuovo corso di laurea");
		}
	}

	public static ArrayList<Dipartimento> GetAllDipartimenti(Connection conn) {
		ArrayList<Dipartimento> listaDipartimenti = new ArrayList<Dipartimento>();
		Dipartimento newDipartimento = null;
		try {
			String Query = "Select * From \"Dipartimento\"";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(Query);
			while (rs.next()) {
				newDipartimento = new Dipartimento();
				newDipartimento.setId(rs.getInt(1));
				newDipartimento.setNome(rs.getString(2));
				listaDipartimenti.add(newDipartimento);
			}
			return listaDipartimenti;
		} catch (Exception e) {
			System.out.println("Errore query per reperire tutti i dipartimenti");
		}

		return null;
	}

	public static void NuovoDipartimento(Connection conn, Dipartimento nuovoDipartimento) {
		try {
			String query = "INSERT INTO \"Dipartimento\" (\"Nome\") VALUES (\'" + nuovoDipartimento.getNome() + "\')";
			Statement s = conn.createStatement();
			s.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Errore inserimento nuovo dipartimento");
		}
	}

	public static ArrayList<Corso> GetAllCorsiMateria(Connection conn) {
		ArrayList<Corso> listaCorsiMateria = new ArrayList<Corso>();
		Corso corsoMateria = null;
		CorsoDiLaurea corsoLaurea = null;
		String query = "Select * From \"CorsiMateria\"";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				corsoMateria = new Corso();
				corsoMateria.setIdCorso( rs.getInt("ID"));
				corsoMateria.setNome(rs.getString("Nome"));
				corsoMateria.setAnnoAttivazione(rs.getInt("AnnoAtt"));
				corsoMateria.setDescrizione(rs.getString("Descrizione"));
				corsoMateria.setCdLCorso(CommonList.GetCorsoLaurea(rs.getLong("CorsoLaurea")).getIdCdL());
				listaCorsiMateria.add(corsoMateria);
			}
			return listaCorsiMateria;
		} catch (Exception e) {

		}

		return null;
	}

	public static void NuovoCorsoMateria(Connection conn, Corso nuovoCorso) {
		try {
			String query = "INSERT INTO \"CorsiMateria\" (\"CorsoLaurea\", \"Nome\", \"AnnoAtt\", \"Descrizione\")"
					+ " VALUES (" + nuovoCorso.getCdLCorso() + ", '" + nuovoCorso.getNome() + "', " + nuovoCorso.getAnnoAttivazione()
					+ ", '" + nuovoCorso.getDescrizione() + "');";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Errore inserimento nuovo corso materia");
		}
	}

	public static void UpdateSecondsUser(Connection conn, long idCorso, long secondiUpdate) {
		try {
			String query = "UPDATE \"CorsiMateria\" SET \"SecondiConnessi\" = \"SecondiConnessi\"+" + secondiUpdate
					+ "WHERE \"ID\" =" + idCorso;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Errore updateLocation salvata su db");
		}
	}

	public static void UpdateCorsoMateria(Connection conn, Corso corso) {
		try {
			String query = "UPDATE \"CorsiMateria\" SET \"Nome\" = '" + corso.getNome() + "'" + ", \"Descrizione\" ='"
					+ corso.getDescrizione() + "'" + "WHERE \"ID\" =" + corso.getIdCorso();
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			CommonList.UpdateCorsiMateria(conn);//
		} catch (Exception e) {
			System.out.println("Errore UpdateCorsoMateria salvata su db");
		}
	}

	public static double TempoMedioConnessione(Connection conn, long idCorso) {
		try {
			double media = 0;
			String queryStud = "SELECT Count(*) FROM \"Studenti\" JOIN \"StudentiXCorsi\""
					+ " ON \"Studenti\".\"Matricola\" = \"StudentiXCorsi\".\"Studente\" WHERE \"StudentiXCorsi\".\"Corso\" = "
					+ idCorso;
			String querySec = "SELECT \"SecondiConnessi\" FROM \"CorsiMateria\" WHERE \"CorsiMateria\".\"ID\" = "
					+ idCorso;
			int nStud = 1;
			long seconds = 0;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryStud);
			while (rs.next()) {
				nStud = rs.getInt(1);
			}
			rs = st.executeQuery(querySec);
			while (rs.next()) {
				seconds = rs.getLong(1);
			}
			if (nStud != 0) {
				media = seconds / nStud;
			}
			return media;
		} catch (Exception e) {
			System.out.println("Errore calcolo media");
		}
		return -1;
	}

	private static java.sql.Date getCurrentDate() {
	    Date today = new Date();
	    return new java.sql.Date(today.getTime());
	}
	
	public static void UpdateAccessCount(Connection conn, long idCorso, long idUser) {
		try {
			
			Date now = new Date();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			String date1 = format1.format(now); 
			String query = "INSERT INTO \"AccessiAlCorso\" (\"Utente\", \"Corso\",\"Data\") VALUES " + "(" + idUser
					+ ", " + idCorso + ", '"+date1+"')";
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Errore update Access Count");
		}
	}

	private static java.sql.Date getDate(Date d) {
	    Date today = new Date();
	    return new java.sql.Date(today.getTime());
	}
	
	public static int GetAccessByPeriod(Connection conn, long idCorso, Date start, Date end) {
		try {
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			String date1 = format1.format(start); 
			String date2 = format1.format(end); 
			String query = "SELECT Count(*) FROM \"AccessiAlCorso\" WHERE " + "\"Data\" >= '" + date1 + "'"
					+ "  AND \"Data\" <=  '" + date2 + "'" + " AND \"Corso\" = " + idCorso;
			int cont = 0;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				cont = rs.getInt(1);
			}
			return cont;
		} catch (Exception e) {

		}
		return 0;
	}

}
