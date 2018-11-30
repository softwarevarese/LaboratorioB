package seatInServer.DBModel;

import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.ClientConnection;
import client.Logger;
import client.enums.TipoRichiesta;
import client.pacchetti.RequestPacket;
import client.pacchetti.ResponsePacket;

import static seatInServer.DBModel.Docente.uploadDocumentCont;

/**
 * Questa classe rappresenta un utente.
 */
public abstract class Utente {
    protected String nome;
    protected String cognome;
    protected String email;
    protected int matricola;
    protected String codAttivazione;
    protected boolean locked;
    protected boolean activated;
    private String password;

    protected Socket s;

    public boolean isLocked() {
        return locked;
    }

    public boolean isActivated() {
        return activated;
    }

    public ArrayList<String> anagrafica() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(nome);
        lista.add(cognome);
        lista.add(email);
        lista.add(String.valueOf(matricola));

        return lista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getPassword() {
        return password;
    }

    public void setActivated(boolean value) {activated=value;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCodAttivazione(String codAttivazione) {
        this.codAttivazione = codAttivazione;
    }

    public static void cambiaPasswordByMail(String email, String newPsw) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.CambiaPassword;
            rp.parameters = new Object[]{email, newPsw};
            new ClientConnection().Send(rp);
        } catch (Exception e) {

        }
    }

    public static boolean documentDownload(long idDoc, long userID, String path, String format) {  //utilizzato in utente
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.DownloadDocumento;
            rp.parameters = new Object[] { idDoc };
            ResponsePacket rp1 = new ClientConnection().SendReceive(rp);
            byte[] doc = (byte[]) rp1.parameters[0];
            String finalPath = path;
            try (FileOutputStream fos = new FileOutputStream(finalPath)) {
                fos.write(doc);
            }
            uploadDocumentCont(idDoc, userID);
            return true;
        } catch (Exception e) {
            System.out.println(e + "\nErrore nel download del documento");
            Logger.WriteError(e, "ClientConnection", "DocumentDownload");
        }
        return false;
    }
}