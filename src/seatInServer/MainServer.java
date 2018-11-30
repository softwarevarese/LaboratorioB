package seatInServer;

import database.DBHandler;
import database.RichiestaAlDB;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class MainServer {
	
	private static RichiestaAlDB db = new RichiestaAlDB();

	/**
	 * Funzione per far loggare l'admin all'avvio del server
	 *
	 * @param dbm
	 * @param scan
	 * @return
	 */
	private static boolean StartupLogin(DBHandler dbm, Scanner scan) {
		if (AdminController.isThereAdmin(dbm.conn)) {
			System.out.println("Desideri registrare un nuovo Admin (0) o effettuare un Login? (1)");
			String risp = scan.nextLine();
			scan.reset();
			switch (risp) {
				case "0":
					AdminController.creaAdmin(dbm.conn);
					break;
				case "1":
					if (AdminController.LoginAdmin(dbm.conn)) {
						System.out.println("Login Effettuato");
						return true;
					} else {
						System.out.println("Login fallito");
					}
					break;
				default:
					System.out.println("Errore, selezionare una risposta valida");
					break;
			}

		} else {
			System.out.println("Non esiste nessun admin...avvio inserimento di un nuovo admin");
			AdminController.creaAdmin(dbm.conn);
		}
		return false;
	}

	public static void main(String[] args) {
		
		/* TEST */
		db.testQueryOnDB();

		final int PORT = 50123;
		ServerSocket ss = null;
		Socket s = null;
		ServerThread serverThread = null; //ClientInstance newClient = null;

		// Ciao
		try {
			Scanner scan = new Scanner(System.in);
			DBHandler databse = new DBHandler(); //DatabaseManager dbm = new DatabaseManager();
			seatInServer.utilities.EmailSender.SetPassword(scan);
			while (!StartupLogin(dbm, scan)) {
			}
			scan.close();
			ss = new ServerSocket(PORT);
			System.out.println("Server avviato con successo");
			while (true) {
				System.out.println("Ricerca nuova connessione...");
				s = ss.accept();
				System.out.println("Connessione ricevuta, avvio nuovo client");
				newClient = new ClientInstance(s, dbm);
				newClient.start();
			}
		} catch (Exception e) {
		}

		
	}
	
	/*
	 *	METODI DELLA CLASSE 
	 */
	
	public String getUserID(String nomeUtente) {
		//TODO
		return null;
	}

	
	public String getPassword(String nomeUtente) {
		//TODO
		return null;
	}
	
	
	public Boolean setPassword(String nomeUtente, String password) {
		//TODO
		return null;
	}
	
	
	public Boolean queryToString(String query) {
		//TODO
		return null;
	}
	
	
	/**
	 * 
	 * @param query
	 */
	public void setQuery(String query) {
		
	}

	public static String generateAttCode() {
		String alfabeto = "123456789";
		Random rand = new Random();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int randIndex = rand.nextInt(alfabeto.length());
			res.append(alfabeto.charAt(randIndex));
		}
		return res.toString();
	}


}
