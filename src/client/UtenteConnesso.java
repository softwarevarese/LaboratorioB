package client;

import client.enums.TipoUtente;
import seatInServer.DBModel.Utente;

/**
 * Contiene le informazioni sull'utente che ha eseguito l'accesso.
 */
public class UtenteConnesso {
    public TipoUtente tipoUtente;
    public Utente connesso;
    private boolean verificato;

    public boolean connesso() {
        if (connesso != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isVerificato() {
        return verificato;
    }

    public void isUtenteConnesso() {

    }

    public UtenteConnesso rispostaUtenteConnesso() {

    }
}
