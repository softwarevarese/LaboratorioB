package database;

import client.Logger;
import client.enums.StatoCarriera;
import seatInServer.DBModel.Corso;
import seatInServer.DBModel.Studente;
import seatInServer.utilities.CommonList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RichiestaStudente {

    private static StatoCarriera ConvertiStatoCar(int sc) {
        switch (sc) {
            case 1:
                return StatoCarriera.I;
            case 2:
                return StatoCarriera.II;
            case 3:
                return StatoCarriera.III;
            default:
                return StatoCarriera.FuoriCorso;
        }
    }

    public static void creaStudente(Connection conn, Studente newStudente) {
        newStudente.setCodAttivazione(DatabaseManager.generateAttCode());
        try {
            long codAtt = RichiestaAlDB.GenCodAtt();
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO \"Anagrafiche\" (\"Email\", \"Nome\", \"Cognome\", \"Password\", \"CodiceAttivazione\") VALUES (?, ?, ?, ?, ?)");
            st.setString(1, newStudente.getEmail());
            st.setString(2, newStudente.getNome());
            st.setString(3, newStudente.getCognome());
            st.setString(4, newStudente.getPassword());
            st.setLong(5, codAtt);
            st.executeUpdate();
            st.close();
            java.sql.Statement st1 = conn.createStatement();
            ResultSet rs = st1.executeQuery(
                    "SELECT \"Matricola\" FROM \"Anagrafiche\" WHERE \"Email\" LIKE \'" + newStudente.getEmail() + "\'");
            String matS = "-1";
            while (rs.next()) {
                matS = rs.getString(1);
            }
            st1.close();
            long mat = Long.parseLong(matS);
            PreparedStatement stF = conn
                    .prepareStatement("INSERT INTO \"Studenti\" (\"Matricola\", \"Corso\", \"AnnoMat\", \"StatoCar\") "
                            + "VALUES (?,?,?,?)");
            stF.setLong(1, mat);
            stF.setLong(2, newStudente.getCdL());
            stF.setInt(3, newStudente.getAnnoImm());
            stF.setInt(4, newStudente.getStatoCarriera());
            stF.executeUpdate();
            stF.close();
            seatInServer.utilities.EmailSender.send_uninsubria_email(newStudente.getEmail(), "Registrazione SimpleElearning",
                    "Codice d'attivazione:" + codAtt);
        } catch (Exception e) {
            Logger.WriteError(e, "StudentController", "CreaStudente");
        }
    }

    public static ArrayList<Corso> ReperisciCorsi(Connection conn, long studID) {
        String query = "SELECT \"CorsiMateria\".\"ID\" FROM \"CorsiMateria\" JOIN \"StudentiXCorsi\" ON \"CorsiMateria\".\"ID\" = \"StudentiXCorsi\".\"Corso\" "
                + "JOIN \"Studenti\" on \"Studenti\".\"Matricola\" = \"StudentiXCorsi\".\"Studente\" "
                + "WHERE \"Studenti\".\"Matricola\" = " + studID;
        ArrayList<Corso> lista = new ArrayList<Corso>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                lista.add(CommonList.GetCorsoMateria(rs.getLong(1)));
            }
            return lista;

        } catch (Exception e) {
            Logger.WriteError(e, "StudentController", "ReperisciCorsi");
        }
        return null;
    }

    public static Studente Login(Connection conn, String Email, String Psw) {
        Studente newStudente = null;
        String query = "SELECT * FROM \"Anagrafiche\""
                + " JOIN \"Studenti\" ON \"Anagrafiche\".\"Matricola\" = \"Studenti\".\"Matricola\""
                + " WHERE \"Email\" LIKE '" + Email + "' AND \"Password\" LIKE '" + Psw + "'";
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                newStudente = new Studente();
                newStudente.setMatricola((int) rs.getLong("Matricola"));
                newStudente.setNome(rs.getString("Nome"));
                newStudente.setCognome(rs.getString("Cognome"));
                newStudente.setEmail(rs.getString("Email"));
                newStudente.setActivated(rs.getBoolean("Verificato"));
                newStudente.setCdL(CommonList.GetCorsoLaurea(rs.getLong("Corso")).getIdCdL());
                newStudente.setAnnoImm((rs.getInt("AnnoMat")));
                newStudente.setStatoCarriera((ConvertiStatoCar(rs.getInt("StatoCar"))));
                // newStudente.CorsiFrequentati = ReperisciCorsi(conn, newStudente.Matricola);
            }
            return newStudente;

        } catch (Exception e) {
            Logger.WriteError(e, "StudentController", "Login");
        }

        return null;
    }

    public static void SubscribeCourse(Connection conn, long idUtente, long idCorso) {
        try {
            String query = "INSERT INTO \"StudentiXCorsi\" (\"Studente\", \"Corso\")" + " VALUES (" + idUtente + ","
                    + idCorso + ")";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            query = "SELECT \"Email\" FROM \"Anagrafiche\" WHERE \"Matricola\" = idUtente";
            ResultSet rs = st.executeQuery(query);
            String emailStud = "";
            while (rs.next()) {
                emailStud = rs.getString("Email");
            }
            query = "SELECT \"Nome\", \"AnnoAtt\" FROM \"CorsiMateria\" WHERE \"ID\" = " + idCorso;
            rs = st.executeQuery(query);
            String nomeCorso = "";
            long annoAtt = 0;
            while (rs.next()) {
                nomeCorso = rs.getString("Nome");
                annoAtt = rs.getLong("AnnoAtt");
            }
            query = "SELECT \"Email\" FROM \"Anagrafiche\" JOIN \"DocentiXCorsi\" ON \"DocentiXCorsi\".\"Docente\" = \"Anagrafiche\".\"Matricola\"\r\n"
                    + "WHERE \"DocentiXCorsi\".\"Corso\" = " + idCorso;
            seatInServer.utilities.EmailSender.send_uninsubria_email(emailStud, "Iscrizione corso",
                    "Complimenti, ti sei iscritto al corso " + nomeCorso + "-" + annoAtt);
            rs = st.executeQuery(query);
            while (rs.next()) {
                String emaildocente = rs.getString("Email");
                seatInServer.utilities.EmailSender.send_uninsubria_email(emailStud, "Iscrizione studente al corso",
                        "Lo studente " + "" + emailStud + " si � iscritto al corso");

            }
        } catch (Exception e) {
            Logger.WriteError(e, "StudenteController", "SubscribeCourse");
        }
    }

    public static ArrayList<Studente> GetAllStudenti(Connection conn) {
        try {
            ArrayList<Studente> lista = new ArrayList<Studente>();

            Studente newStudente = null;
            String query = "SELECT * FROM \"Anagrafiche\""
                    + " JOIN \"Studenti\" ON \"Anagrafiche\".\"Matricola\" = \"Studenti\".\"Matricola\"";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                newStudente = new Studente();
                newStudente.setMatricola((int) rs.getLong("Matricola"));
                newStudente.setNome(rs.getString("Nome"));
                newStudente.setCognome(rs.getString("Cognome"));
                newStudente.setEmail(rs.getString("Email"));
                newStudente.setActivated(rs.getBoolean("Verificato"));
                newStudente.setCdL(CommonList.GetCorsoLaurea(rs.getLong("Corso")).getIdCdL());
                newStudente.setAnnoImm((rs.getInt("AnnoMat")));
                newStudente.setStatoCarriera((ConvertiStatoCar(rs.getInt("StatoCar"))));
                // newStudente.CorsiFrequentati = ReperisciCorsi(conn, newStudente.Matricola);
                lista.add(newStudente);
            }
            return lista;
        } catch (Exception e) {
            Logger.WriteError(e, "StudentController", "GetAllStudenti");
        }
        return null;
    }

    public static Studente GetStudenteByMat(Connection conn, long matricola) {
        try {
            Studente newStudente = null;
            String query = "SELECT * FROM \"Anagrafiche\""
                    + "					JOIN \"Studenti\" ON \"Anagrafiche\".\"Matricola\" = \"Studenti\".\"Matricola\""
                    + "					WHERE  \"Anagrafiche\".\"Matricola\" = " + matricola;
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                newStudente.setMatricola((int) rs.getLong("Matricola"));
                newStudente.setNome(rs.getString("Nome"));
                newStudente.setCognome(rs.getString("Cognome"));
                newStudente.setEmail(rs.getString("Email"));
                newStudente.setActivated(rs.getBoolean("Verificato"));
                newStudente.setCdL(CommonList.GetCorsoLaurea(rs.getLong("Corso")).getIdCdL());
                newStudente.setAnnoImm((rs.getInt("AnnoMat")));
                newStudente.setStatoCarriera((ConvertiStatoCar(rs.getInt("StatoCar"))));
                // newStudente.CorsiFrequentati = ReperisciCorsi(conn, newStudente.Matricola);
            }
            return newStudente;
        } catch (Exception e) {
            Logger.WriteError(e, "StudentController", "GetStudenteByMat");
        }
        return null;
    }
}
