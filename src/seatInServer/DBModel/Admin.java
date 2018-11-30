package seatInServer.DBModel;

import client.ClientConnection;
import client.Logger;
import client.enums.TipoRichiesta;
import client.enums.TipoUtente;
import client.pacchetti.RequestPacket;
import client.pacchetti.ResponsePacket;

import java.util.ArrayList;
import java.util.Date;

/**
 * Questa classe rappresenta il profilo di un utente amministratore.
 */
public class Admin extends Utente {
    private String corsoGestito;

    protected ArrayList<String> adminDetails() {
        ArrayList<String> lista = super.anagrafica();
        lista.add(corsoGestito);

        return lista;
    }

    public void disableProfile(String email) {
        ClientConnection conn = new ClientConnection();
    	try {
            if (s == null || s.isClosed()) {  //necessario?
                new ClientConnection();
            }
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.PasswordDimenticata;
            rp.parameters = new Object[] { email };
            conn.Send(rp);
        } catch (Exception e) {
            Logger.WriteError(e, "ClientConnection", "disableProfile");
        }
    }

    private void enableProfile(String email) {

    }

    private void assegnaDocCorso(int matricolaDocente, int idCorso) {
            try {
            	ClientConnection conn = new ClientConnection();
                RequestPacket rp = new RequestPacket();
                rp.type = TipoRichiesta.LinkDocenteCorso;
                rp.parameters = new Object[] { matricolaDocente, idCorso };
                conn.Send(rp);
            } catch (Exception e) {
                System.out.println("LinkDocenteCorso");
            }
    }

    private void monitorPiattaforma() {

    }

    public static boolean createCorso(Corso newCorso) {
        try {
        	ClientConnection conn = new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.CreateCorsoMateria;
            rp.parameters = new Object[] { newCorso };
            conn.Send(rp);
            return true;
        } catch (Exception e) {
            System.out.println("Errore nella creazione di un corso materia");
        }
        return false;
    }

    public static void editCorso(Corso corsoModificato) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.ModificaCorsoMateria;
            rp.parameters = new Object[] { corsoModificato };
            new ClientConnection().Send(rp);
        } catch (Exception e) {
            System.out.println("Errore ModificaCorsoMateria");
        }
    }

    public void creaStudente(Studente stud) {
        try {
        	ClientConnection conn = new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.CreateUser;
            rp.tipoUtente = TipoUtente.Studente;
            rp.parameters = new Object[] { stud };
            conn.Send(rp);
        } catch (Exception e) {

        }
    }

    public void creaDocente(Docente doc) {
        try {
        	ClientConnection conn = new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.CreateUser;
            rp.tipoUtente = TipoUtente.Docente;
            rp.parameters = new Object[] { doc };
            conn.Send(rp);
        } catch (Exception e) {

        }
    }

    public void creaAdmin(Admin admin) {
        try {
        	ClientConnection conn = new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.CreateUser;
            rp.tipoUtente = TipoUtente.Admin;
            rp.parameters = new Object[] { admin };
            conn.Send(rp);
        } catch (Exception e) {

        }
    }

    public void cambiaVisibilita(long idSez, client.enums.VisibilitaSezione vis) {
        try {
        	ClientConnection conn = new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.CambiaVisSez;
            rp.parameters = new Object[] { idSez, vis };
            conn.Send(rp);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //monitoraggio
    public static int GetNumberLoggedUser(ClientConnection client) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.getLoggedUserNumber;
            rp.parameters = new Object[] {};
            ResponsePacket rpp = client.SendReceive(rp);
            int logged = (int) rpp.parameters[0];
            return logged;
        } catch (Exception e) {

        }
        return 0;
    }

    public static int getAccessiByPeriod(long idCorso, Date start, Date end, ClientConnection client) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.getNumberAccessByPeriod;
            rp.parameters = new Object[] { idCorso, start, end };
            ResponsePacket rpp = client.SendReceive(rp);
            int logged = (int) rpp.parameters[0];
            return logged;
        } catch (Exception e) {

        }
        return 0;
    }

    public static double getTempoMedio(long idCorso, ClientConnection client) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.getMediaSecCorso;
            rp.parameters = new Object[] { idCorso };
            ResponsePacket rpp = client.SendReceive(rp);
            double seconds = (double) rpp.parameters[0];
            return seconds;
        } catch (Exception e) {

        }
        return 0;
    }

    public boolean verifyCodAttivazione(int userID, int code) {  //utilizzato in verifyCodAttivazione, Admin
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.VerificaCod;
            rp.parameters = new Object[] { userID, code };
            ResponsePacket rpp = new ClientConnection().SendReceive(rp);
            boolean ris = (boolean) rpp.parameters[0];
            return ris;
        } catch (Exception e) {
            Logger.WriteError(e, "ClientConnection", "VerificaCod");
        }
        return false;
    }
}
