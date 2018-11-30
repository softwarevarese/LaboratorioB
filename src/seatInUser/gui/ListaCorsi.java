package seatInUser.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.*;
import seatInServer.DBModel.Corso;
import seatInServer.DBModel.Docente;
import seatInServer.DBModel.Studente;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Questa classe rappresenta l'interfaccia visualizzante la lista dei corsi a cui uno studente e iscritto
 * o di cui un docente e' titolare.
 */
public class ListaCorsi extends JFrame {

	private JPanel contentPane;
	private UtenteConnesso utenteConnesso;
	ArrayList<Corso> LcorsiregisteredStudent;
	ArrayList<Corso> LcorsiregisteredDocente;
	String titolodainviare = null;
	String descrizionedasend = null;
	int idsend;
	
	/**
	 * Launch the application.
	 */
	public static void main(UtenteConnesso connesso) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaCorsi frame = new ListaCorsi(connesso);
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
	public ListaCorsi(UtenteConnesso connesso) {
		this.utenteConnesso=connesso;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaCorsi.class.getResource("/media/Icona.PNG")));
		setTitle("SEATIN USER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setToolTipText("SEATIN USER");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listaCorsi = new JList();
		DefaultListModel model = new DefaultListModel<String>();
		listaCorsi.setBounds(12, 48, 393, 362);
		contentPane.add(listaCorsi);
		
		// Se lo studente loggato e uno studente visualizzo i suoi corsi
		if (utenteConnesso.tipoUtente.toString().equals("Studente")) {
			LcorsiregisteredStudent = new Studente().getCorsiStudente(utenteConnesso.connesso.getMatricola());

			for (Corso cm : LcorsiregisteredStudent) {
				// nel model si inserisce nome e id
				model.addElement(cm.getIdCorso() + " " + cm.getNome());

			}
			listaCorsi.setModel(model);

		} else if (utenteConnesso.tipoUtente.toString().equals("Docente")) {   // altrimenti visualizzo quelli del docente
			LcorsiregisteredDocente = new Docente().getCorsiByDocente(utenteConnesso.connesso.getMatricola());
			
			System.out.println(LcorsiregisteredDocente.size());

			for (Corso cmd : LcorsiregisteredDocente) {
				
				System.out.println(cmd.getIdCorso() + " " + cmd.getNome());
				// nel model si inserisce nome e id
				model.addElement(cmd.getIdCorso() + " " + cmd.getNome());
			}
			listaCorsi.setModel(model);
		}
		
		
		JLabel lblListaDeiCorsi = new JLabel("Lista dei corsi a cui sei iscritto");
		lblListaDeiCorsi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblListaDeiCorsi.setBounds(12, 19, 384, 16);
		contentPane.add(lblListaDeiCorsi);
		if (utenteConnesso.tipoUtente.toString().equals("Docente")) {	lblListaDeiCorsi.setText("Lista dei corsi assegnati"); }
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(22, 423, 97, 25);
		contentPane.add(btnIndietro);
		
		JButton visualizzaCorso = new JButton("Visualizza il corso selezionato");
		visualizzaCorso.setBounds(189, 423, 216, 25);
		contentPane.add(visualizzaCorso);
		
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrincipaleStudente.main(null);
			}
		});
		
		visualizzaCorso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (utenteConnesso.tipoUtente.toString().equals("Studente")) {
					String temp = (String) listaCorsi.getSelectedValue();
					String[] courseData = temp.split(" ");

					for (Corso cm : LcorsiregisteredStudent) {
						if (Long.parseLong(courseData[0]) == cm.getIdCorso()) {

							titolodainviare = cm.getNome();
							descrizionedasend = cm.getDescrizione();
							idsend = cm.getIdCorso();
						}
					}

				} else {  //se l'utente � un docente
					String temp = (String) listaCorsi.getSelectedValue();
					String[] courseData = temp.split(" ");

					for (Corso cm : LcorsiregisteredDocente) {
						if (Long.parseLong(courseData[0]) == cm.getIdCorso()) {

							titolodainviare = cm.getNome();
							descrizionedasend = cm.getDescrizione();
							idsend = cm.getIdCorso();
						}
					}

				}
				
				//frame.dispose();
				if (utenteConnesso.tipoUtente.toString().equals("Studente"))
					ClientConnection.updateLoc(utenteConnesso.connesso.getMatricola(), idsend);
					new RisorseCorso(utenteConnesso);

			}
		});
	}
}
