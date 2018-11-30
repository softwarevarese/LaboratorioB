package seatInServer.DBModel;

import java.io.Serializable;

public class CorsoDiLaurea implements Serializable {
    /**
     * Classe rappresentante un Corso di Laurea.
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private int dipartimento;

    public int getIdCdL() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getDipartimento() {
        return dipartimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDipartimento(int dipartimento) {
        this.dipartimento = dipartimento;
    }
}
