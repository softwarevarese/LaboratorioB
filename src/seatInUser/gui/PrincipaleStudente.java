package seatInUser.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.UtenteConnesso;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class PrincipaleStudente extends JFrame {

	private JPanel contentPane;
	private UtenteConnesso connesso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//PrincipaleStudente frame = new PrincipaleStudente();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipaleStudente(UtenteConnesso connesso) {
		setTitle("SEATIN USER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipaleStudente.class.getResource("/media/Icona.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 2, 2));
		
		JButton corsiButton = new JButton("I miei corsi");
		panel.add(corsiButton);
		
		JButton altriCorsiButton = new JButton("Altri corsi");
		panel.add(altriCorsiButton);
		
		JButton anagraficheButton = new JButton("Info anagrafiche");
		panel.add(anagraficheButton);
		
		JButton logoutButton = new JButton("Logout");
		panel.add(logoutButton);
		
		JLabel nomeUtente = new JLabel("Benvenuto <nome> <cognome>");
		contentPane.add(nomeUtente, BorderLayout.NORTH);
		
		corsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				ListaCorsi.main(connesso);
			}
		});
		
		altriCorsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltriCorsi.main(connesso);
			}
		});
		
		anagraficheButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
}
