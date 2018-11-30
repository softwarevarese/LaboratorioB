package seatInServer.DBModel;

import java.util.ArrayList;

public class Corso {
    private int idCorso;
    private long cdLCorso;
    private String nome;
    private int annoAttivazione;
    private String descrizione;

    public ArrayList<String> getDataCorso() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(String.valueOf(idCorso));
        //lista.add(cdLCorso);
        lista.add(nome);
        lista.add(String.valueOf(annoAttivazione));

        return lista;
    }
    
    public int getIdCorso() { return idCorso; }
    public String getNome() { return nome; }

    public long getCdLCorso() {
        return cdLCorso;
    }

    public void setIdCorso(int idCorso) {
        this.idCorso = idCorso;
    }

    public void setCdLCorso(long cdLCorso) {
        this.cdLCorso = cdLCorso;
    }


    public int getAnnoAttivazione() {
        return annoAttivazione;
    }

    public String getDescrizione() { return descrizione; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnnoAttivazione(int annoAttivazione) {
        this.annoAttivazione = annoAttivazione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
