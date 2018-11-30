package seatInUser.gui;

import client.UtenteConnesso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltriCorsi extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso utente) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltriCorsi frame = new AltriCorsi(utente);
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
	public AltriCorsi(UtenteConnesso utente) {
		setTitle("SEATIN USER");
		setBackground(new Color(240, 248, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltriCorsi.class.getResource("/media/Icona.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(9);
		textArea.setBounds(12, 48, 393, 362);
		contentPane.add(textArea);
		
		JLabel lblListaDeiCorsi = new JLabel("Lista degli altri corsi");
		lblListaDeiCorsi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblListaDeiCorsi.setBounds(12, 19, 393, 16);
		contentPane.add(lblListaDeiCorsi);
		
		JButton infoButton = new JButton("Info sul corso");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		infoButton.setBounds(152, 423, 253, 25);
		contentPane.add(infoButton);
		
		JButton backButton = new JButton("Indietro");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaCorsi.main(utente);
			}
		});
		backButton.setBounds(12, 423, 116, 25);
		contentPane.add(backButton);
	}
}
