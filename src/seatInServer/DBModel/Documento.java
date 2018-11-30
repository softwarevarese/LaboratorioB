package seatInServer.DBModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe rappresentante i documenti memorizzati.
 */
public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idDocumento;
    private int idSezione;
    private String sezione;
    private String nomeDoc;
    private String formato;
    private byte [] dati;

    public ArrayList<String> getDocumento() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(String.valueOf(idDocumento));
        lista.add(String.valueOf(idSezione));
        lista.add(sezione);
        lista.add(nomeDoc);
        lista.add(formato);

        return lista;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getIdSezione() {
        return idSezione;
    }

    public void setIdSezione(int idSezione) {
        this.idSezione = idSezione;
    }

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    public String getNomeDoc() {
        return nomeDoc;
    }

    public void setNomeDoc(String nome) {
        this.nomeDoc = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public byte[] getDati() {
        return dati;
    }

    public void setDati(byte[] dati) {
        this.dati = dati;
    }
}
