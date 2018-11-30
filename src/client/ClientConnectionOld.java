package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

import Common.DBType.*;

import client.enums.*;
import client.pacchetti.*;
import seatInServer.DBModel.*;

/**
 * Classe che permette all'utente di richiedere delle funzionalita al server
 *
 */
public class ClientConnectionOld {

	private static final int PORT = 50123;
	private static String address = "localhost";
	private static Socket s;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;

	public static boolean Connect2Server() {  //trasformato in costruttore
		try {
			s = new Socket(address, PORT);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			return true;
		} catch (Exception e) {
			System.out.println("Errore connessione al server");
		}
		return false;
	}

	public static void Send(RequestPacket rp) {
		try {
			oos.writeObject(rp);
			oos.flush();
		} catch (Exception e) {
			Logger.WriteError(e, "ClientConnectionOld", "Send");
		}
	}


	private static ResponsePacket SendReceive(RequestPacket rp) {
		try {
			Send(rp);
			ResponsePacket res = (ResponsePacket) ois.readObject();
			return res;
		} catch (Exception e) {
			Logger.WriteError(e, "ClientConnectionOld", "SendReceive");
		}
		return null;
	}

	public static ArrayList<CorsoMateria> GetCorsiStud(long studID) {   //utilizzato in getCorsiStudente, Studente
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { studID };
			rp.type = TipoRichiesta.GetCorsiStud;
			ResponsePacket rpp = SendReceive(rp);
			ArrayList<CorsoMateria> lis = (ArrayList<CorsoMateria>) rpp.parameters[0];
			return lis;
		} catch (Exception e) {
			Logger.WriteError(e, "ClientInstance", "GetCorsiStud");
		}
		return new ArrayList<CorsoMateria>();
	}

	public static ArrayList<CorsoLaurea> GetCorsiLaureaByDip(long dipI) {  //usato tale
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetCorsiLaurea;
			rp.parameters = new Object[] { dipI };
			ResponsePacket resP = SendReceive(rp);
			ArrayList<CorsoLaurea> lista = (ArrayList<CorsoLaurea>) resP.parameters[0];
			return lista;
		} catch (Exception e) {
			System.out.println("Errore nell'ottenimento della lista dei corsi");
		}
		return new ArrayList<CorsoLaurea>();
	}

	public static ArrayList<Dipartimento> GetDipartimenti() {  //usato tale
		try {
			if (s == null || s.isClosed()) {
				Connect2Server();
			}
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetDipartimenti;
			ResponsePacket rp1 = SendReceive(rp);
			ArrayList<Dipartimento> lista = (ArrayList<Dipartimento>) rp1.parameters[0];
			return lista;
		} catch (Exception e) {
			
		}
		return null;
	}

	public static void SubscribeCourse(long idUtente, long idCorso) {
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { idUtente, idCorso };
			rp.type = TipoRichiesta.SubscribeCourse;
			Send(rp);
		} catch (Exception e) {
			Logger.WriteError(e, "ClientConnectionOld", "SubscribeCourse");
		}
	}

	public static ArrayList<CorsoMateria> GetCorsiMateriaByLaurea(long index) {  //usato tale
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetCorsiMateria;
			rp.parameters = new Object[] { index };
			ResponsePacket resP = SendReceive(rp);
			ArrayList<CorsoMateria> lista = (ArrayList<CorsoMateria>) resP.parameters[0];
			
			return lista;
		} catch (Exception e) {
			System.out.println("Errore nell'ottenimento della lista dei corsi");
		}
		return new ArrayList<CorsoMateria>();
	}

	public static FileSystemCorsoMateria GetFS(long index) {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetFileSystem;
			rp.parameters = new Object[] { index };
			ResponsePacket rpc = SendReceive(rp);
			FileSystemCorsoMateria fs = (FileSystemCorsoMateria) rpc.parameters[0];
			
			return fs;
		} catch (Exception e) {
			System.out.println("Errore reperimento file system client instance");
		}
		return null;
	}

	private static void UploadDocumentCont(long idDoc, long userID) {  //utilizzato in docente
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.UpdateDocumentCount;
			rp.parameters = new Object[] { idDoc, userID };
			Send(rp);
		} catch (Exception e) {
			System.out.println("Errore UploadDocumentCont");
		}
	}

	public static void CambiaPasswordByMail(String email, String newPsw) {  //utilizzato in Utente
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.CambiaPassword;
			rp.parameters = new Object[] { email, newPsw };
			Send(rp);
		} catch (Exception e) {

		}
	}

	public static void CambiaAnagrafica(Anagrafica mod) {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.ModificaAnagrafica;
			rp.parameters = new Object[] { mod };
			Send(rp);
		} catch (Exception e) {

		}
	}

	public static Studente GetStudenteByMat(long matricola) {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetStudenteByMat;
			rp.parameters = new Object[] { matricola };
			ResponsePacket rpp = SendReceive(rp);
			return (Studente) rpp.parameters[0];
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static boolean DocumentUpload(String path, long idSez) {   //utilizzato in uploadDocumento, docente
		try {
			Documento newDoc = new Documento();
			byte[] array = Files.readAllBytes(new File(path).toPath());
			newDoc.dati = array;
			newDoc.idSez = idSez;
			String replace = path.replace("\\", "�");

		
			// Ottengo nome
			String[] split = replace.split("�");
			String last = split[split.length - 1];
			String splitq[] = last.split("\\.");

			
			if (splitq.length == 1) {
				// caso particolare senza estensione
				newDoc.NomeDocumento = last;
				newDoc.FormatoDocumento = "";
			} else {
				newDoc.NomeDocumento = splitq[0];
				newDoc.FormatoDocumento = splitq[1];

			}

			

			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.UploadDocumento;
			rp.parameters = new Object[] { newDoc };
			Send(rp);

			return true;
		} catch (Exception e) {
			System.out.println("err doc upload");

		}
		return false;
	}

	public static CorsoLaurea GetCorsoLaureaByID(long corsoLaureaID) {
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { corsoLaureaID };
			rp.type = TipoRichiesta.GetCorsoLaureaByID;
			ResponsePacket rpp = SendReceive(rp);
			return (CorsoLaurea) rpp.parameters[0];//
		} catch (Exception e) {

		}
		return null;
	}

	public static boolean DocumentDownload(long idDoc, long userID, String path, String format) {  //utilizzato in utente
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.DownloadDocumento;
			rp.parameters = new Object[] { idDoc };
			ResponsePacket rp1 = SendReceive(rp);
			byte[] doc = (byte[]) rp1.parameters[0];
			String finalPath = path;
			try (FileOutputStream fos = new FileOutputStream(finalPath)) {
				fos.write(doc);
			}
			UploadDocumentCont(idDoc, userID);
			return true;
		} catch (Exception e) {
			System.out.println(e + "\nErrore nel download del documento");
			Logger.WriteError(e, "ClientConnectionOld", "DocumentDownload");
		}
		return false;
	}

	public static void LinkDocenteCorso(long idDocente, long idCorso) { ///utilizzato in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.LinkDocenteCorso;
			rp.parameters = new Object[] { idDocente, idCorso };
			Send(rp);
		} catch (Exception e) {
			System.out.println("LinkDocenteCorso");
		}
	}

	public static void SendNewsLetter(long idCorso, String oggetto, String messaggio) {  //utilizzato in newsLetter, docente
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { idCorso, oggetto, messaggio };
			rp.type = TipoRichiesta.SendNewsLetter;
			Send(rp);
		} catch (Exception e) {
			System.out.println("SendNewsLetter error");
		}
	}

	public static ArrayList<Studente> GetAllStudenti() {
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] {};
			rp.type = TipoRichiesta.GetAllStudenti;
			ResponsePacket rps = SendReceive(rp);
			return (ArrayList<Studente>) rps.parameters[0];
		} catch (Exception e) {
			System.out.println("SendNewsLetter error");
		}
		return new ArrayList<Studente>();
	}

	public static ArrayList<CorsoMateria> GetCorsiByDocente(long idDocente) {  //utilizzato in getCorsiByDocente, Docente
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { idDocente };
			rp.type = TipoRichiesta.GetCorsiDoc;
			ResponsePacket rps = SendReceive(rp);
			return (ArrayList<CorsoMateria>) rps.parameters[0];
		} catch (Exception e) {
			System.out.println("SendNewsLetter error");
		}
		return new ArrayList<CorsoMateria>();
	}

	public static void BloccaAccount(String Email) {  //utilizzato in disableProfile, admin
		try {
			if (s == null || s.isClosed()) {
				Connect2Server();
			}
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.PasswordDimenticata;
			rp.parameters = new Object[] { Email };
			Send(rp);
		} catch (Exception e) {
			Logger.WriteError(e, "ClientConnectionOld", "BloccaAccount");
		}
	}

	public static void ModificaCorsoMateria(CorsoMateria corsoModificato) {   //utilizzato in editCorso, admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.ModificaCorsoMateria;
			rp.parameters = new Object[] { corsoModificato };
			Send(rp);
		} catch (Exception e) {
			System.out.println("Errore ModificaCorsoMateria");
		}
	}

	public static void CambiaVisibilita(long idSez, Common.Enumerators.VisibilitaSezione vis) {  //utilizzato in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.CambiaVisSez;
			rp.parameters = new Object[] { idSez, vis };
			Send(rp);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static boolean CreateCorsoMateria(CorsoMateria newCorso) {  //utilizzato in createCorso, admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.CreateCorsoMateria;
			rp.parameters = new Object[] { newCorso };
			Send(rp);
			return true;
		} catch (Exception e) {
			System.out.println("Errore nella creazione di un corso materia");
		}
		return false;
	}

	

	public static ArrayList<CorsoMateria> OttieniCorsi() {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetCorsiMateria;
			ResponsePacket resP = SendReceive(rp);
			ArrayList<CorsoMateria> lista = (ArrayList<CorsoMateria>) resP.parameters[0];
		} catch (Exception e) {
			System.out.println("Errore nell'ottenimento dei corsi");
		}
		return new ArrayList<CorsoMateria>();
	}

	public static void CreaSezione(CartellaSezione newFolder, long idCorso) {       //utilizzato in creaSezione, docente
		try {
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { newFolder, idCorso };
			rp.type = TipoRichiesta.CreaSezione;
			Send(rp);
		} catch (Exception e) {
			System.out.println("Errore nella generazione della nuova cartella");
		}
	}

	public static boolean LoginRequest(String Email, String Psw, UserType tipo_user) {
		try {
			if (s == null || s.isClosed()) {
				Connect2Server();
			}
			RequestPacket rq = new RequestPacket();
			rq.type = TipoRichiesta.Login;
			rq.parameters = new Object[] { Email, Psw };
			rq.userType = tipo_user;
			ResponsePacket rs = SendReceive(rq);

			if (rs.parameters[0] != null) {
				switch (tipo_user) {
				case Admin:
					Client.LoggedUser.userType = UserType.Admin;
					Client.LoggedUser.logged = (Admin) rs.parameters[0];
					break;
				case Docente:
					Client.LoggedUser.userType = UserType.Docente;
					Client.LoggedUser.logged = (Docente) rs.parameters[0];
					break;
				case Studente:
					Client.LoggedUser.userType = UserType.Studente;
					Client.LoggedUser.logged = (Studente) rs.parameters[0];
					break;
				default:
					break;
				}
				return true;
			}

		} catch (Exception e) {
			System.out.println("Errore nell'inoltro della richiesta \"Login\"");
		}
		return false;
	}

	public static void CreaStudente(Studente stud) {  //utilizzato in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.CreateUser;
			rp.userType = UserType.Studente;
			rp.parameters = new Object[] { stud };
			Send(rp);
		} catch (Exception e) {

		}
	}

	public static void CreaDocente(Docente doc) {   //utilizzato in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.CreateUser;
			rp.userType = UserType.Docente;
			rp.parameters = new Object[] { doc };
			Send(rp);
		} catch (Exception e) {

		}
	}

	public static void CreaAdmin(Admin admin) {    //utilizzato in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.CreateUser;
			rp.userType = UserType.Admin;
			rp.parameters = new Object[] { admin };
			Send(rp);
		} catch (Exception e) {

		}
	}

	public static ArrayList<Docente> GetAllDocenti() {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetAllDocenti;
			ResponsePacket rp1 = SendReceive(rp);
			ArrayList<Docente> lis = (ArrayList<Docente>) rp1.parameters[0];
			return lis;
		} catch (Exception e) {

		}
		return new ArrayList<Docente>();
	}

	public static void UpdateLoc(long idUtente, long idCorso) {  //usato così
		try {
			// idCorso = -1 se si sta uscendo
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.UpdateLoc;
			rp.parameters = new Object[] { idCorso, idUtente };
			Send(rp);
		} catch (Exception e) {

		}
	}

	public static int GetNumberLoggedUser() {	//utilizzato da getUtentiConnessi, admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.getLoggedUserNumber;
			rp.parameters = new Object[] {};
			ResponsePacket rpp = SendReceive(rp);
			int logged = (int) rpp.parameters[0];
			return logged;
		} catch (Exception e) {

		}
		return 0;
	}

	public static int GetNumberLoggedUserByCorso(long idCorso) {  //utilizzato da getUtentiConnessiByCorso, docente
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.getLoggedUserNumberByCorso;
			rp.parameters = new Object[] { idCorso };
			ResponsePacket rpp = SendReceive(rp);
			int logged = (int) rpp.parameters[0];
			return logged;
		} catch (Exception e) {

		}
		return 0;
	}

	public static int GetNumberAccessByPeriod(long idCorso, Date start, Date end) {  //utilizzato in getAccessiByPeriod in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.getNumberAccessByPeriod;
			rp.parameters = new Object[] { idCorso, start, end };
			ResponsePacket rpp = SendReceive(rp);
			int logged = (int) rpp.parameters[0];
			return logged;
		} catch (Exception e) {

		}
		return 0;
	}

	public static double GetMediaCorso(long idCorso) {   //utilizzato in getTempoMedio in admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.getMediaSecCorso;
			rp.parameters = new Object[] { idCorso };
			ResponsePacket rpp = SendReceive(rp);
			double seconds = (double) rpp.parameters[0];
			return seconds;
		} catch (Exception e) {

		}
		return 0;
	}

	public static int DocumentContByCorso(long idCorso) {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.DocumentContByCorso;
			rp.parameters = new Object[] { idCorso };
			ResponsePacket rpp = SendReceive(rp);
			int cont = (int) rpp.parameters[0];
			return cont;
		} catch (Exception e) {
			System.out.println("Error DocumentContByCorso");
		}
		return 0;
	}

	public static int ContUtentiDownload(Date start, Date end) {  //utilizzato in getDownloadByPeriod, in docente
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.ContUtentiDownload;
			rp.parameters = new Object[] { start, end };
			ResponsePacket rpp = SendReceive(rp);
			int cont = (int) rpp.parameters[0];
			return cont;
		} catch (Exception e) {
			System.out.println("Err ContUtentiDownload");
		}
		return 0;
	}

	public static boolean VerificaCod(long userID, long code) {  //utilizzato in verifyCodAttivazione, Admin
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.VerificaCod;
			rp.parameters = new Object[] { userID, code };
			ResponsePacket rpp = SendReceive(rp);
			boolean ris = (boolean) rpp.parameters[0];
			return ris;
		} catch (Exception e) {
			Logger.WriteError(e, "ClientConnectionOld", "VerificaCod");
		}
		return false;
	}

	public static ArrayList<CorsoMateria> GetAllCorsiMateria() {
		try {
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.GetAllCorsiMateria;
			ResponsePacket rp1 = SendReceive(rp);
			ArrayList<CorsoMateria> lista = (ArrayList<CorsoMateria>) rp1.parameters[0];
			for (CorsoMateria m : lista) {
			
			}
			return lista;
		} catch (Exception e) {
			Logger.WriteError(e, "ClientConnectionOld", "GetAllCorsiMateria");
		}
		return new ArrayList<CorsoMateria>();
	}


	public static void Close() {
		try {
			System.out.println("Chiususura connessione");
			RequestPacket rp = new RequestPacket();
			rp.type = TipoRichiesta.Close;
			Send(rp);
			Client.LoggedUser.logged = null;
			ois.close();
			oos.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Errore close client connection");
		}
	}

}

