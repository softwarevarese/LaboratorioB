package seatInServer.utilities;

import java.sql.Connection;
import java.util.ArrayList;

import database.CorsiLaureaController;
import seatInServer.DBModel.Corso;
import seatInServer.DBModel.CorsoDiLaurea;
import seatInServer.DBModel.Dipartimento;

import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

/**
 * Classe che contiene le liste utilizzate per fare da cache per i corsi e i dip
 */
public class CommonList {
    private static ArrayList<CorsoDiLaurea> CorsiLaurea = null;
    private static ArrayList<Dipartimento> Dipartimenti = null;
    private static ArrayList<Corso> CorsiMateria = null;

    // CorsiMateria quanto vorrei i #region

    public static void InserisciNuovoCorsoMateria(Connection conn, Corso nuovoCorso) {
        CorsiLaureaController.NuovoCorsoMateria(conn, nuovoCorso);
        UpdateCorsiMateria(conn);
    }

    public static ArrayList<Corso> GetAllCorsiMateria(Connection conn){
        UpdateCorsiMateria(conn);//
        return CorsiMateria;
    }

    public static ArrayList<Corso> GetCorsiMateriaByLaurea(long IdCorsoLaurea) {
        ArrayList<Corso> lista = new ArrayList<Corso>();
        for (Corso d : CorsiMateria) {
            if (d.getCdLCorso() == IdCorsoLaurea) {
                lista.add(d);
            }
        }
        return lista;
    }
	/*public static ArrayList<CorsoMateria> getCorsoLaureaByDipartimento(long dip) {
		ArrayList<CorsoMateria> lista = new ArrayList<CorsoMateria>();
		for (CorsoMateria d : CorsiMateria) {
			if (d.corsoLaurea.Dipartimento == dip) {
				lista.add(d);
			}
		}
		return lista;
	}*/


    public static Corso GetCorsoMateria(long index) {
        for (Corso d : CorsiMateria) {
            if (d.getIdCorso() == index) {
                return d;
            }
        }
        return null;
    }

    public static void UpdateCorsiMateria(Connection conn) {
        // Chiamata al db.

    if (CorsiMateria == null) {
        CorsiMateria = new ArrayList<Corso>();
    }
    ArrayList<Corso> risultatoQuery = CorsiLaureaController.GetAllCorsiMateria(conn);
		if (risultatoQuery != null) {
        synchronized (CorsiMateria) {
            CorsiMateria = risultatoQuery;
        }
    }
}

    // Corsi Laurea
    public static ArrayList<CorsoDiLaurea> getCorsi() {
        return CorsiLaurea;
    }

    public static ArrayList<CorsoDiLaurea> GetCorsiLaureaByDip(long idDip) {
        ArrayList<CorsoDiLaurea> lista = new ArrayList<CorsoDiLaurea>();
        for (CorsoDiLaurea c : CorsiLaurea) {
            if (c.getDipartimento() == idDip) {
                lista.add(c);
            }
        }
        return lista;
    }

    public static void UpdateCorsiLaurea(Connection conn) {
        if (CorsiLaurea == null) {
            CorsiLaurea = new ArrayList<CorsoDiLaurea>();
        }
        ArrayList<CorsoDiLaurea> risultatoQuery = CorsiLaureaController.GetAllCorsiLaurea(conn);
        if (risultatoQuery != null) {
            synchronized (CorsiLaurea) {
                CorsiLaurea = risultatoQuery;
            }
        }
    }

    public static void InserisciNuovoCorsoLaurea(Connection conn, CorsoDiLaurea nuovoCorso) {
        CorsiLaureaController.NuovoCorsoLaurea(conn, nuovoCorso);
        UpdateCorsiLaurea(conn);
    }

    public static CorsoDiLaurea GetCorsoLaurea(long id) {
        for (CorsoDiLaurea c : CorsiLaurea) {
            if (c.getIdCdL() == id) {
                return c;
            }
        }
        return null;
    }

    // Dipartimenti
    public static ArrayList<Dipartimento> getDipartimenti() {
        return Dipartimenti;
    }

    public static int getDipartimento(int Id) {
        for (Dipartimento d : Dipartimenti) {
            if (d.getId() == Id) {
                return d.getId();
            }
        }
        return -1;
    }

    public static void UpdateDipartimenti(Connection conn) {
        // Chiamata al db.
        if (Dipartimenti == null) {
            Dipartimenti = new ArrayList<Dipartimento>();
        }
        ArrayList<Dipartimento> risultatoQuery = CorsiLaureaController.GetAllDipartimenti(conn);
        if (risultatoQuery != null) {
            synchronized (Dipartimenti) {
                Dipartimenti = risultatoQuery;
            }
        }
    }

    public static void InserisciNuovoDipartimento(Connection conn, Dipartimento nuovoDipartimento) {
        CorsiLaureaController.NuovoDipartimento(conn, nuovoDipartimento);
        UpdateDipartimenti(conn);
    }

}