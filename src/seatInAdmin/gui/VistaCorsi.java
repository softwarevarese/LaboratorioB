package seatInAdmin.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;

import client.*;
import seatInServer.DBModel.Corso;
import seatInServer.DBModel.CorsoDiLaurea;
import seatInServer.DBModel.Dipartimento;
import seatInUser.gui.RisorseCorso;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class VistaCorsi {

	private JFrame frmListaCorsi;
	public static String title = null;
	public static String description = null;
	public static int iddainviare = -1;
	boolean controllo = false;
	int iDip;
	int idL = -1;
	JComboBox selectCourseL = new JComboBox();
	ArrayList<Corso> listaaa = new ArrayList<Corso>();

	private UtenteConnesso adminConnesso;

	/**
	 * Launch the application.
	 */

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCorsi window = new VistaCorsi(new UtenteConnesso());
					window.frmListaCorsi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaCorsi(UtenteConnesso admin) {
		initialize();
		adminConnesso=admin;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaCorsi = new JFrame();
		frmListaCorsi.setTitle("seatInServer- Lista dei corsi");
		frmListaCorsi.setResizable(false);
		frmListaCorsi.setIconImage(Toolkit.getDefaultToolkit().getImage("media/f.png"));
		frmListaCorsi.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				PrincipaleAdmin window = new PrincipaleAdmin(adminConnesso);
				window.main(adminConnesso);
				frmListaCorsi.dispose();
			}
		});

		frmListaCorsi.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frmListaCorsi.setBackground(Color.LIGHT_GRAY);
		frmListaCorsi.setBounds(100, 100, 571, 376);
		frmListaCorsi.getContentPane().setLayout(null);

		// PULSANTE INDIETRO

		JButton BackButton = new JButton("Indietro");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrincipaleAdmin window = new PrincipaleAdmin(adminConnesso);
				window.main(adminConnesso);
				frmListaCorsi.dispose();
			}
		});

		BackButton.setBounds(12, 305, 115, 23);
		frmListaCorsi.getContentPane().add(BackButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 11, 240, 232);
		frmListaCorsi.getContentPane().add(scrollPane);

		// DA INSERIRE QUERY GET CORSO MATERIA BY LAUREA

		JList cl = new JList();
		DefaultListModel<String> modelcl = new DefaultListModel<>();
		scrollPane.setViewportView(cl);

		// SCELTA DIPARTIMENTO

		Vector<String> dipList = new Vector<>();
		for (Dipartimento dip : ClientConnection.getDipartimenti()) {
			dipList.add(dip.getNome());
		}

		JComboBox selectDip = new JComboBox(dipList);
		selectDip.setBounds(262, 10, 293, 20);
		frmListaCorsi.getContentPane().add(selectDip);
		
		selectCourseL.setBounds(262, 144, 293, 20);
		frmListaCorsi.getContentPane().add(selectCourseL);
		selectCourseL.setVisible(false);

		// CONFERMA CORSO LAUREA

		JButton confirmCourseL = new JButton("Conferma corso di laurea");
		confirmCourseL.setVisible(false);
		confirmCourseL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelcl.clear();
				String selectedCourseL = (String) selectCourseL.getSelectedItem();

				for (CorsoDiLaurea cl2 : ClientConnection.getCorsiLaureaByDip(iDip)) {
					if (cl2.getNome() == selectedCourseL) {
						idL = cl2.getIdCdL();
						break;
					}
				}
				
				for (Corso cm : ClientConnection.getCorsiMateriaByLaurea(idL))
				{

					modelcl.addElement(cm.getNome());
				}
				
				
				cl.setModel(modelcl);
				
			}
		});
		confirmCourseL.setBounds(312, 220, 210, 23);
		frmListaCorsi.getContentPane().add(confirmCourseL);

		// CONFERMA DIPARTIMENTO
		JButton confirmDip = new JButton("Conferma dipartimento");
		confirmDip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// SCELTA CORSO
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel();
				String selectedDip = (String) selectDip.getSelectedItem();
				for (Dipartimento d : ClientConnection.getDipartimenti()) {
					if (selectedDip == d.getNome()) {
						iDip = d.getId();
					}

				}
				
				for (CorsoDiLaurea course : ClientConnection.getCorsiLaureaByDip(iDip)) {
					model.addElement(course.getNome());
				}

				selectCourseL.setModel(model);
				selectCourseL.setVisible(true);
				confirmCourseL.setVisible(true);
			}
		});
		confirmDip.setBounds(312, 71, 210, 23);
		frmListaCorsi.getContentPane().add(confirmDip);
		
		JButton btnNewButton = new JButton("Visualizza corso");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedc = (String) cl.getSelectedValue();
				for (Corso cm : ClientConnection.getCorsiMateriaByLaurea(idL))
				{

					if ( selectedc == cm.getNome()) {
						title = cm.getNome();
						description = cm.getDescrizione();
						iddainviare = cm.getIdCorso();
					}
				}
				new RisorseCorso(adminConnesso);
				frmListaCorsi.dispose();
				
			}
		});
		btnNewButton.setBounds(12, 254, 240, 23);
		frmListaCorsi.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 290, 565, 4);
		frmListaCorsi.getContentPane().add(separator);

	}
}
