package seatInServer.DBModel;

import client.ClientConnection;
import client.Logger;
import client.enums.StatoCarriera;
import client.enums.TipoRichiesta;
import client.pacchetti.RequestPacket;
import client.pacchetti.ResponsePacket;

import java.util.ArrayList;

/**
 * Questa classe rappresenta il profilo di uno studente.
 */
public class Studente extends Utente{
    private long cdL;
    private StatoCarriera statoCarriera;
    private int annoImm;

    protected ArrayList<String> infoStudente() {
        ArrayList<String> lista = super.anagrafica();
        //lista.add(cdL);
        //lista.add(StatoCarriera.statoCarriera);
        lista.add(String.valueOf(annoImm));

        return lista;
    }

    protected String getContenutoCorso() {
        /**
         * visualizza i contenuti di un corso
         *
         */
    }

    public long getCdL() {
        return cdL;
    }

    public int getAnnoImm() {
        return annoImm;
    }

    public int getStatoCarriera() {
        return statoCarriera.ordinal();
    }

    public void setStatoCarriera(StatoCarriera statoCarriera) {
        this.statoCarriera = statoCarriera;
    }

    public void setAnnoImm(int annoImm) {
        this.annoImm = annoImm;
    }

    public void setCdL(long cdL) {
        this.cdL = cdL;
    }

    protected void download() {

    }

    protected boolean sendMailDocente() {

    }
    
    public ArrayList<Corso> getCorsiStudente(int studID) {
		try {
			ClientConnection conn = new ClientConnection();
			RequestPacket rp = new RequestPacket();
			rp.parameters = new Object[] { studID };
			rp.type = TipoRichiesta.GetCorsiStud;
			ResponsePacket rpp = conn.SendReceive(rp);
			ArrayList<Corso> lis = (ArrayList<Corso>) rpp.parameters[0];
			return lis;
		} catch (Exception e) {
			Logger.WriteError(e, "ClientInstance", "GetCorsiStud");
		}
		return new ArrayList<Corso>();
	}


    
}
