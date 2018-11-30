package seatInAdmin.gui;

import seatInServer.DBModel.Docente;
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

public class RegistrazioneNewDocente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin, Docente nuovoDocente) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrazioneNewDocente frame = new RegistrazioneNewDocente(admin,nuovoDocente);
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
	public RegistrazioneNewDocente(UtenteConnesso admin, Docente nuovoDocente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documenti\\Diapositive\\Laboratorio Interdisciplinare B\\Progetto\\LaboratorioB\\src\\media\\Icona.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrazioneNuovoStudente = new JLabel("REGISTRAZIONE NUOVO DOCENTE");
		lblRegistrazioneNuovoStudente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrazioneNuovoStudente.setBounds(12, 13, 306, 16);
		contentPane.add(lblRegistrazioneNuovoStudente);
		
		JLabel lblNewLabel = new JLabel("Dipartimento");
		lblNewLabel.setBounds(28, 72, 102, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(28, 101, 253, 22);
		contentPane.add(comboBox);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBounds(63, 136, 177, 25);
		contentPane.add(btnConferma);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(205, 240, 97, 25);
		contentPane.add(btnAvanti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(12, 240, 97, 25);
		contentPane.add(btnIndietro);
		
		JButton btnNewButton = new JButton("Assegnazione dei corsi");
		btnNewButton.setBounds(63, 174, 174, 25);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 225, 329, 2);
		contentPane.add(separator);
	}
}
