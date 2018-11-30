package database;

import java.beans.Statement;
import java.io.IOException;
import java.util.Scanner;

import Common.Logger;
import Common.DBType.Anagrafica;
import Server.CommonList;
import client.Logger;
import seatInServer.utilities.CommonList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Classe che gestisce l'apertura della connessione al db,e contiene l'oggetto di riferimento per la connessione
 */
public class DatabaseManager {
	/*
	 * private String connectionString =
	 * "jdbc:postgresql://127.0.0.1:5432/dbSeatIn"; private String userName =
	 * "postgres"; private String password = "Password";
	 */

	public Connection conn;

	private void PulisciConsole() throws IOException {
		final String operatingSystem = System.getProperty("os.name");

		if (operatingSystem.contains("Windows")) {
			Runtime.getRuntime().exec("cls");
		} else {
			Runtime.getRuntime().exec("clear");
		}
	}

	private void setup() throws SQLException {
		Scanner scan = new Scanner(System.in);
		while ((conn == null) || (conn.isClosed())) {

			System.out.println("Tentativo connessione al database...inserire host");
			String host = scan.nextLine();
			System.out.println("Inserire Username");
			String user = scan.nextLine();
			System.out.println("Inserire Password");
			String psw = scan.nextLine();

			String connectionStringU = "jdbc:postgresql://" + host + ":5432/dbSeatIn";

			// conn = DriverManager.getConnection(connectionString, userName, password);
			try {
				conn = DriverManager.getConnection(connectionStringU, user, psw);
			} catch (Exception e) {
				Logger.WriteError(e, "sas", "sssss");
				try {
					// Pulisce la console in caso ci sia un eccezione che non riesco a
					PulisciConsole();
				} catch (IOException e1) {

				}
				System.out.println("Connessione al db fallita. Inserire credenzali valide");
				Logger.WriteError(e, "DBManager", "SignIn");
			}
		}
		System.out.println("Connection up");

		System.out.println("Caricamento liste iniziali");

		CommonList.UpdateDipartimenti(conn);
		CommonList.UpdateCorsiLaurea(conn);
		CommonList.UpdateCorsiMateria(conn);

		System.out.println("Liste iniziali caricate");

	}

	public DatabaseManager() {
		try {
			setup();
		} catch (Exception e) {
			System.out.println("Error DB setup");
			System.out.println(e.getMessage());
		}
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