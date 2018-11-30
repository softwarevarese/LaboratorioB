package client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import seatInServer.DBModel.Admin;
import seatInServer.DBModel.Utente;
import client.UtenteConnesso;

public class AttivazioneProfilo {

	private JFrame frame;
	private JTextField codiceatt;
	private UtenteConnesso utenteConnesso;

	/**
	 * Launch the application.
	 */
	public static void main(String mail, UtenteConnesso utenteConnesso)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					AttivazioneProfilo window = new AttivazioneProfilo( mail,utenteConnesso);
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AttivazioneProfilo(String mail, UtenteConnesso utenteConnesso) {
		this.utenteConnesso=utenteConnesso;
		initialize(mail);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mail) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("SEATIN");
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().setForeground(SystemColor.inactiveCaption);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("media/f.png"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAttivazioneNuovoAcount = new JLabel("Attivazione nuovo profilo");
		lblAttivazioneNuovoAcount.setForeground(Color.BLACK);
		lblAttivazioneNuovoAcount.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAttivazioneNuovoAcount.setBounds(69, 13, 325, 33);
		frame.getContentPane().add(lblAttivazioneNuovoAcount);

		JLabel LB_newpsw = new JLabel("nuova password");
		LB_newpsw.setVisible(false);
		LB_newpsw.setHorizontalAlignment(SwingConstants.CENTER);
		LB_newpsw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LB_newpsw.setBounds(12, 65, 164, 16);
		frame.getContentPane().add(LB_newpsw);

		JTextPane nuova_psw = new JTextPane();
		nuova_psw.setBounds(216, 59, 192, 22);
		frame.getContentPane().add(nuova_psw);
		nuova_psw.setVisible(false);

		JTextPane conferma_psw = new JTextPane();
		conferma_psw.setBounds(216, 98, 192, 22);
		frame.getContentPane().add(conferma_psw);
		conferma_psw.setVisible(false);

		JLabel LB_errori = new JLabel();
		LB_errori.setForeground(Color.RED);
		LB_errori.setVisible(false);
		LB_errori.setHorizontalAlignment(SwingConstants.CENTER);
		LB_errori.setBounds(12, 236, 420, 16);
		frame.getContentPane().add(LB_errori);
         // Dopo la conferma del codice di attivazione viene   aggiornata password e utente rimandato a login iniziale
		JButton BTN_creaprf = new JButton("attiva il profilo utente");
		BTN_creaprf.setVisible(false);
		BTN_creaprf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psw_aggiornata = nuova_psw.getText();
				String conferma = conferma_psw.getText();
				if ((psw_aggiornata.equals(conferma)) == true) 
				{
					
					// frame.dispose e stata spostata prima per vedere se chiude prima frame e poi mostra popup e loginClient
					frame.dispose();
						Utente.cambiaPasswordByMail(mail,psw_aggiornata );
					JOptionPane.showMessageDialog(null, "Password Aggiornata");
					
					Login.main(null);
				} else {
					LB_errori.setText("le password non coincidono");
					LB_errori.setVisible(true);
					nuova_psw.setText("");
					conferma_psw.setText("");
				}

			}
		});
		BTN_creaprf.setBounds(126, 198, 192, 25);
		frame.getContentPane().add(BTN_creaprf);

		JLabel LB_confermapsw = new JLabel("conferma password");
		LB_confermapsw.setHorizontalAlignment(SwingConstants.CENTER);
		LB_confermapsw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LB_confermapsw.setBounds(12, 94, 182, 16);
		frame.getContentPane().add(LB_confermapsw);
		LB_confermapsw.setVisible(false);

		JLabel LB_controllocodice = new JLabel("Inserire codice di attivazione");
		LB_controllocodice.setHorizontalAlignment(SwingConstants.CENTER);
		LB_controllocodice.setBounds(12, 136, 192, 16);
		frame.getContentPane().add(LB_controllocodice);

		codiceatt = new JTextField();
		codiceatt.setBounds(292, 133, 116, 22);
		frame.getContentPane().add(codiceatt);
		codiceatt.setColumns(10);

		JButton BTN_creaprofilo = new JButton("Verifica codice");
		BTN_creaprofilo.setBounds(126, 198, 192, 25);
		frame.getContentPane().add(BTN_creaprofilo);
		BTN_creaprofilo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// VERIFICO CHE IL CODICE ESISTE SUL SERVER...
				// SE ESISTE :
				Integer CODICEATT = Integer.parseInt(codiceatt.getText());// parte inutile: controllo va eseguito su server
				if (new Admin().verifyCodAttivazione(utenteConnesso.connesso.getMatricola(), CODICEATT))// stabilire dove collocare il metodo..
				{
					LB_errori.setVisible(false);
					LB_controllocodice.setVisible(false);
					codiceatt.setVisible(false);
					BTN_creaprofilo.setVisible(false);
					LB_newpsw.setVisible(true);
					nuova_psw.setVisible(true);
					LB_confermapsw.setVisible(true);
					conferma_psw.setVisible(true);
					BTN_creaprf.setVisible(true);
				} else {
					LB_errori.setText("codice di attivazione errato o scaduto");
					LB_errori.setVisible(true);
				}
			}
		});
	}
}
