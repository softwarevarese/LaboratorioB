package seatInAdmin.gui;

import client.UtenteConnesso;
import client.gui.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;

public class PrincipaleAdmin extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipaleAdmin frame = new PrincipaleAdmin(admin);
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
	public PrincipaleAdmin(UtenteConnesso admin) {
		setTitle("seatInAdmin");
		setBackground(new Color(240, 248, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipaleAdmin.class.getResource("/media/Icona.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 2, 2));
		
		JButton viewCorsiButton = new JButton("Visualizza corsi");
		viewCorsiButton.setToolTipText("Visualizza i corsi disponibili");
		viewCorsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VistaCorsi(admin);
				frame.dispose();
			}
		});
		panel.add(viewCorsiButton);
		
		JButton assegnaCorsoButton = new JButton("Assegna corso");
		assegnaCorsoButton.setToolTipText("Assegna docente ad un corso");
		assegnaCorsoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AssegnaCorsoDocente(admin);
				frame.dispose();
			}
		});
		panel.add(assegnaCorsoButton);
		
		JButton anagraficaButton = new JButton("Info anagrafiche");
		anagraficaButton.setToolTipText("Info anagrafiche");
		anagraficaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel.add(anagraficaButton);

		JButton createCorsoButton = new JButton("Crea corso");
		createCorsoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreazioneCorso(admin);
				frame.dispose();
			}
		});
		createCorsoButton.setToolTipText("Crea un corso");
		panel.add(createCorsoButton);
		
		JButton newUserButton = new JButton("Registra nuovo utente");
		newUserButton.setToolTipText("Registra un nuovo tipo di utente");
		newUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistrazioneNewUtente(admin);
				frame.dispose();
			}
		});
		panel.add(newUserButton);
		
		JButton monitorButton = new JButton("Monitoraggio piattaforma");
		monitorButton.setToolTipText("Monitoraggio piattaforma");
		monitorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MonitoraggioHub(admin);
				frame.dispose();
			}
		});
		panel.add(monitorButton);
		
		JButton editUserButton = new JButton("Modifica utente");
		editUserButton.setToolTipText("Modifica utente");
		panel.add(editUserButton);

		
		JButton adminLogoutButton = new JButton("Logout");
		adminLogoutButton.setToolTipText("Esegui il logout dall'utente attuale");
		adminLogoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.dispose();
			}
		});
		panel.add(adminLogoutButton);
		
		JLabel lblNewLabel = new JLabel("Benvenuto <nome> <cognome>");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
