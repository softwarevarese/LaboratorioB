package database;

import seatInServer.DBModel.Admin;
import seatInServer.MainServer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RichiestaAdmin extends RichiestaAlDB {

    //metodi admin
    public static boolean LoginAdmin(Connection conn) {
        Scanner s = new Scanner(System.in);
        try {
            System.out.println("Inserisci Email");
            String Email = s.nextLine();
            System.out.println("Inserisci Password");
            String Psw = s.nextLine();
            String query = "Select Count(*) from \"Admin\" JOIN \"Anagrafiche\" ON "
                    + "\"Admin\".\"Matricola\" = \"Anagrafiche\".\"Matricola\"" + "WHERE \"Email\" LIKE \'" + Email
                    + "\'AND \"Password\" LIKE \'" + Psw + "\'";

            java.sql.Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int ris = rs.getInt(1);
                if (ris == 1) {
                    return true;
                }
            }

        } catch (Exception e) {

            System.out.println("Errore Login Admin");
            System.out.println(e.toString());
        }
        return false;
    }

    public static Admin Login(Connection conn, String Email, String Psw) {
        Admin admin = null;
        try {
            String query = "Select * from \"Admin\" JOIN \"Anagrafiche\" ON "
                    + "\"Admin\".\"Matricola\" = \"Anagrafiche\".\"Matricola\"" + "WHERE \"Email\" LIKE \'" + Email
                    + "\'AND \"Password\" LIKE \'" + Psw + "\'";

            java.sql.Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                admin = new Admin();
                admin.setMatricola((int) rs.getLong("Matricola"));
                admin.setEmail(rs.getString("Email"));
                admin.setNome( rs.getString("Nome"));
                admin.setCognome(rs.getString("Cognome"));
                admin.setActivated(rs.getBoolean("Verificato"));

            }
            return admin;
        }

        catch (Exception e) {

            System.out.println("Errore Login Admin");
            System.out.println(e.toString());
        }
        return null;
    }

    public static void creaAdmin(Connection conn) {
        Scanner s = new Scanner(System.in);
        Admin newAdmin = new Admin();
        System.out.println("Inserire E-Mail");
        newAdmin.setEmail(s.nextLine());
        System.out.println("Inserire il Cognome");
        newAdmin.setCognome(s.nextLine());
        System.out.println("Inserire il Nome");
        newAdmin.setNome(s.nextLine());
        System.out.println("Inserire la Password");
        newAdmin.setPassword(s.nextLine());
        newAdmin.setCodAttivazione(MainServer.generateAttCode());
        try {
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO \"Anagrafiche\" (\"Email\", \"Nome\", \"Cognome\", \"Password\", \"CodiceAttivazione\", \"Verificato\") VALUES (?,?, ?, ?, ?, ?)");
            st.setString(1, newAdmin.getEmail());
            st.setString(2, newAdmin.getNome());
            st.setString(3, newAdmin.getCognome());
            st.setString(4, newAdmin.getPassword());
            st.setLong(5, 123456);
            st.setBoolean(6, true);
            st.executeUpdate();
            st.close();
            java.sql.Statement st1 = conn.createStatement();
            ResultSet rs = st1.executeQuery(
                    "SELECT \"Matricola\" FROM \"Anagrafiche\" WHERE \"Email\" LIKE \'" + newAdmin.getEmail() + "\'");

            String matS = "-1";
            while (rs.next()) {
                matS = rs.getString(1);
            }
            System.out.println(matS);
            st1.close();
            long mat = Long.parseLong(matS);
            PreparedStatement stF = conn.prepareStatement("INSERT INTO \"Admin\" (\"Matricola\") VALUES (?)");
            stF.setLong(1, mat);
            stF.executeUpdate();
            stF.close();
            seatInServer.utilities.EmailSender.send_uninsubria_email(newAdmin.getEmail(), "Registrazione SimpleElearning", "Operazione registrazione nuovo admin andata a buon fine");
        } catch (Exception e) {
            System.out.println("");
            System.out.println(e.getMessage());
        }
        s.close();

    }

    public static void creaAdmin(Connection conn, Admin newAdmin) {
        try {
            long codAtt = RichiestaAlDB.GenCodAtt();
            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO \"Anagrafiche\" (\"Email\", \"Nome\", \"Cognome\", \"Password\", \"CodiceAttivazione\") VALUES (?, ?, ?, ?, ?)");
            st.setString(1, newAdmin.getEmail());
            st.setString(2, newAdmin.getNome());
            st.setString(3, newAdmin.getCognome());
            st.setString(4, newAdmin.getPassword());
            st.setLong(5, codAtt);
            st.executeUpdate();
            st.close();
            java.sql.Statement st1 = conn.createStatement();
            ResultSet rs = st1.executeQuery(
                    "SELECT \"Matricola\" FROM \"Anagrafiche\" WHERE \"Email\" LIKE \'" + newAdmin.getEmail() + "\'");

            long mat = -1;
            while (rs.next()) {
                mat = rs.getLong(1);
            }
            st1.close();
            PreparedStatement stF = conn.prepareStatement("INSERT INTO \"Admin\" (\"Matricola\") VALUES (?)");
            stF.setLong(1, mat);
            stF.executeUpdate();
            stF.close();
            seatInServer.utilities.EmailSender.send_uninsubria_email(newAdmin.getEmail(), "Registrazione SimpleElearning", "Operazione registrazione nuovo admin andata a buon fine");
        } catch (Exception e) {
            System.out.println("");
            System.out.println(e.getMessage());
        }
    }

    // Query che controlla se esistono Admin
    public static boolean isThereAdmin(Connection conn) {
        try {
            String query = "Select Count(*) from \"Admin\"";
            java.sql.Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                String num = r.getString(1);
                if (Integer.parseInt(num) > 0) {
                    s.close();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
