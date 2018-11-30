package seatInAdmin.gui;

import seatInServer.DBModel.Studente;
import client.UtenteConnesso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Toolkit;

public class RegistrazioneNewStudente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin, Studente nuovoStudente) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrazioneNewStudente frame = new RegistrazioneNewStudente(admin,nuovoStudente);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrazioneNewStudente(UtenteConnesso admin,Studente nuovoStudente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrazioneNuovoStudente = new JLabel("REGISTRAZIONE NUOVO STUDENTE");
		lblRegistrazioneNuovoStudente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrazioneNuovoStudente.setBounds(12, 13, 306, 16);
		contentPane.add(lblRegistrazioneNuovoStudente);
		
		JLabel lblNewLabel = new JLabel("Dipartimento");
		lblNewLabel.setBounds(28, 56, 102, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Corso di Laurea");
		lblNewLabel_1.setBounds(28, 139, 90, 16);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(28, 85, 253, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(28, 168, 253, 22);
		contentPane.add(comboBox_1);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBounds(63, 216, 177, 25);
		contentPane.add(btnConferma);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(205, 331, 97, 25);
		contentPane.add(btnAvanti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(12, 331, 97, 25);
		contentPane.add(btnIndietro);
		
		JButton btnNewButton = new JButton("Iscrizione ai corsi");
		btnNewButton.setBounds(63, 260, 174, 25);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 316, 329, 2);
		contentPane.add(separator);
	}
}
