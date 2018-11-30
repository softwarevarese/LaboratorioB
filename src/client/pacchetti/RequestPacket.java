package client.pacchetti;

import java.io.Serializable;

import client.enums.TipoRichiesta;
import client.enums.TipoUtente;
/**
 * Contiene i parametri richiesti dall'utente
 *
 */
public class RequestPacket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public TipoRichiesta type;
	public TipoUtente tipoUtente;
	public Object[] parameters;
}