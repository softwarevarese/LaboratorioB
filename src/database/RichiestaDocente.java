package database;

import seatInServer.DBModel.Corso;
import seatInServer.DBModel.Docente;
import seatInServer.MainServer;
import seatInServer.utilities.CommonList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RichiestaDocente {

    public static void LinkDocenteCorso(Connection conn, long idDocente, long idCorso) {
        try {
            String query = "INSERT INTO \"DocentiXCorsi\" (\"Docente\", \"Corso\") VALUES (" + idDocente + "," + idCorso
                    + ")";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Errore link docente corso");
        }
    }

    public static void creaDocente(Connection conn, Docente newDocente) {
        newDocente.setCodAttivazione(MainServer.generateAttCode());
        try {
            long codAtt = RichiestaAlDB.GenCodAtt();
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO \"Anagrafiche\" (\"Email\", \"Nome\", \"Cognome\", \"Password\", \"CodiceAttivazione\") VALUES (?, ?, ?, ?, ?)");
            st.setString(1, newDocente.getEmail());
            st.setString(2, newDocente.getNome());
            st.setString(3, newDocente.getCognome());
            st.setString(4, newDocente.getPassword());
            st.setLong(5, codAtt);
            st.executeUpdate();
            st.close();
            java.sql.Statement st1 = conn.createStatement();
            ResultSet rs = st1.executeQuery(
                    "SELECT \"Matricola\" FROM \"Anagrafiche\" WHERE \"Email\" LIKE \'" + newDocente.getEmail() + "\'");
            String matS = "-1";
            while (rs.next()) {
                matS = rs.getString(1);
            }
            st1.close();
            long mat = Long.parseLong(matS);
            PreparedStatement stF = conn
                    .prepareStatement("INSERT INTO \"Docenti\" (\"Matricola\", \"Dipartimento\") " + "VALUES (?,?)");
            stF.setLong(1, mat);
            stF.setLong(2, Long.parseLong(newDocente.getDipartimento()));
            stF.executeUpdate();
            stF.close();
            seatInServer.utilities.EmailSender.send_uninsubria_email(newDocente.getEmail(), "Registrazione SimpleElearning",
                    "Codice d'attivazione:" + codAtt);
        } catch (Exception e) {
            System.out.println("");
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Corso> getCorsiDocente(Connection conn, long matricola) {
        String query = "SELECT \"DocentiXCorsi\".\"Corso\" FROM \"DocentiXCorsi\" WHERE \"DocentiXCorsi\".\"Docente\" = "
                + matricola;
        ArrayList<Corso> lista = new ArrayList<Corso>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                lista.add(CommonList.GetCorsoMateria(rs.getLong(1)));
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Errore nell'ottenimento dei corsi dell'insegnante");
        }

        return null;
    }

    public static Docente Login(Connection conn, String Email, String Psw) {
        Docente newDocente = null;

        String query = "SELECT * FROM \"Anagrafiche\""
                + " JOIN \"Docenti\" ON \"Anagrafiche\".\"Matricola\" = \"Docenti\".\"Matricola\""
                + " WHERE \"Email\" LIKE '" + Email + "' AND \"Password\" LIKE '" + Psw + "'";
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                newDocente = new Docente();
                newDocente.setMatricola((int)rs.getLong("Matricola"));
                newDocente.setNome( rs.getString("Nome"));
                newDocente.setCognome( rs.getString("Cognome"));
                newDocente.setEmail( rs.getString("Email"));
                newDocente.setActivated(rs.getBoolean("Verificato"));
                newDocente.setDipartimento(CommonList.getDipartimento(rs.getInt("Dipartimento")));
                newDocente.setCorsiDocente( getCorsiDocente(conn, newDocente.getMatricola()));

            }
            return newDocente;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Errore Login Professore");
        }
        return null;
    }

    public static ArrayList<Docente> GetAllDocenti(Connection conn) {
        String query = "SELECT * FROM \"Docenti\" JOIN \"Anagrafiche\" ON \"Anagrafiche\".\"Matricola\" = \"Docenti\".\"Matricola\"";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Docente newDoc = null;
            ArrayList<Docente> listaDoc = new ArrayList<Docente>();
            while (rs.next()) {
                newDoc = new Docente();
                newDoc.setMatricola( (int) rs.getLong("Matricola"));
                newDoc.setNome( rs.getString("Nome"));
                newDoc.setCognome( rs.getString("Cognome"));
                newDoc.setDipartimento( CommonList.getDipartimento(rs.getLong("Dipartimento")));
                newDoc.setEmail(  rs.getString("Email"));
                listaDoc.add(newDoc);
            }
            return listaDoc;
        } catch (Exception e) {
            System.out.println("Errore nell'ottenimento di tutti i docenti");
        }
        return new ArrayList<Docente>();
    }
}
