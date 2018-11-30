package client.pacchetti;

import java.io.Serializable;

/**
 * Un pacchetto inviato dal server in risposta al client contenenti i parametri richiesti dall'utente.
 *
 */
public class ResponsePacket implements Serializable {

	private static final long serialVersionUID = 1L;
	public Object[] parameters;

}
