package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import client.enums.VisibilitaSezione;
import seatInServer.DBModel.Documento;
import seatInServer.DBModel.Sezione;
import seatInServer.utilities.CommonList;


/**
 * Classe che contiene tutti i metodi per il file system e le operazione a lui relativi per il DB   1234
 */
public class FileSystemController {

	private static VisibilitaSezione TraduciVisibilita(int vis) {
		switch (vis) {
		case 0:
			return VisibilitaSezione.Privata;
		case 1:
		default:
			return VisibilitaSezione.Pubblica;
		}
	}

	private static int TraduciVisibilita(VisibilitaSezione vis) {
		switch (vis) {
		case Privata:
			return 0;
		case Pubblica:
		default:
			return 1;
		}
	}

	public static void UpdateDownloadCont(Connection conn, long docID, long userID) {
		try {
			
			Date now = new Date();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			String date1 = format1.format(now); 
			String query = "INSERT INTO \"DownloadMonitoraggio\" (\"Utente\", \"Documento\", \"Data\") VALUES (" + userID
					+ "," + docID + ",'" + date1 + "');";
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Errore UpdateDownloadCont");
		}
	}

	public static void CambiaVisSez(Connection conn, long idSez, VisibilitaSezione vis) {
		try {
			int visI = TraduciVisibilita(vis);
			String query = "UPDATE \"Sezioni\" SET \"Visibilita\" = "+visI + 
					" WHERE \"ID\" ="+idSez;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static int ContUtentiDownload(Connection conn, Date start, Date end) {
		try {
			int cont = 0;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			String date1 = format1.format(start); 
			String date2 = format1.format(end); 
			String query = "SELECT COUNT(*) FROM \"DownloadMonitoraggio\" WHERE \"Data\" > '" + date1
					+ "' AND \"Data\" <= '" + date2 + "' GROUP BY \"Utente\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				cont = rs.getInt(1);
			}
			return cont;

		} catch (Exception e) {
			System.out.println("Err ContUtentiDownload");
		}
		return 0;
	}
	

	public static int ContDownloadByCorso(Connection conn, long corsoID) {
		try {
			int cont = 0;
			String query = "SELECT COUNT(*) FROM \"DownloadMonitoraggio\" JOIN \"Contenuto\" ON \"DownloadMonitoraggio\".\"Documento\" = \"Contenuto\".\"Id\" "
					+ "JOIN \"Sezioni\" ON \"Contenuto\".\"Sezione\" = \"Sezioni\".\"ID\" WHERE \"Sezioni\".\"CorsoAp\" = "
					+ corsoID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				cont = rs.getInt(1);
			}
			return cont;
		} catch (Exception e) {
			System.out.println("ERR ContDownloadByCorso");
		}
		return 0;
	}

	public static void SalvaDoc(Connection conn, Documento doc) {
		try {
			PreparedStatement st = conn.prepareStatement(
					"INSERT INTO \"Contenuto\" (\"Sezione\", \"Formato\", \"File\", \"Nome\") VALUES (?, ?, ?, ?)");
			st.setLong(1, doc.getIdSezione());
			st.setString(2, doc.getFormato());
			st.setBytes(3, doc.getDati());
			st.setString(4, doc.getNomeDoc());
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento nel db del nuovo documento");
		}
	}

	public static void CreaSez(Connection conn, Sezione sez, long idCorso) {
		try {
			String query = "";
			if (sez.getCartellaPadre() == -1) {
				query = "INSERT INTO \"Sezioni\" (\"CorsoAp\", \"Nome\", \"Descrizione\", \"Visibilita\") VALUES\r\n"
						+ "(" + idCorso + ",'" + sez.getNomeSez() + "', '" + sez.getDescrizione() + "', "
						+ TraduciVisibilita(sez.getVisibilita()) + ")";
			} else {
				query = "INSERT INTO \"Sezioni\" (\"CorsoAp\",\"Sezione\", \"Nome\", \"Descrizione\", \"Visibilita\") VALUES\r\n"
						+ "(" + idCorso + "," + sez.getCartellaPadre() + ",'" + sez.getNomeSez() + "', '" + sez.getDescrizione()
						+ "', " + TraduciVisibilita(sez.getVisibilita()) + ")";
			}
			Statement st = conn.createStatement();

			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Errore nuova sezione");
			
		}
	}

