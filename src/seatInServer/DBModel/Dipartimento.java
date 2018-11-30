package seatInServer.DBModel;

import java.io.Serializable;

public class Dipartimento implements Serializable {
    /**
     * Classe rappresentante un dipartimento.
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}
