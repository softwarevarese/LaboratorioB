package seatInServer.utilities;

import java.util.Properties;
import java.util.Scanner;

public class EmailSender {
    private static final String usr = "mrettani@studenti.uninsubria.it";
    private static String pwd = "";

    public static void SetPassword(Scanner s) {
        try {
            System.out.println("Inserire Password Email mrettani@studenti.uninsubria.it:\n");
            pwd=s.nextLine();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /** Function to send an e-mail */
    public static void send_uninsubria_email(String to, String subject, String body) {
        try {
            String password = pwd;
            String username = usr;
            String host = "smtp.office365.com";
            String from = username;

            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", 587);

            Session session = Session.getInstance(props);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(body);
            // invia...
            Transport.send(msg, username, password);
            System.out.println("\nMail was sent successfully.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Errore nell'invio della mail");
        }
    }
}