	private static ArrayList<Sezione> GetSezioniPadri(Connection conn, long corsoID) {
		ArrayList<Sezione> lista = new ArrayList<Sezione>();
		Sezione sez = null;
		String query = "select * from \"Sezioni\" where \"Sezione\" IS NULL AND \"CorsoAp\" = " + corsoID;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				sez = new Sezione();
				sez.setIdSezione(rs.getInt(3));
				sez.setCartellaPadre(-1);
				sez.setNomeSez(rs.getString(4));
				sez.setVisibilita(TraduciVisibilita(rs.getInt(6)));
				lista.add(sez);
			}
			
			return lista;
		} catch (Exception e) {
			System.out.println("Errore nell'ottenimento delle cartelle padre del corso " + corsoID);
		}

		return null;
	}

	private static ArrayList<Sezione> GetSottoCartelle(Connection conn, Sezione padre) {
		Sezione sez = null;
		String query = "select * from \"Sezioni\" Where \"Sezione\" = " + padre.getIdSezione();
		ArrayList<Sezione> sottoCartelle = new ArrayList<Sezione>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				sez = new Sezione();
				sez.setCartellaPadre(padre.getIdSezione());
				sez.setDocumenti(CercaDocumento(conn, rs.getInt(1)));
				sez.setNomeSez(rs.getString("Nome"));//
				sez.setDescrizione(rs.getString("Descrizione"));
				sottoCartelle.add(sez);
			}
			if (sottoCartelle.size() > 0) {
				// Vuol dire che ha trovato qualcosa...
				for (Sezione sez1 : sottoCartelle) {
					sez1.setSottoSezioni(GetSottoCartelle(conn, sez1));
				}
			}
			return sottoCartelle;
		} catch (Exception e) {
			System.out.println("Errore nell'ottenimento della sottocartella");
		}

		return null;
	}

	private static ArrayList<Documento> CercaDocumento(Connection conn, int sezID) {
		String query = "select \"Id\", \"Nome\", \"Formato\" from \"Contenuto\" where \"Sezione\" = " + sezID;
		Documento doc = null;
		ArrayList<Documento> lista = new ArrayList<Documento>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				doc = new Documento();
				doc.setIdDocumento(rs.getInt("Id"));
				doc.setIdSezione(sezID);
				doc.setFormato(rs.getString("Formato"));
				doc.setNomeDoc(rs.getString("Nome"));
				lista.add(doc);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Errore nella ricerca del documento");
		}

		return null;
	}

	public static byte[] DownloadDocumento(Connection conn, long docId) {
		String query = "select \"File\" from \"Contenuto\" Where \"Id\" = " + docId;
		byte[] file = null;
		try {
		
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
			
				file = rs.getBytes("File");
			}
			
			return file;
		} catch (Exception e) {
		
			System.out.println("Errore nel download del documento");
		}

		return null;
	}

	public static FileSystemCorsoMateria GenerateFileSystem(Connection conn, long corsoID) {
		FileSystemCorsoMateria fs = new FileSystemCorsoMateria();
		try {
			fs.corso = CommonList.GetCorsoMateria(corsoID);
			fs.sezioni = GetSezioniPadri(conn, corsoID);

			for (Sezione sez : fs.sezioni) {

				sez.setDocumenti(CercaDocumento(conn, sez.getIdSezione()));
				sez.setSottoSezioni(GetSottoCartelle(conn, sez));
			}
			return fs;
		} catch (Exception e) {
			System.out.println("Errore nella generazione del file system");
		}

		return null;
	}

}
