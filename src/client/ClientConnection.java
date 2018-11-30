package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.enums.*;
import client.pacchetti.*;
import seatInServer.DBModel.*;

/**
 * Classe che permette all'utente di richiedere funzionalità al server.
 */
public class ClientConnection {

    private static final int PORT = 50123;
    private static String address = "localhost";
    private static Socket s;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ClientConnection()  {
        try {
            s = new Socket(address, PORT);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

        } catch (Exception e) {
            System.out.println("Errore di apertura della connessione al server");
        }
        System.out.println("Connessione aperta con successo");
    }

    public boolean isUtenteConnesso(Utente utente) {
        ClientConnection conn = new ClientConnection();
        try {
            if (s == null || s.isClosed()) {  //necessario?
                new ClientConnection();
            }
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.isVerificato;
            rp.parameters = new Object[] { utente.getMatricola() };
            conn.Send(rp);
        } catch (Exception e) {
            Logger.WriteError(e, "ClientConnection", "disableProfile");
            return false;
        }
        return true;
    }

    public void Send(RequestPacket rp) {
        try {
            oos.writeObject(rp);
            oos.flush();
        } catch (Exception e) {
            Logger.WriteError(e, "ClientConnection", "Send");
        }
    }


    public ResponsePacket SendReceive(RequestPacket rp) {
        try {
            this.Send(rp);
            ResponsePacket res = (ResponsePacket) ois.readObject();
            return res;
        } catch (Exception e) {
            Logger.WriteError(e, "ClientConnection", "SendReceive");
        }
        return null;
    }

    public UtenteConnesso LoginRequest(String Email, String Psw, TipoUtente tipo_user) {
        try {
            if (s == null || s.isClosed()) {
                //Connect2Server();
                new ClientConnection();
            }
            RequestPacket rq = new RequestPacket();
            rq.type = TipoRichiesta.Login;
            rq.parameters = new Object[] { Email, Psw };
            rq.tipoUtente = tipo_user;
            ResponsePacket rs = SendReceive(rq);

            UtenteConnesso logged = new UtenteConnesso();

            if (rs.parameters[0] != null) {
                switch (tipo_user) {
                    case Admin:
                        logged.tipoUtente = TipoUtente.Admin;
                        logged.connesso = (Admin) rs.parameters[0];
                        break;
                    case Docente:
                        logged.tipoUtente = TipoUtente.Docente;
                        logged.connesso = (Docente) rs.parameters[0];
                        break;
                    case Studente:
                        logged.tipoUtente = TipoUtente.Studente;
                        logged.connesso = (Studente) rs.parameters[0];
                        break;
                    default:
                        break;
                }
                return logged;  //return true
            }

        } catch (Exception e) {
            System.out.println("Errore nell'inoltro della richiesta \"Login\"");
        }
        return null;  //return false
    }

    public static FileSystem getFS(long index) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.GetFileSystem;
            rp.parameters = new Object[] { index };
            ResponsePacket rpc = new ClientConnection().SendReceive(rp);
            FileSystem fs = (FileSystem) rpc.parameters[0];

            return fs;
        } catch (Exception e) {
            System.out.println("Errore reperimento file system client instance");
        }
        return null;
    }

    public static void updateLoc(int idUtente, int idCorso) {
        try {
            // idCorso = -1 se si sta uscendo
            ClientConnection conn = new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.UpdateLoc;
            rp.parameters = new Object[] { idCorso, idUtente };
            conn.Send(rp);
        } catch (Exception e) {

        }
    }

    public static ArrayList<Dipartimento> getDipartimenti() {
        try {
            if (s == null || s.isClosed()) {
                new ClientConnection();
            }
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.GetDipartimenti;
            ResponsePacket rp1 = new ClientConnection().SendReceive(rp);
            ArrayList<Dipartimento> lista = (ArrayList<Dipartimento>) rp1.parameters[0];
            return lista;
        } catch (Exception e) {

        }
        return null;
    }

    public static ArrayList<CorsoDiLaurea> getCorsiLaureaByDip(int dipI) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.GetCorsiLaurea;
            rp.parameters = new Object[] { dipI };
            ResponsePacket resP = new ClientConnection().SendReceive(rp);
            ArrayList<CorsoDiLaurea> lista = (ArrayList<CorsoDiLaurea>) resP.parameters[0];
            return lista;
        } catch (Exception e) {
            System.out.println("Errore nell'ottenimento della lista dei corsi");
        }
        return new ArrayList<CorsoDiLaurea>();
    }

    public static ArrayList<Corso> getCorsiMateriaByLaurea(int index) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.GetCorsiMateria;
            rp.parameters = new Object[] { index };
            ResponsePacket resP = new ClientConnection().SendReceive(rp);
            ArrayList<Corso> lista = (ArrayList<Corso>) resP.parameters[0];

            return lista;
        } catch (Exception e) {
            System.out.println("Errore nell'ottenimento della lista dei corsi");
        }
        return new ArrayList<Corso>();
    }

    public void close(UtenteConnesso logged) {
        try {
            System.out.println("Chiusura connessione");
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.Close;
            this.Send(rp);
            logged.connesso = null;
            ois.close();
            oos.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Errore: close client connection");
        }
    }
}