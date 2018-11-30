package seatInServer.DBModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe modello del file system
 *
 */
public class FileSystem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Corso corso;
    public ArrayList<Sezione> sezioni;
}
