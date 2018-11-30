package seatInUser.gui;

import client.UtenteConnesso;
import client.gui.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class PrincipaleDocente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso docente) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipaleDocente frame = new PrincipaleDocente(docente);
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
	public PrincipaleDocente(UtenteConnesso docente) {
		setTitle("SEATIN USER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipaleDocente.class.getResource("/media/Icona.PNG")));
		setBackground(new Color(240, 248, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 2, 2));
		
		JButton corsiButton = new JButton("I miei corsi");
		corsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaCorsi.main(docente);
			}
		});
		panel.add(corsiButton);
		
		JButton altriCorsiButton = new JButton("Altri corsi");
		altriCorsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltriCorsi.main(docente);
			}
		});
		panel.add(altriCorsiButton);
		
		JButton anagraficheButton = new JButton("Info anagrafiche");
		anagraficheButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creare gui anagrafica
			}
		});
		panel.add(anagraficheButton);
		
		JButton monitorButton = new JButton("Monitoraggio");
		monitorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MonitoraggioDocente.main(docente);
			}
		});
		panel.add(monitorButton);
		
		JButton emailButton = new JButton("e-mail");
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creare gui email
			}
		});
		panel.add(emailButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.main(null);
			}
		});
		panel.add(logoutButton);
		
		JLabel lblNewLabel = new JLabel("Benvenuto <nome> >cognome>");
		lblNewLabel.setBackground(new Color(240, 248, 255));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
