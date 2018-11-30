package seatInServer.DBModel;

import client.ClientConnection;
import client.enums.TipoRichiesta;
import client.pacchetti.RequestPacket;
import client.pacchetti.ResponsePacket;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

import static client.ClientConnectionOld.Send;

/**
 * Questa classe rappresenta il profilo di un docente.
 */
public class Docente extends Utente {
   private String dipartimento;
   private ArrayList<Corso> corsiDocente;

    public String getDipartimento() {
        return dipartimento;
    }

    public void setCorsiDocente(ArrayList<Corso> corsiDocente) {
        this.corsiDocente = corsiDocente;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    protected ArrayList<String> docenteDetails() {
        ArrayList<String> lista = super.anagrafica();
        lista.add(dipartimento);

        return lista;
    }

    protected void pubblicaLessons() {   //può coincidere con uploadDocumento?

    }


    protected void newsLetter(int idCorso, String oggetto, String messaggio) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.parameters = new Object[] { idCorso, oggetto, messaggio };
            rp.type = TipoRichiesta.SendNewsLetter;
            Send(rp);
        } catch (Exception e) {
            System.out.println("SendNewsLetter error");
        }
    }

    protected void monitorAccessiCorso() {

    }


    public void creaSezione(Sezione newFolder, int idCorso) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.parameters = new Object[] { newFolder, idCorso };
            rp.type = TipoRichiesta.CreaSezione;
            Send(rp);
        } catch (Exception e) {
            System.out.println("Errore nella generazione della nuova cartella");
        }
    }

    public boolean uploadDocumento(String path, int idSez) {
        try {
            Documento newDoc = new Documento();
            byte[] array = Files.readAllBytes(new File(path).toPath());
            newDoc.setDati(array);          //newDoc.dati = array;
            newDoc.setIdSezione(idSez);        //newDoc.idSezione = idSez;
            String replace = path.replace("\\", "�");

            // Ottengo nome
            String[] split = replace.split("�");
            String last = split[split.length - 1];
            String splitq[] = last.split("\\.");

            if (splitq.length == 1) {
                // caso particolare senza estensione
                newDoc.setNomeDoc(last);   //newDoc.nome = last;
                newDoc.setFormato("");  //newDoc.formato = "";
            } else {
                newDoc.setNomeDoc(splitq[0]);      //newDoc.nome = splitq[0];
                newDoc.setFormato(splitq[1]);   //newDoc.formato = splitq[1];
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

    public static void uploadDocumentCont(long idDoc, long userID) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.UpdateDocumentCount;
            rp.parameters = new Object[] { idDoc, userID };
            Send(rp);
        } catch (Exception e) {
            System.out.println("Errore UploadDocumentCont");
        }
    }

    public static ArrayList<Corso> getCorsiByDocente(int idDocente) {
        try {
            ClientConnection conn= new ClientConnection();
            RequestPacket rp = new RequestPacket();
            rp.parameters = new Object[] { idDocente };
            rp.type = TipoRichiesta.GetCorsiDoc;
            ResponsePacket rps = conn.SendReceive(rp);
            return (ArrayList<Corso>) rps.parameters[0];
        } catch (Exception e) {
            System.out.println("errore getCorsiByDocente");
        }
        return new ArrayList<Corso>();
    }

    //monitoraggio
    public static int getUtentiConnessiByCorso(long idCorso,ClientConnection client) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.getLoggedUserNumberByCorso;
            rp.parameters = new Object[] { idCorso };
            ResponsePacket rpp = client.SendReceive(rp);
            int logged = (int) rpp.parameters[0];
            return logged;
        } catch (Exception e) {

        }
        return 0;
    }

    public static int getDownloadByPeriod(Date start, Date end, ClientConnection client) {
        try {
            RequestPacket rp = new RequestPacket();
            rp.type = TipoRichiesta.ContUtentiDownload;
            rp.parameters = new Object[] { start, end };
            ResponsePacket rpp = client.SendReceive(rp);
            int cont = (int) rpp.parameters[0];
            return cont;
        } catch (Exception e) {
            System.out.println("Err ContUtentiDownload");
        }
        return 0;
    }
}
