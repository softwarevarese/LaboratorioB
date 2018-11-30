package seatInServer.DBModel;

import client.enums.VisibilitaSezione;

import java.util.ArrayList;

/**
 * Classe modello di una cartella del file sistem che rappresenta una sezione.
 */
public class Sezione {
    private static final long serialVersionUID = 1L;
    private int idSezione;
    private String nomeSez;
    private String descrizione;
    private VisibilitaSezione visibilita;
    private int cartellaPadre;
    private ArrayList<Sezione> sottoSezioni;
    private ArrayList<Documento> documenti;

    public ArrayList<String> getSezione() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(String.valueOf(idSezione));
        lista.add(nomeSez);
        lista.add(descrizione);
        //if (visibilita==VisibilitaSezione.Pubblica) {lista.add("pubblica");}
        //else if (visibilita==VisibilitaSezione.Privata) { lista.add("privata");}
        //if (visibilita) lista.add("pubblica"); else lista.add("privata");
        lista.add(String.valueOf(cartellaPadre));

        return lista;
    }

    public int getIdSezione() {
        return idSezione;
    }

    public String getNomeSez() {
        return nomeSez;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public VisibilitaSezione getVisibilita() {
        return visibilita;
    }

    public void setVisibilita(VisibilitaSezione visibilita) {
        this.visibilita = visibilita;
    }

    public int getCartellaPadre() {
        return cartellaPadre;
    }

    public ArrayList<Sezione> getSottoSezioni() {
        return sottoSezioni;
    }

    public ArrayList<Documento> getDocumenti() {
        return documenti;
    }

    public void setNomeSez(String nomeSez) {
        this.nomeSez = nomeSez;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public void setIdSezione(int idSezione) {
        this.idSezione = idSezione;
    }

    public void setCartellaPadre(int cartellaPadre) {
        this.cartellaPadre = cartellaPadre;
    }

    public void setSottoSezioni(ArrayList<Sezione> sottoSezioni) {
        this.sottoSezioni = sottoSezioni;
    }

    public void setDocumenti(ArrayList<Documento> documenti) {
        this.documenti = documenti;
    }
}
