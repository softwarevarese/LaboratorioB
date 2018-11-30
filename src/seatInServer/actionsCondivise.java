package seatInServer;

/**
 * 
 * Questa classe contiene campi e metodi sincronizzati, in modo da prevenire situazioni di stallo (deadlock).
 *
 */
public class actionsCondivise {
	private String query;
	
	public actionsCondivise() {
	}
	
	public synchronized void setQuery(String query) {
		this.query=query;
	}
	
	
}
